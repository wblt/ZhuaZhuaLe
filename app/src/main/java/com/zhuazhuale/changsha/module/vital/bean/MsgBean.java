package com.zhuazhuale.changsha.module.vital.bean;



import com.tencent.imsdk.TIMElem;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/1.
 */

public class MsgBean implements Serializable{
    private String grpSendName;
    private TIMElem context;
    private int  type;

    public String getGrpSendName() {
        return grpSendName;
    }

    public void setGrpSendName(String grpSendName) {
        this.grpSendName = grpSendName;
    }

    public TIMElem getContext() {
        return context;
    }

    public void setContext(TIMElem context) {
        this.context = context;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
