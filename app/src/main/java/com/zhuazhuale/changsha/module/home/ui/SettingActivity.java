package com.zhuazhuale.changsha.module.home.ui;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.app.constant.BaseConstants;
import com.zhuazhuale.changsha.module.home.Bean.EditAddressBean;
import com.zhuazhuale.changsha.module.home.presenter.SettingPresenter;
import com.zhuazhuale.changsha.module.login.ui.FlashActivity;
import com.zhuazhuale.changsha.module.login.ui.LoginActivity;
import com.zhuazhuale.changsha.util.PreferenceUtil;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;
import com.zhuazhuale.changsha.view.widget.MaterialDialog;

import butterknife.BindView;

/**
 * 设置页面
 * Created by 丁琪 on 2017/12/13 0013.
 */

public class SettingActivity extends AppBaseActivity implements View.OnClickListener, ISettingView {


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
    private Intent intent;
    private MaterialDialog mDialog;
    private SettingPresenter presenter;
    private ImageView iv_bjyx;
    private ImageView iv_yy;
    private ImageView iv_lp;

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

        iv_bjyx = (ImageView) ic_setting_bjyx.findViewById(R.id.iv_list_tu);
        iv_yy = (ImageView) ic_setting_yy.findViewById(R.id.iv_list_tu);
        iv_lp = (ImageView) ic_setting_lp.findViewById(R.id.iv_list_tu);

        boolean isbjyx = PreferenceUtil.getBoolean(getContext(), BaseConstants.Is_bjyx, true);
        boolean isyy = PreferenceUtil.getBoolean(getContext(), BaseConstants.Is_yy, true);
        boolean islp = PreferenceUtil.getBoolean(getContext(), BaseConstants.Is_lp, false);
        if (isbjyx) {
            iv_bjyx.setImageResource(R.mipmap.icon_kai);
        } else {
            iv_bjyx.setImageResource(R.mipmap.icon_guan);
        }
        if (isyy) {
            iv_yy.setImageResource(R.mipmap.icon_kai);
        } else {
            iv_yy.setImageResource(R.mipmap.icon_guan);
        }
        if (islp) {
            iv_lp.setImageResource(R.mipmap.icon_kai);
        } else {
            iv_lp.setImageResource(R.mipmap.icon_guan);
        }

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
        presenter = new SettingPresenter(this);
        mDialog = new MaterialDialog(this);
    }

    @Override
    protected void initEvent() {
        ic_setting_tzzx.setOnClickListener(this);
        ic_setting_yqjl.setOnClickListener(this);
        ic_setting_sryqm.setOnClickListener(this);
        iv_bjyx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean is_bjyx = PreferenceUtil.getBoolean(getContext(), BaseConstants.Is_bjyx, true);
                if (is_bjyx) {
                    iv_bjyx.setImageResource(R.mipmap.icon_guan);
                    PreferenceUtil.putBoolean(getContext(), BaseConstants.Is_bjyx, false);

                } else {
                    iv_bjyx.setImageResource(R.mipmap.icon_kai);
                    PreferenceUtil.putBoolean(getContext(), BaseConstants.Is_bjyx, true);

                }
            }
        });
        iv_yy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean is_yy = PreferenceUtil.getBoolean(getContext(), BaseConstants.Is_yy, true);
                if (is_yy) {
                    iv_yy.setImageResource(R.mipmap.icon_guan);
                    PreferenceUtil.putBoolean(getContext(), BaseConstants.Is_yy, false);
                } else {
                    iv_yy.setImageResource(R.mipmap.icon_kai);
                    PreferenceUtil.putBoolean(getContext(), BaseConstants.Is_yy, true);


                }
            }
        });
        iv_lp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean is_lp = PreferenceUtil.getBoolean(getContext(), BaseConstants.Is_lp, false);
                if (is_lp) {
                    iv_lp.setImageResource(R.mipmap.icon_guan);
                    PreferenceUtil.putBoolean(getContext(), BaseConstants.Is_lp, false);
                } else {
                    iv_lp.setImageResource(R.mipmap.icon_kai);
                    PreferenceUtil.putBoolean(getContext(), BaseConstants.Is_lp, true);
                }
            }
        });
        ic_setting_wtfk.setOnClickListener(this);
        ic_setting_gywm.setOnClickListener(this);
        ic_setting_tcdl.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.ic_setting_tzzx:
               /* intent = new Intent(SettingActivity.this, FlashActivity.class);
                startActivity(intent);*/
                break;
            case R.id.ic_setting_yqjl:
                intent = new Intent(SettingActivity.this, InviteActivity.class);
                startActivity(intent);
                break;
            case R.id.ic_setting_sryqm:
                intent = new Intent(SettingActivity.this, InputCodeActivity.class);
                startActivity(intent);
                break;
            case R.id.ic_setting_bjyx:


                break;
            case R.id.ic_setting_yy:
                break;
            case R.id.ic_setting_lp:
                break;
            case R.id.ic_setting_wtfk:
                intent = new Intent(SettingActivity.this, FeedBackActivity.class);
                startActivity(intent);
                break;
            case R.id.ic_setting_gywm:
                intent = new Intent(SettingActivity.this, AboutUsActivity.class);
                startActivity(intent);
                break;
            case R.id.ic_setting_tcdl:

                mDialog.setTitle("娃娃乐温馨提示");
                mDialog.setMessage("是否要退出登录");

                mDialog.setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                        showLoadingDialog();
                        presenter.initLoginOut();
                    }
                });
                mDialog.setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                    }
                });
                mDialog.show();


                break;
        }

    }

    @Override
    public void showLoginOut(EditAddressBean bean) {
        if (bean.getCode() == 0) {
            ToastUtil.show(bean.getInfo());
        } else {
            HomeActivity.instance.finish();
            // 退出登录 返回登录页面
            PreferenceUtil.putBoolean(SettingActivity.this, BaseConstants.IsLogin, false);
            intent = new Intent(SettingActivity.this, LoginActivity.class);
            startActivity(intent);
            getActivityStackManager().exitActivity(this);
        }
    }

    @Override
    public void showFaild() {
        ToastUtil.show("退出失败,请检查网络!");
    }

    @Override
    public void showFinish() {
        dismissLoadingDialog();
    }


}
