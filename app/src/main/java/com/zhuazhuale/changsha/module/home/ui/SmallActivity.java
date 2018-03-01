package com.zhuazhuale.changsha.module.home.ui;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.DeviceGoodsBean;
import com.zhuazhuale.changsha.module.home.adapter.DeviceGoodsAdapter;
import com.zhuazhuale.changsha.module.home.adapter.MallGoodsAdapter;
import com.zhuazhuale.changsha.module.home.presenter.MallPresenter;
import com.zhuazhuale.changsha.module.vital.ui.PlayActivity;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;
import com.zhuazhuale.changsha.view.widget.MaterialDialog;
import com.zhuazhuale.changsha.view.widget.loadlayout.OnLoadListener;
import com.zhuazhuale.changsha.view.widget.loadlayout.State;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 商城
 * Created by Administrator on 2018/2/28 0028.
 */

public class SmallActivity extends AppBaseActivity implements IMallView {
    @BindView(R.id.rv_small_list)
    RecyclerView rv_small_list;
    @BindView(R.id.rfv_small)
    SmartRefreshLayout rfv_home;
    private MallPresenter mallPresenter;
    private int mStart = 1;
    private int mCont = 8;
    private MallGoodsAdapter adapter;
    private boolean isLoadingMore;//是否正在进行“加载更多”的操作，避免重复发起请求
    private List<DeviceGoodsBean.RowsBean> beanList = new ArrayList<>();
    private MaterialDialog mDialog;
    private Intent intent;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_small);
    }

    @Override
    protected void initView() {
        mDialog = new MaterialDialog(this);
        adapter = new MallGoodsAdapter(getContext(), beanList);
        rv_small_list.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rv_small_list.setAdapter(adapter);
    }

    @Override
    protected void obtainData() {
        mallPresenter = new MallPresenter(this);
        showLoadingDialog("");
        mallPresenter.initDeviceGoods(1, mCont, "", Constant.INIT);
        //状态为加载时的监听,请求网络
        getLoadLayout().setOnLoadListener(new OnLoadListener() {
            @Override
            public void onLoad() {

                mallPresenter.initDeviceGoods(1, mCont, "", Constant.INIT);
            }
        });
    }

    @Override
    protected void initEvent() {
        rfv_home.setEnableOverScrollDrag(true);//是否启用越界拖动（仿苹果效果）1.0.4-6
        //下拉刷新
        rfv_home.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mallPresenter.initDeviceGoods(1, mCont, "", Constant.REFRESH);
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
                    mallPresenter.initDeviceGoods(mStart, mCont, "", Constant.LOADMORE);

                }
            }
        });
    }

    @Override
    public void showDeviceGoods(DeviceGoodsBean Bean, int type) {
        switch (type) {
            case Constant.INIT:
                rfv_home.finishRefresh();
                mStart = 1;
                if (0 == Bean.getCode()) {
//                    getLoadLayout().setLayoutState(State.NO_DATA);
                    ToastUtil.show(Bean.getInfo());
                } else {
                    adapter.replaceData(Bean.getRows());
                    if (Bean.getRows() == null || Bean.getRows().size() == 0) {
                        //设置页面为“没数据”状态
//                        getLoadLayout().setLayoutState(State.NO_DATA);
                        ToastUtil.show(Bean.getInfo());

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
        adapter.setClickListener(new MallGoodsAdapter.OnHomeClickListener() {
            @Override
            public void onHomeClick(DeviceGoodsBean.RowsBean rowsBean) {
                mDialog.setTitle("城市抓抓乐温馨提示");
                mDialog.setMessage("需要购买娃娃,请联系客服!");

                mDialog.setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                        intent = new Intent(SmallActivity.this, FeedBackActivity.class);
                        startActivity(intent);

                    }
                });
                mDialog.setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                    }
                });
                mDialog.show();
            }
        });

    }

    @Override
    public void showDeviceGoodsFailed(int type) {
        switch (type) {
            //初始化数据
            case Constant.INIT:
                //设置页面为“失败”状态
                ToastUtil.show("加载失败,请检查网络!");
                getLoadLayout().setLayoutState(State.FAILED);
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
    public void showFinish() {
        dismissLoadingDialog();
    }
}
