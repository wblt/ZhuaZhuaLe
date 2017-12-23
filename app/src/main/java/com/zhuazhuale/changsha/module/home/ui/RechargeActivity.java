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
import com.zhuazhuale.changsha.model.entity.eventbus.CPfreshEvent;
import com.zhuazhuale.changsha.module.home.Bean.AllPriceProductBean;
import com.zhuazhuale.changsha.module.home.Bean.NewCPBean;
import com.zhuazhuale.changsha.module.home.Bean.WxUnifiedOrder;
import com.zhuazhuale.changsha.module.home.adapter.RechargeAdapter;
import com.zhuazhuale.changsha.module.home.presenter.RechargePresenter;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.EventBusUtil;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;
import com.zhuazhuale.changsha.view.widget.loadlayout.OnLoadListener;
import com.zhuazhuale.changsha.view.widget.loadlayout.State;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
        EventBusUtil.register(this);//订阅事件
    }

    //EventBus的事件接收，从事件中获取最新的收藏数量并更新界面展示
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(CPfreshEvent event) {
        String code = event.getCPisFresh();
        LogUtil.e(code);
        if ("刷新".equals(code)) {
            presenter.iniNewCP();
        }
        dismissLoadingDialog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅
        EventBusUtil.unregister(this);
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

    /**
     * 充值列表
     * @param allPriceProductBean
     */
    @Override
    public void showAllPriceProduct(AllPriceProductBean allPriceProductBean) {
        getLoadLayout().setLayoutState(State.SUCCESS);
        if (allPriceProductBean.getCode() == 1) {
            RechargeAdapter adapter = new RechargeAdapter(this, allPriceProductBean.getRows());
            rv_recharge_list.setLayoutManager(new GridLayoutManager(this, 2));
            rv_recharge_list.setAdapter(adapter);
        } else {
            ToastUtil.show(allPriceProductBean.getInfo());
        }

    }

    @Override
    public void showFailed() {
        ToastUtil.show("失败,请检查网络!");
    }


    /**
     * 微信支付订单生成成功
     *
     * @param wxUnifiedOrder
     */
    @Override
    public void showWxUnifiedOrder(WxUnifiedOrder wxUnifiedOrder) {
        if (wxUnifiedOrder.getCode() == 1) {
            payMonenyForWX(wxUnifiedOrder);
        } else {
            ToastUtil.show(wxUnifiedOrder.getInfo());
        }
    }

    private void payMonenyForWX(WxUnifiedOrder order) {
        WxUnifiedOrder.RowsBean rowsBean = order.getRows();
        IWXAPI api = WXAPIFactory.createWXAPI(this, Constant.APPID);
        api.registerApp(Constant.APPID);

        PayReq req = new PayReq();
        req.appId = rowsBean.getAppid();
        req.partnerId = rowsBean.getPartnerid();// 微信支付分配的商户号
        req.prepayId = rowsBean.getPrepayid();// 预支付订单号，app服务器调用“统一下单”接口获取
        req.nonceStr = rowsBean.getNoncestr();// 随机字符串，不长于32位，服务器小哥会给咱生成
        req.timeStamp = rowsBean.getTimestamp() + "";// 时间戳，app服务器小哥给出
        req.packageValue = rowsBean.getPackageX();// 固定值Sign=WXPay，可以直接写死，服务器返回的也是这个固定值
        req.sign = rowsBean.getSign();// 签名，服务器小哥给出，他会根据：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=4_3指导得到这个
        api.sendReq(req);

    }

    @Override
    public void showFinish() {
        dismissLoadingDialog();
    }

    /**
     * 微信充值
     *
     * @param rowsBean
     * @param position
     */
    public void rechargeCP(AllPriceProductBean.RowsBean rowsBean, int position) {
        showLoadingDialog();
        LogUtil.e(rowsBean.getF_ID()+"");
        presenter.iniWxUnifiedOrder(rowsBean.getF_ID());

    }
}
