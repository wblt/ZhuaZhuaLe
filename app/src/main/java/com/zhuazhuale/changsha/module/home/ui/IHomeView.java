package com.zhuazhuale.changsha.module.home.ui;

import com.zhuazhuale.changsha.module.home.Bean.BaseDataBean;
import com.zhuazhuale.changsha.module.home.Bean.DeviceGoodsBean;
import com.zhuazhuale.changsha.presenter.iview.IBaseView;

import java.util.List;

/**
 * Created by Administrator on 2017/12/12.
 */

public interface IHomeView extends IBaseView {

    void showImagePage(List<BaseDataBean.RowsBean> rows);

    void showDeviceGoods(List<DeviceGoodsBean.RowsBean> rows);
}
