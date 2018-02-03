package com.zhuazhuale.changsha.module.vital.ui;

import android.util.Log;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConnListener;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMCustomElem;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMFriendshipManager;
import com.tencent.imsdk.TIMGroupEventListener;
import com.tencent.imsdk.TIMGroupManager;
import com.tencent.imsdk.TIMGroupMemberInfo;
import com.tencent.imsdk.TIMGroupSystemElem;
import com.tencent.imsdk.TIMGroupSystemElemType;
import com.tencent.imsdk.TIMGroupTipsElem;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.tencent.imsdk.TIMRefreshListener;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.TIMUserConfig;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.imsdk.TIMUserStatusListener;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.group.TIMGroupManagerExt;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.module.vital.bean.MsgBean;
import com.zhuazhuale.changsha.util.EventBusUtil;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;

import java.util.ArrayList;
import java.util.List;

import tencent.tls.platform.TLSErrInfo;
import tencent.tls.platform.TLSPwdLoginListener;
import tencent.tls.platform.TLSStrAccRegListener;
import tencent.tls.platform.TLSUserInfo;


/**
 * 腾讯云通讯的工具类
 * Created by Administrator on 2018/1/31 0031.
 */

public class IMChat {
    private String tag = getClass().getName();
    private String groupIds = "";

    public static IMChat getInstance() {
        return IMChat.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final IMChat instance = new IMChat();

    }

    /**
     * 加入群
     *
     * @param groupID
     */
    public void joinGroup(String groupID) {
        TIMGroupManager.getInstance().applyJoinGroup(groupID, "somereason", new TIMCallBack() {
            @java.lang.Override
            public void onError(int code, String desc) {
                //接口返回了错误码code和错误描述desc，可用于原因
                //错误码code列表请参见错误码表
                LogUtil.e(tag, "disconnected");
            }

            @java.lang.Override
            public void onSuccess() {
                LogUtil.e(tag, "成功加入");
            }
        });
    }

