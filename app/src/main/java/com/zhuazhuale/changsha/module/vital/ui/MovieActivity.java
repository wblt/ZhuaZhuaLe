package com.zhuazhuale.changsha.module.vital.ui;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;

import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXVodPlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import butterknife.BindView;

/**观看视频
 * Created by Administrator on 2018/1/2 0002.
 */

public class MovieActivity extends AppBaseActivity {
    @BindView(R.id.vv_movie_look)
    TXCloudVideoView videoView;
    private TXVodPlayer mVodPlayer;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_movie1);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        String videoUrl = intent.getStringExtra("MoviePath");
        //网络视频
        String videoUrl2 = videoUrl;


        //mPlayerView即step1中添加的界面view
//        TXCloudVideoView mView = (TXCloudVideoView) view.findViewById(R.id.video_view);
//创建player对象
        mVodPlayer = new TXVodPlayer(this);
//关键player对象与界面view
        mVodPlayer.setPlayerView(videoView);
//        mVodPlayer.setRenderMode();
        mVodPlayer.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN);//填充
        mVodPlayer.startPlay(videoUrl2);
    }



    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mVodPlayer.stopPlay(true); // true代表清除最后一帧画面
        videoView.onDestroy();
    }
}
