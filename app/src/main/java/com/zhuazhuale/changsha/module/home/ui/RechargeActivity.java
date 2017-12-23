package com.zhuazhuale.changsha.module.home.ui;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.AllPriceProductBean;
import com.zhuazhuale.changsha.module.home.Bean.NewCPBean;
import com.zhuazhuale.changsha.module.home.Bean.WxUnifiedOrder;
import com.zhuazhuale.changsha.module.home.adapter.RechargeAdapter;
import com.zhuazhuale.changsha.module.home.presenter.RechargePresenter;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;
import com.zhuazhuale.changsha.view.widget.loadlayout.OnLoadListener;
import com.zhuazhuale.changsha.view.widget.loadlayout.State;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 充值页面
 * Created by 丁琪 on 2017/12/18.
 */

public class RechargeActivity extends AppBaseActivity implements View.OnClickListener, IRechargeView {

    @BindView(R.id.tv_recharge_ye)
    TextView tv_recharge_ye;
    @BindView(R.id.rv_recharge_list)
    RecyclerView rv_recharge_list;
    private RechargePresenter presenter;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_recharge);
        presenter = new RechargePresenter(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void obtainData() {
        //设置“加载”状态时要做的事情
        getLoadLayout().setOnLoadListener(new OnLoadListener() {
            @Override
            public void onLoad() {
                initData();

            }
        });
        showLoadingDialog();
        initData();
    }

    /**
     * 请求数据
     */
    private void initData() {
        presenter.iniNewCP();
        presenter.initAllPriceProduct();
    }


    @Override
    protected void initEvent() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    /**
     * 余额
     *
     * @param newCPBean
     */
    @Override
    public void showNewCP(NewCPBean newCPBean) {
        tv_recharge_ye.setText(newCPBean.getRows().getCP() + "");
    }

    @Override
    public void showAllPriceProduct(AllPriceProductBean allPriceProductBean) {
        getLoadLayout().setLayoutState(State.SUCCESS);
        dismissLoadingDialog();
        RechargeAdapter adapter = new RechargeAdapter(this, allPriceProductBean.getRows());
        rv_recharge_list.setLayoutManager(new GridLayoutManager(this, 2));
        rv_recharge_list.setAdapter(adapter);
    }

    @Override
    public void showFailed() {
        getLoadLayout().setLayoutState(State.FAILED);
        dismissLoadingDialog();
    }

    @Override
    public void showNoData() {
        getLoadLayout().setLayoutState(State.NO_DATA);
        dismissLoadingDialog();
    }

    /**
     * 微信支付订单生成成功
     *
     * @param wxUnifiedOrder
     */
    @Override
    public void showWxUnifiedOrder(WxUnifiedOrder wxUnifiedOrder) {
        if (wxUnifiedOrder.getCode()==1){
            payMonenyForWX(wxUnifiedOrder);
        }else {
            ToastUtil.show(wxUnifiedOrder.getInfo());
        }
    }

    private void payMonenyForWX(WxUnifiedOrder wxUnifiedOrder) {
        IWXAPI api= WXAPIFactory.createWXAPI(this, Constant.APPID);
        api.registerApp(Constant.APPID);

        PayReq payRequest=new PayReq();
        payRequest.appId=Constant.APPID;

    }

    @Override
    public void showFinish() {

    }

    /**
     * 微信充值
     *
     * @param rowsBean
     * @param position
     */
    public void rechargeCP(AllPriceProductBean.RowsBean rowsBean, int position) {
        presenter.iniWxUnifiedOrder(rowsBean.getF_ID());
    }
}
