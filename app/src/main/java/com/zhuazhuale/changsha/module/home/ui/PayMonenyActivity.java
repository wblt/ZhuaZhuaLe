package com.zhuazhuale.changsha.module.home.ui;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.AlipayBean;
import com.zhuazhuale.changsha.module.home.Bean.AllPriceProductBean;
import com.zhuazhuale.changsha.module.home.Bean.AuthResult;
import com.zhuazhuale.changsha.module.home.Bean.PayResult;
import com.zhuazhuale.changsha.module.home.Bean.WxUnifiedOrder;
import com.zhuazhuale.changsha.module.home.presenter.PayMonenyPresenter;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.OrderInfoUtil2_0;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import java.util.Map;

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
                        presenter.initAliUnifiedOrder(rowsBean.getF_ID());
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

    /**
     * 微信支付
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

    /**
     * 支付宝支付
     *
     * @param alipayBean
     */
    @Override
    public void showAliUnifiedOrde(AlipayBean alipayBean) {
        if (alipayBean.getCode() == 1) {
            payMonenyForAlipay(alipayBean);
        } else {
            ToastUtil.show(alipayBean.getInfo());
        }
    }

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    private void payMonenyForAlipay(AlipayBean alipayBean) {

        boolean rsa2 = true;
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap("2017102309480225", rsa2, alipayBean);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = alipayBean.getRows().getVKey();
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;
        LogUtil.e(orderInfo);

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(PayMonenyActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                LogUtil.e("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(PayMonenyActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(PayMonenyActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(PayMonenyActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(PayMonenyActivity.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

}
