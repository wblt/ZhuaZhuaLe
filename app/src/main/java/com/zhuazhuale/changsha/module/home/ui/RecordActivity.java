package com.zhuazhuale.changsha.module.home.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.GradWaterBean;
import com.zhuazhuale.changsha.module.home.adapter.RecordAdapter;
import com.zhuazhuale.changsha.module.home.adapter.WaWaBiAdapter;
import com.zhuazhuale.changsha.module.home.presenter.RecordPresenter;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;
import com.zhuazhuale.changsha.view.widget.loadlayout.OnLoadListener;
import com.zhuazhuale.changsha.view.widget.loadlayout.OnNoDataListener;
import com.zhuazhuale.changsha.view.widget.loadlayout.State;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 我的抓取记录
 * Created by 丁琪 on 2017/12/18.
 */

public class RecordActivity extends AppBaseActivity implements View.OnClickListener, IRecordView {

    @BindView(R.id.rv_record_list)
    RecyclerView rv_record_list;
    @BindView(R.id.rfv_record_fresh)
    SmartRefreshLayout rfv_record_fresh;
    private int mStart = 1;//请求数据的起始点
    private int mCount = 10;//每次请求的数据数量
    private boolean isLoadingMore;//是否正在进行“加载更多”的操作，避免重复发起请求
    private RecordAdapter adapter;
    private RecordPresenter presenter;
    private boolean isFirst = true;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_record);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void obtainData() {
        presenter = new RecordPresenter(this);
        //设置“加载”状态时要做的事情
        getLoadLayout().setOnLoadListener(new OnLoadListener() {
            @Override
            public void onLoad() {
                //  初始化数据
                getData(mStart, mCount, Constant.INIT);
            }
        });
        rfv_record_fresh.autoRefresh();
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
        presenter.initGrabWater(PageIndex, PageSize, type);
    }


    @Override
    protected void initEvent() {
        //没有查到数据页面的点击监听事件
        getLoadLayout().setOnNoDataListener(new OnNoDataListener() {
            @Override
            public void onGoTo() {
//                getLoadLayout().setLayoutState(State.LOADING);
                //  回到首页
                getActivityStackManager().exitAllActivityExceptCurrent(HomeActivity.class);
            }
        });

        rfv_record_fresh.setOnRefreshListener(new OnRefreshListener() {
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
        rfv_record_fresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                //上拉加载更多
                if (rfv_record_fresh.isRefreshing()) {
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
    }

    /**
     * 查看详情
     *
     * @param rowsBean
     * @param position
     */
    public void goToDetails(GradWaterBean.RowsBean rowsBean, int position) {
        Intent intent = new Intent(RecordActivity.this, RecordDetailsActivity.class);
        intent.putExtra("rowsBean", rowsBean);
        startActivityForResult(intent, 110);
    }

    @Override
    public void showGradWater(GradWaterBean Bean, int type) {
        switch (type) {
            case Constant.INIT:
                rfv_record_fresh.finishRefresh();
                mStart = 0;
                if (0 == Bean.getCode()) {
                    getLoadLayout().setLayoutState(State.NO_DATA);
                } else {
                    //设置页面为“成功”状态，显示正文布局
                    getLoadLayout().setLayoutState(State.SUCCESS);
                    adapter = new RecordAdapter(this, Bean.getRows());
                    rv_record_list.setLayoutManager(new LinearLayoutManager(this));
                    rv_record_list.setAdapter(adapter);
                }

                break;
            case Constant.REFRESH:
                mStart = 0;
                rfv_record_fresh.finishRefresh();
                adapter.replaceData(Bean.getRows());
                break;
            case Constant.LOADMORE:
                isLoadingMore = false;
                if (0 == Bean.getCode()) {
                    //全部数据加载完毕
                    rfv_record_fresh.finishLoadmoreWithNoMoreData();
                    rfv_record_fresh.resetNoMoreData();
                } else {
                    rfv_record_fresh.finishLoadmore();
                    adapter.insertItems(Bean.getRows());
                }
                break;

        }
        if (Bean.getCode() != 0 && adapter != null) {
            LogUtil.e("adapter.getItemCount()   " + adapter.getItemCount() + "      Bean.getTotal() " + Bean.getTotal());
            if (adapter.getItemCount() >= Bean.getTotal()) {
                rfv_record_fresh.finishLoadmoreWithNoMoreData();//完成加载并标记没有更多数据 1.0.4-6参考https://github.com/scwang90/SmartRefreshLayout/blob/master/art/md_property.md
                rfv_record_fresh.resetNoMoreData();//恢复没有更多数据的原始状态 1.0.4-6

            }
        }
    }

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
                rfv_record_fresh.finishRefresh(false);
                ToastUtil.show("刷新失败");
                break;

            //加载更多数据
            case Constant.LOADMORE:
                rfv_record_fresh.finishLoadmore(false);
                isLoadingMore = false;
                mStart = mStart - 1;//请求失败时需回退mStart值，确保下次请求的数据正确
                ToastUtil.show("加载更多失败");
                break;
        }
    }
}
