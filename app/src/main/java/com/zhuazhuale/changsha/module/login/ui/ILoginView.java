package com.zhuazhuale.changsha.module.login.ui;

import com.zhuazhuale.changsha.module.home.Bean.LoginInfoBean;
import com.zhuazhuale.changsha.presenter.iview.IBaseView;

/**
 * Created by wb on 2017/12/14.
 */

public interface ILoginView extends IBaseView {


    void goToHomeActivity(LoginInfoBean s);

    void showFailed(int msg);

    void showFinish();
}
