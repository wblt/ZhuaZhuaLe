package com.zhuazhuale.changsha.app;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConnListener;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMFriendshipSettings;
import com.tencent.imsdk.TIMGroupEventListener;
import com.tencent.imsdk.TIMGroupMemberInfo;
import com.tencent.imsdk.TIMGroupReceiveMessageOpt;
import com.tencent.imsdk.TIMGroupSettings;
import com.tencent.imsdk.TIMGroupTipsElem;
import com.tencent.imsdk.TIMLogLevel;
import com.tencent.imsdk.TIMLogListener;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMOfflinePushListener;
import com.tencent.imsdk.TIMOfflinePushNotification;
import com.tencent.imsdk.TIMRefreshListener;
import com.tencent.imsdk.TIMSNSChangeInfo;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.imsdk.TIMUser;
import com.tencent.imsdk.TIMUserConfig;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.imsdk.TIMUserStatusListener;
import com.tencent.imsdk.ext.group.TIMGroupAssistantListener;
import com.tencent.imsdk.ext.group.TIMGroupCacheInfo;
import com.tencent.imsdk.ext.group.TIMUserConfigGroupExt;
import com.tencent.imsdk.ext.message.TIMUserConfigMsgExt;
import com.tencent.imsdk.ext.sns.TIMFriendGroup;
import com.tencent.imsdk.ext.sns.TIMFriendshipProxyListener;
import com.tencent.imsdk.ext.sns.TIMUserConfigSnsExt;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.qalsdk.QALSDKManager;
import com.tencent.qalsdk.sdk.MsfSdkUtils;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.model.net.RetrofitUtil;
import com.zhuazhuale.changsha.module.home.Bean.LoginInfoBean;
import com.zhuazhuale.changsha.module.login.ui.LoginActivity;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.CrashLogUtil;
import com.zhuazhuale.changsha.util.FrescoUtil;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import tencent.tls.platform.TLSHelper;


/**
 * author:  ljy
 * date:    2017/9/13
 * description: 全局初始化操作
 * <p>
 * 开发框架的各个模块用法与介绍，请到我的博客 http://www.jianshu.com/u/2ebe42698573 进行查看
 */

public class MyApplication extends Application {
    private static MyApplication instance;
    private LoginInfoBean.RowsBean rowsBean;
    private TLSHelper tlsHelper;

    public TLSHelper getTlsHelper() {
        return tlsHelper;
    }

    public void setTlsHelper(TLSHelper tlsHelper) {
        this.tlsHelper = tlsHelper;
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public LoginInfoBean.RowsBean getRowsBean() {
        return rowsBean;
    }

    public void setRowsBean(LoginInfoBean.RowsBean rowsBean) {
        this.rowsBean = rowsBean;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //内存泄露检测
//        if (LeakCanary.isInAnalyzerProcess(this)) return;
//        refWatcher = LeakCanary.install(this);

        instance = this;
//必须调用初始化
        OkGo.getInstance().init(this);
        ToastUtil.init(this);//初始化吐司
        LogUtil.init(true);//初始化Log打印，true表示显示打印，false表示不显示打印
        CrashLogUtil.getInstance().init(this);//初始化崩溃输出
        RetrofitUtil.init(this);//初始化retrofit
        FrescoUtil.getInstance().initializeFresco(this);//初始化Fresco
        //        EventBusUtil.openIndex();//开启Index加速
        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        // 初始化Bugly
        //腾讯bugly检测工具
        CrashReport.initCrashReport(getApplicationContext(), "060b9179aa", true);
        //极光注册,初始化
        JPushInterface.setDebugMode(true);//如果时正式版就改成false
        JPushInterface.init(this);
        regToWx();

        // 务必检查IMSDK已做以下初始化
        QALSDKManager.getInstance().setEnv(0);
        QALSDKManager.getInstance().init(getApplicationContext(), Constant.IMSDK_APPID);
        // 初始化TLSSDK
        tlsHelper = TLSHelper.getInstance().init(getApplicationContext(), Constant.IMSDK_APPID);
        regToIMSDK();



    }

    /**
     * 初始化腾讯IMSDK
     * 即时通讯
     */
    private void regToIMSDK() {
        //初始化SDK基本配置
        TIMSdkConfig config = new TIMSdkConfig(Constant.IMSDK_APPID)
                .enableCrashReport(false)
                .enableLogPrint(true)
                .setLogLevel(TIMLogLevel.DEBUG)
                .setLogPath(Environment.getExternalStorageDirectory().getPath() + "/justfortest/");

//初始化SDK
        TIMManager.getInstance().init(getApplicationContext(), config);
        setUserConfig();
    }

    public static IWXAPI api;

    private void regToWx() {
        // 通过WXAPIFactory工厂,获得IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, Constant.APPID, false);
        // 将应用的appid注册到微信
        api.registerApp(Constant.APPID);


    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 内存泄露检测
     */
//    private RefWatcher refWatcher;
//
//    public static RefWatcher getRefWatcher(Context context) {
//        MyApplication application = (MyApplication) context.getApplicationContext();
//        return application.refWatcher;
//    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * 设置当前用户的用户配置，登录前设置
     *
     * @param userConfig 用户配置
     */
    private String tag = "111";

    public void setUserConfig() {
        //基本用户配置
        TIMUserConfig userConfig = new TIMUserConfig()
                //设置群组资料拉取字段
//                .setGroupSettings(initGroupSettings())
                //设置资料关系链拉取字段
//                .setFriendshipSettings(initFriendshipSettings())
                //设置用户状态变更事件监听器
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
                        Log.i(tag, "onRefreshConversation, conversation size: " + conversations.size());
                    }
                });

