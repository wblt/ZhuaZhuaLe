package com.zhuazhuale.changsha.module.home.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.jude.rollviewpager.RollPagerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.BaseDataBean;
import com.zhuazhuale.changsha.module.home.Bean.BaseTypeDataBean;
import com.zhuazhuale.changsha.module.home.Bean.DeviceGoodsBean;
import com.zhuazhuale.changsha.module.home.Bean.DevicesTypeBean;
import com.zhuazhuale.changsha.module.home.adapter.DeviceGoodsAdapter;
import com.zhuazhuale.changsha.module.home.adapter.HomeAdapter;
import com.zhuazhuale.changsha.module.home.presenter.HomePresenter;
import com.zhuazhuale.changsha.module.home.ui.HomeActivity;
import com.zhuazhuale.changsha.module.home.ui.InviteActivity;
import com.zhuazhuale.changsha.module.home.ui.RechargeActivity;
import com.zhuazhuale.changsha.module.home.ui.XieYiActivity;
import com.zhuazhuale.changsha.module.vital.ui.PlayActivity;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.IItemOnClickListener;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;
import com.zhuazhuale.changsha.view.ScrollBottomScrollView;
import com.zhuazhuale.changsha.view.fragment.base.BaseFragment;
import com.zhuazhuale.changsha.view.widget.MaterialDialog;
import com.zhuazhuale.changsha.view.widget.loadlayout.OnLoadListener;
import com.zhuazhuale.changsha.view.widget.loadlayout.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/29.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener, IHomeView {
    @BindView(R.id.rfv_home)
    SmartRefreshLayout rfv_home;
    @BindView(R.id.rpv_mall)
    RollPagerView rpv_mall;
    @BindView(R.id.rv_home_list)
    RecyclerView rv_home_list;


    private HomePresenter homePresenter;
    private int mStart = 1;
    private int mCont = 8;
    private DeviceGoodsAdapter adapter;

    public static HomeActivity instance = null;
    private Intent intent;
    private boolean isLoadingMore;//是否正在进行“加载更多”的操作，避免重复发起请求
    private List<DeviceGoodsBean.RowsBean> beanList = new ArrayList<>();
    private List<BaseDataBean.RowsBean> imgList = new ArrayList<>();

    private int mCurrentType;
    private HomeAdapter homeAdapter;
    private BaseTypeDataBean.RowsBean rowsBean;
    private Thread thread;
    private Handler mHandler;
    private Timer mTimer;
    private TimerTask mTimerTask;
    private MaterialDialog mDialog3;

    public static HomeFragment newInstance(int type, BaseTypeDataBean.RowsBean rowsBean) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putSerializable("RowsBean", rowsBean);
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
        mDialog3 = new MaterialDialog(getContext());
        adapter = new DeviceGoodsAdapter(getContext(), beanList);
        rv_home_list.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rv_home_list.setAdapter(adapter);

    }

    @Override
    protected void obtainData() {
        mCurrentType = getArguments().getInt("type", 0);
        rowsBean = (BaseTypeDataBean.RowsBean) getArguments().getSerializable("RowsBean");
        if (mCurrentType == 0) {
            rowsBean.setF_ID("");
        }
        homePresenter = new HomePresenter(this);
        if (mCurrentType > 0) {
            rpv_mall.setVisibility(View.GONE);
        } else {
            homePresenter.initData();
            rpv_mall.setVisibility(View.VISIBLE);
            homeAdapter = new HomeAdapter(imgList);
            //设置播放时间间隔
            rpv_mall.setPlayDelay(2000);
            //设置透明度
            rpv_mall.setAnimationDurtion(500);
            rpv_mall.setHintView(null);
            //设置适配器
            rpv_mall.setAdapter(homeAdapter);

        }
        homePresenter.initDeviceGoods(1, mCont, rowsBean.getF_ID(), Constant.INIT);
        //状态为加载时的监听,请求网络
        getLoadLayout().setOnLoadListener(new OnLoadListener() {
            @Override
            public void onLoad() {
                if (mCurrentType == 0) {
                    homePresenter.initData();
                }
                homePresenter.initDeviceGoods(1, mCont, rowsBean.getF_ID(), Constant.INIT);
            }
        });
        //定时刷新状态
        mHandler = new Handler();
        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
//                        LogUtil.e("我在刷新");
                        // TODO Auto-generated method stub

                        if (adapter != null && adapter.getItemCount() != 0) {
//                            LogUtil.e("我在刷新" + adapter.getItemCount());
                            List<DeviceGoodsBean.RowsBean> mData = adapter.getDataList();
                            String resouse = "";
                            for (int aa = 0; aa < mData.size(); aa++) {
                                if (aa == 1) {
                                    resouse = mData.get(aa).getF_ID();
                                } else {
                                    resouse = resouse + "," + mData.get(aa).getF_ID();
                                }
                            }
                            LogUtil.e(resouse);
                            homePresenter.initGetDevicesStatus(resouse, Constant.REFRESH);
                        }
                    }
                });
            }
        };
        mTimer.schedule(mTimerTask, 0, 5000);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
    }

    @Override
    protected void initEvent() {
        rfv_home.setEnableOverScrollDrag(true);//是否启用越界拖动（仿苹果效果）1.0.4-6
        //下拉刷新
        rfv_home.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (mCurrentType == 0) {
                    homePresenter.initData();
                }

                homePresenter.initDeviceGoods(1, mCont, rowsBean.getF_ID(), Constant.REFRESH);
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
                    homePresenter.initDeviceGoods(mStart, mCont, rowsBean.getF_ID(), Constant.LOADMORE);

                }
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
    public void showImagePage(final List<BaseDataBean.RowsBean> rows) {

        homeAdapter.reFresh(rows);
//        rpv_mall.setHintView(new ColorPointHintView(this, Color.BLACK, Color.GRAY));

        homeAdapter.setOnItemClickListener(new IItemOnClickListener() {
            @Override
            public void itemOnClick(View view, int position) {
                switch (rows.get(position).getF_Type()) {
                    case 0:
                        //邀请码界面
                        intent = new Intent(getContext(), XieYiActivity.class);
                        intent.putExtra("url", rows.get(position).getF_ContentUrl());
                        startActivity(intent);
                        break;
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
                    case 3:
                        mDialog3.setTitle("城市抓抓乐温馨提示");
                        mDialog3.setMessage("城市抓抓乐想打开QQ");

                        mDialog3.setPositiveButton("打开", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mDialog3.dismiss();
                                boolean isJoin = joinQQGroup("PskjSgIiYpsWWuHSZbv-w__Mdq3gGAMC");
                                if (!isJoin) {
                                    ToastUtil.show("未安装手Q或安装的版本不支持");
                                }
                            }
                        });
                        mDialog3.setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mDialog3.dismiss();
                            }
                        });
                        mDialog3.show();

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

    public boolean joinQQGroup(String key) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
        // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            startActivity(intent);
            return true;
        } catch (Exception e) {
            // 未安装手Q或安装的版本不支持
            return false;
        }
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
//                    getLoadLayout().setLayoutState(State.NO_DATA);
                    ToastUtil.show(Bean.getInfo());
                } else {
                    adapter.replaceData(Bean.getRows());
                    if (Bean.getRows() == null || Bean.getRows().size() == 0) {
                        //设置页面为“没数据”状态
//                        getLoadLayout().setLayoutState(State.NO_DATA);
                        ToastUtil.show(Bean.getInfo());

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
//            LogUtil.e("adapter.getItemCount()   " + adapter.getItemCount() + "      Bean.getTotal() " + Bean.getTotal());
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

       /* if (thread == null) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        if (adapter != null && adapter.getItemCount() != 0) {
                            homePresenter.initDeviceGoods(1, adapter.getItemCount(), rowsBean.getF_ID(), Constant.REFRESH);
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }*/


    }

    @Override
    public void showFailed() {
        ToastUtil.show("刷新失败,请检查网络!");
        rfv_home.finishRefresh(false);
//        getLoadLayout().setLayoutState(State.FAILED);
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
                getLoadLayout().setLayoutState(State.FAILED);
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

    /**
     * 刷新状态
     *
     * @param deviceGoodsBean
     */
    @Override
    public void showDeviceGoodsType(DevicesTypeBean deviceGoodsBean) {
        if (adapter != null && adapter.getItemCount() != 0) {
            List<DeviceGoodsBean.RowsBean> rowsBeans = adapter.getDataList();
            if (deviceGoodsBean.getRows().size() == rowsBeans.size()) {
                for (int ss = 0; ss < rowsBeans.size(); ss++) {
                    rowsBeans.get(ss).setF_Status(deviceGoodsBean.getRows().get(ss).getF_Status());
                }
                adapter.replaceData(rowsBeans);
            }

        }

    }

    @Override
    public void showFinish() {
    }
}
