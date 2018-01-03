package com.zhuazhuale.changsha.module.login.ui;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.app.constant.BaseConstants;
import com.zhuazhuale.changsha.model.entity.eventbus.LoginEvent;
import com.zhuazhuale.changsha.module.home.Bean.LoginInfoBean;
import com.zhuazhuale.changsha.module.home.ui.HomeActivity;
import com.zhuazhuale.changsha.module.login.presenter.LoginPresenter;
import com.zhuazhuale.changsha.util.CommonUtil;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.EventBusUtil;
import com.zhuazhuale.changsha.util.PreferenceUtil;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class LoginActivity extends AppBaseActivity implements View.OnClickListener, ILoginView {
    @BindView(R.id.iv_login_wxlogin)
    ImageView iv_login_wxlogin;
    @BindView(R.id.iv_login_check)
    ImageView iv_login_check;
    private boolean isAgree = true;


    private LoginPresenter loginPresenter;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_login);
        getToolbar().setVisibility(View.GONE);

    }

    /**
     * 从本地拿取数据
     */
    private void getUserInfoByPreference() {
        String F_ID = PreferenceUtil.getString(this, BaseConstants.F_ID, "");
        String F_Code = PreferenceUtil.getString(this, BaseConstants.F_Code, "");
        String F_Code1 = PreferenceUtil.getString(this, BaseConstants.F_Code1, "");
        String F_Code2 = PreferenceUtil.getString(this, BaseConstants.F_Code2, "");
        String F_Name = PreferenceUtil.getString(this, BaseConstants.F_Name, "");
        int F_CP = PreferenceUtil.getInt(this, BaseConstants.F_CP, 0);
        String F_Img = PreferenceUtil.getString(this, BaseConstants.F_Img, "");
        String F_VerCode = PreferenceUtil.getString(this, BaseConstants.F_VerCode, "");
        String F_FxUrl = PreferenceUtil.getString(this, BaseConstants.F_FxUrl, "");
        LoginInfoBean.RowsBean rowsBean = new LoginInfoBean.RowsBean();
        rowsBean.setF_ID(F_ID);
        rowsBean.setF_Code(F_Code);
        rowsBean.setF_Code1(F_Code1);
        rowsBean.setF_Code2(F_Code2);
        rowsBean.setF_Name(F_Name);
        rowsBean.setF_CP(F_CP);
        rowsBean.setF_Img(F_Img);
        rowsBean.setF_VerCode(F_VerCode);
        rowsBean.setF_FxUrl(F_FxUrl);
        // 保存在内存中
        MyApplication.getInstance().setRowsBean(rowsBean);
    }

    @Override
    protected void initView() {
        int color = getResourceColor(R.color.white);
        setStatusBarColor(color, 0);
    }

    @Override
    protected void obtainData() {
        loginPresenter = new LoginPresenter(this);
        EventBusUtil.register(this);//订阅事件
    }

    //EventBus的事件接收，从事件中获取最新的收藏数量并更新界面展示
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(LoginEvent event) {
        String code = event.getCode();
        loginPresenter.initWX(code, Constant.APPID, Constant.secret);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅
        EventBusUtil.unregister(this);
    }

    @Override
    public void onBackPressed() {
        getActivityStackManager().exitApplication();
    }

    @Override
    protected void initEvent() {
        iv_login_wxlogin.setOnClickListener(this);
        iv_login_check.setOnClickListener(this);

        boolean isLogin = PreferenceUtil.getBoolean(this, BaseConstants.IsLogin, false);
        if (isLogin) {
            // 从本地拿取用户数据,并放到内存中,方便使用
            getUserInfoByPreference();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            // 结束此界面
            getActivityStackManager().exitActivity(this);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_login_wxlogin:
                if (isAgree) {
                    if (CommonUtil.isWeixinAvilible(this)) {
                        showLoadingDialog();
                        loginPresenter.getCode();
                    } else {
                        ToastUtil.show("您的手机还未安装最新的微信,请安装后再登录!");
                    }
                }

                break;
            case R.id.iv_login_check:
                if (isAgree) {
                    isAgree = false;
                    iv_login_check.setImageResource(R.mipmap.selector_off);
                    iv_login_wxlogin.setImageResource(R.mipmap.icon_login_wechat_dis);
                } else {
                    isAgree = true;
                    iv_login_check.setImageResource(R.mipmap.selector_on);
                    iv_login_wxlogin.setImageResource(R.mipmap.icon_login_wechat_nor);

                }
                break;
        }
    }


    @Override
    public void goToHomeActivity(LoginInfoBean infoBean) {
        if (infoBean.getCode() == 0) {
            ToastUtil.show("登录失败" + infoBean.getInfo());
        } else {
            //  微信授权登录成功后,将用户信息保存在本地,下次登录就不需要授权登录了,直接判断IsLogin
            LoginInfoBean.RowsBean bean = infoBean.getRows();
            PreferenceUtil.putString(this, BaseConstants.F_ID, bean.getF_ID());
            PreferenceUtil.putString(this, BaseConstants.F_Code, bean.getF_Code());
            PreferenceUtil.putString(this, BaseConstants.F_Code1, bean.getF_Code1());
            PreferenceUtil.putString(this, BaseConstants.F_Code2, bean.getF_Code2());
            PreferenceUtil.putString(this, BaseConstants.F_Name, bean.getF_Name());
            PreferenceUtil.putInt(this, BaseConstants.F_CP, bean.getF_CP());
            PreferenceUtil.putString(this, BaseConstants.F_Img, bean.getF_Img());
            PreferenceUtil.putString(this, BaseConstants.F_VerCode, bean.getF_VerCode());
            PreferenceUtil.putString(this, BaseConstants.F_FxUrl, bean.getF_FxUrl());
            PreferenceUtil.putBoolean(this, BaseConstants.IsLogin, true);

            MyApplication.getInstance().setRowsBean(infoBean.getRows());
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void showFailed(int msg) {
        switch (msg) {
            case 1:
                LogUtil.e("微信登录失败_1");
                break;
            case 2:
                LogUtil.e("微信登录失败_2");
                break;
            case 3:
                LogUtil.e("娃娃乐登录失败");
                break;
        }
        ToastUtil.show("登录失败,请检查网络!");
        dismissLoadingDialog();
    }

    @Override
    public void showFinish() {
        dismissLoadingDialog();
    }
}
