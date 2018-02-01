package com.zhuazhuale.changsha.module.vital.bean;

import com.tencent.imcore.Msg;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/1.
 */

public class MsgBean implements Serializable{
    private String MsgId;
    private Msg msgInfo;
    private String Sender;

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }

    public Msg getMsgInfo() {
        return msgInfo;
    }

    public void setMsgInfo(Msg msgInfo) {
        this.msgInfo = msgInfo;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }
}
