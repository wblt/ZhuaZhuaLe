package com.zhuazhuale.changsha.module.home.model;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.log.LogUtil;

import java.util.List;

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

    /**
     * 删除地址
     *
     * @param id
     * @param iCallListener
     */
    public void getDeleteAddress(String id, final ICallListener<String> iCallListener) {
        OkGo.<String>post(Constant.DeleteUserAddress)
                .tag(this)
                .params("vF_ID", id)
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

    /*
    {
  "F_UserID": "string",
  "F_Address": "string",
  "F_Consignee": "string",
  "F_Mobile": "string",
  "F_Remark": "string"
}
     */

    /**
     * 生成订单
     *
     * @param F_Consignee
     * @param F_Mobile
     * @param F_Address
     * @param F_Remark
     * @param iCallListener
     */
    public void getCreateOrder(String F_Consignee, String F_Mobile, String F_Address, String F_Remark, final ICallListener<String> iCallListener) {
        OkGo.<String>post(Constant.CreateOrder)
                .tag(this)
                .params("F_UserID", MyApplication.getInstance().getRowsBean().getF_ID())
                .params("F_Address", F_Address)
                .params("F_Consignee", F_Consignee)
                .params("F_Mobile", F_Mobile)
                .params("F_Remark", F_Remark)
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
     * 修改商品的选中状态
     *
     * @param iCallListener
     */
    /*
    {
  "vF_ID": [
    "string"
  ],
  "vUserID": "string",
  "vCheck": 0
}
     */
    public void getModifyUserGoods(List<String> vF_ID, final ICallListener<String> iCallListener) {
        for (String s:vF_ID){
            LogUtil.e("商品编号:  "+s);
        }
        OkGo.<String>post(Constant.ModifyUserGoods)
                .tag(this)
                .params("vUserID", MyApplication.getInstance().getRowsBean().getF_ID())
                .params("vCheck",1)
                .addUrlParams("vF_ID",vF_ID)
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
