package com.zhuazhuale.changsha.module.home.ui;

import android.graphics.Color;
import android.view.View;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.BaseDataBean;
import com.zhuazhuale.changsha.module.home.adapter.HomeAdapter;
import com.zhuazhuale.changsha.module.home.presenter.HomePresenter;
import com.zhuazhuale.changsha.util.IItemOnClickListener;
import com.zhuazhuale.changsha.view.RefreshableView;
import com.zhuazhuale.changsha.view.activity.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 丁琪 on 2017/12/10 0010.
 */

public class HomeActivity extends BaseActivity implements IHomeView, View.OnClickListener {

    @BindView(R.id.rfv_home)
    RefreshableView rfv_home;
    @BindView(R.id.rpv_mall)
    RollPagerView rpv_mall;
    private HomePresenter homePresenter;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

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
}
