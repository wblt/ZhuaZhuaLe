package com.zhuazhuale.changsha.module.home.presenter;

import com.google.gson.Gson;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.module.home.Bean.SpoilsBean;
import com.zhuazhuale.changsha.module.home.model.SpoilsModel;
import com.zhuazhuale.changsha.module.home.ui.ISpoilsView;
import com.zhuazhuale.changsha.presenter.base.BasePresenter;
import com.zhuazhuale.changsha.util.log.LogUtil;

/**
 * 我的战利品
 * Created by 丁琪 on 2017/12/20.
 */

public class SpoilsPresenter extends BasePresenter<ISpoilsView> {
    private String TAG = getClass().getName();

    private final SpoilsModel spoilsModel;

    public SpoilsPresenter(ISpoilsView iSpoilsView) {
        super(iSpoilsView);
        spoilsModel = SpoilsModel.getInstance();
    }

    public void initQueryUserGoods(int vCheck, final int type) {
        spoilsModel.getQueryUserGoods(vCheck, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
                Gson gson = new Gson();
                SpoilsBean spoilsBean = gson.fromJson(s, SpoilsBean.class);
                mIView.showQueryUserGoods(spoilsBean, type);
            }

            @Override
            public void callFailed() {
                mIView.showFailed(type);
            }

            @Override
            public void onFinish() {
                LogUtil.e(TAG, "接口结束");
                mIView.showFinish();
            }
        });
    }


}
