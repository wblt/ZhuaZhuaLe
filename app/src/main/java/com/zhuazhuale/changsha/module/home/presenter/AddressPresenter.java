package com.zhuazhuale.changsha.module.home.presenter;

import com.google.gson.Gson;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.module.home.Bean.AddressBean;
import com.zhuazhuale.changsha.module.home.Bean.EditAddressBean;
import com.zhuazhuale.changsha.module.home.model.AddressModel;
import com.zhuazhuale.changsha.module.home.ui.IAddressView;
import com.zhuazhuale.changsha.presenter.base.BasePresenter;
import com.zhuazhuale.changsha.util.Constant;
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

    public void initQueryUserAddress(int vCheck, final int type) {
        addressModel.getQueryUserAddress(vCheck, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
                Gson gson = new Gson();
                AddressBean addressBean = gson.fromJson(s, AddressBean.class);
                mIView.showQueryUserAddress(addressBean, type);
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

    /**
     * 删除地址
     *
     * @param id
     * @param position
     */
    public void initDeleteUserAddress(String id, final int position) {
        LogUtil.e("id  " + id + "   position  " + position);
        addressModel.getDeleteAddress(id, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
                Gson gson = new Gson();
                EditAddressBean addressBean = gson.fromJson(s, EditAddressBean.class);
                mIView.showDeleteUserAddress(addressBean, position);
            }

            @Override
            public void callFailed() {
                mIView.showFailed(Constant.REFRESH);
            }

            @Override
            public void onFinish() {
                LogUtil.e(TAG, "接口结束");
                mIView.showFinish();
            }
        });
    }


}
