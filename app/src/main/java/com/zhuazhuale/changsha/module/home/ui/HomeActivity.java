package com.zhuazhuale.changsha.module.home.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.BaseDataBean;
import com.zhuazhuale.changsha.module.home.Bean.DeviceGoodsBean;
import com.zhuazhuale.changsha.module.home.adapter.DeviceGoodsAdapter;
import com.zhuazhuale.changsha.module.home.adapter.HomeAdapter;
import com.zhuazhuale.changsha.module.home.presenter.HomePresenter;
import com.zhuazhuale.changsha.util.IItemOnClickListener;
import com.zhuazhuale.changsha.view.RefreshableView;
import com.zhuazhuale.changsha.view.activity.base.ToolbarBaseActivity;
import com.zhuazhuale.changsha.view.adapter.CollectAdapter;
import com.zhuazhuale.changsha.view.widget.loadlayout.State;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 丁琪 on 2017/12/10 0010.
 */

public class HomeActivity extends ToolbarBaseActivity implements IHomeView, View.OnClickListener {

    @BindView(R.id.rfv_home)
    RefreshableView rfv_home;
    @BindView(R.id.rpv_mall)
    RollPagerView rpv_mall;
    @BindView(R.id.rv_home_list)
    RecyclerView rv_home_list;
    @BindView(R.id.iv_home_shezhi)
    ImageView iv_home_shezhi;
    @BindView(R.id.iv_home_mine)
    ImageView iv_home_mine;

    private HomePresenter homePresenter;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void obtainData() {
        homePresenter = new HomePresenter(this);
        homePresenter.initData();
        homePresenter.initDeviceGoods(1, 4);

    }

    @Override
    protected void initEvent() {
        iv_home_shezhi.setOnClickListener(this);
        iv_home_mine.setOnClickListener(this);
        rfv_home.addPullToRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                rfv_home.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rfv_home.complete();
                    }
                }, 2000);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_home_mine:
                Intent intent1 = new Intent(HomeActivity.this, MineActivity.class);
                startActivity(intent1);
                break;
            case R.id.iv_home_shezhi:
                Intent intent2 = new Intent(HomeActivity.this, SettingActivity.class);
                startActivity(intent2);
                break;

        }

    }

    /**
     * 循环viewpager
     *
     * @param rows
     */
    @Override
    public void showImagePage(List<BaseDataBean.RowsBean> rows) {
        HomeAdapter homeAdapter = new HomeAdapter(this, rows);
        //设置播放时间间隔
        rpv_mall.setPlayDelay(2000);
        //设置透明度
        rpv_mall.setAnimationDurtion(500);
        //设置适配器
        rpv_mall.setAdapter(homeAdapter);
        rpv_mall.setHintView(new ColorPointHintView(this, Color.BLACK, Color.GRAY));
        homeAdapter.setOnItemClickListener(new IItemOnClickListener() {
            @Override
            public void itemOnClick(View view, int position) {

            }

            @Override
            public void itemLongOnClick(View view, int position) {

            }
        });
    }

    @Override
    public void showDeviceGoods(List<DeviceGoodsBean.RowsBean> rows) {
        //设置页面为“成功”状态，显示正文布局
        getLoadLayout().setLayoutState(State.SUCCESS);
        DeviceGoodsAdapter mCollectAdapter = new DeviceGoodsAdapter(getContext(), rows);
        rv_home_list.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rv_home_list.setHasFixedSize(false);
        rv_home_list.setAdapter(mCollectAdapter);
    }
}
