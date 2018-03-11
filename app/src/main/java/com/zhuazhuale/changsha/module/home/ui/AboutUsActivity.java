package com.zhuazhuale.changsha.module.home.ui;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import butterknife.BindView;

/**
 * 关于我们
 * Created by Administrator on 2017/12/14.
 */

public class AboutUsActivity extends AppBaseActivity implements View.OnClickListener {

    @BindView(R.id.ic_aboutus_gwpf)
    View ic_aboutus_gwpf;
    @BindView(R.id.ic_aboutus_yhxy)
    View ic_aboutus_yhxy;
    @BindView(R.id.tv_about)
    TextView tv_about;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_aboutus);
    }

    @Override
    protected void initView() {
        TextView tv_gwpf = (TextView) ic_aboutus_gwpf.findViewById(R.id.tv_list_n);
        TextView tv_yhxy = (TextView) ic_aboutus_yhxy.findViewById(R.id.tv_list_n);
        tv_gwpf.setText("给我评分");
        tv_yhxy.setText("用户协议");
    }

    @Override
    protected void obtainData() {
        String v = "";
        try {
            v = getVersionName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tv_about.setText(v + "版本");
    }

    private String getVersionName() throws Exception {
        // 获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        String version = packInfo.versionName;
        return version;
    }

    @Override
    protected void initEvent() {
        ic_aboutus_gwpf.setOnClickListener(this);
        ic_aboutus_yhxy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ic_aboutus_gwpf:
                break;
            case R.id.ic_aboutus_yhxy:
                Intent intent = new Intent(getContext(), XieYiActivity.class);
                intent.putExtra("url","http://zhuazhuale.com/rules.html");
                startActivity(intent);
                break;
        }
    }
}
