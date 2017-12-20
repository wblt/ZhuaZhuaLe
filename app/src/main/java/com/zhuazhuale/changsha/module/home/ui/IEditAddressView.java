package com.zhuazhuale.changsha.module.home.ui;

import com.zhuazhuale.changsha.module.home.Bean.EditAddressBean;
import com.zhuazhuale.changsha.presenter.iview.IBaseView;

/**
 * 添加地址
 * Created by 丁琪 on 2017/12/20.
 */

public interface IEditAddressView extends IBaseView {
    void showModifyUserAddress(EditAddressBean editAddressBean);


    void showFailed();

    void showFinish();
}
