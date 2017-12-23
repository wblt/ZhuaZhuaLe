package com.zhuazhuale.changsha.module.home.ui;

import com.zhuazhuale.changsha.module.home.Bean.AddressBean;
import com.zhuazhuale.changsha.presenter.iview.IBaseView;

/**
 * 申请发货地址列表选择
 * Created by Administrator on 2017/12/23 0023.
 */

public interface IAddressListView extends IBaseView {
    void showUserAddress(AddressBean addressBean, int type);
    void showFailed(int type);

    void showFinish();

}