    /**
     * 退出群组
     *
     * @param groupId
     */
    public void quitGroup(String groupId) {
        //退出群组
        TIMGroupManager.getInstance().quitGroup(groupId, new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
                LogUtil.e(tag, code + "  退群失败  " + desc);
                //错误码code和错误描述desc，可用于定位请求失败原因
                //错误码code含义请参见错误码表
            }

            @Override
            public void onSuccess() {
                LogUtil.e(tag, "退出群成功");
            }
        });

    }

    /**
     * 获取群成员列表
     *
     * @param groupId
     */
    public void getGroupMembers(String groupId) {
        groupIds = groupId;
        //创建回调
        TIMValueCallBack<List<TIMGroupMemberInfo>> cb = new TIMValueCallBack<List<TIMGroupMemberInfo>>() {
            @Override
            public void onError(int code, String desc) {
                LogUtil.e("获取群成员失败" + code + "  / " + desc);
            }

            @Override
            public void onSuccess(List<TIMGroupMemberInfo> infoList) {//参数返回群组成员信息
                List<String> strings = new ArrayList<>();
                for (TIMGroupMemberInfo info : infoList) {
                    LogUtil.e(tag, "user: " + info.getUser() +
                            "join time: " + info.getJoinTime() +
                            "role: " + info.getRole());
                    strings.add(info.getUser());
                }
                getUsersProfile(strings);

            }
        };

        //获取群组成员信息
        TIMGroupManagerExt.getInstance().getGroupMembers(
                groupId, //群组Id
                cb);     //回调
    }

    /**
     * 获取用户的资料
     *
     * @param userlist
     */
    public void getUsersProfile(List<String> userlist) {
        //待获取用户资料的用户列表
        List<String> users = new ArrayList<String>();
        users.add("sample_user_1");
        users.add("sample_user_2");

        //获取用户资料
        TIMFriendshipManager.getInstance().getUsersProfile(userlist, new TIMValueCallBack<List<TIMUserProfile>>() {
            @Override
            public void onError(int code, String desc) {
                //错误码code和错误描述desc，可用于定位请求失败原因
                //错误码code列表请参见错误码表
                LogUtil.e(tag, "getUsersProfile failed: " + code + " desc");
            }

            @Override
            public void onSuccess(List<TIMUserProfile> result) {
                Log.e(tag, "getUsersProfile succ");
                for (TIMUserProfile res : result) {
                    LogUtil.e(tag, "identifier: " + res.getIdentifier() + " nickName: " + res.getNickName()
                            + " remark: " + res.getFaceUrl());
                }
                EventBusUtil.postEvent(result);
            }
        });
    }

    /**
     * 监听群加入和退出的群事件
     */
    public void changeGroup() {
        TIMUserConfig userConfig = new TIMUserConfig()
                .setUserStatusListener(new TIMUserStatusListener() {
                    @Override
                    public void onForceOffline() {
                        //被其他终端踢下线
                        LogUtil.e("onForceOffline");
                        ToastUtil.show("您的账号在别处登录!");
                    }

                    @Override
                    public void onUserSigExpired() {
                        //用户签名过期了，需要刷新userSig重新登录SDK
                        LogUtil.e("onUserSigExpired");
                    }
                })
                //设置连接状态事件监听器
                .setConnectionListener(new TIMConnListener() {
                    @Override
                    public void onConnected() {
                        LogUtil.e("onConnected");
                    }

                    @Override
                    public void onDisconnected(int code, String desc) {
                        LogUtil.e("onDisconnected");
                    }

                    @Override
                    public void onWifiNeedAuth(String name) {
                        LogUtil.e("onWifiNeedAuth");
                    }
                })
                //设置群组事件监听器
                .setGroupEventListener(new TIMGroupEventListener() {
                    @Override
                    public void onGroupTipsEvent(TIMGroupTipsElem elem) {
                        LogUtil.e("onGroupTipsEvent, type: " + elem.getTipsType());
                     /*   elem.getOpUserInfo().getNickName();
                        elem.getOpUserInfo().getFaceUrl();*/
                        switch (elem.getTipsType()) {
                            case Join://加入群
                                getGroupMembers(groupIds);
                                LogUtil.e("有人加入了" + elem.getOpUserInfo().getNickName() + '/' + elem.getOpUserInfo().getFaceUrl());
                                break;
                            case Quit://退出群
                                getGroupMembers(groupIds);
                                LogUtil.e("有人退出了" + elem.getOpUserInfo().getNickName() + '/' + elem.getOpUserInfo().getFaceUrl());
                                break;
                        }
                    }
                })
                //设置会话刷新监听器
                .setRefreshListener(new TIMRefreshListener() {
                    @Override
                    public void onRefresh() {
                        LogUtil.e("onRefresh");
                    }

                    @Override
                    public void onRefreshConversation(List<TIMConversation> conversations) {

                        LogUtil.e("onRefreshConversation, Send_RecMsg_Utils size: " + conversations.size());
                    }
                });
        //设置消息监听器，收到新消息时，通过此监听器回调
        TIMManager.getInstance().addMessageListener(new TIMMessageListener() {
            @Override
            public boolean onNewMessages(List<TIMMessage> list) {
                for (TIMMessage message : list) {
                    String groupId = message.getConversation().getPeer();
                    TIMElem element = message.getElement(0);
                    switch (element.getType()) {
                        case GroupSystem: {
                            TIMGroupSystemElemType systemElemType = ((TIMGroupSystemElem) element).getSubtype();
                            switch (systemElemType) {
                                case TIM_GROUP_SYSTEM_DELETE_GROUP_TYPE: {
                                    groupId = ((TIMGroupSystemElem) element).getGroupId();
                                  /*  if (mListener != null)
                                        mListener.onIMGroupDelete(groupId);
                                    if (mChatGroup.equals(groupId)) {
                                        mChatGroup = "";
                                    }
                                    if (mIssueGroup.equals(groupId)) {
                                        mIssueGroup = "";
                                    }*/
                                    return true;
                                }
                            }
                            break;
                        }//case GroupSystem
                        case Text: {
                            TIMTextElem textMsg = (TIMTextElem) element;
                            if (groupIds.equals(groupId)) {
                                TIMUserProfile sendUser = message.getSenderProfile();
                                String name = "";
                                if (sendUser != null) {
                                    name = sendUser.getNickName();
                                    LogUtil.e("sendUser  " + sendUser.getNickName());
                                } else {
                                    name = message.getSender();
                                    LogUtil.e("getSender  " + message.getSender());
                                }
                                MsgBean msgBean = new MsgBean();
                                msgBean.setType(2);
                                msgBean.setContext(textMsg.getText());
                                msgBean.setGrpSendName(name);
                                EventBusUtil.postEvent(msgBean);
                                LogUtil.e(textMsg.getText());
                            }

                            return true;
                        }
                        case Custom: {
                            byte[] userData = ((TIMCustomElem) element).getData();
                            if (userData == null || userData.length == 0) {
                                return true;
                            }
                           /* if (mListener != null && mIssueGroup.equals(groupId)) {
                                mListener.onRecvIssueMessage(userData);
                            }*/
                            return true;
                        }
                    }
                }
                return false;
            }
        });

        TIMManager.getInstance().setUserConfig(userConfig);

    }

    /**
     * 用户登录
     *
     * @param name
     * @param passWord
     */
    public void login(final String name, final String passWord) {
        final String na = name;
        String p = passWord;
        MyApplication.getInstance().getTlsHelper().TLSPwdLogin(na, p.getBytes(), new TLSPwdLoginListener() {
            @Override
            public void OnPwdLoginSuccess(TLSUserInfo userInfo) {
          /* 登录成功了，在这里可以获取用户票据*/
                String usersig = MyApplication.getInstance().getTlsHelper().getUserSig(userInfo.identifier);
                LogUtil.e("TLS登录成功!");
                // identifier为用户名，userSig 为用户登录凭证
                TIMManager.getInstance().login(na, usersig, new TIMCallBack() {
                    @Override
                    public void onError(int code, String desc) {
                        //错误码code和错误描述desc，可用于定位请求失败原因
                        //错误码code列表请参见错误码表
                        LogUtil.e("login 失败. code: " + code + " errmsg: " + desc);
                    }

                    @Override
                    public void onSuccess() {
                        LogUtil.e("login 成功");
                        setNickName();
                    }
                });

            }

            @Override
            public void OnPwdLoginReaskImgcodeSuccess(byte[] picData) {
          /* 请求刷新图片验证码成功，此时需要用picData 更新验证码图片，原先的验证码已经失效*/
            }

            @Override
            public void OnPwdLoginNeedImgcode(byte[] picData, TLSErrInfo errInfo) {
                LogUtil.e(errInfo.Msg);
          /* 用户需要进行图片验证码的验证，需要把验证码图片展示给用户，并引导用户输入；如果验证码输入错误，仍然会到达此回调并更新图片验证码*/
            }

            @Override
            public void OnPwdLoginFail(TLSErrInfo errInfo) {
                LogUtil.e(errInfo.Msg);
                ZhuCeAccount(na, passWord);
          /* 登录失败，比如说密码错误，用户帐号不存在等，通过errInfo.ErrCode, errInfo.Title, errInfo.Msg等可以得到更具体的错误信息*/
            }

            @Override
            public void OnPwdLoginTimeout(TLSErrInfo errInfo) {
                LogUtil.e(errInfo.Msg);
          /* 密码登录过程中任意一步都可以到达这里，顾名思义，网络超时，可能是用户网络环境不稳定，一般让用户重试即可*/
            }
        });

    }

    /**
     * 设置头像和昵称
     */
    public void setNickName() {
        //初始化参数，修改昵称为“cat”
        TIMFriendshipManager.ModifyUserProfileParam param = new TIMFriendshipManager.ModifyUserProfileParam();
//        String path = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517584490096&di=cd3b7dd058b791fba268c078a1033490&imgtype=0&src=http%3A%2F%2Fwww.uuuu.cc%2Fuploads%2Fallimg%2Fc160108%2F145222J62E520-23NR.jpg";
        param.setNickname(MyApplication.getInstance().getRowsBean().getF_Name());
        param.setFaceUrl(MyApplication.getInstance().getRowsBean().getF_Img());
//        param.setFaceUrl(path);

        TIMFriendshipManager.getInstance().modifyProfile(param, new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
                //错误码code和错误描述desc，可用于定位请求失败原因
                //错误码code列表请参见错误码表
                LogUtil.e(tag, "设置头像和昵称失败:  modifyProfile failed: " + code + " desc" + desc);
            }

            @Override
            public void onSuccess() {
                LogUtil.e(tag, "设置头像和昵称成功  :modifyProfile succ");
            }
        });
    }


    /**
     * 注册账号
     * name前面必须带个英文字母,passWord必须超过8位数
     *
     * @param name
     * @param passWord
     */
    public void ZhuCeAccount(final String name, final String passWord) {
        LogUtil.e(name + "  // " + passWord);
        int result = MyApplication.getInstance().getTlsHelper().TLSStrAccReg(name, passWord, new TLSStrAccRegListener() {
            @Override
            public void OnStrAccRegSuccess(TLSUserInfo tlsUserInfo) {
                ToastUtil.show("注册成功");
                LogUtil.e("注册成功" + tlsUserInfo.toString());
                login(name, passWord);
            }

            @Override
            public void OnStrAccRegFail(TLSErrInfo tlsErrInfo) {
                ToastUtil.show("注册失败" + tlsErrInfo.ExtraMsg);
                LogUtil.e("注册失败");

            }

            @Override
            public void OnStrAccRegTimeout(TLSErrInfo tlsErrInfo) {
                ToastUtil.show("注册超时" + tlsErrInfo.ExtraMsg);
                LogUtil.e("注册超时");

            }
        });
        if (result == TLSErrInfo.INPUT_INVALID) {
            LogUtil.e("账号格式不对");
        }
    }


    /**
     * 发送消息,群聊和单聊都可以用这个,具体要看TIMConversation的类型
     */
    public void sendMessage(String groupId, final String msgInfo) {
     /*   //获取群聊会话
        TIMConversation conversation = TIMManager.getInstance().getConversation(
                TIMConversationType.Group,      //会话类型：群组
                groupId);                       //群组Id

          //获取单聊会话
        //获取与用户 "sample_user_1" 的会话
        TIMConversation conversation = TIMManager.getInstance().getConversation(
                TIMConversationType.C2C,    //会话类型：单聊
                peer);                      //会话对方用户帐号//对方id

        */
        TIMConversation conversation = TIMManager.getInstance().getConversation(
                TIMConversationType.Group,      //会话类型：群组
                groupId);                       //群组Id

        //构造一条消息
        TIMMessage msg = new TIMMessage();
        //添加文本内容
        TIMTextElem elem = new TIMTextElem();
        elem.setText(msgInfo);

        //将elem添加到消息
        if (msg.addElement(elem) != 0) {
            Log.e(tag, "消息添加失败!");
            return;
        }
        //发送消息
        conversation.sendMessage(msg, new TIMValueCallBack<TIMMessage>() {//发送消息回调
            @Override
            public void onError(int code, String desc) {//发送消息失败
                //错误码code和错误描述desc，可用于定位请求失败原因
                //错误码code含义请参见错误码表
                Log.e(tag, "消息发送失败  send message failed. code: " + code + " errmsg: " + desc);
            }

            @Override
            public void onSuccess(TIMMessage timMessage) {//发送消息成功
                Log.e(tag, "消息发送成功" + timMessage.getMsg());

                TIMUserProfile sendUser = timMessage.getSenderProfile();
                String name;
                if (sendUser != null) {
                    name = sendUser.getNickName();
                } else {
                    name = timMessage.getSender();
                }
                MsgBean msgBean = new MsgBean();
                msgBean.setType(1);
                msgBean.setContext(msgInfo);
                msgBean.setGrpSendName(name);
                EventBusUtil.postEvent(msgBean);
            }


//            }

        });
    }
}
