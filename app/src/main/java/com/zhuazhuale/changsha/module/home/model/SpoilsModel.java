package com.zhuazhuale.changsha.module.home.model;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.log.LogUtil;

/**
 * 我的战利品
 * Created by 丁琪 on 2017/12/20.
 */

public class SpoilsModel {

    public static SpoilsModel getInstance() {
        return SpoilsModel.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final SpoilsModel instance = new SpoilsModel();
    }

    /**
     * 获取我的战利品列表
     *
     * @param iCallListener
     */
    public void getQueryUserGoods(int vCheck, final ICallListener<String> iCallListener) {
        OkGo.<String>post(Constant.QueryUserGoods)
                .tag(this)
                .params("zzl", MyApplication.getInstance().getRowsBean().getF_ID())
                .params("vCheck", vCheck)
                .params("vUserID", MyApplication.getInstance().getRowsBean().getF_ID())
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
     * 娃娃兑换游戏币
     *
     * @param vUserGoodsID
     * @param vDeviceID
     * @param iCallListener
     */
    public void getExChangeCP(String vUserGoodsID, String vDeviceID, final ICallListener<String> iCallListener) {
        OkGo.<String>post(Constant.ExChangeCP)
                .tag(this)
                .params("zzl", MyApplication.getInstance().getRowsBean().getF_ID())
                .params("vUserGoodsID", vUserGoodsID)
                .params("vDeviceID", vDeviceID)
                .params("vUserID", MyApplication.getInstance().getRowsBean().getF_ID())
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
