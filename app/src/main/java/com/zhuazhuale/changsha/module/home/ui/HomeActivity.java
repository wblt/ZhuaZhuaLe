package com.zhuazhuale.changsha.module.home.ui;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.bugly.crashreport.CrashReport;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.BaseDataBean;
import com.zhuazhuale.changsha.module.home.Bean.DeviceGoodsBean;
import com.zhuazhuale.changsha.module.home.adapter.DeviceGoodsAdapter;
import com.zhuazhuale.changsha.module.home.adapter.HomeAdapter;
import com.zhuazhuale.changsha.module.home.presenter.HomePresenter;
import com.zhuazhuale.changsha.module.login.bean.WeiXinLoginGetUserinfoBean;
import com.zhuazhuale.changsha.module.vital.ui.PlayActivity;
import com.zhuazhuale.changsha.util.FrescoUtil;
import com.zhuazhuale.changsha.util.IItemOnClickListener;
import com.zhuazhuale.changsha.util.PermissionUtil;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;
import com.zhuazhuale.changsha.view.widget.loadlayout.State;

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
    private int mCont = 4;
    private DeviceGoodsAdapter mCollectAdapter;

    public static HomeActivity instance = null;
    private Intent intent;

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
        homePresenter = new HomePresenter(this);
//        getLoadLayout().setLayoutState(State.LOADING);
//        rfv_home.autoRefresh();
        showLoadingDialog();
        homePresenter.initData();
        homePresenter.initDeviceGoods(1, mCont);
        getToolbar().setVisibility(View.GONE);
    }

    @Override
    protected void initEvent() {
        ll_home_shezhi.setOnClickListener(this);
        ll_home_mine.setOnClickListener(this);

        rfv_home.setEnableOverScrollDrag(true);//是否启用越界拖动（仿苹果效果）1.0.4-6
        rfv_home.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                homePresenter.initData();
                homePresenter.initDeviceGoods(1, mCont);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ll_home_mine:

                Intent intent1 = new Intent(HomeActivity.this, MineActivity.class);
                startActivity(intent1);
                break;
            case R.id.ll_home_shezhi:
                Intent intent2 = new Intent(HomeActivity.this, SettingActivity.class);
                startActivity(intent2);
                break;
            case R.id.iv_home_fresh:
                mStart = mStart + 1;
                showLoadingDialog();
                homePresenter.initDeviceGoods(mStart, mCont);
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

        HomeAdapter homeAdapter = new HomeAdapter(this, rows);
        //设置播放时间间隔
        rpv_mall.setPlayDelay(2000);
        //设置透明度
        rpv_mall.setAnimationDurtion(500);
        //设置适配器
        rpv_mall.setAdapter(homeAdapter);
        rpv_mall.setHintView(new ColorPointHintView(this, Color.BLACK, Color.GRAY));
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
     * @param rows
     */
    @Override
    public void showDeviceGoods(DeviceGoodsBean rows) {
        if (rows.getCode() == 0) {
            rfv_home.finishRefresh(false);
            ToastUtil.show(rows.getInfo());
        } else {
            iv_home_fresh.setOnClickListener(this);
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
            }
        }

    }

    @Override
    public void showFailed() {
        ToastUtil.show("刷新失败,请检查网络!");
        rfv_home.finishRefresh(false);
    }

    @Override
    public void showFinish() {
        dismissLoadingDialog();
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
}
