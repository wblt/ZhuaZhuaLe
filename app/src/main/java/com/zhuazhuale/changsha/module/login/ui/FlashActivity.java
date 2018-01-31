package com.zhuazhuale.changsha.module.login.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.app.constant.BaseConstants;
import com.zhuazhuale.changsha.module.home.adapter.FlashAdapter;
import com.zhuazhuale.changsha.util.IItemOnClickListener;
import com.zhuazhuale.changsha.util.PreferenceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 指导Flash页面
 * Created by Administrator on 2017/12/22.
 */

public class FlashActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_flash);
        boolean isFirstLogin = PreferenceUtil.getBoolean(this, BaseConstants.IsFirstLoGin, false);
        if (isFirstLogin) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Intent intent = new Intent(FlashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 1000);

        } else {
            initView();
        }

    }

    private void initView() {
        List<Integer> imgs = new ArrayList<>();
        imgs.add(R.mipmap.launch_1);
        imgs.add(R.mipmap.launch_2);
        imgs.add(R.mipmap.launch_3);
        imgs.add(R.mipmap.launch_4);
        RollPagerView rpv_flash = (RollPagerView) findViewById(R.id.rpv_flash);
        FlashAdapter adapter = new FlashAdapter(this, imgs);

        //设置播放时间间隔
//        rpv_flash.setPlayDelay(2000);
        //设置透明度
        rpv_flash.setAnimationDurtion(500);
        //设置适配器
        rpv_flash.setAdapter(adapter);
        rpv_flash.setHintView(new ColorPointHintView(this, Color.BLACK, Color.GRAY));
        adapter.setOnItemClickListener(new IItemOnClickListener() {
            @Override
            public void itemOnClick(View view, int position) {
                PreferenceUtil.putBoolean(FlashActivity.this, BaseConstants.IsFirstLoGin, true);
                Intent intent = new Intent(FlashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void itemLongOnClick(View view, int position) {

            }
        });
    }
}
