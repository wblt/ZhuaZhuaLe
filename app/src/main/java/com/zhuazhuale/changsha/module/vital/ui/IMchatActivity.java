package com.zhuazhuale.changsha.module.vital.ui;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMManager;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import butterknife.BindView;
import tencent.tls.platform.TLSErrInfo;
import tencent.tls.platform.TLSPwdLoginListener;
import tencent.tls.platform.TLSStrAccRegListener;
import tencent.tls.platform.TLSUserInfo;

/**
 * Created by Administrator on 2018/1/26.
 */

public class IMchatActivity extends AppBaseActivity {

    @BindView(R.id.et_chat_name)
    EditText et_chat_name;
    @BindView(R.id.et_chat_password)
    EditText et_chat_password;
    @BindView(R.id.tv_chat_login)
    TextView tv_chat_login;


    @BindView(R.id.et_chat_namez)
    EditText et_chat_namez;
    @BindView(R.id.et_chat_passwordz)
    EditText et_chat_passwordz;
    @BindView(R.id.tv_chat_loginz)
    TextView tv_chat_loginz;
    private String userName;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_imchat);
    }

    @Override
    protected void initView() {
        tv_chat_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_chat_name.getText().toString().trim();
                String passWord = et_chat_password.getText().toString().trim();
                if (name.isEmpty() || passWord.isEmpty()) {
                    ToastUtil.show("账号密码不能为空!");
                } else {
                    login(name, passWord);
                }
            }
        });
    }

    private void login(String name, String passWord) {
        userName = name;
        MyApplication.getInstance().getTlsHelper().TLSPwdLogin(name, passWord.getBytes(), pwdLoginListener);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {
        tv_chat_loginz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.show("我点击了");
                String name = et_chat_namez.getText().toString().trim();
                String passWord = et_chat_passwordz.getText().toString().trim();
                MyApplication.getInstance().getTlsHelper().TLSStrAccRegWithOA(name, passWord, new TLSStrAccRegListener() {
                    @Override
                    public void OnStrAccRegSuccess(TLSUserInfo tlsUserInfo) {
                        ToastUtil.show("注册成功");
                    }

                    @Override
                    public void OnStrAccRegFail(TLSErrInfo tlsErrInfo) {
                        ToastUtil.show("注册失败"+tlsErrInfo.ExtraMsg);

                    }

                    @Override
                    public void OnStrAccRegTimeout(TLSErrInfo tlsErrInfo) {
                        ToastUtil.show("注册超时"+tlsErrInfo.ExtraMsg);
                    }
                });
            }
        });
    }

    private TLSPwdLoginListener pwdLoginListener = new TLSPwdLoginListener() {
        @Override
        public void OnPwdLoginSuccess(TLSUserInfo userInfo) {
          /* 登录成功了，在这里可以获取用户票据*/
            String usersig = MyApplication.getInstance().getTlsHelper().getUserSig(userInfo.identifier);
            ToastUtil.show(usersig);
            LogUtil.e(usersig);
            // identifier为用户名，userSig 为用户登录凭证
            TIMManager.getInstance().login(userName, usersig, new TIMCallBack() {
                @Override
                public void onError(int code, String desc) {
                    //错误码code和错误描述desc，可用于定位请求失败原因
                    //错误码code列表请参见错误码表
                    LogUtil.e("login 失败. code: " + code + " errmsg: " + desc);
                }

                @Override
                public void onSuccess() {
                    LogUtil.e("login 成功");
                }
            });

        }

        @Override
        public void OnPwdLoginReaskImgcodeSuccess(byte[] picData) {
          /* 请求刷新图片验证码成功，此时需要用picData 更新验证码图片，原先的验证码已经失效*/
        }

        @Override
        public void OnPwdLoginNeedImgcode(byte[] picData, TLSErrInfo errInfo) {
          /* 用户需要进行图片验证码的验证，需要把验证码图片展示给用户，并引导用户输入；如果验证码输入错误，仍然会到达此回调并更新图片验证码*/
        }

        @Override
        public void OnPwdLoginFail(TLSErrInfo errInfo) {
            LogUtil.e(errInfo.Msg);
          /* 登录失败，比如说密码错误，用户帐号不存在等，通过errInfo.ErrCode, errInfo.Title, errInfo.Msg等可以得到更具体的错误信息*/
        }

        @Override
        public void OnPwdLoginTimeout(TLSErrInfo errInfo) {
          /* 密码登录过程中任意一步都可以到达这里，顾名思义，网络超时，可能是用户网络环境不稳定，一般让用户重试即可*/
        }
    };


}
