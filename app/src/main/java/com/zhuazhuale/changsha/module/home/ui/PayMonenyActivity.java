package com.zhuazhuale.changsha.module.home.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.AllPriceProductBean;
import com.zhuazhuale.changsha.module.home.Bean.WxUnifiedOrder;
import com.zhuazhuale.changsha.module.home.presenter.PayMonenyPresenter;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/2.
 */

public class PayMonenyActivity extends AppBaseActivity implements View.OnClickListener, IPayMonenyView {
    @BindView(R.id.ll_pay_zfb)
    LinearLayout ll_pay_zfb;
    @BindView(R.id.ll_pay_wx)
    LinearLayout ll_pay_wx;
    @BindView(R.id.tv_pay_moneny1)
    TextView tv_pay_moneny1;
    @BindView(R.id.tv_pay_moneny2)
    TextView tv_pay_moneny2;
    @BindView(R.id.tv_pay_ok)
    TextView tv_pay_ok;
    @BindView(R.id.iv_pay_check_zfb)
    ImageView iv_pay_check_zfb;
    @BindView(R.id.iv_pay_check_wx)
    ImageView iv_pay_check_wx;
    private int numType;
    private PayMonenyPresenter presenter;
    private AllPriceProductBean.RowsBean rowsBean;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_paymoneny);
        numType = 2;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void obtainData() {
        presenter = new PayMonenyPresenter(this);
        rowsBean = (AllPriceProductBean.RowsBean) getIntent().getSerializableExtra("rowsBean");
        tv_pay_moneny1.setText("￥ " + rowsBean.getF_Price3());
        tv_pay_moneny2.setText("￥ " + rowsBean.getF_Price3());
    }

    @Override
    protected void initEvent() {
        ll_pay_zfb.setOnClickListener(this);
        ll_pay_wx.setOnClickListener(this);
        tv_pay_ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_pay_zfb:
                numType = 1;
                iv_pay_check_zfb.setImageResource(R.mipmap.selector_on);
                iv_pay_check_wx.setImageResource(R.mipmap.selector_off);
                break;
            case R.id.ll_pay_wx:
                numType = 2;
                iv_pay_check_zfb.setImageResource(R.mipmap.selector_off);
                iv_pay_check_wx.setImageResource(R.mipmap.selector_on);
                break;
            case R.id.tv_pay_ok:

                switch (numType) {
                 /*   case 0:
                        ToastUtil.show("请选择支付方式!");
                        break;*/
                    case 1:
                        break;
                    case 2:
                        showLoadingDialog("");
                        presenter.iniWxUnifiedOrder(rowsBean.getF_ID());
                        break;
                }
                break;
        }
    }

    @Override
    public void showFailed() {

    }

    @Override
    public void showFinish() {
        dismissLoadingDialog();
    }

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
}
