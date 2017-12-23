package com.zhuazhuale.changsha.module.home.presenter;

import com.google.gson.Gson;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.module.home.Bean.AddressBean;
import com.zhuazhuale.changsha.module.home.Bean.EditAddressBean;
import com.zhuazhuale.changsha.module.home.Bean.NewCPBean;
import com.zhuazhuale.changsha.module.home.model.AddressModel;
import com.zhuazhuale.changsha.module.home.model.MineModel;
import com.zhuazhuale.changsha.module.home.ui.IDeliveryView;
import com.zhuazhuale.changsha.module.home.ui.IMineView;
import com.zhuazhuale.changsha.presenter.base.BasePresenter;
import com.zhuazhuale.changsha.util.log.LogUtil;

import java.util.List;

/**
 * 申请发货界面
 * Created by 丁琪 on 2017/12/20.
 */

public class DeliveryPresenter extends BasePresenter<IDeliveryView> {
    private String TAG = getClass().getName();

    private final AddressModel addressModel;

    public DeliveryPresenter(IDeliveryView iDeliveryView) {
        super(iDeliveryView);
        addressModel = AddressModel.getInstance();
    }

    public void initUserAddress(int vCheck) {
        addressModel.getQueryUserAddress(vCheck, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
                Gson gson = new Gson();
                AddressBean addressBean = gson.fromJson(s, AddressBean.class);
                mIView.showUserAddress(addressBean);
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

    /**
     * 修改商品选择状态
     * @param goodsIDs
     */
    public void initModifyUserGoods(List<String> goodsIDs) {
        addressModel.getModifyUserGoods(goodsIDs,new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
                Gson gson = new Gson();
                EditAddressBean bean=gson.fromJson(s,EditAddressBean.class);
                mIView.showModifyUserGoods(bean);

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
    /**
     * 生成订单
     * @param name
     * @param phone
     * @param address
     * @param remark
     */
    public void initCreateOrder(String name, String phone, String address,String remark) {
        addressModel.getCreateOrder(name,phone,address, remark,new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
                Gson gson = new Gson();

                EditAddressBean bean=gson.fromJson(s,EditAddressBean.class);
                mIView.showCreateOrder(bean);
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
