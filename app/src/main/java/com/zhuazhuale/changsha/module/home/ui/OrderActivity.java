package com.zhuazhuale.changsha.module.home.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.OrderBean;
import com.zhuazhuale.changsha.module.home.adapter.OrderAdapter;
import com.zhuazhuale.changsha.module.home.presenter.OrderPresenter;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;
import com.zhuazhuale.changsha.view.widget.loadlayout.OnLoadListener;
import com.zhuazhuale.changsha.view.widget.loadlayout.OnNoDataListener;
import com.zhuazhuale.changsha.view.widget.loadlayout.State;

import butterknife.BindView;

/**
 * 我的订单
 * Created by 丁琪 on 2017/12/15.
 */

public class OrderActivity extends AppBaseActivity implements View.OnClickListener, IOrederView {

    @BindView(R.id.rv_order_list)
    RecyclerView rv_order_list;
    @BindView(R.id.srl_order_fresh)
    SmartRefreshLayout srl_order_fresh;

    private Intent intent;
    private OrderPresenter presenter;
    private OrderAdapter addressAdapter;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_order);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void obtainData() {
        showLoadingDialog();
        presenter = new OrderPresenter(this);
        presenter.initGetOrders(0, Constant.INIT);
        getLoadLayout().setOnLoadListener(new OnLoadListener() {
            @Override
            public void onLoad() {
                presenter.initGetOrders(0, Constant.INIT);
            }
        });
    }

    @Override
    protected void initEvent() {
        //刷新监听
        srl_order_fresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                presenter.initGetOrders(0, Constant.REFRESH);
            }
        });
        //没有查到数据页面的点击监听事件
        getLoadLayout().setOnNoDataListener(new OnNoDataListener() {
            @Override
            public void onGoTo() {
//                getLoadLayout().setLayoutState(State.LOADING);
                //  回到首页
                getActivityStackManager().exitAllActivityExceptCurrent(HomeActivity.class);
            }
        });
    }

    @Override
    public void onClick(View v) {


    }

    /**
     * 进入编辑
     *
     * @param rowsBean
     * @param position
     */
    public void goToDetails(OrderBean.RowsBean rowsBean, int position) {
        intent = new Intent(OrderActivity.this, OrderDetailsActivity.class);
        intent.putExtra("OrderBean", rowsBean);
        startActivity(intent);
    }

    /**
     * 获取成功,订单列表
     *
     * @param orderBean
     * @param type
     */
    @Override
    public void showGetOrder(OrderBean orderBean, int type) {
        switch (type) {
            case Constant.INIT:
                if (0 == orderBean.getCode()) {
                    //设置页面为“空数据”状态
                    getLoadLayout().setLayoutState(State.NO_DATA);
                } else {
                    //设置页面为“成功”状态，显示正文布局
                    getLoadLayout().setLayoutState(State.SUCCESS);
                    addressAdapter = new OrderAdapter(this, orderBean.getRows());
                    rv_order_list.setLayoutManager(new LinearLayoutManager(this));
                    rv_order_list.setHasFixedSize(false);
                    rv_order_list.setAdapter(addressAdapter);
                }
                break;
            case Constant.REFRESH:
                if (0 == orderBean.getCode()) {
                    //设置页面为“空数据”状态
                    getLoadLayout().setLayoutState(State.NO_DATA);
                    ToastUtil.show(orderBean.getInfo());
                } else {
                    //设置页面为“成功”状态，显示正文布局
                    getLoadLayout().setLayoutState(State.SUCCESS);
                    addressAdapter.replaceData(orderBean.getRows());
                    ToastUtil.show("刷新成功!");

                }
                break;
        }


    }

    @Override
    public void showFailed(int type) {
        switch (type) {
            case Constant.INIT:
                //设置页面为“失败”状态
                getLoadLayout().setLayoutState(State.FAILED);
                break;
            case Constant.REFRESH:
                ToastUtil.show("刷新失败,请检查网络!");
                break;
        }

    }

    @Override
    public void showFinish() {
        dismissLoadingDialog();
        srl_order_fresh.finishRefresh();
    }
}
