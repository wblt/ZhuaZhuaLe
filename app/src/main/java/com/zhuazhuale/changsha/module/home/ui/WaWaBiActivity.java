package com.zhuazhuale.changsha.module.home.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.BanlanceWaterBean;
import com.zhuazhuale.changsha.module.home.adapter.WaWaBiAdapter;
import com.zhuazhuale.changsha.module.home.presenter.WaWaBiPresenter;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;
import com.zhuazhuale.changsha.view.widget.loadlayout.OnLoadListener;
import com.zhuazhuale.changsha.view.widget.loadlayout.State;


import butterknife.BindView;

/**
 * 我的娃娃币页面
 * Created by 丁琪 on 2017/12/18.
 */

public class WaWaBiActivity extends AppBaseActivity implements View.OnClickListener, IWaWaBiView {

    @BindView(R.id.rv_wawabi_list)
    RecyclerView rv_wawabi_list;
    @BindView(R.id.rfv_wawabi_fresh)
    SmartRefreshLayout rfv_wawabi_fresh;
    private WaWaBiPresenter presenter;
    private int mStart = 1;//请求数据的起始点
    private int mCount = 10;//每次请求的数据数量
    private boolean isLoadingMore;//是否正在进行“加载更多”的操作，避免重复发起请求
    private boolean isFirst = true;

    private WaWaBiAdapter adapter;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_wawabi);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void obtainData() {
        presenter = new WaWaBiPresenter(this);
        //设置“加载”状态时要做的事情
        getLoadLayout().setOnLoadListener(new OnLoadListener() {
            @Override
            public void onLoad() {
                //  初始化数据
                getData(mStart, mCount, Constant.INIT);
            }
        });
//        getLoadLayout().setLayoutState(State.LOADING);
        rfv_wawabi_fresh.autoRefresh();
    }

    /**
     * 请求数据
     *
     * @param PageIndex
     * @param PageSize
     * @param type
     */
    private void getData(int PageIndex, int PageSize, int type) {
        LogUtil.e("PageIndex    " + PageIndex + "   PageSize    " + PageSize + "    type    " + type);
        presenter.inittBanlanceWater(PageIndex, PageSize, type);
    }


    @Override
    protected void initEvent() {
        rfv_wawabi_fresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
//                下拉刷新数据
                if (isFirst) {
                    isFirst = false;
                    getData(0, mCount, Constant.INIT);
                } else {
                    getData(0, mCount, Constant.REFRESH);

                }
            }
        });
        rfv_wawabi_fresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                //上拉加载更多
                if (rfv_wawabi_fresh.isRefreshing()) {
                    return;
                }
                if (!isLoadingMore) {
                    isLoadingMore = true;
                    mStart = mStart + 1;
                    //加载更多
                    getData(mStart, mCount, Constant.LOADMORE);
                }

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    /**
     * 我的娃娃币列表
     *
     * @param Bean
     * @param type
     */
    @Override
    public void showBanlanceWater(BanlanceWaterBean Bean, int type) {
        switch (type) {
            case Constant.INIT:
                rfv_wawabi_fresh.finishRefresh();
                mStart = 0;
                if (0 == Bean.getCode()) {
                    getLoadLayout().setLayoutState(State.NO_DATA);
                } else {
                    //设置页面为“成功”状态，显示正文布局
                    getLoadLayout().setLayoutState(State.SUCCESS);
                    adapter = new WaWaBiAdapter(this, Bean.getRows());
                    rv_wawabi_list.setLayoutManager(new LinearLayoutManager(this));
                    rv_wawabi_list.setAdapter(adapter);
                }

                break;
            case Constant.REFRESH:
                mStart = 0;
                rfv_wawabi_fresh.finishRefresh();
                adapter.replaceData(Bean.getRows());
                break;
            case Constant.LOADMORE:
                isLoadingMore = false;
                if (0 == Bean.getCode()) {
                    //全部数据加载完毕
                    rfv_wawabi_fresh.finishLoadmoreWithNoMoreData();
                    rfv_wawabi_fresh.resetNoMoreData();
                } else {
                    rfv_wawabi_fresh.finishLoadmore();
                    adapter.insertItems(Bean.getRows());
                }
                break;

        }
        if (Bean.getCode() != 0 && adapter != null) {
            LogUtil.e("adapter.getItemCount()   " + adapter.getItemCount() + "      Bean.getTotal() " + Bean.getTotal());
            if (adapter.getItemCount() >= Bean.getTotal()) {
                rfv_wawabi_fresh.finishLoadmoreWithNoMoreData();
                rfv_wawabi_fresh.resetNoMoreData();
            }
        }

    }


    /**
     * 请求失败
     *
     * @param type
     */
    @Override
    public void showFailed(int type) {

        switch (type) {
            //初始化数据
            case Constant.INIT:
                //设置页面为“失败”状态
                getLoadLayout().setLayoutState(State.FAILED);
                break;

            //刷新数据
            case Constant.REFRESH:
                rfv_wawabi_fresh.finishRefresh(false);
                ToastUtil.show("刷新失败");
                break;

            //加载更多数据
            case Constant.LOADMORE:
                rfv_wawabi_fresh.finishLoadmore(false);
                isLoadingMore = false;
                mStart = mStart - 1;//请求失败时需回退mStart值，确保下次请求的数据正确
                ToastUtil.show("加载更多失败");
                break;
        }
    }

}
