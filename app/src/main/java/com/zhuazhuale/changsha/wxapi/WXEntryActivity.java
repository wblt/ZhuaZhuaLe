package com.zhuazhuale.changsha.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.zhuazhuale.changsha.model.entity.eventbus.LoginEvent;
import com.zhuazhuale.changsha.util.EventBusUtil;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;

import java.util.HashMap;

import static com.zhuazhuale.changsha.app.MyApplication.api;


/**
 * Created by wb on 2017/12/14.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private LoginEvent event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api.handleIntent(getIntent(), this);
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
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
        api.handleIntent(intent, this);
    }

    /**
     * 微信登录第一步：获取code
     */
    @Override
    public void onResp(BaseResp resp) {
        switch (resp.getType()) {
            case ConstantsAPI.COMMAND_SENDAUTH:
                String code1 = ((SendAuth.Resp) resp).code;
                LogUtil.e("获取code成功1：" + code1);

                switch (resp.errCode) {
                    case BaseResp.ErrCode.ERR_OK:
                        //发送成功
                        event = new LoginEvent(code1,true);
                        EventBusUtil.postEvent(event);
                        break;
                    case BaseResp.ErrCode.ERR_USER_CANCEL:
                        //发送取消
                        ToastUtil.show("微信登录被取消");
                        event = new LoginEvent("",false);
                        EventBusUtil.postEvent(event);
                        break;
                    case BaseResp.ErrCode.ERR_AUTH_DENIED:
                        //发送被拒绝
                        ToastUtil.show("微信登录被拒绝");
                        event = new LoginEvent("",false);
                        EventBusUtil.postEvent(event);
                        break;
                    default:
                        //发送返回
                        break;
                }
                break;

            case ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX:
                String result2 = null;

                switch (resp.errCode) {
                    case BaseResp.ErrCode.ERR_OK:
                        result2 = "分享成功";
                        break;
                    case BaseResp.ErrCode.ERR_USER_CANCEL:
                        result2 = "分享取消";
                        break;
                    case BaseResp.ErrCode.ERR_AUTH_DENIED:
                        result2 = "分享失败";
                        break;
                    default:
                        result2 = "未知错误";
                        break;
                }

                ToastUtil.show( result2);
                break;
            default:
                break;
        }

        finish();

    }


}
