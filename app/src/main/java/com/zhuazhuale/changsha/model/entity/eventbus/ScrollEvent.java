package com.zhuazhuale.changsha.model.entity.eventbus;

/**
 * Created by Administrator on 2018/1/31.
 */

public class ScrollEvent {
    private String scroll;

    public ScrollEvent(String scroll) {
        this.scroll = scroll;
    }

    public String getScroll() {
        return scroll;
    }

    public void setScroll(String scroll) {
        this.scroll = scroll;
    }
}
