package com.zhuazhuale.changsha.module.home.ui;

import android.graphics.Color;
import android.view.View;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.adapter.HomeAdapter;
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

public class HomeActivity extends BaseActivity {

    @BindView(R.id.rfv_home)
    RefreshableView rfv_home;
    @BindView(R.id.rpv_mall)
    RollPagerView rpv_mall;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }



    @Override
    protected void initView() {
        List<String> strings = new ArrayList<>();
        strings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1513057567577&di=f296baf1542db77b414b0ab967be4678&imgtype=0&src=http%3A%2F%2Fimg4.duitang.com%2Fuploads%2Fblog%2F201405%2F20%2F20140520112708_QcPBY.jpeg");
        strings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1513057567577&di=f296baf1542db77b414b0ab967be4678&imgtype=0&src=http%3A%2F%2Fimg4.duitang.com%2Fuploads%2Fblog%2F201405%2F20%2F20140520112708_QcPBY.jpeg");
        strings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1513057567577&di=f296baf1542db77b414b0ab967be4678&imgtype=0&src=http%3A%2F%2Fimg4.duitang.com%2Fuploads%2Fblog%2F201405%2F20%2F20140520112708_QcPBY.jpeg");
        strings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1513058334341&di=2bb5f7bce7b2056ab1119092580decdd&imgtype=0&src=http%3A%2F%2Fimg1.szhk.com%2FImage%2F2016%2F05%2F03%2F201605031135418662.jpg");
        strings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1513058334341&di=2bb5f7bce7b2056ab1119092580decdd&imgtype=0&src=http%3A%2F%2Fimg1.szhk.com%2FImage%2F2016%2F05%2F03%2F201605031135418662.jpg");
        showViewPager(strings);
    }

    @Override
    protected void obtainData() {

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

    public void showViewPager(List<String> imgList) {
        HomeAdapter homeAdapter = new HomeAdapter(this, imgList);
        //设置播放时间间隔
//        rpv_mall.setPlayDelay(2000);
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
