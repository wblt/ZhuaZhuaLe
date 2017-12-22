package com.zhuazhuale.changsha.module.home.ui;

import com.zhuazhuale.changsha.module.home.Bean.BanlanceWaterBean;
import com.zhuazhuale.changsha.presenter.iview.IBaseView;

/**
 * 我的娃娃币
 * Created by Administrator on 2017/12/19.
 */

public interface IWaWaBiView extends IBaseView {
    void showBanlanceWater(BanlanceWaterBean banlanceWaterBean, int type);


    void showFailed(int type);

    void showFinish();
}
