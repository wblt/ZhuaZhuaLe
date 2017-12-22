package com.zhuazhuale.changsha.module.home.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.SpoilsBean;
import com.zhuazhuale.changsha.module.home.adapter.DeliveryAdapter;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import butterknife.BindView;

/**
 * 申请发货界面
 * Created by 丁琪 on 2017/12/22 0022.
 */

public class DeliveryActivity extends AppBaseActivity implements View.OnClickListener {
    @BindView(R.id.rv_delivery_list)
    RecyclerView rv_delivery_list;
    private SpoilsBean spoilsBean;
    @BindView(R.id.tv_delivery_address)
    TextView tv_delivery_address;
    @BindView(R.id.tv_delivery_name)
    TextView tv_delivery_name;
    @BindView(R.id.tv_delivery_phone)
    TextView tv_delivery_phone;
    @BindView(R.id.tv_delivery_num)
    TextView tv_delivery_num;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_delivery);


    }

    @Override
    protected void initView() {
    }

    @Override
    protected void obtainData() {
        Intent intent = getIntent();
        spoilsBean = (SpoilsBean) intent.getSerializableExtra("SpoilsBean");
        tv_delivery_num.setText(spoilsBean.getRows().size() + " 件商品");

        showDelivery(spoilsBean);
    }

    private void showDelivery(SpoilsBean spoilsBean) {
        DeliveryAdapter adapter = new DeliveryAdapter(this, spoilsBean.getRows());
        rv_delivery_list.setLayoutManager(new LinearLayoutManager(this));
        rv_delivery_list.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void onClick(View view) {

    }
}
