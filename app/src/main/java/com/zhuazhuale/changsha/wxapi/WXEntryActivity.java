package com.zhuazhuale.changsha.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.module.home.Bean.LoginInfoBean;
import com.zhuazhuale.changsha.module.home.ui.HomeActivity;
import com.zhuazhuale.changsha.module.login.presenter.LoginPresenter;
import com.zhuazhuale.changsha.module.login.presenter.WeiXinLoginGetUserinfoBean;
import com.zhuazhuale.changsha.module.login.ui.LoginActivity;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.EventBusUtil;
import com.zhuazhuale.changsha.util.HashCoderUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Date;

import okhttp3.Call;

/**
 * Created by wb on 2017/12/14.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        LoginPresenter.api.handleIntent(getIntent(), this);
    }


    /**
     * //推送
     * NSString * const APP_PushKey = @"259e93ff2386e0a6aaa03aee";
     * NSString * const APP_Pushchannel = @"zzlPublishchannel";
     *
     * @param baseReq
     */

    @Override
    public void onReq(BaseReq baseReq) {

    }


    /**
     * 微信登录第一步：获取code
     */
    @Override
    public void onResp(BaseResp resp) {
        String code1 = ((SendAuth.Resp) resp).code;
        LogUtil.e("获取code成功1：" + code1);

        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                //发送成功
                EventBusUtil.postEvent(code1);
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                //发送取消
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                //发送被拒绝
                break;
            default:
                //发送返回
                break;
        }
        finish();

    }



}
