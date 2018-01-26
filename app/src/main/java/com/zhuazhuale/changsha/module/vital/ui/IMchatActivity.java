package com.zhuazhuale.changsha.module.vital.ui;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMManager;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/26.
 */

public class IMchatActivity extends AppBaseActivity  {

    @BindView(R.id.et_chat_name)
    EditText et_chat_name;
    @BindView(R.id.et_chat_password)
    EditText et_chat_password;
    @BindView(R.id.tv_chat_login)
    TextView tv_chat_login;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_imchat);
    }

    @Override
    protected void initView() {
        tv_chat_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=et_chat_name.getText().toString().trim();
                String passWord=et_chat_password.getText().toString().trim();
                if (name.isEmpty()||passWord.isEmpty()){
                    ToastUtil.show("账号密码不能为空!");
                }else {
                    login(name,passWord);
                }
            }
        });
    }

    private void login(String name,String passWord) {
// identifier为用户名，userSig 为用户登录凭证
        TIMManager.getInstance().login(name, passWord, new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
                //错误码code和错误描述desc，可用于定位请求失败原因
                //错误码code列表请参见错误码表
                LogUtil.e( "login 失败. code: " + code + " errmsg: " + desc);
            }

            @Override
            public void onSuccess() {
                LogUtil.e( "login 成功");
            }
        });
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }

}
