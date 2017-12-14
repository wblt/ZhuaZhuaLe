package com.zhuazhuale.changsha.module.login.presenter;

import android.content.Context;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhuazhuale.changsha.module.login.ui.ILoginView;
import com.zhuazhuale.changsha.presenter.base.BasePresenter;

/**
 * Created by wb on 2017/12/14.
 */

public class LoginPresenter extends BasePresenter<ILoginView> {

    public LoginPresenter(ILoginView iLoginView) {
        super(iLoginView);
        regToWx();
    }

    /** -------------------------微信第三方登录---------------------- */
    /**
     * 微信平台应用授权登录接入代码示例
     */
    //---------------------------微信第三方相关
    public static IWXAPI api;

    private void regToWx() {
        // 通过WXAPIFactory工厂,获得IWXAPI的实例
        api = WXAPIFactory.createWXAPI((Context) mIView, "wx6a6349cd0ccf0b09", true);
        // 将应用的appid注册到微信
        api.registerApp("wx6a6349cd0ccf0b09");
    }

    //获取微信访问getCode
    public void getCode() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "carjob_wx_login";
        api.sendReq(req);
    }
    /** -------------------------微信第三方登录结束-------------------- */
}
