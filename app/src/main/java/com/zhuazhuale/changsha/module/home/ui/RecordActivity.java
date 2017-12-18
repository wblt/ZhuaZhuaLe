package com.zhuazhuale.changsha.module.home.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.adapter.RecordAdapter;
import com.zhuazhuale.changsha.module.home.adapter.WaWaBiAdapter;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 我的抓取记录
 * Created by 丁琪 on 2017/12/18.
 */

public class RecordActivity extends AppBaseActivity implements View.OnClickListener {
    @BindView(R.id.iv_home_back)
    ImageView iv_home_back;

    @BindView(R.id.rv_record_list)
    RecyclerView rv_record_list;
    @BindView(R.id.rfv_record_fresh)
    SmartRefreshLayout rfv_record_fresh;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_record);
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
        RecordAdapter adapter = new RecordAdapter(this, strings);
        rv_record_list.setLayoutManager(new LinearLayoutManager(this));
        rv_record_list.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {
        iv_home_back.setOnClickListener(this);
        rfv_record_fresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                rfv_record_fresh.finishLoadmore(2000);
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

    /**
     * 查看详情
     *
     * @param s
     * @param position
     */
    public void goToDetails(String s, int position) {
        Intent intent = new Intent(RecordActivity.this, RecordDetailsActivity.class);
        startActivity(intent);
    }
}
