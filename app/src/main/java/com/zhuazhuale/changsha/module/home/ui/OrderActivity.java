package com.zhuazhuale.changsha.module.home.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.adapter.OrderAdapter;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 我的订单
 * Created by 丁琪 on 2017/12/15.
 */

public class OrderActivity extends AppBaseActivity implements View.OnClickListener {

    @BindView(R.id.rv_order_list)
    RecyclerView rv_order_list;

    private Intent intent;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_order);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void obtainData() {
        showAddressList();

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
     * 地址列表
     */
    private void showAddressList() {
        List<String> strings = new ArrayList<>();
        strings.add("  ");
        strings.add("  ");
        strings.add("  ");
        strings.add("  ");
        strings.add("  ");
        strings.add("  ");
        OrderAdapter addressAdapter = new OrderAdapter(this, strings);
        rv_order_list.setLayoutManager(new LinearLayoutManager(this));
        rv_order_list.setHasFixedSize(false);
        rv_order_list.setAdapter(addressAdapter);

    }

    /**
     * 进入编辑
     *
     * @param s
     * @param position
     */
    public void goToDetails(String s, int position) {
        intent = new Intent(OrderActivity.this, OrderDetailsActivity.class);
        startActivity(intent);
    }
}
