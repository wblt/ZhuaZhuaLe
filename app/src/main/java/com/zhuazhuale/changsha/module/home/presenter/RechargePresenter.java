package com.zhuazhuale.changsha.module.home.presenter;

import com.google.gson.Gson;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.module.home.Bean.AllPriceProductBean;
import com.zhuazhuale.changsha.module.home.Bean.BaseDataBean;
import com.zhuazhuale.changsha.module.home.Bean.DeviceGoodsBean;
import com.zhuazhuale.changsha.module.home.Bean.NewCPBean;
import com.zhuazhuale.changsha.module.home.Bean.WxUnifiedOrder;
import com.zhuazhuale.changsha.module.home.model.HomeModel;
import com.zhuazhuale.changsha.module.home.model.RechargeModel;
import com.zhuazhuale.changsha.module.home.ui.IHomeView;
import com.zhuazhuale.changsha.module.home.ui.IRechargeView;
import com.zhuazhuale.changsha.presenter.base.BasePresenter;
import com.zhuazhuale.changsha.util.log.LogUtil;

/**
 * 充值界面
 * Created by dingqi on 2017/12/19.
 */

public class RechargePresenter extends BasePresenter<IRechargeView> {
    private String TAG = getClass().getName();

    private final RechargeModel rechargeModel;

    public RechargePresenter(IRechargeView iRechargeView) {
        super(iRechargeView);
        rechargeModel = RechargeModel.getInstance();
    }


    public void iniNewCP() {
        rechargeModel.getNewCP(new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
                Gson gson = new Gson();
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

    public void initAllPriceProduct() {
        rechargeModel.getAllPriceProduct(new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(s);
                Gson gson = new Gson();
                AllPriceProductBean allPriceProductBean = gson.fromJson(s, AllPriceProductBean.class);
                mIView.showAllPriceProduct(allPriceProductBean);

            }

            @Override
            public void callFailed() {
                mIView.showFailed();
            }

            @Override
            public void onFinish() {
                LogUtil.e("接口结束");
                mIView.showFinish();

            }
        });
    }

    public void iniWxUnifiedOrder(String productId) {
        rechargeModel.getWxUnifiedOrder(productId, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
                Gson gson = new Gson();
                WxUnifiedOrder wxUnifiedOrder = gson.fromJson(s, WxUnifiedOrder.class);
                mIView.showWxUnifiedOrder(wxUnifiedOrder);

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
