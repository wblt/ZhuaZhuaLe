package com.zhuazhuale.changsha.module.home.presenter;

import com.google.gson.Gson;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.module.home.Bean.BaseDataBean;
import com.zhuazhuale.changsha.module.home.Bean.DeviceGoodsBean;
import com.zhuazhuale.changsha.module.home.Bean.LoginInfoBean;
import com.zhuazhuale.changsha.module.home.fragment.IHomeView;
import com.zhuazhuale.changsha.module.home.model.HomeModel;
import com.zhuazhuale.changsha.module.home.ui.IMallView;
import com.zhuazhuale.changsha.presenter.base.BasePresenter;
import com.zhuazhuale.changsha.util.log.LogUtil;

/**
 * 商城
 * Created by Administrator on 2017/12/12.
 */

public class MallPresenter extends BasePresenter<IMallView> {
    private String TAG = getClass().getName();

    private final HomeModel homeModel;
    private final Gson gson;

    public MallPresenter(IMallView iHomeView) {
        super(iHomeView);
        homeModel = HomeModel.getInstance();
        gson = new Gson();
    }

    public void initDeviceGoods(int PageIndex, int PageSize, String TypeID, final int type) {
        homeModel.getGetDeviceGoods(PageIndex, PageSize, TypeID, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(s);
                DeviceGoodsBean deviceGoodsBean = gson.fromJson(s, DeviceGoodsBean.class);
                mIView.showDeviceGoods(deviceGoodsBean, type);
            }

            @Override
            public void callFailed() {
                mIView.showDeviceGoodsFailed(type);
            }

            @Override
            public void onFinish() {
                LogUtil.e("接口结束");
                mIView.showFinish();
            }
        });
    }

}
