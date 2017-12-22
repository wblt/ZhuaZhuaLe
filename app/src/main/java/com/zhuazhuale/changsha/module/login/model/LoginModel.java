package com.zhuazhuale.changsha.module.login.model;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.module.login.bean.WeiXinLoginGetUserinfoBean;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.HashCoderUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * 登录界面
 * Created by 丁琪 on 2017/12/20.
 */

public class LoginModel {

    public static LoginModel getInstance() {
        return LoginModel.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final LoginModel instance = new LoginModel();
    }

    /**
     * 获取账户余额信息
     *
     * @param iCallListener
     */
    public void getWX(String code, String APPID, String secret, final ICallListener<String> iCallListener) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" + "appid=" + APPID + "&secret=" + secret + "&grant_type=authorization_code" + "&code=" + code;
        OkGo.<String>get(url)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtil.e("微信请求成功1:" + response.body());
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            String access_token = jsonObject.getString("access_token");
                            String openid = jsonObject.getString("openid");
                            String url = "https://api.weixin.qq.com/sns/userinfo?" + "access_token=" + access_token + "&openid=" + openid;
                            LogUtil.e("微信下一个地址:" + url);
                            iCallListener.callSuccess(url);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        LogUtil.e("微信请求失败1:" + response.body());
                        iCallListener.callFailed();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        iCallListener.onFinish();

                    }
                });
    }

    public void setUrl(String url, final ICallListener<String> iCallListener) {
        OkGo.<String>get(url)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        iCallListener.callSuccess(response.body());
                        LogUtil.e("微信请求成功2:" + response.body());

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        iCallListener.callFailed();

                        LogUtil.e("微信请求失败2:" + response.body());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        iCallListener.onFinish();
                    }
                });

    }

    /**
     * {
     * "vCode": "string",
     * "vName": "string",
     * "vImgUrl": "string",
     * "vPhoneType": "string",
     * "vSystemType": "string",
     * "vToken": "string",
     * "vTimeStamp": "string"
     * }
     *
     * @param bean
     */
    public void login(WeiXinLoginGetUserinfoBean bean, final ICallListener<String> iCallListener) {
        Date date = new Date();
        long timeStamp = date.getTime()/1000;
        String key = "#$%&*[zzl]168";
        String s = bean.getOpenid() + timeStamp + key;
        String vToken = HashCoderUtil.MD5Encode(s, "");
        LogUtil.e(bean.toString());
        LogUtil.e("vToken   " + vToken + "     vPhoneType    " + "Android" + "   vTimeStamp   " + timeStamp);

        OkGo.<String>post(Constant.Login)
                .tag(this)
                .params("vCode", bean.getOpenid())
                .params("vName", bean.getNickname())
                .params("vImgUrl", bean.getHeadimgurl())
                .params("vSystemType", "android")
                .params("vToken", vToken)
                .params("vTimeStamp", timeStamp)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        iCallListener.callSuccess(response.body());
                        LogUtil.e("娃娃乐登录成功:" + response.body());


                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        LogUtil.e("娃娃乐登录失败:" + response.body());
                        iCallListener.callFailed();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        iCallListener.onFinish();
                    }
                });
    }
}
