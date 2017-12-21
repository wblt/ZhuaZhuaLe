package com.zhuazhuale.changsha.module.home.ui;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import butterknife.BindView;

/**
 * 问题反馈
 * Created by Administrator on 2017/12/14.
 */

public class FeedBackActivity extends AppBaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_feedback)
    TextView tv_feedback;
    private String qqUrl;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_feedback);
    }

    @Override
    protected void initView() {
        qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin=1987231609";
//        String url="mqqwpa://im/chat?chat_type=wpa&uin=501863587";
//        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {
        tv_feedback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_feedback:
                boolean aBoolean = isSpecialApplInstalled(FeedBackActivity.this, "com.tencent.mobileqq");
                if (aBoolean) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl)));
                } else {
                    ToastUtil.show("您的手机还未安装最新的QQ,请安装后再联系客服!");
                }

                break;
        }
    }

    /**
     * 判断手机设备是否安装指定包名的apk应用程序
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isSpecialApplInstalled(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        try {
            packageManager.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
