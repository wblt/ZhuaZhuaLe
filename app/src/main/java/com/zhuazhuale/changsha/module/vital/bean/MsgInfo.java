package com.zhuazhuale.changsha.module.vital.bean;

/**
 * Created by Administrator on 2018/2/2.
 */

public class MsgInfo {

    /**
     * userAction : 2
     * nickName : A已成过去式
     * userId : zhuazhuale277191
     * headPic :
     * msg :
     */

    private int userAction;
    private String nickName;
    private String userId;
    private String headPic;
    private String msg;

    public int getUserAction() {
        return userAction;
    }

    public void setUserAction(int userAction) {
        this.userAction = userAction;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
