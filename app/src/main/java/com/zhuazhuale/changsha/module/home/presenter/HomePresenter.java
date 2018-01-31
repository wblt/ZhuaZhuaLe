package com.zhuazhuale.changsha.module.home.presenter;

import com.google.gson.Gson;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.module.home.Bean.BaseDataBean;
import com.zhuazhuale.changsha.module.home.Bean.DeviceGoodsBean;
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
                LogUtil.e(s);
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

  /*  *//**
     * 检测是否要更新app
     * @param vVersion
     *//*
    public void initVersion(String vVersion) {
        homeModel.getVersionCheck(vVersion, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(s);
                VersionBean versionBean = gson.fromJson(s, VersionBean.class);
                mIView.showChange(versionBean);
            }

            @Override
            public void callFailed() {
                mIView.showFailed();

            }

            @Override
            public void onFinish() {
                LogUtil.e("接口结束");
                mIView.showFinish();
            }
        });
    }
    *//* 下载包安装路径 *//*
    private String fileName = "zhuazhuale.apk";
    private String savePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/updatedir/";
    public void downloadApk(String vUrl) {
        String Url = "http://img.zhuazhuale.com/apk/zhuazhuale20180110.apk";
        OkGo.<File>get(vUrl)
                .tag(this)
                .execute(new FileCallback(savePath,fileName) {
                    @Override
                    public void onSuccess(Response<File> response) {
                        LogUtil.e(response.body());
                        mIView.installApk(response.body());
                    }

                    @Override
                    public void downloadProgress(Progress progress) {
                        super.downloadProgress(progress);
                        LogUtil.e(progress.fraction);
                        mIView.showProgress(progress.fraction);
                    }
                });


    }*/
}
