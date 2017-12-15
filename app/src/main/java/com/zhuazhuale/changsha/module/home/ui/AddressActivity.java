package com.zhuazhuale.changsha.module.home.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.adapter.AddressAdapter;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 我的地址
 * Created by 丁琪 on 2017/12/15.
 */

public class AddressActivity extends AppBaseActivity implements View.OnClickListener {
    @BindView(R.id.iv_home_back)
    ImageView iv_home_back;
    @BindView(R.id.rv_address_list)
    RecyclerView rv_address_list;
    @BindView(R.id.tv_address_add)
    TextView tv_address_add;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_address);
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
        iv_home_back.setOnClickListener(this);
        iv_home_back.setOnClickListener(this);
        tv_address_add.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home_back:
                finish();
                break;
        }
    }

    private void showAddressList() {
        List<String> strings = new ArrayList<>();
        strings.add("  ");
        strings.add("  ");
        strings.add("  ");
        strings.add("  ");
        strings.add("  ");
        strings.add("  ");
        AddressAdapter addressAdapter = new AddressAdapter(this, strings);
        rv_address_list.setLayoutManager(new LinearLayoutManager(this));
        rv_address_list.setHasFixedSize(false);
        rv_address_list.setAdapter(addressAdapter);
    }
}
