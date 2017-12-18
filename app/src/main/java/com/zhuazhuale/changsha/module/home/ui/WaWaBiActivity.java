package com.zhuazhuale.changsha.module.home.ui;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.adapter.RechargeAdapter;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 我的娃娃币页面
 * Created by 丁琪 on 2017/12/18.
 */

public class WaWaBiActivity extends AppBaseActivity implements View.OnClickListener {
    @BindView(R.id.iv_home_back)
    ImageView iv_home_back;

    @BindView(R.id.rv_recharge_list)
    RecyclerView rv_recharge_list;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_wawabi);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void obtainData() {
        List<String> strings = new ArrayList<>();
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        showRechargeList(strings);
    }

    private void showRechargeList(List<String> strings) {
        RechargeAdapter adapter = new RechargeAdapter(this, strings);
        rv_recharge_list.setLayoutManager(new GridLayoutManager(this, 2));
        rv_recharge_list.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {
        iv_home_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home_back:
                finish();
                break;
        }
    }


}
