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
 * 抓取详情
 * Created by 丁琪 on 2017/12/18.
 */

public class RecordDetailsActivity extends AppBaseActivity implements View.OnClickListener {
    @BindView(R.id.iv_home_back)
    ImageView iv_home_back;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_record_detais);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void obtainData() {

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

    /**
     * 申诉
     *
     * @param s
     * @param position
     */
    public void goToDetails(String s, int position) {
        Intent intent = new Intent(RecordDetailsActivity.this, RecordDetailsActivity.class);
        startActivity(intent);
    }
}
