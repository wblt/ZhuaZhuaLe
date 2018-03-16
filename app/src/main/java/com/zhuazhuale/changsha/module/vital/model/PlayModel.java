package com.zhuazhuale.changsha.module.vital.model;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.app.constant.BaseConstants;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.PreferenceUtil;
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
     * 获取账户余额信息
     *
     * @param iCallListener
     */
    public void getNewCP(final ICallListener<String> iCallListener) {
        String F_ID = PreferenceUtil.getString(MyApplication.getInstance(), BaseConstants.F_ID, "");
        OkGo.<String>post(Constant.GetNewCP)
                .tag(this)
                .params("zzl", F_ID)
                .params("vF_ID", F_ID)
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
     * 查询游戏机的状态
     *
     * @param vDeviceID
     * @param iCallListener
     */
    public void getQueryGame(String vDeviceID, final ICallListener<String> iCallListener) {
        OkGo.<String>post(Constant.QueryGame)
                .tag(this)
                .params("vDeviceID", vDeviceID)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
//                        LogUtil.e("请求成功" + response.body());
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
     * 游戏上机
     *
     * @param iCallListener
     */
    public void getUpperGame(String vDeviceID, final ICallListener<String> iCallListener) {
        String F_ID = PreferenceUtil.getString(MyApplication.getInstance(), BaseConstants.F_ID, "");
        OkGo.<String>post(Constant.UpperGame)
                .tag(this)
                .params("vUserID", F_ID)
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
     * 下机
     * @param vDeviceID
     * @param iCallListener
     */
    public void getLowerGame(String vDeviceID, final ICallListener<String> iCallListener) {
        String F_ID = PreferenceUtil.getString(MyApplication.getInstance(), BaseConstants.F_ID, "");
        OkGo.<String>post(Constant.LowerGame)
                .tag(this)
                .params("vUserID", F_ID)
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
    public void getControlGame(String vDeviceID, String vAction, String vToken, String timeStamp, final ICallListener<String> iCallListener) {
        String F_ID = PreferenceUtil.getString(MyApplication.getInstance(), BaseConstants.F_ID, "");
        OkGo.<String>post(Constant.ControlGame)
                .tag(this)
                .params("vUserID", F_ID)
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

    /**
     * 查询在这台机器用户抓取成功的记录
     *
     * @param vDeviceID
     * @param iCallListener
     */
    public void getGetAllUserTrueByDeviceID(String vDeviceID, final ICallListener<String> iCallListener) {
        String F_ID = PreferenceUtil.getString(MyApplication.getInstance(), BaseConstants.F_ID, "");
        OkGo.<String>post(Constant.GetAllUserTrueByDeviceID)
                .tag(this)
                .params("zzl",F_ID)
                .params("vUserID", F_ID)
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
     * 获取视频签名
     *
     * @param iCallListener
     */
    public void getGetUploadSignature(final ICallListener<String> iCallListener) {
        String F_ID = PreferenceUtil.getString(MyApplication.getInstance(), BaseConstants.F_ID, "");
        OkGo.<String>post(Constant.GetUploadSignature)
                .tag(this)
                .params("zzl",F_ID)
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
     * 上传视频的接口
     * @param F_GrabWaterID 抓取之后 返回的
     * @param F_VideoUrl    视频的链接
     * @param iCallListener
     */
    public void getModiflyVideoUrl(String F_GrabWaterID, String F_VideoUrl, final ICallListener<String> iCallListener) {
        String F_ID = PreferenceUtil.getString(MyApplication.getInstance(), BaseConstants.F_ID, "");
        OkGo.<String>post(Constant.ModiflyVideoUrl)
                .tag(this)
                .params("zzl",F_ID)
                .params("F_GrabWaterID", F_GrabWaterID)
                .params("F_VideoUrl", F_VideoUrl)
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
