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
 * 充值页面
 * Created by Administrator on 2017/12/12.
 */

public class RechargeModel {

    public static RechargeModel getInstance() {
        return RechargeModel.SingletonHolder.instance;
    }


    private static class SingletonHolder {
        private static final RechargeModel instance = new RechargeModel();
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
                .params("vF_ID",F_ID)
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
     * 获取充值兑换比例
     *
     * @param iCallListener
     */
    public void getAllPriceProduct(final ICallListener<String> iCallListener) {
        String F_ID = PreferenceUtil.getString(MyApplication.getInstance(), BaseConstants.F_ID, "");
        OkGo.<String>post(Constant.GetAllPriceProduct)
                .tag(this)
                .params("zzl",F_ID)
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
     * 微信充值
     *
     * @param iCallListener
     */
    public void getWxUnifiedOrder(String productId, final ICallListener<String> iCallListener) {
        String F_ID = PreferenceUtil.getString(MyApplication.getInstance(), BaseConstants.F_ID, "");
        String F_Code = PreferenceUtil.getString(MyApplication.getInstance(), BaseConstants.F_Code, "");
        OkGo.<String>post(Constant.WxUnifiedOrder)
                .tag(this)
                .params("zzl", F_ID)
                .params("userid", F_ID)
                .params("openid", F_Code)
                .params("productId", productId)
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
     * 支付宝下单
     *
     * @param productId
     * @param iCallListener
     */
    public void getAliUnifiedOrder(String productId, final ICallListener<String> iCallListener) {
        String F_Code = PreferenceUtil.getString(MyApplication.getInstance(), BaseConstants.F_Code, "");
        String F_ID = PreferenceUtil.getString(MyApplication.getInstance(), BaseConstants.F_ID, "");
        LogUtil.e("F_Code  "+F_Code+"  productId  "+productId);
        OkGo.<String>post(Constant.AliUnifiedOrder)
                .tag(this)
                .params("userid", F_ID)
                .params("openid", F_Code)
                .params("productId", productId)
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

}
