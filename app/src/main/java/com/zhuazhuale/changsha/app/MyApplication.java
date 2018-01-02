package com.zhuazhuale.changsha.app;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhuazhuale.changsha.model.net.RetrofitUtil;
import com.zhuazhuale.changsha.module.home.Bean.LoginInfoBean;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.CrashLogUtil;
import com.zhuazhuale.changsha.util.FrescoUtil;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import cn.jpush.android.api.JPushInterface;

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
    }

    public static IWXAPI api;

    private void regToWx() {
        // 通过WXAPIFactory工厂,获得IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, Constant.APPID, true);
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


}
