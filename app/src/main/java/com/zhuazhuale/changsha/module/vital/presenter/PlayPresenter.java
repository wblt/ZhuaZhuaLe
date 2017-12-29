package com.zhuazhuale.changsha.module.vital.presenter;

import com.google.gson.Gson;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.module.home.Bean.EditAddressBean;
import com.zhuazhuale.changsha.module.home.Bean.NewCPBean;
import com.zhuazhuale.changsha.module.home.Bean.QueryGameBean;
import com.zhuazhuale.changsha.module.home.model.MineModel;
import com.zhuazhuale.changsha.module.home.ui.IMineView;
import com.zhuazhuale.changsha.module.vital.bean.AllUserTrueByDeviceIDBean;
import com.zhuazhuale.changsha.module.vital.bean.ControlGameBean;
import com.zhuazhuale.changsha.module.vital.bean.StartGameBean;
import com.zhuazhuale.changsha.module.vital.model.PlayModel;
import com.zhuazhuale.changsha.module.vital.ui.IPlayView;
import com.zhuazhuale.changsha.presenter.base.BasePresenter;
import com.zhuazhuale.changsha.util.log.LogUtil;

/**
 * 个人中心
 * Created by 丁琪 on 2017/12/20.
 */

public class PlayPresenter extends BasePresenter<IPlayView> {
    private String TAG = getClass().getName();

    private final PlayModel playModel;

    public PlayPresenter(IPlayView view) {
        super(view);
        playModel = PlayModel.getInstance();
    }

    /**
     * 查询游戏币
     */
    public void initNewCP() {
        playModel.getNewCP(new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
                Gson gson = new Gson();
                NewCPBean newCPBean = gson.fromJson(s, NewCPBean.class);
                mIView.showNewCP(newCPBean);
            }

            @Override
            public void callFailed() {
                mIView.showNewCPFailed();
            }

            @Override
            public void onFinish() {
                LogUtil.e(TAG, "接口结束");
//                mIView.showFinish();
            }
        });
    }

    /**
     * 查询机器的状态
     *
     * @param vDeviceID
     * @param type
     */
    public void initQueryGame(String vDeviceID, final int type) {
        playModel.getQueryGame(vDeviceID, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
                Gson gson = new Gson();
                QueryGameBean queryGameBean = gson.fromJson(s, QueryGameBean.class);
                mIView.showQueryGame(queryGameBean,type);
            }

            @Override
            public void callFailed() {
                mIView.showFailed();
            }

            @Override
            public void onFinish() {
                LogUtil.e(TAG, "接口结束");
//                mIView.showFinish();
            }
        });
    }

    /**
     * 游戏上机
     *
     * @param vDeviceID
     */
    public void initUpperGame(String vDeviceID) {
        playModel.getUpperGame(vDeviceID, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
                Gson gson = new Gson();
                StartGameBean gameBean = gson.fromJson(s, StartGameBean.class);
                mIView.showStartGame(gameBean);
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
     * 游戏下机
     * @param vDeviceID
     */
    public void initLowerGame(String vDeviceID) {
        playModel.getLowerGame(vDeviceID, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
                Gson gson = new Gson();
                EditAddressBean LowerGame = gson.fromJson(s, EditAddressBean.class);
                mIView.showLowerGame(LowerGame);
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
     * 操作游戏
     * @param vDeviceID
     * @param vAction
     * @param vToken
     * @param timeStamp
     */
    public void initControlGame(String vDeviceID, final String vAction, String vToken, String timeStamp) {
        playModel.getControlGame(vDeviceID, vAction, vToken, timeStamp, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
                Gson gson = new Gson();
                ControlGameBean controlGameBean = gson.fromJson(s, ControlGameBean.class);
                mIView.showControlGame(controlGameBean,vAction);
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

    /**
     * 查询在这台机器用户抓取成功的记录
     * @param vDeviceID
     */

    public void initGetAllUserTrueByDeviceID(String vDeviceID, final int type) {
        playModel.getGetAllUserTrueByDeviceID(vDeviceID, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
                Gson gson = new Gson();
                AllUserTrueByDeviceIDBean trueByDeviceIDBean = gson.fromJson(s, AllUserTrueByDeviceIDBean.class);
                mIView.showAllUserTrues(trueByDeviceIDBean,type);
            }

            @Override
            public void callFailed() {
                mIView.showFailed();
            }

            @Override
            public void onFinish() {
                LogUtil.e(TAG, "接口结束");
//                mIView.showFinish();
            }
        });
    }


}
