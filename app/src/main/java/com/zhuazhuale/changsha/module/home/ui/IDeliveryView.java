package com.zhuazhuale.changsha.module.home.ui;

import com.zhuazhuale.changsha.module.home.Bean.AddressBean;
import com.zhuazhuale.changsha.module.home.Bean.EditAddressBean;
import com.zhuazhuale.changsha.presenter.iview.IBaseView;

/**
 * 申请发货界面
 * Created by 丁琪 on 2017/12/23 0023.
 */

public interface IDeliveryView extends IBaseView {
    void showUserAddress(AddressBean addressBean);

    void showFailed();

    void showFinish();

    void showModifyUserGoods(EditAddressBean bean);

    void showCreateOrder(EditAddressBean bean);
}
