package com.zhuazhuale.changsha.module.home.ui;

import com.zhuazhuale.changsha.module.home.Bean.NewCPBean;
import com.zhuazhuale.changsha.presenter.iview.IBaseView;

/**
 * 个人中心
 * Created by 丁琪 on 2017/12/20.
 */

public interface IMineView extends IBaseView {
    void showNewCP(NewCPBean newCPBean);
}
