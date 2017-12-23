package com.zhuazhuale.changsha.module.vital.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.DeviceGoodsBean;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

/**
 * 游戏页面
 * Created by dingqi on 2017/12/23 0023.
 */

public class PlayActivity extends AppBaseActivity implements View.OnClickListener, IPlayView {


    private DeviceGoodsBean.RowsBean rowsBean;
    private TXLivePlayer mLivePlayer;
    private TXCloudVideoView mView;

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
//        mLivePlayer.setRenderMode(TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION);
        mLivePlayer.setRenderMode(TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION);
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
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLivePlayer.stopPlay(true); // true代表清除最后一帧画面
        mView.onDestroy();
    }
}
