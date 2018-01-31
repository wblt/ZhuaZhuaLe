package com.zhuazhuale.changsha.module.vital.ui;

import android.util.Log;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConnListener;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMGroupEventListener;
import com.tencent.imsdk.TIMGroupManager;
import com.tencent.imsdk.TIMGroupMemberInfo;
import com.tencent.imsdk.TIMGroupTipsElem;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.tencent.imsdk.TIMRefreshListener;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.TIMUserConfig;
import com.tencent.imsdk.TIMUserStatusListener;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.group.TIMGroupManagerExt;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;

import java.util.List;

import tencent.tls.platform.TLSErrInfo;
import tencent.tls.platform.TLSPwdLoginListener;
import tencent.tls.platform.TLSStrAccRegListener;
import tencent.tls.platform.TLSUserInfo;


/**
 * Created by Administrator on 2018/1/31 0031.
 */

public class IMChat {
    private String tag = getClass().getName();
    private String userName;
    private TIMMessage msg;
    private TIMTextElem elem;

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
                LogUtil.i(tag, "成功加入");
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
    public void getGroupMembers(String groupId, final TIMValueCallBack timValueCallBack) {
        //创建回调
        TIMValueCallBack<List<TIMGroupMemberInfo>> cb = new TIMValueCallBack<List<TIMGroupMemberInfo>>() {
            @Override
            public void onError(int code, String desc) {
                timValueCallBack.onError(code,desc);
            }

            @Override
            public void onSuccess(List<TIMGroupMemberInfo> infoList) {//参数返回群组成员信息
                timValueCallBack.onSuccess(infoList);
                for (TIMGroupMemberInfo info : infoList) {
                    LogUtil.e(tag, "user: " + info.getUser() +
                            "join time: " + info.getJoinTime() +
                            "role: " + info.getRole());
                }
            }
        };

        //获取群组成员信息
        TIMGroupManagerExt.getInstance().getGroupMembers(
                groupId, //群组Id
                cb);     //回调
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
                        Log.i(tag, "onForceOffline");
                    }

                    @Override
                    public void onUserSigExpired() {
                        //用户签名过期了，需要刷新userSig重新登录SDK
                        Log.i(tag, "onUserSigExpired");
                    }
                })
                //设置连接状态事件监听器
                .setConnectionListener(new TIMConnListener() {
                    @Override
                    public void onConnected() {
                        Log.i(tag, "onConnected");
                    }

                    @Override
                    public void onDisconnected(int code, String desc) {
                        Log.i(tag, "onDisconnected");
                    }

                    @Override
                    public void onWifiNeedAuth(String name) {
                        Log.i(tag, "onWifiNeedAuth");
                    }
                })
                //设置群组事件监听器
                .setGroupEventListener(new TIMGroupEventListener() {
                    @Override
                    public void onGroupTipsEvent(TIMGroupTipsElem elem) {
                        Log.i(tag, "onGroupTipsEvent, type: " + elem.getTipsType());

                        switch (elem.getTipsType()) {
                            case Join://加入群
                                LogUtil.e(tag, "有人加入了" + elem.getGroupId());
                                break;
                            case Quit://退出群
                                LogUtil.e(tag, "有人退出了" + elem.getGroupId());
                                break;
                        }
                    }
                })
                //设置会话刷新监听器
                .setRefreshListener(new TIMRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i(tag, "onRefresh");
                    }

                    @Override
                    public void onRefreshConversation(List<TIMConversation> conversations) {
                        Log.e(tag, "onRefreshConversation, Send_RecMsg_Utils size: " + conversations.size());
                    }
                });
        //设置消息监听器，收到新消息时，通过此监听器回调
        TIMManager.getInstance().addMessageListener(new TIMMessageListener() {
            @Override
            public boolean onNewMessages(List<TIMMessage> list) {
//                resultCallBack.onNewMessages(list,type);//新消息的回调，做了简单的实现；
                TIMMessage timMessage=new TIMMessage();
                timMessage.isSelf();
                return false;
            }//消息监听器
        });

        TIMManager.getInstance().setUserConfig(userConfig);


    }

    /**
     * 用户登录
     *
     * @param name
     * @param passWord
     */
    public void login(String name, String passWord) {
        userName = name;
        MyApplication.getInstance().getTlsHelper().TLSPwdLogin(name, passWord.getBytes(), pwdLoginListener);

    }

    // 登录监听
    private TLSPwdLoginListener pwdLoginListener = new TLSPwdLoginListener() {
        @Override
        public void OnPwdLoginSuccess(TLSUserInfo userInfo) {
          /* 登录成功了，在这里可以获取用户票据*/
            String usersig = MyApplication.getInstance().getTlsHelper().getUserSig(userInfo.identifier);
            ToastUtil.show(usersig);
            LogUtil.e(usersig);
            // identifier为用户名，userSig 为用户登录凭证
            TIMManager.getInstance().login(userName, usersig, new TIMCallBack() {
                @Override
                public void onError(int code, String desc) {
                    //错误码code和错误描述desc，可用于定位请求失败原因
                    //错误码code列表请参见错误码表
                    LogUtil.e("login 失败. code: " + code + " errmsg: " + desc);
                }

                @Override
                public void onSuccess() {
                    LogUtil.e("login 成功");
                }
            });

        }

        @Override
        public void OnPwdLoginReaskImgcodeSuccess(byte[] picData) {
          /* 请求刷新图片验证码成功，此时需要用picData 更新验证码图片，原先的验证码已经失效*/
        }

        @Override
        public void OnPwdLoginNeedImgcode(byte[] picData, TLSErrInfo errInfo) {
          /* 用户需要进行图片验证码的验证，需要把验证码图片展示给用户，并引导用户输入；如果验证码输入错误，仍然会到达此回调并更新图片验证码*/
        }

        @Override
        public void OnPwdLoginFail(TLSErrInfo errInfo) {
          /* 登录失败，比如说密码错误，用户帐号不存在等，通过errInfo.ErrCode, errInfo.Title, errInfo.Msg等可以得到更具体的错误信息*/
        }

        @Override
        public void OnPwdLoginTimeout(TLSErrInfo errInfo) {
          /* 密码登录过程中任意一步都可以到达这里，顾名思义，网络超时，可能是用户网络环境不稳定，一般让用户重试即可*/
        }
    };


    /**
     * 发送消息,群聊和单聊都可以用这个,具体要看TIMConversation的类型
     */
    public void sendMessage(TIMConversation conversation, String msgInfo) {
     /*   //获取群聊会话
        TIMConversation conversation = TIMManager.getInstance().getConversation(
                TIMConversationType.Group,      //会话类型：群组
                groupId);                       //群组Id
        conversation.sendMessage();

          //获取单聊会话
        //获取与用户 "sample_user_1" 的会话
        TIMConversation conversation = TIMManager.getInstance().getConversation(
                TIMConversationType.C2C,    //会话类型：单聊
                peer);                      //会话对方用户帐号//对方id

        */
        //构造一条消息
        if (msg == null) {
            msg = new TIMMessage();
        }
        //添加文本内容
        if (elem == null) {
            elem = new TIMTextElem();
        }
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
                Log.d(tag, "群聊消息发送失败  send message failed. code: " + code + " errmsg: " + desc);
            }

            @Override
            public void onSuccess(TIMMessage msg) {//发送消息成功
                Log.e(tag, "群聊消息发送成功  SendMsg ok");
            }
        });
    }
}
