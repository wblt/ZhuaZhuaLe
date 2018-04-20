package com.zhuazhuale.changsha.module.vital.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.module.home.Bean.EditAddressBean;
import com.zhuazhuale.changsha.module.home.Bean.NewCPBean;
import com.zhuazhuale.changsha.module.home.Bean.QueryGameBean;
import com.zhuazhuale.changsha.module.vital.bean.AllUserTrueByDeviceIDBean;
import com.zhuazhuale.changsha.module.vital.bean.ControlGameBean;
import com.zhuazhuale.changsha.module.vital.bean.StartGameBean;
import com.zhuazhuale.changsha.module.vital.bean.UploadBean;
import com.zhuazhuale.changsha.module.vital.model.PlayModel;
import com.zhuazhuale.changsha.module.vital.ui.IPlayView;
import com.zhuazhuale.changsha.presenter.base.BasePresenter;
import com.zhuazhuale.changsha.util.FileUtil;
import com.zhuazhuale.changsha.util.TXupload.TXUGCPublish;
import com.zhuazhuale.changsha.util.TXupload.TXUGCPublishTypeDef;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;

import java.io.File;


/**
 * 个人中心
 * Created by 丁琪 on 2017/12/20.
 */

public class PlayPresenter extends BasePresenter<IPlayView> {
    private String TAG = getClass().getName();

    private final PlayModel playModel;
    private Gson gson;

    public PlayPresenter(IPlayView view) {
        super(view);
        playModel = PlayModel.getInstance();
        gson = new Gson();
    }

    /**
     * 查询游戏币
     */
    public void initNewCP() {
        playModel.getNewCP(new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
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
//                LogUtil.e(TAG, s);
                QueryGameBean queryGameBean = gson.fromJson(s, QueryGameBean.class);
                mIView.showQueryGame(queryGameBean, type);
            }

            @Override
            public void callFailed() {
                mIView.showFailed();
            }

            @Override
            public void onFinish() {
//                LogUtil.e(TAG, "接口结束");
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
     *
     * @param vDeviceID
     */
    public void initLowerGame(String vDeviceID) {
        playModel.getLowerGame(vDeviceID, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
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
     *
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
                ControlGameBean controlGameBean = gson.fromJson(s, ControlGameBean.class);
                mIView.showControlGame(controlGameBean, vAction);
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
     *
     * @param vDeviceID
     */

    public void initGetAllUserTrueByDeviceID(String vDeviceID, final int type) {
        playModel.getGetAllUserTrueByDeviceID(vDeviceID, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
                AllUserTrueByDeviceIDBean trueByDeviceIDBean = gson.fromJson(s, AllUserTrueByDeviceIDBean.class);
                mIView.showAllUserTrues(trueByDeviceIDBean, type);
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
     * 获取视频签名
     *
     * @param grabID
     * @param file
     */
    public void initgetGetUploadSignature(final String grabID, final String moviePath, final File file) {
        LogUtil.e("grabID   " + grabID + "  moviePath  " + moviePath);
        playModel.getGetUploadSignature(new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
                UploadBean uploadBean = gson.fromJson(s, UploadBean.class);
                initPushMP4ToTX(uploadBean.getRows().getVToken(), moviePath, grabID, file);
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
     * 上传视频到腾讯云
     *
     * @param mCosSignature
     * @param mVideoPath
     * @param grabID
     * @param file
     */
    public void initPushMP4ToTX(String mCosSignature, final String mVideoPath, final String grabID, final File file) {
//        TXUGCPublish mVideoPublish = new TXUGCPublish(MyApplication.getInstance().getApplicationContext());
        TXUGCPublish mVideoPublish = new TXUGCPublish((Context) mIView);
// 文件发布默认是采用断点续传
        TXUGCPublishTypeDef.TXPublishParam param = new TXUGCPublishTypeDef.TXPublishParam();
        param.signature = mCosSignature;                        // 需要填写第四步中计算的上传签名
// 录制生成的视频文件路径, ITXVideoRecordListener 的 onRecordComplete 回调中可以获取
        param.videoPath = mVideoPath;
// 录制生成的视频首帧预览图，ITXVideoRecordListener 的 onRecordComplete 回调中可以获取
        param.coverPath = "";
        mVideoPublish.publishVideo(param);
        mVideoPublish.setListener(new TXUGCPublishTypeDef.ITXVideoPublishListener() {
            @Override
            public void onPublishProgress(long uploadBytes, long totalBytes) {
                LogUtil.e(uploadBytes + "上传");
            }

            @Override
            public void onPublishComplete(TXUGCPublishTypeDef.TXPublishResult result) {
                LogUtil.e(result.toString());
                initModiflyVideoUrl(grabID, result.videoURL);
                FileUtil.deleteFile(file);

            }
        });

    }

    public void initModiflyVideoUrl(String F_GrabWaterID, String F_VideoUrl) {
        playModel.getModiflyVideoUrl(F_GrabWaterID, F_VideoUrl, new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {
                LogUtil.e(TAG, s);
              /*  UploadBean uploadBean = gson.fromJson(s, UploadBean.class);
                initPushMP4ToTX(uploadBean.getRows().getVToken(), moviePath);*/
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
