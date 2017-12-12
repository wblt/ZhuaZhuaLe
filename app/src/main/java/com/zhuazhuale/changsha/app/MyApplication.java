package com.zhuazhuale.changsha.app;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.zhuazhuale.changsha.model.net.RetrofitUtil;
import com.zhuazhuale.changsha.util.CrashLogUtil;
import com.zhuazhuale.changsha.util.FrescoUtil;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;

/**
 * author:  ljy
 * date:    2017/9/13
 * description: 全局初始化操作
 *
 * 开发框架的各个模块用法与介绍，请到我的博客 http://www.jianshu.com/u/2ebe42698573 进行查看
 *
 */

public class MyApplication extends Application {
    private static MyApplication instance;
    public static MyApplication getInstance() {
        return instance;
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
