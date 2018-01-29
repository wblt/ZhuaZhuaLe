package com.zhuazhuale.changsha.module.home.presenter;

import android.os.Environment;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.module.home.Bean.VersionBean;
import com.zhuazhuale.changsha.module.home.model.HomeModel;
import com.zhuazhuale.changsha.module.home.ui.IHomeView2;
import com.zhuazhuale.changsha.presenter.base.BasePresenter;
import com.zhuazhuale.changsha.util.log.LogUtil;

import java.io.File;

/**
 * 主页
 * Created by Administrator on 2017/12/12.
 */

public class HomePresenter2 extends BasePresenter<IHomeView2> {
    private String TAG = getClass().getName();

    private final HomeModel homeModel;
    private final Gson gson;

    public HomePresenter2(IHomeView2 iHomeView) {
        super(iHomeView);
        homeModel = HomeModel.getInstance();
        gson = new Gson();
    }

    /**
     * 检测是否要更新app
     * @param vVersion
     */
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
    /* 下载包安装路径 */
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


    }
}
