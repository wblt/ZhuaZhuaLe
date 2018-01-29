package com.zhuazhuale.changsha.module.home.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.RollPagerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.BaseDataBean;
import com.zhuazhuale.changsha.module.home.Bean.DeviceGoodsBean;
import com.zhuazhuale.changsha.module.home.Bean.VersionBean;
import com.zhuazhuale.changsha.module.home.adapter.DeviceGoodsAdapter;
import com.zhuazhuale.changsha.module.home.adapter.HomeAdapter;
import com.zhuazhuale.changsha.module.home.presenter.HomePresenter;
import com.zhuazhuale.changsha.module.home.ui.HomeActivity;
import com.zhuazhuale.changsha.module.home.ui.InviteActivity;
import com.zhuazhuale.changsha.module.home.ui.RechargeActivity;
import com.zhuazhuale.changsha.module.vital.ui.PlayActivity;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.FrescoUtil;
import com.zhuazhuale.changsha.util.IItemOnClickListener;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;
import com.zhuazhuale.changsha.view.fragment.base.BaseFragment;
import com.zhuazhuale.changsha.view.widget.loadlayout.OnLoadListener;
import com.zhuazhuale.changsha.view.widget.loadlayout.State;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.crosswall.lib.coverflow.CoverFlow;
import me.crosswall.lib.coverflow.core.PageItemClickListener;
import me.crosswall.lib.coverflow.core.PagerContainer;

/**
 * Created by Administrator on 2018/1/29.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener, IHomeView {
    @BindView(R.id.rfv_home)
    SmartRefreshLayout rfv_home;
    @BindView(R.id.rpv_mall)
    ViewPager rpv_mall;
    @BindView(R.id.rv_home_list)
    RecyclerView rv_home_list;
    @BindView(R.id.iv_home_fresh)
    ImageView iv_home_fresh;
    @BindView(R.id.pager_container)
    PagerContainer pager_container;
    @BindView(R.id.banner)
    MZBannerView mMZBanner;

    private HomePresenter homePresenter;
    private int mStart = 1;
    private int mCont = 6;
    private DeviceGoodsAdapter adapter;

    public static HomeActivity instance = null;
    private Intent intent;
    private ProgressBar mProgress;
    private boolean isLoadingMore;//是否正在进行“加载更多”的操作，避免重复发起请求
    private List<DeviceGoodsBean.RowsBean> beanList = new ArrayList<>();
    private List<BaseDataBean.RowsBean> imgList = new ArrayList<>();

    private int mCurrentType;
    private HomeAdapter homeAdapter;

    public static HomeFragment newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int setContentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

        adapter = new DeviceGoodsAdapter(getContext(), beanList);
        rv_home_list.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rv_home_list.setAdapter(adapter);
        List<Integer> list=new ArrayList<>();
        list.add(R.mipmap.camera_2);
        list.add(R.mipmap.camera_2);
        list.add(R.mipmap.camera_2);
        list.add(R.mipmap.camera_2);


    }
    public static class BannerViewHolder implements MZViewHolder<BaseDataBean.RowsBean> {
        private SimpleDraweeView mImageView;
        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
            mImageView = (SimpleDraweeView) view.findViewById(R.id.iv_home_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, BaseDataBean.RowsBean data) {
            // 数据绑定
//            mImageView.setImageResource(data);
            Uri imageUri = Uri.parse(data.getF_ImgUrl());
            //开始下载
            mImageView.setImageURI(imageUri);
        }
    }

    @Override
    protected void obtainData() {
        mCurrentType = getArguments().getInt("type", 1);
        homePresenter = new HomePresenter(this);
        if (mCurrentType > 1) {
            rpv_mall.setVisibility(View.GONE);
        } else {
            homePresenter.initData();
            rpv_mall.setVisibility(View.VISIBLE);
            homeAdapter = new HomeAdapter(imgList);
          /*  //设置播放时间间隔
            rpv_mall.setPlayDelay(2000);
            //设置透明度
            rpv_mall.setAnimationDurtion(500);
                 rpv_mall.setHintView(null);
            */
            //设置适配器
            rpv_mall.setAdapter(homeAdapter);
            new CoverFlow.Builder()
                    .with(rpv_mall)
                    .pagerMargin(0f)
                    .scale(0.3f)
                    .spaceSize(0f)
                    .rotationY(0f)
                    .build();
            pager_container.setPageItemClickListener(new PageItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    //Toast.makeText(context,"position:" + position,Toast.LENGTH_SHORT).show();
                }
            });
        }

