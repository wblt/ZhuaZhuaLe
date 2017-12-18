package com.zhuazhuale.changsha.module.home.ui;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.adapter.RechargeAdapter;
import com.zhuazhuale.changsha.module.home.adapter.WaWaBiAdapter;
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

    @BindView(R.id.rv_wawabi_list)
    RecyclerView rv_wawabi_list;
    @BindView(R.id.rfv_wawabi_fresh)
    SmartRefreshLayout rfv_wawabi_fresh;

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
        WaWaBiAdapter adapter = new WaWaBiAdapter(this, strings);
        rv_wawabi_list.setLayoutManager(new LinearLayoutManager(this));
        rv_wawabi_list.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {
        iv_home_back.setOnClickListener(this);
        rfv_wawabi_fresh.setOnRefreshListener(new OnRefreshListener() {
                    @Override
                    public void onRefresh(RefreshLayout refreshlayout) {
                        rfv_wawabi_fresh.finishLoadmore(2000);
                    }
                });
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
