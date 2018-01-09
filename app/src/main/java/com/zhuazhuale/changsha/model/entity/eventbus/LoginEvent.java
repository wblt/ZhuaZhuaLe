package com.zhuazhuale.changsha.model.entity.eventbus;

/**
 * author:  dq
 * date:    2017/12/22
 * description: EventBus通信的事件类
 */

public class LoginEvent {
    private String code;
    private boolean isLogin;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public LoginEvent(String code, boolean isLogin) {
        this.code = code;
        this.isLogin = isLogin;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
