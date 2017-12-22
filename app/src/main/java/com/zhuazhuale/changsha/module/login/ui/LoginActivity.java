package com.zhuazhuale.changsha.module.login.ui;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.module.home.Bean.LoginInfoBean;
import com.zhuazhuale.changsha.module.home.ui.HomeActivity;
import com.zhuazhuale.changsha.module.login.presenter.LoginPresenter;
import com.zhuazhuale.changsha.util.EventBusUtil;
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
    private boolean isLogin = true;

    private String APPID = "wx6a6349cd0ccf0b09";
    private String secret = "5bd3f329e4df0d79c07181d4e2b9f072";

    private LoginPresenter loginPresenter;

    @Override
    protected void setContentLayout() {
        int color = getResourceColor(R.color.white);
        setStatusBarColor(color, 0);
        setContentView(R.layout.activity_login);
        getToolbar().setVisibility(View.GONE);

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void obtainData() {
        loginPresenter = new LoginPresenter(this);
        EventBusUtil.register(this);//订阅事件
    }

    //EventBus的事件接收，从事件中获取最新的收藏数量并更新界面展示
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(String event) {
        String code = event.toString();
        loginPresenter.initWX(code, APPID, secret);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅
        EventBusUtil.unregister(this);
    }

    @Override
    protected void initEvent() {
        iv_login_wxlogin.setOnClickListener(this);
        iv_login_check.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_login_wxlogin:
                if (isLogin) {
                    showLoadingDialog();
                    loginPresenter.getCode();
                }

                break;
            case R.id.iv_login_check:
                if (isLogin) {
                    isLogin = false;
                    iv_login_check.setImageResource(R.mipmap.selector_off);
                    iv_login_wxlogin.setImageResource(R.mipmap.icon_login_wechat_dis);
                } else {
                    isLogin = true;
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

    }

    @Override
    public void showFinish() {
        dismissLoadingDialog();
    }
}
