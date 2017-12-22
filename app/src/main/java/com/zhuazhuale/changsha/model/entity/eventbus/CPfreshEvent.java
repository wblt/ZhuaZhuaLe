package com.zhuazhuale.changsha.model.entity.eventbus;

/**
 * 游戏币刷新
 * Created by Administrator on 2017/12/23 0023.
 */

public class CPfreshEvent {
    private String CPisFresh;

    public CPfreshEvent(String CPisFresh) {
        this.CPisFresh = CPisFresh;
    }

    public String getCPisFresh() {
        return CPisFresh;
    }

    public void setCPisFresh(String CPisFresh) {
        this.CPisFresh = CPisFresh;
    }
}
