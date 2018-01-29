package com.zhuazhuale.changsha.module.home.ui;

import com.zhuazhuale.changsha.module.home.Bean.VersionBean;
import com.zhuazhuale.changsha.presenter.iview.IBaseView;

import java.io.File;

/**
 * Created by Administrator on 2018/1/29.
 */

public interface IHomeView2 extends IBaseView {
    void showChange(VersionBean versionBean);

    void showFailed();

    void showFinish();

    void installApk(File body);

    void showProgress(float fraction);
}
