package com.zhuazhuale.changsha.module.home.Bean;

/**
 * Created by Administrator on 2017/12/19.
 */

public class LiYouBean {
    private boolean isClick;
    private String liYou;

    public LiYouBean(boolean isClick, String liYou) {
        this.isClick = isClick;
        this.liYou = liYou;
    }

    public String getLiYou() {
        return liYou;
    }

    public void setLiYou(String liYou) {
        this.liYou = liYou;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }
}
