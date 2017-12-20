package com.zhuazhuale.changsha.module.home.ui;

import com.zhuazhuale.changsha.module.home.Bean.SpoilsBean;
import com.zhuazhuale.changsha.presenter.iview.IBaseView;

/**
 * 我的战利品
 * Created by Administrator on 2017/12/20.
 */

public interface ISpoilsView extends IBaseView {
    void showQueryUserGoods(SpoilsBean spoilsBean);

    void showFinish();

    void showFailed();
}
