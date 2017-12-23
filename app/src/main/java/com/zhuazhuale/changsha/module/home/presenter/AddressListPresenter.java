package com.zhuazhuale.changsha.module.home.presenter;

import com.google.gson.Gson;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.module.home.Bean.AddressBean;
import com.zhuazhuale.changsha.module.home.model.AddressModel;
import com.zhuazhuale.changsha.module.home.ui.IAddressListView;
import com.zhuazhuale.changsha.module.home.ui.IDeliveryView;
import com.zhuazhuale.changsha.presenter.base.BasePresenter;
import com.zhuazhuale.changsha.util.log.LogUtil;

/**
 * 申请发货界面
 * Created by 丁琪 on 2017/12/20.
 */

public class AddressListPresenter extends BasePresenter<IAddressListView> {
    private String TAG = getClass().getName();

    private final AddressModel addressModel;

    public AddressListPresenter(IAddressListView iAddressListView) {
        super(iAddressListView);
        addressModel = AddressModel.getInstance();
    }

    public void initUserAddress(int vCheck, final int type) {
        addressModel.getQueryUserAddress(vCheck, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
                Gson gson = new Gson();
                AddressBean addressBean = gson.fromJson(s, AddressBean.class);
                mIView.showUserAddress(addressBean,type);
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
