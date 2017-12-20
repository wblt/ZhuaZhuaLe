package com.zhuazhuale.changsha.module.home.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.OrderBean;
import com.zhuazhuale.changsha.module.home.adapter.OrderDetailsAdapter;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import butterknife.BindView;

/**
 * 订单详情
 * Created by 丁琪 on 2017/12/19 0019.
 */

public class OrderDetailsActivity extends AppBaseActivity implements View.OnClickListener {
    @BindView(R.id.tv_order_details_name)
    TextView tv_name;
    @BindView(R.id.tv_order_details_phone)
    TextView tv_phone;
    @BindView(R.id.tv_order_details_address)
    TextView tv_address;
    @BindView(R.id.tv_order_details_no)
    TextView tv_no;
    @BindView(R.id.tv_order_details_time)
    TextView tv_time;
    @BindView(R.id.tv_order_details_express)
    TextView tv_express;
    @BindView(R.id.tv_order_details_expressno)
    TextView tv_expressno;
    @BindView(R.id.tv_order_details_remark)
    TextView tv_remark;
    @BindView(R.id.rv_order_details_goods)
    RecyclerView rv_goods;
    private OrderBean.RowsBean rowsBean;
    private OrderDetailsAdapter adapter;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_order_details);
        Intent intent = getIntent();
        rowsBean = (OrderBean.RowsBean) intent.getSerializableExtra("OrderBean");
    }

    @Override
    protected void initView() {
        tv_name.setText(rowsBean.getF_Consignee());
        tv_phone.setText(rowsBean.getF_Mobile());
        tv_address.setText(rowsBean.getF_Address());
        tv_no.setText(rowsBean.getF_OrderNo());
        tv_time.setText(rowsBean.getF_CreateTime());
        tv_express.setText(rowsBean.getF_Express());
        tv_expressno.setText(rowsBean.getF_ExpressNo());
        tv_remark.setText(rowsBean.getF_Remark());
        showGoods();
    }

    private void showGoods() {
        adapter = new OrderDetailsAdapter(this, rowsBean.getDetail());
        rv_goods.setLayoutManager(new LinearLayoutManager(this));
        rv_goods.setAdapter(adapter);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void onClick(View view) {

    }
}
