package com.zhuazhuale.changsha.module.home.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.DeviceGoodsBean;
import com.zhuazhuale.changsha.module.home.Bean.VersionBean;
import com.zhuazhuale.changsha.module.home.adapter.HomeFragmentPagerAdapter;
import com.zhuazhuale.changsha.module.home.presenter.HomePresenter2;
import com.zhuazhuale.changsha.module.vital.ui.PlayActivity;
import com.zhuazhuale.changsha.util.PermissionUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/29.
 */

public class HomeActivity2 extends AppBaseActivity implements IHomeView2{

    @BindView(R.id.tl_home_title)
    TabLayout tl_home_title;
    @BindView(R.id.vp_home_info)
    ViewPager vp_home_info;
    private HomeFragmentPagerAdapter pagerAdapter;
    private Intent intent;
    private ProgressBar mProgress;
    private HomePresenter2 presenter2;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_home2);
    }

    @Override
    protected void initView() {
        PermissionUtil.requestPerssions(this, 1, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        getTvToolbarRight().setBackgroundResource(R.mipmap.mine);
        getTvToolbarRight().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), MineActivity.class);
                startActivity(intent);
            }
        });
        getIvToolbarLeft().setImageResource(R.mipmap.shezhi);
        getIvToolbarLeft().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), SettingActivity.class);
                startActivity(intent);
            }
        });
        List<String> titleList = new ArrayList<>();
        titleList.add("全部");
        titleList.add("爆款");
        titleList.add("新款");
        titleList.add("特价");
        titleList.add("任务");
        pagerAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager(), titleList);
        vp_home_info.setAdapter(pagerAdapter);
        tl_home_title.setupWithViewPager(vp_home_info);
    }

    @Override
    protected void obtainData() {
        presenter2 = new HomePresenter2(this);
        String version = "";
        try {
            version = getVersionName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogUtil.e(" version " + version);
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

    }
    /**
     * 更新app
     *
     * @param versionBean
     */
    public void showChange(VersionBean versionBean) {
        if (versionBean.getCode() == 1) {
            if (versionBean.getRows().getVForce() == 1) {
                showDownloadDialog(versionBean.getRows().getVUrl());
            } else {
                showNoticeDialog(versionBean.getRows().getVUrl());
            }
        } else {
            LogUtil.e(versionBean.getInfo());
        }
    }

    @Override
    public void showFailed() {

    }

    @Override
    public void showFinish() {

    }


    /**
     * 打开设备
     *
     * @param rowsBean
     */
    public void open(DeviceGoodsBean.RowsBean rowsBean) {
        Intent intent = new Intent(getContext(), PlayActivity.class);
        intent.putExtra("DeviceGoods", rowsBean);
        startActivity(intent);
    }

    Dialog noticeDialog = null;

    private void showNoticeDialog(final String vUrl) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("软件版本更新");
//        builder.setMessage();
        builder.setPositiveButton("下载", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                showDownloadDialog(vUrl);
            }
        });
        builder.setNegativeButton("以后再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        noticeDialog = builder.create();
        noticeDialog.setCanceledOnTouchOutside(false);
        noticeDialog.setCancelable(false);
        noticeDialog.show();
    }

    private Dialog downloadDialog;

    private void showDownloadDialog(String vUrl) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("软件版本更新");

        final LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.progress, null);
        mProgress = (ProgressBar) v.findViewById(R.id.id_progress);

        builder.setView(v);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        downloadDialog = builder.create();
        downloadDialog.setCanceledOnTouchOutside(false);
        downloadDialog.setCancelable(false);
        downloadDialog.show();
        presenter2.downloadApk(vUrl);
    }

    /**
     * 安装apk
     *
     * @param
     */
    public void installApk(File apkfile) {
        downloadDialog.dismiss();
//        File apkfile = new File(body);
        if (!apkfile.exists()) {
            return;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
        startActivity(i);
        finish();
    }

    public void showProgress(float fraction) {
        mProgress.setProgress((int) (fraction * 100));
    }

}
