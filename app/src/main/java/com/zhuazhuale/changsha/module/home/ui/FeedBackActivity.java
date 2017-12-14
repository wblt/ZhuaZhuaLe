package com.zhuazhuale.changsha.module.home.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import butterknife.BindView;

/**
 * 问题反馈
 * Created by Administrator on 2017/12/14.
 */

public class FeedBackActivity extends AppBaseActivity implements View.OnClickListener {
    @BindView(R.id.iv_home_back)
    ImageView iv_home_back;
    @BindView(R.id.tv_feedback)
    TextView tv_feedback;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_feedback);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {
        iv_home_back.setOnClickListener(this);
        tv_feedback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home_back:
                finish();
                break;
            case R.id.tv_feedback:
                break;
        }
    }
}
