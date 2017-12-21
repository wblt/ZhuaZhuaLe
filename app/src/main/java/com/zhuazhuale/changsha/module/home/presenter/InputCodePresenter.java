package com.zhuazhuale.changsha.module.home.presenter;

import com.google.gson.Gson;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.module.home.Bean.EditAddressBean;
import com.zhuazhuale.changsha.module.home.Bean.NewCPBean;
import com.zhuazhuale.changsha.module.home.model.InputCodeModel;
import com.zhuazhuale.changsha.module.home.model.MineModel;
import com.zhuazhuale.changsha.module.home.ui.IInputCodeView;
import com.zhuazhuale.changsha.module.home.ui.IMineView;
import com.zhuazhuale.changsha.presenter.base.BasePresenter;
import com.zhuazhuale.changsha.util.log.LogUtil;

/**
 * 输入邀请码
 * Created by 丁琪 on 2017/12/20.
 */

public class InputCodePresenter extends BasePresenter<IInputCodeView> {
    private String TAG = getClass().getName();

    private final InputCodeModel inputCodeModel;

    public InputCodePresenter(IInputCodeView iInputCodeView) {
        super(iInputCodeView);
        inputCodeModel = InputCodeModel.getInstance();
    }

    public void initInvitation(String vCode) {
        inputCodeModel.getInvitation(vCode, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
                Gson gson = new Gson();
                EditAddressBean bean = gson.fromJson(s, EditAddressBean.class);
                mIView.showSuccess(bean);
            }

            @Override
            public void callFailed() {
                mIView.showFailed();
            }

            @Override
            public void onFinish() {
                LogUtil.e(TAG, "接口结束");
                mIView.showFinish();
            }
        });
    }


}
