package com.zhuazhuale.changsha.module.home.model;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.log.LogUtil;

/**
 * 添加地址
 * Created by 丁琪 on 2017/12/20.
 */

public class EditAddressModel {

    public static EditAddressModel getInstance() {
        return EditAddressModel.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final EditAddressModel instance = new EditAddressModel();
    }

    /**
     * 添加地址
     *
     * @param iCallListener
     */
    public void getModifyUserAddress(String address, String name, String ID, String phone, final ICallListener<String> iCallListener) {
        if (ID.isEmpty()) {
            ID = "";
        }
        LogUtil.e("我的ID :"+MyApplication.getInstance().getRowsBean().getF_ID());
        OkGo.<String>post(Constant.ModifyUserAddress)
                .tag(this)
                .params("zzl", MyApplication.getInstance().getRowsBean().getF_ID())
                .params("F_ID", ID)
                .params("F_UserID", MyApplication.getInstance().getRowsBean().getF_ID())
                .params("F_Consignee", name)
                .params("F_Mobile", phone)
                .params("F_Address", address)
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
