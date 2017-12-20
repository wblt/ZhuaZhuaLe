package com.zhuazhuale.changsha.module.home.presenter;

import com.google.gson.Gson;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.module.home.Bean.AddressBean;
import com.zhuazhuale.changsha.module.home.Bean.EditAddressBean;
import com.zhuazhuale.changsha.module.home.model.AddressModel;
import com.zhuazhuale.changsha.module.home.model.EditAddressModel;
import com.zhuazhuale.changsha.module.home.ui.IAddressView;
import com.zhuazhuale.changsha.module.home.ui.IEditAddressView;
import com.zhuazhuale.changsha.presenter.base.BasePresenter;
import com.zhuazhuale.changsha.util.log.LogUtil;

/**
 * 添加地址
 * Created by 丁琪 on 2017/12/20.
 */

public class EditAddressPresenter extends BasePresenter<IEditAddressView> {
    private String TAG = getClass().getName();

    private final EditAddressModel editAddressModel;

    public EditAddressPresenter(IEditAddressView iEditAddressView) {
        super(iEditAddressView);
        editAddressModel = EditAddressModel.getInstance();
    }

    public void initModifyUserAddress(String address, String name, String ID, String phone) {
        LogUtil.e("address  " + address + "     name    " + name + "     ID  " + ID + "     phone   " + phone);
        editAddressModel.getModifyUserAddress(address, name, ID, phone, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
                Gson gson = new Gson();
                EditAddressBean editAddressBean = gson.fromJson(s, EditAddressBean.class);
                mIView.showModifyUserAddress(editAddressBean);
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
