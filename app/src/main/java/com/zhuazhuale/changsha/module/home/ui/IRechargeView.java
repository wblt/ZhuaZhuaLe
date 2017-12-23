package com.zhuazhuale.changsha.module.home.ui;

import com.zhuazhuale.changsha.module.home.Bean.AllPriceProductBean;
import com.zhuazhuale.changsha.module.home.Bean.NewCPBean;
import com.zhuazhuale.changsha.module.home.Bean.WxUnifiedOrder;
import com.zhuazhuale.changsha.presenter.iview.IBaseView;

/**
 * 充值界面
 * Created by 丁琪 on 2017/12/19.
 */

public interface IRechargeView extends IBaseView {
    void showNewCP(NewCPBean newCPBean);

    void showAllPriceProduct(AllPriceProductBean allPriceProductBean);

    void showFailed();


    void showWxUnifiedOrder(WxUnifiedOrder wxUnifiedOrder);

    void showFinish();
}
