package com.zhuazhuale.changsha.module.home.model;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.log.LogUtil;

/**
 * 主页
 * Created by Administrator on 2017/12/12.
 */

public class HomeModel {

    public static HomeModel getInstance() {
        return HomeModel.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final HomeModel instance = new HomeModel();
    }

    public void getBaseData(final ICallListener<String> iCallListener) {
        OkGo.<String>post(Constant.BaseData)
                .tag(this)
                .params("zzl", MyApplication.getInstance().getRowsBean().getF_ID())
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

    public void getLoginMain(final ICallListener<String> iCallListener) {
        OkGo.<String>post(Constant.LoginMain)
                .tag(this)
                .params("vEmailCode", "test@zhuazhuale.com")
                .params("vPassWord", "lezhuazhua.888")
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
     * 获取机器的列表
     *
     * @param PageIndex
     * @param PageSize
     * @param iCallListener
     */
    public void getGetDeviceGoods(int PageIndex, int PageSize, final ICallListener<String> iCallListener) {
        OkGo.<String>post(Constant.GetDeviceGoods)
                .tag(this)
                .params("zzl", MyApplication.getInstance().getRowsBean().getF_ID())
                .params("PageIndex", PageIndex)
                .params("PageSize", PageSize)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtil.e(response.toString());
                        iCallListener.callSuccess(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        LogUtil.e(response.toString());
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
     * 检测版本更新
     * @param vVersion
     * @param iCallListener
     */
    public void getVersionCheck(String vVersion,final ICallListener<String>iCallListener) {

        OkGo.<String>post(Constant.VersionCheck)
                .tag(this)
                .params("vSystem", "ANDROID")
                .params("vVersion", vVersion)
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
