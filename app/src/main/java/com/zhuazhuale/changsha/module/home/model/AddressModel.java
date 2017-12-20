package com.zhuazhuale.changsha.module.home.model;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.log.LogUtil;

/**
 * 我的地址
 * Created by 丁琪 on 2017/12/20.
 */

public class AddressModel {

    public static AddressModel getInstance() {
        return AddressModel.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final AddressModel instance = new AddressModel();
    }

    /**
     * 获取我的地址列表
     *
     * @param iCallListener
     */
    public void getQueryUserAddress(int vCheck, final ICallListener<String> iCallListener) {
        OkGo.<String>post(Constant.QueryUserAddress)
                .tag(this)
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
}
