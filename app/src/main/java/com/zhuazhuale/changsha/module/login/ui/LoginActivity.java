package com.zhuazhuale.changsha.module.login.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.login.presenter.LoginPresenter;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import butterknife.BindView;

public class LoginActivity extends AppBaseActivity implements View.OnClickListener,ILoginView {
    @BindView(R.id.tv_wxlogin)
    private TextView tv_wxlogin;

    private LoginPresenter loginPresenter;
    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void obtainData() {
        loginPresenter = new LoginPresenter(this);
    }

    @Override
    protected void initEvent() {
        tv_wxlogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_wxlogin:
                loginPresenter.getCode();
                break;
        }
    }


}
