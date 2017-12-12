package com.zhuazhuale.changsha.module.home.presenter;

import com.google.gson.Gson;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.module.home.Bean.BaseDataBean;
import com.zhuazhuale.changsha.module.home.Bean.DeviceGoodsBean;
import com.zhuazhuale.changsha.module.home.model.HomeModel;
import com.zhuazhuale.changsha.module.home.ui.IHomeView;
import com.zhuazhuale.changsha.presenter.base.BasePresenter;
import com.zhuazhuale.changsha.util.log.LogUtil;

/**
 * Created by Administrator on 2017/12/12.
 */

public class HomePresenter extends BasePresenter<IHomeView> {
    private String TAG = getClass().getName();

    private final HomeModel homeModel;

    public HomePresenter(IHomeView iHomeView) {
        super(iHomeView);
        homeModel = HomeModel.getInstance();
    }


    public void initData() {
        homeModel.getBaseData(new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
                Gson gson = new Gson();
                BaseDataBean baseDataBean = gson.fromJson(s, BaseDataBean.class);
                mIView.showImagePage(baseDataBean.getRows());
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

    public void initDeviceGoods(int PageIndex, int PageSize) {
        homeModel.getGetDeviceGoods(PageIndex, PageSize, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(s);
                Gson gson = new Gson();
                DeviceGoodsBean deviceGoodsBean = gson.fromJson(s, DeviceGoodsBean.class);
                mIView.showDeviceGoods(deviceGoodsBean.getRows());
            }

            @Override
            public void callFailed() {

            }

            @Override
            public void onFinish() {
                LogUtil.e("接口结束");

            }
        });
    }
}
