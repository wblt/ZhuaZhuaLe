package com.zhuazhuale.changsha.module.home.presenter;

import com.google.gson.Gson;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.module.home.Bean.EditAddressBean;
import com.zhuazhuale.changsha.module.home.Bean.NewCPBean;
import com.zhuazhuale.changsha.module.home.model.MineModel;
import com.zhuazhuale.changsha.module.home.model.SettingModel;
import com.zhuazhuale.changsha.module.home.ui.IMineView;
import com.zhuazhuale.changsha.module.home.ui.ISettingView;
import com.zhuazhuale.changsha.presenter.base.BasePresenter;
import com.zhuazhuale.changsha.util.log.LogUtil;

/**
 * 设置页面
 * Created by 丁琪 on 2017/12/20.
 */

public class SettingPresenter extends BasePresenter<ISettingView> {
    private String TAG = getClass().getName();

    private final SettingModel settingModel;

    public SettingPresenter(ISettingView iSettingView) {
        super(iSettingView);
        settingModel = SettingModel.getInstance();
    }

    public void initLoginOut() {
        settingModel.getLoginOut(new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
                Gson gson = new Gson();
                EditAddressBean bean = gson.fromJson(s, EditAddressBean.class);
                mIView.showLoginOut(bean);
            }

            @Override
            public void callFailed() {
                mIView.showFaild();
            }

            @Override
            public void onFinish() {
                LogUtil.e(TAG, "接口结束");
                mIView.showFinish();
            }
        });
    }


}
