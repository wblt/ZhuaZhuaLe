package com.zhuazhuale.changsha.module.home.ui;

import com.zhuazhuale.changsha.module.home.Bean.EditAddressBean;
import com.zhuazhuale.changsha.module.home.Bean.SpoilsBean;
import com.zhuazhuale.changsha.presenter.iview.IBaseView;

/**
 * 我的战利品
 * Created by Administrator on 2017/12/20.
 */

public interface ISpoilsView extends IBaseView {
    void showQueryUserGoods(SpoilsBean spoilsBean, int type);

    void showFinish();

    void showFailed(int type);

    void showExChangeCP(EditAddressBean bean, int pos);
}
