package com.zhuazhuale.changsha.module.home.ui;

import com.zhuazhuale.changsha.module.home.Bean.DeviceGoodsBean;
import com.zhuazhuale.changsha.presenter.iview.IBaseView;

/**
 * Created by Administrator on 2018/2/28.
 */

public interface IMallView extends IBaseView {
    void showDeviceGoods(DeviceGoodsBean deviceGoodsBean, int type);

    void showDeviceGoodsFailed(int type);

    void showFinish();
}
