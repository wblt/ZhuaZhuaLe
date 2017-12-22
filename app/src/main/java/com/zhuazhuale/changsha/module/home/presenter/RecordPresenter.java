package com.zhuazhuale.changsha.module.home.presenter;

import com.google.gson.Gson;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.module.home.Bean.BanlanceWaterBean;
import com.zhuazhuale.changsha.module.home.Bean.GradWaterBean;
import com.zhuazhuale.changsha.module.home.model.RecordModel;
import com.zhuazhuale.changsha.module.home.model.WaWaBiModel;
import com.zhuazhuale.changsha.module.home.ui.IRecordView;
import com.zhuazhuale.changsha.module.home.ui.IWaWaBiView;
import com.zhuazhuale.changsha.presenter.base.BasePresenter;
import com.zhuazhuale.changsha.util.log.LogUtil;

/**
 * 我的抓取记录
 * Created by Administrator on 2017/12/12.
 */

public class RecordPresenter extends BasePresenter<IRecordView> {
    private String TAG = getClass().getName();

    private final RecordModel recordModel;

    public RecordPresenter(IRecordView iRecordView) {
        super(iRecordView);
        recordModel = RecordModel.getInstance();
    }

    /**
     * @param PageIndex
     * @param PageSize
     * @param type      类型：初始化数据INIT、刷新数据REFRESH、加载更多数据LOADMORE
     */
    public void initGrabWater(int PageIndex, int PageSize, final int type) {
        recordModel.getGrabWater(PageIndex, PageSize, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
                Gson gson = new Gson();
                GradWaterBean gradWaterBean = gson.fromJson(s, GradWaterBean.class);
                mIView.showGradWater(gradWaterBean, type);
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
