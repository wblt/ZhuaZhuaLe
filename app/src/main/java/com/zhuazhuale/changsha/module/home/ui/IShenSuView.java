package com.zhuazhuale.changsha.module.home.ui;

import com.zhuazhuale.changsha.module.home.Bean.EditAddressBean;
import com.zhuazhuale.changsha.presenter.iview.IBaseView;

/**
 * 申诉
 * Created by Administrator on 2017/12/21.
 */

public interface IShenSuView extends IBaseView {
    void showFinish();

    void showSuccess(EditAddressBean newCPBean);

    void showFailed();
}
