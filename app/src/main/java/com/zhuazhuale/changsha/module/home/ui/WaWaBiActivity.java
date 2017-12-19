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
import com.zhuazhuale.changsha.module.home.Bean.BanlanceWaterBean;
import com.zhuazhuale.changsha.module.home.adapter.WaWaBiAdapter;
import com.zhuazhuale.changsha.module.home.presenter.WaWaBiPresenter;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;
import com.zhuazhuale.changsha.view.widget.loadlayout.OnLoadListener;
import com.zhuazhuale.changsha.view.widget.loadlayout.State;


import butterknife.BindView;

/**
 * 我的娃娃币页面
 * Created by 丁琪 on 2017/12/18.
 */

public class WaWaBiActivity extends AppBaseActivity implements View.OnClickListener, IWaWaBiView {
    @BindView(R.id.iv_home_back)
    ImageView iv_home_back;

    @BindView(R.id.rv_wawabi_list)
    RecyclerView rv_wawabi_list;
    @BindView(R.id.rfv_wawabi_fresh)
    SmartRefreshLayout rfv_wawabi_fresh;
    private WaWaBiPresenter presenter;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_wawabi);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void obtainData() {
        presenter = new WaWaBiPresenter(this);
        //设置“加载”状态时要做的事情
        getLoadLayout().setOnLoadListener(new OnLoadListener() {
            @Override
            public void onLoad() {
                //  请求数据
                presenter.inittBanlanceWater(0, 10);
            }
        });
        getLoadLayout().setLayoutState(State.LOADING);

    }


    @Override
    protected void initEvent() {
        iv_home_back.setOnClickListener(this);
        rfv_wawabi_fresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                presenter.inittBanlanceWater(0, 10);
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
     * 我的娃娃币列表
     *
     * @param banlanceWaterBean
     */
    @Override
    public void showBanlanceWater(BanlanceWaterBean banlanceWaterBean) {
        rfv_wawabi_fresh.finishRefresh();
        getLoadLayout().setLayoutState(State.SUCCESS);
        WaWaBiAdapter adapter = new WaWaBiAdapter(this, banlanceWaterBean.getRows());
        rv_wawabi_list.setLayoutManager(new LinearLayoutManager(this));
        rv_wawabi_list.setAdapter(adapter);
    }

    /**
     * 无数据
     */
    @Override
    public void showNoData() {
        getLoadLayout().setLayoutState(State.NO_DATA);
    }

    /**
     * 请求失败
     */
    @Override
    public void showFailed() {
        getLoadLayout().setLayoutState(State.FAILED);
    }
}
