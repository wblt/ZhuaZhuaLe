package com.zhuazhuale.changsha.module.home.presenter;

import com.google.gson.Gson;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.module.home.Bean.BanlanceWaterBean;
import com.zhuazhuale.changsha.module.home.model.WaWaBiModel;
import com.zhuazhuale.changsha.module.home.ui.IWaWaBiView;
import com.zhuazhuale.changsha.presenter.base.BasePresenter;
import com.zhuazhuale.changsha.util.log.LogUtil;

/**
 * 我的娃娃币
 * Created by Administrator on 2017/12/12.
 */

public class WaWaBiPresenter extends BasePresenter<IWaWaBiView> {
    private String TAG = getClass().getName();

    private final WaWaBiModel waWaBiModel;

    public WaWaBiPresenter(IWaWaBiView iWaWaBiView) {
        super(iWaWaBiView);
        waWaBiModel = WaWaBiModel.getInstance();
    }


    public void inittBanlanceWater(int PageIndex, int PageSize) {
        waWaBiModel.getBanlanceWater(PageIndex, PageSize, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
                Gson gson = new Gson();
                BanlanceWaterBean banlanceWaterBean = gson.fromJson(s, BanlanceWaterBean.class);
                if (1 == banlanceWaterBean.getCode()) {
                    mIView.showBanlanceWater(banlanceWaterBean);
                } else {
                    mIView.showNoData();
                }
            }

            @Override
            public void callFailed() {
                mIView.showFailed();
            }

            @Override
            public void onFinish() {
                LogUtil.e(TAG, "接口结束");
            }
        });
    }


}
