package com.zhuazhuale.changsha.module.home.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.app.constant.BaseConstants;
import com.zhuazhuale.changsha.model.entity.eventbus.CPfreshEvent;
import com.zhuazhuale.changsha.module.home.Bean.BaseTypeDataBean;
import com.zhuazhuale.changsha.module.home.Bean.DeviceGoodsBean;
import com.zhuazhuale.changsha.module.home.Bean.VersionBean;
import com.zhuazhuale.changsha.module.home.adapter.HomeFragmentPagerAdapter;
import com.zhuazhuale.changsha.module.home.presenter.HomePresenter2;
import com.zhuazhuale.changsha.module.vital.ui.IMChat;
import com.zhuazhuale.changsha.module.vital.ui.PlayActivity;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.EventBusUtil;
import com.zhuazhuale.changsha.util.PermissionUtil;
import com.zhuazhuale.changsha.util.PreferenceUtil;
import com.zhuazhuale.changsha.util.ScreenRecorder;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;
import com.zhuazhuale.changsha.view.widget.MaterialDialog;
import com.zhuazhuale.changsha.view.widget.loadlayout.OnLoadListener;
import com.zhuazhuale.changsha.view.widget.loadlayout.State;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/29.
 */

public class HomeActivity2 extends AppBaseActivity implements IHomeView2 {

    @BindView(R.id.tl_home_title)
    TabLayout tl_home_title;
    @BindView(R.id.vp_home_info)
    ViewPager vp_home_info;
    @BindView(R.id.ll_home_shezhi)
    LinearLayout ll_home_shezhi;
    @BindView(R.id.ll_home_mine)
    LinearLayout ll_home_mine;

    private HomeFragmentPagerAdapter pagerAdapter;
    private Intent intent;
    private ProgressBar mProgress;
    private HomePresenter2 presenter2;
    private String version;
    public static HomeActivity2 instance = null;
    private long time1;
    private MediaProjectionManager mMediaProjectionManager;
    private MaterialDialog mDialog;
    private boolean is_lpqx;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_home2);
        instance = this;
        is_lpqx = PreferenceUtil.getBoolean(getContext(), BaseConstants.Is_lpqx, false);
        if (!is_lpqx) {
            luZhi();
        }
        PermissionUtil.getExternalStoragePermissions(this, 110);
       /* boolean b = PermissionUtil.deniedRequestAgain(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (b) {
            ToastUtil.show("请打开手机的存储权限！");
            gotoMiuiPermission();
        }*/
    }

    /**
     * 跳转到miui的权限管理页面
     */
    private void gotoMiuiPermission() {
        Intent i = new Intent("miui.intent.action.APP_PERM_EDITOR");
        ComponentName componentName = new ComponentName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        i.setComponent(componentName);
        i.putExtra("extra_pkgname", getPackageName());
        try {
            startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        MediaProjection mediaProjection = mMediaProjectionManager.getMediaProjection(resultCode, data);
        if (mediaProjection == null) {
            Log.e("@@", "media projection is null");
            mDialog.setTitle("城市抓抓乐温馨提示");
            mDialog.setMessage("取消录制视频会影响申诉功能，是否重新开启录制视频！");

            mDialog.setPositiveButton("开启", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                    luZhi();
                }
            });
            mDialog.setNegativeButton("取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                }
            });
            mDialog.show();
        } else {
            PreferenceUtil.putBoolean(getContext(), BaseConstants.Is_lpqx, true);

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void luZhi() {
        //录屏
        boolean is_lp = PreferenceUtil.getBoolean(getContext(), BaseConstants.Is_lp, true);
        if (is_lp) {
            mMediaProjectionManager = (MediaProjectionManager) getSystemService(MEDIA_PROJECTION_SERVICE);
            Intent captureIntent = mMediaProjectionManager.createScreenCaptureIntent();
            startActivityForResult(captureIntent, 1);
            mDialog = new MaterialDialog(this);
        }

    }

    @Override
    protected void initView() {
        getToolbar().setVisibility(View.GONE);
        PermissionUtil.requestPerssions(this, 1, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        getTvToolbarRight().setBackgroundResource(R.mipmap.mine);
//        getTvToolbarRight().setBackgroundResource(R.mipmap.grzx);
        ll_home_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), MineActivity.class);
                startActivity(intent);
            }
        });
//        getIvToolbarLeft().setImageResource(R.mipmap.shezhi);
       /* getIvToolbarLeft().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), SettingActivity.class);
                startActivity(intent);
            }
        });*/
        ll_home_shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), SettingActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void obtainData() {
        showLoadingDialog("");
        presenter2 = new HomePresenter2(this);

        version = "";
        try {
            version = getVersionName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogUtil.e(" version " + version);
        presenter2.initBaseTypeData();
        presenter2.initVersion(version);
        getLoadLayout().setOnLoadListener(new OnLoadListener() {
            @Override
            public void onLoad() {
                presenter2.initBaseTypeData();
                presenter2.initVersion(version);
            }
        });
        EventBusUtil.register(this);//订阅事件
    }

    //EventBus的事件接收，从事件中获取最新的收藏数量并更新界面展示
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(String event) {
        if ("TXLOFIN".equals(event)) {
            dismissLoadingDialog();
        }
        if ("onForceOffline".equals(event)) {
            showLoadingDialog("您的账号在别处登录");
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Date date = new Date();
        long time2 = date.getTime();
        long time = time2 - time1;
        if (time < 2000) {
            return super.onKeyDown(keyCode, event);
        } else {
            time1 = time2;
            ToastUtil.show("再点一次退出");
            return true;
        }

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
     * Tab列表
     *
     * @param bean
     */
    @Override
    public void showTabList(BaseTypeDataBean bean) {
        if (bean.getCode() == 1) {
            getLoadLayout().setLayoutState(State.SUCCESS);
            pagerAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager(), bean.getRows());
            vp_home_info.setAdapter(pagerAdapter);
            tl_home_title.setupWithViewPager(vp_home_info);
            //设置可以滑动
            tl_home_title.setTabMode(TabLayout.MODE_SCROLLABLE);
        } else {
            ToastUtil.show(bean.getInfo());
        }


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
        getLoadLayout().setLayoutState(State.FAILED);

    }

    @Override
    public void showFinish() {

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
