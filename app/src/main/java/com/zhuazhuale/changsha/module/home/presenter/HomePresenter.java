package com.zhuazhuale.changsha.module.home.presenter;

import com.google.gson.Gson;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.module.home.Bean.BaseDataBean;
import com.zhuazhuale.changsha.module.home.Bean.DeviceGoodsBean;
import com.zhuazhuale.changsha.module.home.Bean.DevicesTypeBean;
import com.zhuazhuale.changsha.module.home.Bean.LoginInfoBean;
import com.zhuazhuale.changsha.module.home.model.HomeModel;
import com.zhuazhuale.changsha.module.home.fragment.IHomeView;
import com.zhuazhuale.changsha.presenter.base.BasePresenter;
import com.zhuazhuale.changsha.util.log.LogUtil;

/**
 * 主页
 * Created by Administrator on 2017/12/12.
 */

public class HomePresenter extends BasePresenter<IHomeView> {
    private String TAG = getClass().getName();

    private final HomeModel homeModel;
    private final Gson gson;

    public HomePresenter(IHomeView iHomeView) {
        super(iHomeView);
        homeModel = HomeModel.getInstance();
        gson = new Gson();
    }


    public void initData() {
        homeModel.getBaseData(new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {

                BaseDataBean baseDataBean = gson.fromJson(s, BaseDataBean.class);
                mIView.showImagePage(baseDataBean.getRows());
            }

            @Override
            public void callFailed() {
                mIView.showFailed();
            }

            @Override
            public void onFinish() {
                LogUtil.e(TAG, "接口结束");

            }
        });
//        login();

    }

    private void login() {
        homeModel.getLoginMain(new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e("用户信息请求成功:    " + s);
                LoginInfoBean infoBean = gson.fromJson(s, LoginInfoBean.class);
                MyApplication.getInstance().setRowsBean(infoBean.getRows());
            }

            @Override
            public void callFailed() {
                LogUtil.e("用户信息请求失败:    ");
            }

            @Override
            public void onFinish() {
            }
        });
    }

    public void initDeviceGoods(int PageIndex, int PageSize, String TypeID,final int type) {
        homeModel.getGetDeviceGoods(PageIndex, PageSize,TypeID, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
//                LogUtil.e(s);
                DeviceGoodsBean deviceGoodsBean = gson.fromJson(s, DeviceGoodsBean.class);
                mIView.showDeviceGoods(deviceGoodsBean,type);
            }

            @Override
            public void callFailed() {
                mIView.showDeviceGoodsFailed(type);
            }

            @Override
            public void onFinish() {
                LogUtil.e("接口结束");
                mIView.showFinish();
            }
        });
    }
    public void initGetDevicesStatus( String TypeID,final int type) {
        homeModel.getGetDevicesStatus(TypeID, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(s);
                DevicesTypeBean deviceGoodsBean = gson.fromJson(s, DevicesTypeBean.class);
                mIView.showDeviceGoodsType(deviceGoodsBean);
            }

            @Override
            public void callFailed() {
                mIView.showDeviceGoodsFailed(type);
            }

            @Override
            public void onFinish() {
                LogUtil.e("接口结束");
                mIView.showFinish();
            }
        });
    }

}