//        getLoadLayout().setLayoutState(State.LOADING);
//        rfv_home.autoRefresh();

        homePresenter.initDeviceGoods(1, mCont, Constant.INIT);
        //状态为加载时的监听,请求网络
        getLoadLayout().setOnLoadListener(new OnLoadListener() {
            @Override
            public void onLoad() {
                homePresenter.initDeviceGoods(1, mCont, Constant.INIT);
            }
        });

    }


    @Override
    protected void initEvent() {
        rfv_home.setEnableOverScrollDrag(true);//是否启用越界拖动（仿苹果效果）1.0.4-6
        //下拉刷新
        rfv_home.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (mCurrentType == 1) {
                    homePresenter.initData();
                }

                homePresenter.initDeviceGoods(1, mCont, Constant.REFRESH);
            }
        });
        rfv_home.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

                //上拉加载更多
                if (rfv_home.isRefreshing()) {
                    return;
                }
                if (!isLoadingMore) {
                    isLoadingMore = true;
                    mStart = mStart + 1;
                    LogUtil.e(" mStart  =" + mStart);
                    //加载更多
                    homePresenter.initDeviceGoods(mStart, mCont, Constant.LOADMORE);

                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_home_fresh:
                mStart = mStart + 1;
                homePresenter.initDeviceGoods(1, mStart, mCont);
                break;

        }

    }


    /**
     * 循环viewpager
     *
     * @param rows
     */

    @Override
    public void showImagePage(final List<BaseDataBean.RowsBean> rows) {
        // 设置数据
        mMZBanner.setPages(rows, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
        mMZBanner.setIndicatorVisible(false);
        homeAdapter.reFresh(rows);
//        rpv_mall.setHintView(new ColorPointHintView(this, Color.BLACK, Color.GRAY));

        homeAdapter.setOnItemClickListener(new IItemOnClickListener() {
            @Override
            public void itemOnClick(View view, int position) {
                switch (rows.get(position).getF_Type()) {
                    case 1:
                        //邀请码界面
                        intent = new Intent(getContext(), InviteActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        //充值界面
                        intent = new Intent(getContext(), RechargeActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void itemLongOnClick(View view, int position) {

            }
        });
    }

    /**
     * 设备列表
     *
     * @param Bean
     * @param type
     */
    @Override
    public void showDeviceGoods(DeviceGoodsBean Bean, int type) {
        switch (type) {
            case Constant.INIT:
                rfv_home.finishRefresh();
                mStart = 1;
                if (0 == Bean.getCode()) {
                    getLoadLayout().setLayoutState(State.NO_DATA);
                } else {

                 /*   adapter = new DeviceGoodsAdapter(getContext(), Bean.getRows());
                    rv_home_list.setLayoutManager(new GridLayoutManager(getContext(), 2));
                    rv_home_list.setNestedScrollingEnabled(false);
                    rv_home_list.setAdapter(adapter);*/
                    adapter.replaceData(Bean.getRows());
                    if (Bean.getRows() == null || Bean.getRows().size() == 0) {
                        //设置页面为“没数据”状态
                        getLoadLayout().setLayoutState(State.NO_DATA);

                    } else {
                        //设置页面为“成功”状态，显示正文布局
                        getLoadLayout().setLayoutState(State.SUCCESS);
                    }
                }

                break;
            case Constant.REFRESH:
                mStart = 1;
                rfv_home.finishRefresh();
                if (Bean.getCode() == 0) {
                    if (adapter.getItemCount() > 0) {
                        adapter.removeAll();
                    }
//                    getLoadLayout().setLayoutState(State.NO_DATA);
                    ToastUtil.show(Bean.getInfo());
                } else {
                    adapter.replaceData(Bean.getRows());
                }

                break;
            case Constant.LOADMORE:
                isLoadingMore = false;
                if (0 == Bean.getCode()) {
                    //全部数据加载完毕
                    rfv_home.finishLoadmoreWithNoMoreData();
                    rfv_home.resetNoMoreData();
                } else {
                    rfv_home.finishLoadmore();
                    adapter.insertItems(Bean.getRows());
                }
                break;

        }
        if (Bean.getCode() != 0 && adapter != null) {
            LogUtil.e("adapter.getItemCount()   " + adapter.getItemCount() + "      Bean.getTotal() " + Bean.getTotal());
            if (adapter.getItemCount() >= Bean.getTotal()) {
                rfv_home.finishLoadmoreWithNoMoreData();

            } else {
                rfv_home.resetNoMoreData();
                rfv_home.setEnableLoadmore(true);
            }
        }
        adapter.setClickListener(new DeviceGoodsAdapter.OnHomeClickListener() {
            @Override
            public void onHomeClick(DeviceGoodsBean.RowsBean rowsBean) {
                Intent intent = new Intent(getContext(), PlayActivity.class);
                intent.putExtra("DeviceGoods", rowsBean);
                startActivity(intent);
            }
        });


    }

    @Override
    public void showFailed() {
        ToastUtil.show("刷新失败,请检查网络!");
        rfv_home.finishRefresh(false);
    }

    /**
     * 请求机器数据失败
     *
     * @param type
     */
    @Override
    public void showDeviceGoodsFailed(int type) {
        switch (type) {
            //初始化数据
            case Constant.INIT:
                //设置页面为“失败”状态
                ToastUtil.show("加载失败,请检查网络!");
                break;

            //刷新数据
            case Constant.REFRESH:
                rfv_home.finishRefresh(false);
                ToastUtil.show("刷新失败");
                break;

            //加载更多数据
            case Constant.LOADMORE:
                rfv_home.finishLoadmore(false);
                isLoadingMore = false;
                mStart = mStart - 1;//请求失败时需回退mStart值，确保下次请求的数据正确
                ToastUtil.show("加载更多失败");
                break;
        }
    }

    @Override
    public void showFinish() {
    }
/*
    @Override
    public void showChange(VersionBean versionBean) {

    }

    @Override
    public void installApk(File body) {

    }

    @Override
    public void showProgress(float fraction) {

    }*/


}
