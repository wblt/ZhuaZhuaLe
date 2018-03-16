package com.zhuazhuale.changsha.module.home.model;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.app.constant.BaseConstants;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.PreferenceUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;

/**
 * 申诉
 * Created by 丁琪 on 2017/12/20.
 */

public class ShenSuModel {

    public static ShenSuModel getInstance() {
        return ShenSuModel.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final ShenSuModel instance = new ShenSuModel();
    }

    /**
     * 获取账户余额信息
     *
     * @param iCallListener
     */
    public void getAppeal(String vDeviceID, String vGrabID, String vRemark, String vVideoUrl, final ICallListener<String> iCallListener) {
        String F_ID = PreferenceUtil.getString(MyApplication.getInstance(), BaseConstants.F_ID, "");

        OkGo.<String>post(Constant.Appeal)
                .tag(this)
                .params("zzl", F_ID)
                .params("vUserID", F_ID)
                .params("vDeviceID", vDeviceID)
                .params("vGrabID", vGrabID)
                .params("vRemark", vRemark)
                .params("vVideoUrl", vVideoUrl)
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
