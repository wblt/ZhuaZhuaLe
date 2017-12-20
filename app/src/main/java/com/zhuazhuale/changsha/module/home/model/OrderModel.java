package com.zhuazhuale.changsha.module.home.model;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.log.LogUtil;

/**
 * 我的订单
 * Created by 丁琪 on 2017/12/20.
 */

public class OrderModel {

    public static OrderModel getInstance() {
        return OrderModel.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final OrderModel instance = new OrderModel();
    }

    /**
     * 获取我的订单
     *
     * @param iCallListener
     */
    public void getGetOrders(int vCheck, final ICallListener<String> iCallListener) {
        OkGo.<String>post(Constant.GetOrders)
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
