package com.zhuazhuale.changsha.module.home.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import butterknife.BindView;

/**
 * 邀请奖励
 * Created by 丁琪 on 2017/12/14.
 */

public class InviteActivity extends AppBaseActivity implements View.OnClickListener {


    @BindView(R.id.tv_invite_1)
    TextView tv_invite_1;
    @BindView(R.id.tv_invite_2)
    TextView tv_invite_2;
    @BindView(R.id.tv_invite_3)
    TextView tv_invite_3;
    @BindView(R.id.tv_invite_4)
    TextView tv_invite_4;
    @BindView(R.id.tv_invite_5)
    TextView tv_invite_5;
    @BindView(R.id.tv_invite_6)
    TextView tv_invite_6;
    @BindView(R.id.iv_invite_fxyqm)//分享邀请
            ImageView iv_invite_fxyqm;
    @BindView(R.id.iv_invite_ss)//搜索
            ImageView iv_invite_ss;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_invite);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {
        iv_invite_fxyqm.setOnClickListener(this);
        iv_invite_ss.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
