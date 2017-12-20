package com.zhuazhuale.changsha.module.home.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.OrderBean;
import com.zhuazhuale.changsha.module.home.adapter.OrderAdapter;
import com.zhuazhuale.changsha.module.home.presenter.OrderPresenter;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;
import com.zhuazhuale.changsha.view.widget.loadlayout.OnLoadListener;
import com.zhuazhuale.changsha.view.widget.loadlayout.State;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 我的订单
 * Created by 丁琪 on 2017/12/15.
 */

public class OrderActivity extends AppBaseActivity implements View.OnClickListener, IOrederView {

    @BindView(R.id.rv_order_list)
    RecyclerView rv_order_list;

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
        presenter.initGetOrders(0);
        getLoadLayout().setOnLoadListener(new OnLoadListener() {
            @Override
            public void onLoad() {
                presenter.initGetOrders(0);
            }
        });
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }

    }

    /**
     * 进入编辑
     *
     * @param s
     * @param position
     */
    public void goToDetails(OrderBean.RowsBean s, int position) {
        intent = new Intent(OrderActivity.this, OrderDetailsActivity.class);
        startActivity(intent);
    }

    /**
     * 获取成功,地址列表
     *
     * @param orderBean
     */
    @Override
    public void showGetOrder(OrderBean orderBean) {
        if (0 == orderBean.getCode()) {
            //设置页面为“空数据”状态
            getLoadLayout().setLayoutState(State.NO_DATA);
            ToastUtil.show(orderBean.getInfo());
        } else {
            //设置页面为“成功”状态，显示正文布局
            getLoadLayout().setLayoutState(State.SUCCESS);
            addressAdapter = new OrderAdapter(this, orderBean.getRows());
            rv_order_list.setLayoutManager(new LinearLayoutManager(this));
            rv_order_list.setHasFixedSize(false);
            rv_order_list.setAdapter(addressAdapter);
        }
    }

    @Override
    public void showFailed() {
        //设置页面为“失败”状态
        getLoadLayout().setLayoutState(State.FAILED);
    }

    @Override
    public void showFinish() {
        dismissLoadingDialog();
    }
}
