package com.zhuazhuale.changsha.module.home.presenter;

import com.google.gson.Gson;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.module.home.Bean.AlipayBean;
import com.zhuazhuale.changsha.module.home.Bean.AllPriceProductBean;
import com.zhuazhuale.changsha.module.home.Bean.NewCPBean;
import com.zhuazhuale.changsha.module.home.Bean.WxUnifiedOrder;
import com.zhuazhuale.changsha.module.home.model.RechargeModel;
import com.zhuazhuale.changsha.module.home.ui.IPayMonenyView;
import com.zhuazhuale.changsha.module.home.ui.IRechargeView;
import com.zhuazhuale.changsha.presenter.base.BasePresenter;
import com.zhuazhuale.changsha.util.log.LogUtil;

/**
 * 选择充值界面
 * Created by dingqi on 2017/12/19.
 */

public class PayMonenyPresenter extends BasePresenter<IPayMonenyView> {
    private String TAG = getClass().getName();

    private final RechargeModel rechargeModel;
    private final Gson gson;

    public PayMonenyPresenter(IPayMonenyView iRechargeView) {
        super(iRechargeView);
        rechargeModel = RechargeModel.getInstance();
        gson = new Gson();
    }


    public void iniWxUnifiedOrder(String productId) {
        rechargeModel.getWxUnifiedOrder(productId, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);

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

    /**
     * 支付宝下单
     * @param productId
     */
    public void initAliUnifiedOrder(String productId) {
        rechargeModel.getAliUnifiedOrder(productId, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
               /* Gson gson = new Gson();
                WxUnifiedOrder wxUnifiedOrder = gson.fromJson(s, WxUnifiedOrder.class);
                mIView.showWxUnifiedOrder(wxUnifiedOrder);*/
                AlipayBean alipayBean=gson.fromJson(s,AlipayBean.class);
                mIView.showAliUnifiedOrde(alipayBean);

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
