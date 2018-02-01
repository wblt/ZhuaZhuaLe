package com.zhuazhuale.changsha.module.vital.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/2/1.
 */

public class MsgInfoListBean implements Serializable {
    private List<MsgBean> msgBeen;

    public List<MsgBean> getMsgBeen() {
        return msgBeen;
    }

    public void setMsgBeen(List<MsgBean> msgBeen) {
        this.msgBeen = msgBeen;
    }
}
