package com.zhuazhuale.changsha.module.vital.model;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.log.LogUtil;

import java.util.Date;

/**
 * 游戏页面
 * Created by 丁琪 on 2017/12/20.
 */

public class PlayModel {

    public static PlayModel getInstance() {
        return PlayModel.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final PlayModel instance = new PlayModel();
    }

    /**
     * 游戏上机
     *
     * @param iCallListener
     */
    public void getUpperGame(String vDeviceID, final ICallListener<String> iCallListener) {
        OkGo.<String>post(Constant.LoginMain)
                .tag(this)
                .params("vUserID", MyApplication.getInstance().getRowsBean().getF_ID())
                .params("vDeviceID", vDeviceID)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtil.e("请求成功" + response.body());
                        iCallListener.callSuccess(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        LogUtil.e("请求失败" + response.toString());
                        iCallListener.callFailed();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        iCallListener.onFinish();
                    }
                });
    }

    /**
     * 游戏操作 上 左右下
     * 上    FORWARD = 1,
     * 下  BACKWARD = 2,
     * 左 LEFT = 3,
     * 右 RIGHT = 4,
     * 抓 DOWN = 5
     *
     * @param vDeviceID
     * @param vAction
     * @param timeStamp
     * @param iCallListener
     */
    public void getControlGame(String vDeviceID, String vAction,String vToken, String timeStamp, final ICallListener<String> iCallListener) {

        OkGo.<String>post(Constant.ControlGame)
                .tag(this)
                .params("vUserID", MyApplication.getInstance().getRowsBean().getF_ID())
                .params("vDeviceID", vDeviceID)
                .params("vAction", vAction)
                .params("vToken", vToken)
                .params("vTimeStamp", timeStamp)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtil.e("请求成功" + response.body());
                        iCallListener.callSuccess(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        LogUtil.e("请求失败" + response.toString());
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
