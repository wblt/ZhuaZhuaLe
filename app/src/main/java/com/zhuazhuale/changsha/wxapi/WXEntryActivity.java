package com.zhuazhuale.changsha.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.zhuazhuale.changsha.module.home.ui.HomeActivity;
import com.zhuazhuale.changsha.module.login.presenter.LoginPresenter;
import com.zhuazhuale.changsha.module.login.bean.WeiXinLoginGetUserinfoBean;
import com.zhuazhuale.changsha.util.log.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by wb on 2017/12/14.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private String APPID = "wx6a6349cd0ccf0b09";
    private String secret = "90e0434b5026f273019c00167ab4f6b1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        LoginPresenter.api.handleIntent(getIntent(), this);
    }

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
                String code = ((SendAuth.Resp) resp).code;
                LogUtil.e("获取code成功：" + code);
                if (code != null) {
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
                                        setUrl(url);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }

                                @Override
                                public void onError(Response<String> response) {
                                    super.onError(response);
                                    LogUtil.e("微信请求失败1:" + response.body());
                                }

                                @Override
                                public void onFinish() {
                                    super.onFinish();
                                }
                            });
                   /* OkHttpUtils.get().url()
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    Tools.print("***********", "请求个人信息失败");

                                }
                                @Override
                                public void onResponse(String response, int id) {
                                    Tools.print("***********", "请求个人信息成功");
                                    Tools.print("----------", response.toString());
                                    Object obj = null;
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        String access_token = jsonObject.getString("access_token");
                                        String openid = jsonObject.getString("openid");
                                        String url = "https://api.weixin.qq.com/sns/userinfo?" + "access_token=" + access_token + "&openid=" + openid;
                                        Tools.print("..............", url);
                                        setUrl(url);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });*/
                }
                break;
            default:
                break;
        }

    }


    public void setUrl(String url) {
        OkGo.<String>get(url)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtil.e("微信请求成功2:" + response.body());
                        Gson gson = new Gson();
                        Type type = new TypeToken<WeiXinLoginGetUserinfoBean>() {
                        }.getType();
                        WeiXinLoginGetUserinfoBean bean = gson.fromJson(response.body(), type);
                        LogUtil.e("获取用户信息成功：\n" + "昵称:" + bean.getNickname() + "\n头像路径" + bean.getHeadimgurl());

                        Intent intent = new Intent(WXEntryActivity.this, HomeActivity.class);
                        intent.putExtra("WeiXinLoginGetUserinfoBean", bean);
                        startActivity(intent);

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        LogUtil.e("微信请求失败2:" + response.body());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
       /* OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Tools.print(TAG, "请求个人信息成功");
                Tools.print(TAG, response.toString());

                Gson gson = new Gson();
                Type type = new TypeToken<WeiXinLoginGetUserinfoBean>() {
                }.getType();
                bean = gson.fromJson(response, type);
                Tools.print(TAG, "获取用户信息成功：\n" + "昵称:" + bean.getNickname() + "\n头像路径" + bean.getHeadimgurl());
                queryForThankyou(bean);
                SharedPreferences sp = getApplication().getSharedPreferences(getString(R.string.login_info), MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(getString(R.string.user_headimgurl), bean.getHeadimgurl());
                editor.putString(getString(R.string.user_weixinname), bean.getNickname());
                editor.commit();

                // 记录微信登录
                Tools.saveAction(String.valueOf(userId), ActionLog.YH_ACCOUNT_LOG_NAME_27,ActionLog.YH_ACCOUNT_LOG_TYPE_27,WXEntryActivity.this);

            }
        });*/
    }
}
