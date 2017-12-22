package com.zhuazhuale.changsha.wxapi;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhuazhuale.changsha.R;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private String TAG = getClass().getName();

    private String WX_APPID = "wx756f557d480f8db0";
    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);

        api = WXAPIFactory.createWXAPI(this, WX_APPID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        Log.d(TAG, "onPayFinish, errCode = " + resp.errCode);

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage(getString(R.string.pay_result_callback_msg, String.valueOf(resp.errCode)));
            builder.show();
        }
    }

    IWXAPI mWxApi = WXAPIFactory.createWXAPI(this, WX_APPID, true);
//     mWxApi.registerApp(WX_APPID);

    // 请求app服务器得到的回调结果
   /* @Override
    public void onGet(JSONObject jsonObject) {
        if (mWxApi != null) {
            PayReq req = new PayReq();

            req.appId = WX_APPID;// 微信开放平台审核通过的应用APPID
            try {
                req.partnerId = jsonObject.getString("partnerid");// 微信支付分配的商户号
                req.prepayId = jsonObject.getString("prepayid");// 预支付订单号，app服务器调用“统一下单”接口获取
                req.nonceStr = jsonObject.getString("noncestr");// 随机字符串，不长于32位，服务器小哥会给咱生成
                req.timeStamp = jsonObject.getString("timestamp");// 时间戳，app服务器小哥给出
                req.packageValue = jsonObject.getString("package");// 固定值Sign=WXPay，可以直接写死，服务器返回的也是这个固定值
                req.sign = jsonObject.getString("sign");// 签名，服务器小哥给出，他会根据：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=4_3指导得到这个
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mWxApi.sendReq(req);
            Log.d("----","发起微信支付申请");
        }

    }*/
}