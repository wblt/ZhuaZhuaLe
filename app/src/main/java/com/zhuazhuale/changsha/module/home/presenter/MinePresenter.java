package com.zhuazhuale.changsha.module.home.presenter;

import com.google.gson.Gson;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.module.home.Bean.EditAddressBean;
import com.zhuazhuale.changsha.module.home.Bean.GradWaterBean;
import com.zhuazhuale.changsha.module.home.Bean.NewCPBean;
import com.zhuazhuale.changsha.module.home.model.MineModel;
import com.zhuazhuale.changsha.module.home.model.RecordModel;
import com.zhuazhuale.changsha.module.home.ui.IMineView;
import com.zhuazhuale.changsha.module.home.ui.IRecordView;
import com.zhuazhuale.changsha.module.home.ui.MineActivity;
import com.zhuazhuale.changsha.presenter.base.BasePresenter;
import com.zhuazhuale.changsha.util.log.LogUtil;

/**
 * 个人中心
 * Created by 丁琪 on 2017/12/20.
 */

public class MinePresenter extends BasePresenter<IMineView> {
    private String TAG = getClass().getName();

    private final MineModel mineModel;
    private final Gson gson;

    public MinePresenter(IMineView view) {
        super(view);
        mineModel = MineModel.getInstance();
        gson = new Gson();
    }

    public void initNewCP() {
        mineModel.getNewCP(new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);

                NewCPBean newCPBean = gson.fromJson(s, NewCPBean.class);
                mIView.showNewCP(newCPBean);
            }

            @Override
            public void callFailed() {

            }

            @Override
            public void onFinish() {
                LogUtil.e(TAG, "接口结束");
            }
        });
    }

    public void initUserSign(final String time) {
        mineModel.getUserSign(time,new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
                EditAddressBean bean = gson.fromJson(s, EditAddressBean.class);
                mIView.showSignSuccess(bean,time);
            }

            @Override
            public void callFailed() {

            }

            @Override
            public void onFinish() {
                LogUtil.e(TAG, "接口结束");
            }
        });
    }


}
