package com.zhuazhuale.changsha.module.home.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.jude.rollviewpager.RollPagerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.BaseDataBean;
import com.zhuazhuale.changsha.module.home.Bean.DeviceGoodsBean;
import com.zhuazhuale.changsha.module.home.Bean.DevicesTypeBean;
import com.zhuazhuale.changsha.module.home.Bean.VersionBean;
import com.zhuazhuale.changsha.module.home.adapter.DeviceGoodsAdapter;
import com.zhuazhuale.changsha.module.home.adapter.HomeAdapter;
import com.zhuazhuale.changsha.module.home.fragment.IHomeView;
import com.zhuazhuale.changsha.module.home.presenter.HomePresenter;
import com.zhuazhuale.changsha.module.vital.ui.PlayActivity;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.IItemOnClickListener;
import com.zhuazhuale.changsha.util.PermissionUtil;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;
import com.zhuazhuale.changsha.view.widget.loadlayout.State;

import java.io.File;
import java.util.List;

import butterknife.BindView;

/**
 * 首页
 * Created by 丁琪 on 2017/12/10 0010.
 */

public class HomeActivity extends AppBaseActivity implements IHomeView, View.OnClickListener {

    @BindView(R.id.rfv_home)
    SmartRefreshLayout rfv_home;
    @BindView(R.id.rpv_mall)
    RollPagerView rpv_mall;
    @BindView(R.id.rv_home_list)
    RecyclerView rv_home_list;
    @BindView(R.id.ll_home_shezhi)
    LinearLayout ll_home_shezhi;
    @BindView(R.id.ll_home_mine)
    LinearLayout ll_home_mine;
    @BindView(R.id.iv_home_fresh)
    ImageView iv_home_fresh;

    private HomePresenter homePresenter;
    private int mStart = 1;
    private int mCont = 6;
    private DeviceGoodsAdapter adapter;

