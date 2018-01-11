package com.zhuazhuale.changsha.module.home.model;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.log.LogUtil;

/**
 * 我的抓取记录
 * Created by Administrator on 2017/12/19.
 */

public class RecordModel {

    public static RecordModel getInstance() {
        return RecordModel.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final RecordModel instance = new RecordModel();
    }

    public void getGrabWater(int PageIndex, int PageSize, final ICallListener<String> iCallListener) {
        OkGo.<String>post(Constant.GrabWater)
                .tag(this)
                .params("zzl", MyApplication.getInstance().getRowsBean().getF_ID())
                .params("PageIndex", PageIndex)
                .params("PageSize", PageSize)
                .params("vUserID",  MyApplication.getInstance().getRowsBean().getF_ID())
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
