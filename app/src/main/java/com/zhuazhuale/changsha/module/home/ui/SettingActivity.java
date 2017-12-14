package com.zhuazhuale.changsha.module.home.ui;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import butterknife.BindView;

/**
 * 设置页面
 * Created by 丁琪 on 2017/12/13 0013.
 */

public class SettingActivity extends AppBaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_home_back)
    ImageView iv_home_back;
    @BindView(R.id.ic_setting_tzzx)
    View ic_setting_tzzx;
    @BindView(R.id.ic_setting_yqjl)
    View ic_setting_yqjl;
    @BindView(R.id.ic_setting_sryqm)
    View ic_setting_sryqm;
    @BindView(R.id.ic_setting_bjyx)
    View ic_setting_bjyx;
    @BindView(R.id.ic_setting_yy)
    View ic_setting_yy;
    @BindView(R.id.ic_setting_lp)
    View ic_setting_lp;
    @BindView(R.id.ic_setting_wtfk)
    View ic_setting_wtfk;
    @BindView(R.id.ic_setting_gywm)
    View ic_setting_gywm;
    @BindView(R.id.ic_setting_tcdl)
    View ic_setting_tcdl;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected void initView() {
        TextView tv_tzzx = (TextView) ic_setting_tzzx.findViewById(R.id.tv_list_n);
        TextView tv_yqjl = (TextView) ic_setting_yqjl.findViewById(R.id.tv_list_n);
        TextView tv_sryqm = (TextView) ic_setting_sryqm.findViewById(R.id.tv_list_n);
        TextView tv_bjyx = (TextView) ic_setting_bjyx.findViewById(R.id.tv_list_n);
        TextView tv_yy = (TextView) ic_setting_yy.findViewById(R.id.tv_list_n);
        TextView tv_lp = (TextView) ic_setting_lp.findViewById(R.id.tv_list_n);
        TextView tv_wtfk = (TextView) ic_setting_wtfk.findViewById(R.id.tv_list_n);
        TextView tv_gywm = (TextView) ic_setting_gywm.findViewById(R.id.tv_list_n);
        TextView tv_tcdl = (TextView) ic_setting_tcdl.findViewById(R.id.tv_list_n);

        ImageView iv_bjyx = (ImageView) ic_setting_bjyx.findViewById(R.id.iv_list_tu);
        ImageView iv_yy = (ImageView) ic_setting_yy.findViewById(R.id.iv_list_tu);
        ImageView iv_lp = (ImageView) ic_setting_lp.findViewById(R.id.iv_list_tu);
        iv_bjyx.setImageResource(R.mipmap.icon_kai);
        iv_yy.setImageResource(R.mipmap.icon_kai);
        iv_lp.setImageResource(R.mipmap.icon_guan);

        tv_tzzx.setText("通知中心");
        tv_yqjl.setText("邀请奖励");
        tv_sryqm.setText("输入邀请码");
        tv_bjyx.setText("背景音效");
        tv_yy.setText("音乐");
        tv_lp.setText("录屏");
        tv_wtfk.setText("问题反馈");
        tv_gywm.setText("关于我们");
        tv_tcdl.setText("退出登录");

    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {
        iv_home_back.setOnClickListener(this);
        ic_setting_tzzx.setOnClickListener(this);
        ic_setting_yqjl.setOnClickListener(this);
        ic_setting_sryqm.setOnClickListener(this);
        ic_setting_bjyx.setOnClickListener(this);
        ic_setting_yy.setOnClickListener(this);
        ic_setting_lp.setOnClickListener(this);
        ic_setting_wtfk.setOnClickListener(this);
        ic_setting_gywm.setOnClickListener(this);
        ic_setting_tcdl.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_home_back:
                finish();
                break;
            case R.id.ic_setting_tzzx:
                break;
            case R.id.ic_setting_yqjl:
                Intent intent = new Intent(SettingActivity.this, InviteActivity.class);
                startActivity(intent);
                break;
            case R.id.ic_setting_sryqm:
                Intent intent1=new Intent(SettingActivity.this, InputCodeActivity.class);
                startActivity(intent1);
                break;
            case R.id.ic_setting_bjyx:
                break;
            case R.id.ic_setting_yy:
                break;
            case R.id.ic_setting_lp:
                break;
            case R.id.ic_setting_wtfk:
                break;
            case R.id.ic_setting_gywm:
                break;
            case R.id.ic_setting_tcdl:
                // 退出登录 测试添加微信登录

                break;
        }

    }


}
