package com.zhuazhuale.changsha.module.home.ui;

import com.zhuazhuale.changsha.module.home.Bean.EditAddressBean;
import com.zhuazhuale.changsha.presenter.iview.IBaseView;

/**
 * 设置
 * Created by Administrator on 2017/12/22.
 */

public interface ISettingView extends IBaseView {
    void showFinish();

    void showLoginOut(EditAddressBean bean);

    void showFaild();
}
