package com.zhuazhuale.changsha.module.vital.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.DeviceGoodsBean;
import com.zhuazhuale.changsha.module.vital.bean.StartGameBean;
import com.zhuazhuale.changsha.module.vital.presenter.PlayPresenter;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import butterknife.BindView;

/**
 * 游戏页面
 * Created by dingqi on 2017/12/23 0023.
 */

public class PlayActivity extends AppBaseActivity implements View.OnClickListener, IPlayView {
    @BindView(R.id.iv_play_startgame)
    ImageView iv_play_startgame;
    @BindView(R.id.iv_play_up)
    ImageView iv_play_up;
    @BindView(R.id.iv_play_left)
    ImageView iv_play_left;
    @BindView(R.id.iv_play_right)
    ImageView iv_play_right;
    @BindView(R.id.iv_play_down)
    ImageView iv_play_down;
    @BindView(R.id.ll_play_caozuo)
    LinearLayout ll_play_caozuo;
    @BindView(R.id.iv_play_catch)
    ImageView iv_play_catch;
    @BindView(R.id.ll_play_open)
    LinearLayout ll_play_open;

    private DeviceGoodsBean.RowsBean rowsBean;
    private TXLivePlayer mLivePlayer;
    private TXCloudVideoView mView;
    private PlayPresenter presenter;
    private StartGameBean.RowsBean gameBeanRows;
    private boolean isPlay = false;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_play);
        Intent intent = getIntent();
        rowsBean = (DeviceGoodsBean.RowsBean) intent.getSerializableExtra("DeviceGoods");
    }

    @Override
    protected void initView() {
        showLoadingDialog();
        //mPlayerView即step1中添加的界面view
        mView = (TXCloudVideoView) findViewById(R.id.video_view);

        //创建player对象 setRenderMode
        mLivePlayer = new TXLivePlayer(getContext());
        //将图像等比例缩放，适配最长边，缩放后的宽和高都不会超过显示区域，居中显示，画面可能会留有黑边。
//        mLivePlayer.setRenderMode(TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION;);
        mLivePlayer.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN);//填充
        mLivePlayer.setRenderRotation(TXLiveConstants.RENDER_ROTATION_LANDSCAPE);
        //关键player对象与界面view
        mLivePlayer.setPlayerView(mView);
        //软解和硬解的切换需要在切换之前先stopPlay，切换之后再startPlay，否则会产生比较严重的花屏问题。
        mLivePlayer.stopPlay(true);
        mLivePlayer.enableHardwareDecode(true);
        mLivePlayer.startPlay(rowsBean.getF_Camera1(), TXLivePlayer.PLAY_TYPE_LIVE_RTMP); //推荐FLV


    }

    @Override
    protected void obtainData() {
        presenter = new PlayPresenter(this);
    }

    @Override
    protected void initEvent() {
        mLivePlayer.setPlayListener(new ITXLivePlayListener() {
            @Override
            public void onPlayEvent(int i, Bundle bundle) {
                LogUtil.e("       i   " + i + "        bundle  " + bundle.toString());
                switch (i) {
                    case 2004:
                        ToastUtil.show("欢迎进入游戏间");
                        dismissLoadingDialog();
                        break;
                    case 2002:
                        ToastUtil.show("欢迎进入游戏间");
                        dismissLoadingDialog();
                        break;
                    case 2007:
                        ToastUtil.show("有点延迟...");
                        showLoadingDialog();
                        break;

                }
            }

            @Override
            public void onNetStatus(Bundle bundle) {
//                ToastUtil.show(bundle.toString());
                LogUtil.e(bundle.toString());
            }
        });
        iv_play_startgame.setOnClickListener(this);
        iv_play_up.setOnClickListener(this);
        iv_play_left.setOnClickListener(this);
        iv_play_right.setOnClickListener(this);
        iv_play_down.setOnClickListener(this);
        iv_play_catch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_play_startgame:
                ll_play_open.setVisibility(View.GONE);
                ll_play_caozuo.setVisibility(View.VISIBLE);
    //                showLoadingDialog();
//                AnimationUtils.addTouchDrak(iv_play_startgame,true);
                presenter.initUpperGame(rowsBean.getF_DeviceNo());
                break;
            case R.id.iv_play_up:
                ControlGame("FORWARD");
                break;
            case R.id.iv_play_down:
                ControlGame("BACKWARD");
                break;
            case R.id.iv_play_left:
                ControlGame("RIGHT");

                break;
            case R.id.iv_play_right:
                ControlGame("LEFT");
                break;
            case R.id.iv_play_catch:
                ControlGame("DOWN");
                break;
        }

    }

    /**
     * 操作方向
     *
     * @param forward
     */
    private void ControlGame(String forward) {
        if (isPlay) {
            presenter.initControlGame(rowsBean.getF_DeviceNo(), forward, gameBeanRows.getToken(), gameBeanRows.getTimestamp() + "");
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLivePlayer.stopPlay(true); // true代表清除最后一帧画面
        mView.onDestroy();
    }

    @Override
    public void showStartGame(StartGameBean gameBean) {
        gameBeanRows = gameBean.getRows();
        isPlay = false;
        switch (gameBean.getCode()) {
            case 1://成功
                isPlay = true;
                ll_play_caozuo.setVisibility(View.VISIBLE);
//                ll_play_caozuo.setVisibility(View.GONE);
                iv_play_catch.setVisibility(View.VISIBLE);
                break;
            case 0://失败
                ToastUtil.show(gameBean.getInfo());
                break;
            case -9999://当前有用户正在游戏
                ToastUtil.show(gameBean.getInfo());
                break;
        }

    }

    @Override
    public void showFailed() {
        isPlay = false;
        ToastUtil.show("游戏失败,请检查网络!");
    }

    @Override
    public void showFinish() {
        dismissLoadingDialog();
    }
}
