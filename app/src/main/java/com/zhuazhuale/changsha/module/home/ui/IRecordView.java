package com.zhuazhuale.changsha.module.home.ui;

import com.zhuazhuale.changsha.module.home.Bean.GradWaterBean;
import com.zhuazhuale.changsha.presenter.iview.IBaseView;

/**
 * 我的抓取记录
 * Created by Administrator on 2017/12/19.
 */

public interface IRecordView extends IBaseView {
    void showGradWater(GradWaterBean gradWaterBean, int type);

    void showFailed(int type);

    void showFinish();
}
