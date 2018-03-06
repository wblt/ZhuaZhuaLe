package com.zhuazhuale.changsha.module.home.ui;

import com.zhuazhuale.changsha.module.home.Bean.AlipayBean;
import com.zhuazhuale.changsha.module.home.Bean.AllPriceProductBean;
import com.zhuazhuale.changsha.module.home.Bean.NewCPBean;
import com.zhuazhuale.changsha.module.home.Bean.WxUnifiedOrder;
import com.zhuazhuale.changsha.presenter.iview.IBaseView;

/**
 * 选择充值
 * Created by 丁琪 on 2017/12/19.
 */

public interface IPayMonenyView extends IBaseView {

    void showFailed();

    void showFinish();

    void showWxUnifiedOrder(WxUnifiedOrder wxUnifiedOrder);

    void showAliUnifiedOrde(AlipayBean alipayBean);
}
