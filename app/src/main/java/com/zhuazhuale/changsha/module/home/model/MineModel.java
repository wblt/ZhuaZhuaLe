package com.zhuazhuale.changsha.module.home.model;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.log.LogUtil;

import static com.jude.rollviewpager.R.id.time;

/**
 * 个人中心
 * Created by 丁琪 on 2017/12/20.
 */

public class MineModel {

    public static MineModel getInstance() {
        return MineModel.SingletonHolder.instance;
    }


    private static class SingletonHolder {
        private static final MineModel instance = new MineModel();
    }

    /**
     * 获取账户余额信息
     *
     * @param iCallListener
     */
    public void getNewCP(final ICallListener<String> iCallListener) {
        OkGo.<String>post(Constant.GetNewCP)
                .tag(this)
                .params("zzl", MyApplication.getInstance().getRowsBean().getF_ID())
                .params("vF_ID", MyApplication.getInstance().getRowsBean().getF_ID())
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

    public void getUserSign(String time,final ICallListener<String> iCallListener) {
        OkGo.<String>post(Constant.UserSign)
                .tag(this)
                .params("vUserID", MyApplication.getInstance().getRowsBean().getF_ID())
                .params("vDateTime",time )
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
     * 兑换包邮
     * @param iCallListener
     */
    public void getExchangeBagNumber(final ICallListener<String> iCallListener) {
        OkGo.<String>post(Constant.ExchangeBagNumber)
                .tag(this)
                .params("F_UserID", MyApplication.getInstance().getRowsBean().getF_ID())
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