        //消息扩展用户配置
        userConfig = new TIMUserConfigMsgExt(userConfig)
                //禁用消息存储
                .enableStorage(false)
                //开启消息已读回执
                .enableReadReceipt(true);

        //资料关系链扩展用户配置
        userConfig = new TIMUserConfigSnsExt(userConfig)
                //开启资料关系链本地存储
                .enableFriendshipStorage(true)
                //设置关系链变更事件监听器
                .setFriendshipProxyListener(new TIMFriendshipProxyListener() {
                    @Override
                    public void OnAddFriends(List<TIMUserProfile> users) {
                        Log.i(tag, "OnAddFriends");
                    }

                    @Override
                    public void OnDelFriends(List<String> identifiers) {
                        Log.i(tag, "OnDelFriends");
                    }

                    @Override
                    public void OnFriendProfileUpdate(List<TIMUserProfile> profiles) {
                        Log.i(tag, "OnFriendProfileUpdate");
                    }

                    @Override
                    public void OnAddFriendReqs(List<TIMSNSChangeInfo> reqs) {
                        Log.i(tag, "OnAddFriendReqs");
                    }

                });

        //群组管理扩展用户配置
        userConfig = new TIMUserConfigGroupExt(userConfig)
                //开启群组资料本地存储
                .enableGroupStorage(true)
                //设置群组资料变更事件监听器
                .setGroupAssistantListener(new TIMGroupAssistantListener() {
                    @Override
                    public void onMemberJoin(String groupId, List<TIMGroupMemberInfo> memberInfos) {
                        Log.i(tag, "onMemberJoin");
                    }

                    @Override
                    public void onMemberQuit(String groupId, List<String> members) {
                        Log.i(tag, "onMemberQuit");
                    }

                    @Override
                    public void onMemberUpdate(String groupId, List<TIMGroupMemberInfo> memberInfos) {
                        Log.i(tag, "onMemberUpdate");
                    }

                    @Override
                    public void onGroupAdd(TIMGroupCacheInfo groupCacheInfo) {
                        Log.i(tag, "onGroupAdd");
                    }

                    @Override
                    public void onGroupDelete(String groupId) {
                        Log.i(tag, "onGroupDelete");
                    }

                    @Override
                    public void onGroupUpdate(TIMGroupCacheInfo groupCacheInfo) {
                        Log.i(tag, "onGroupUpdate");
                    }
                });

        //将用户配置与通讯管理器进行绑定
        TIMManager.getInstance().setUserConfig(userConfig);
    }




}
