package com.zhuazhuale.changsha.model.entity.eventbus;

/**
 * author:  dq
 * date:    2017/12/22
 * description: EventBus通信的事件类
 */

public class LoginEvent {
    private String code;

    public LoginEvent(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
