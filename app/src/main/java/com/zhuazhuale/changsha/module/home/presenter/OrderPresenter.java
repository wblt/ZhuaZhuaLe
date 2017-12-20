package com.zhuazhuale.changsha.module.home.presenter;

import com.google.gson.Gson;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.module.home.Bean.NewCPBean;
import com.zhuazhuale.changsha.module.home.Bean.OrderBean;
import com.zhuazhuale.changsha.module.home.model.MineModel;
import com.zhuazhuale.changsha.module.home.model.OrderModel;
import com.zhuazhuale.changsha.module.home.ui.IMineView;
import com.zhuazhuale.changsha.module.home.ui.IOrederView;
import com.zhuazhuale.changsha.presenter.base.BasePresenter;
import com.zhuazhuale.changsha.util.log.LogUtil;

/**
 * 我的订单
 * Created by 丁琪 on 2017/12/20.
 */

public class OrderPresenter extends BasePresenter<IOrederView> {
    private String TAG = getClass().getName();

    private final OrderModel orderModel;

    public OrderPresenter(IOrederView iOrederView) {
        super(iOrederView);
        orderModel = OrderModel.getInstance();
    }

    public void initGetOrders(int vCheck) {
        orderModel.getGetOrders(vCheck, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
                Gson gson = new Gson();
                OrderBean orderBean = gson.fromJson(s, OrderBean.class);
                mIView.showGetOrder(orderBean);
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
