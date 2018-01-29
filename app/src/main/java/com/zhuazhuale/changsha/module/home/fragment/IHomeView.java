package com.zhuazhuale.changsha.module.home.fragment;

import com.zhuazhuale.changsha.module.home.Bean.BaseDataBean;
import com.zhuazhuale.changsha.module.home.Bean.DeviceGoodsBean;
import com.zhuazhuale.changsha.module.home.Bean.VersionBean;
import com.zhuazhuale.changsha.presenter.iview.IBaseView;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/12/12.
 */

public interface IHomeView extends IBaseView {

    void showImagePage(List<BaseDataBean.RowsBean> rows);

    void showDeviceGoods(DeviceGoodsBean rows, int type);

    void showFailed();

    void showFinish();

   /* void showChange(VersionBean versionBean);

    void installApk(File body);

    void showProgress(float fraction);*/

    void showDeviceGoodsFailed(int type);
}
