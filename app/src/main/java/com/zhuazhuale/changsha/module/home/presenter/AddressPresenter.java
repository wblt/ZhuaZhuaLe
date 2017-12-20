package com.zhuazhuale.changsha.module.home.presenter;

import com.google.gson.Gson;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.module.home.Bean.AddressBean;
import com.zhuazhuale.changsha.module.home.Bean.SpoilsBean;
import com.zhuazhuale.changsha.module.home.model.AddressModel;
import com.zhuazhuale.changsha.module.home.model.SpoilsModel;
import com.zhuazhuale.changsha.module.home.ui.IAddressView;
import com.zhuazhuale.changsha.module.home.ui.ISpoilsView;
import com.zhuazhuale.changsha.presenter.base.BasePresenter;
import com.zhuazhuale.changsha.util.log.LogUtil;

/**
 * 我的地址
 * Created by 丁琪 on 2017/12/20.
 */

public class AddressPresenter extends BasePresenter<IAddressView> {
    private String TAG = getClass().getName();

    private final AddressModel addressModel;

    public AddressPresenter(IAddressView iAddressView) {
        super(iAddressView);
        addressModel = AddressModel.getInstance();
    }

    public void initQueryUserAddress(int vCheck) {
        addressModel.getQueryUserAddress(vCheck, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
                Gson gson = new Gson();
                AddressBean addressBean = gson.fromJson(s, AddressBean.class);
                mIView.showQueryUserAddress(addressBean);
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
