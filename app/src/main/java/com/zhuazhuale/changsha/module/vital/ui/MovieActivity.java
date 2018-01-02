package com.zhuazhuale.changsha.module.vital.ui;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/2 0002.
 */

public class MovieActivity extends AppBaseActivity {
    @BindView(R.id.vv_movie_look)
    VideoView videoView;

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

        Uri uri = Uri.parse(videoUrl2);

        //设置视频控制器
        videoView.setMediaController(new MediaController(this));

        //播放完成回调
        videoView.setOnCompletionListener(new MyPlayerOnCompletionListener());

        //设置视频路径
        videoView.setVideoURI(uri);

        //开始播放视频
        videoView.start();
    }

    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            ToastUtil.show("播放完成了");
        }
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }
}
