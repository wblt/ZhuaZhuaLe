package com.zhuazhuale.changsha.module.vital.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/2/1.
 */

public class MsgInfoListBean implements Serializable {
    private List<MsgBean> msgBeen;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<MsgBean> getMsgBeen() {
        return msgBeen;
    }

    public void setMsgBeen(List<MsgBean> msgBeen) {
        this.msgBeen = msgBeen;
    }
}
