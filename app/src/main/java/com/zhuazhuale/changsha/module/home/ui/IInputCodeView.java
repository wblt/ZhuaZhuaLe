package com.zhuazhuale.changsha.module.home.ui;

import com.zhuazhuale.changsha.module.home.Bean.EditAddressBean;
import com.zhuazhuale.changsha.presenter.iview.IBaseView;

/**
 * 输入邀请码
 * Created by Administrator on 2017/12/21.
 */

public interface IInputCodeView extends IBaseView {
    void showSuccess(EditAddressBean bean);

    void showFinish();

    void showFailed();
}