    public static HomeActivity instance = null;
    private Intent intent;
    private ProgressBar mProgress;
    private boolean isLoadingMore;//是否正在进行“加载更多”的操作，避免重复发起请求


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_home);
        instance = this;
    }

    @Override
    protected void initView() {

        PermissionUtil.requestPerssions(this, 1, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);

    }

    @Override
    protected void obtainData() {
       /* homePresenter = new HomePresenter(this);
//        getLoadLayout().setLayoutState(State.LOADING);
//        rfv_home.autoRefresh();
        showLoadingDialog("");
        homePresenter.initData();
        homePresenter.initDeviceGoods(1, mCont, Constant.INIT);
        getToolbar().setVisibility(View.GONE);
        String version = "";
        try {
            version = getVersionName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogUtil.e(" version " + version);
        homePresenter.initVersion(version);*/
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
        ll_home_shezhi.setOnClickListener(this);
        ll_home_mine.setOnClickListener(this);

        rfv_home.setEnableOverScrollDrag(true);//是否启用越界拖动（仿苹果效果）1.0.4-6
        //下拉刷新
        rfv_home.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                homePresenter.initData();
//                homePresenter.initDeviceGoods(1, mCont, Constant.REFRESH);
            }
        });
        rfv_home.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

                //上拉加载更多
                if (rfv_home.isRefreshing()) {
                    return;
                }
                if (!isLoadingMore) {
                    isLoadingMore = true;
                    mStart = mStart + 1;
                    LogUtil.e(" mStart  =" + mStart);
                    //加载更多
//                    homePresenter.initDeviceGoods(mStart, mCont, Constant.LOADMORE);

                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ll_home_mine:

                intent = new Intent(HomeActivity.this, MineActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_home_shezhi:
                 intent = new Intent(HomeActivity.this, SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_home_fresh:
                mStart = mStart + 1;
                showLoadingDialog("");
//                homePresenter.initDeviceGoods(1, mStart, mCont);
                break;

        }

    }

    /**
     * 循环viewpager
     *
     * @param rows
     */

    @Override
    public void showImagePage(final List<BaseDataBean.RowsBean> rows) {

        HomeAdapter homeAdapter = new HomeAdapter( rows);
        //设置播放时间间隔
        rpv_mall.setPlayDelay(2000);
        //设置透明度
        rpv_mall.setAnimationDurtion(500);
        //设置适配器
        rpv_mall.setAdapter(homeAdapter);
//        rpv_mall.setHintView(new ColorPointHintView(this, Color.BLACK, Color.GRAY));
        rpv_mall.setHintView(null);
        homeAdapter.setOnItemClickListener(new IItemOnClickListener() {
            @Override
            public void itemOnClick(View view, int position) {
                switch (rows.get(position).getF_Type()) {
                    case 1:
                        //邀请码界面
                        intent = new Intent(getContext(), InviteActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        //充值界面
                        intent = new Intent(getContext(), RechargeActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void itemLongOnClick(View view, int position) {

            }
        });
    }

    /**
     * 设备列表
     *
     * @param Bean
     * @param type
     */
    @Override
    public void showDeviceGoods(DeviceGoodsBean Bean, int type) {

           /* iv_home_fresh.setOnClickListener(this);
            rfv_home.finishRefresh(true);
            //设置页面为“成功”状态，显示正文布局
            getLoadLayout().setLayoutState(State.SUCCESS);

            if (mCollectAdapter == null) {
                mCollectAdapter = new DeviceGoodsAdapter(getContext(), rows.getRows());
                rv_home_list.setLayoutManager(new GridLayoutManager(getContext(), 2));
                rv_home_list.setHasFixedSize(false);
                rv_home_list.setAdapter(mCollectAdapter);
            } else {
                mCollectAdapter.replaceData(rows.getRows());
            }
            if (mStart == rows.getPageCount()) {
                mStart = 0;
            }*/
        switch (type) {
            case Constant.INIT:
                rfv_home.finishRefresh();
                mStart = 1;
                if (0 == Bean.getCode()) {
                    getLoadLayout().setLayoutState(State.NO_DATA);
                } else {

                    adapter = new DeviceGoodsAdapter(this, Bean.getRows());
                    rv_home_list.setLayoutManager(new GridLayoutManager(getContext(), 2));
                    rv_home_list.setNestedScrollingEnabled(false);
                    rv_home_list.setAdapter(adapter);
                    if (Bean.getRows() == null || Bean.getRows().size() == 0) {
                        //设置页面为“没数据”状态
                        getLoadLayout().setLayoutState(State.NO_DATA);

                    } else {
                        //设置页面为“成功”状态，显示正文布局
                        getLoadLayout().setLayoutState(State.SUCCESS);
                    }
                }

                break;
            case Constant.REFRESH:
                mStart = 1;
                rfv_home.finishRefresh();
                if (Bean.getCode() == 0) {
                    if (adapter.getItemCount() > 0) {
                        adapter.removeAll();
                    }
//                    getLoadLayout().setLayoutState(State.NO_DATA);
                    ToastUtil.show(Bean.getInfo());
                } else {
                    adapter.replaceData(Bean.getRows());
                }

                break;
            case Constant.LOADMORE:
                isLoadingMore = false;
                if (0 == Bean.getCode()) {
                    //全部数据加载完毕
                    rfv_home.finishLoadmoreWithNoMoreData();
                    rfv_home.resetNoMoreData();
                } else {
                    rfv_home.finishLoadmore();
                    adapter.insertItems(Bean.getRows());
                }
                break;

        }
        if (Bean.getCode() != 0 && adapter != null) {
            LogUtil.e("adapter.getItemCount()   " + adapter.getItemCount() + "      Bean.getTotal() " + Bean.getTotal());
            if (adapter.getItemCount() >= Bean.getTotal()) {
                rfv_home.finishLoadmoreWithNoMoreData();

            } else {
                rfv_home.resetNoMoreData();
                rfv_home.setEnableLoadmore(true);
            }
        }

    }

    @Override
    public void showFailed() {
        ToastUtil.show("刷新失败,请检查网络!");
        rfv_home.finishRefresh(false);
    }

    /**
     * 请求机器数据失败
     *
     * @param type
     */
    @Override
    public void showDeviceGoodsFailed(int type) {
        switch (type) {
            //初始化数据
            case Constant.INIT:
                //设置页面为“失败”状态
                ToastUtil.show("加载失败,请检查网络!");
                break;

            //刷新数据
            case Constant.REFRESH:
                rfv_home.finishRefresh(false);
                ToastUtil.show("刷新失败");
                break;

            //加载更多数据
            case Constant.LOADMORE:
                rfv_home.finishLoadmore(false);
                isLoadingMore = false;
                mStart = mStart - 1;//请求失败时需回退mStart值，确保下次请求的数据正确
                ToastUtil.show("加载更多失败");
                break;
        }
    }

    @Override
    public void showDeviceGoodsType(DevicesTypeBean deviceGoodsBean) {

    }

    @Override
    public void showFinish() {
        dismissLoadingDialog();
    }

    /**
     * 更新app
     *
     * @param versionBean
     *//*
    @Override
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
    }*/


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
//        homePresenter.downloadApk(vUrl);
    }

    /**
     * 安装apk
     *
     * @param
     *//*
    @Override
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

    @Override
    public void showProgress(float fraction) {
        mProgress.setProgress((int) (fraction * 100));
    }
*/
}
