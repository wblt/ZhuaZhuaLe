package com.zhuazhuale.changsha.module.home.ui;

import com.zhuazhuale.changsha.module.home.Bean.OrderBean;
import com.zhuazhuale.changsha.presenter.iview.IBaseView;

/**
 * 我的订单
 * Created by Administrator on 2017/12/20.
 */

public interface IOrederView extends IBaseView {
    void showGetOrder(OrderBean orderBean);

    void showFailed();

    void showFinish();
}
