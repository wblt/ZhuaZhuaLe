package com.zhuazhuale.changsha.module.vital.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.app.constant.ICallListener;
import com.zhuazhuale.changsha.model.entity.eventbus.LoginEvent;
import com.zhuazhuale.changsha.model.entity.eventbus.ScrollEvent;
import com.zhuazhuale.changsha.module.home.Bean.DeviceGoodsBean;
import com.zhuazhuale.changsha.module.home.fragment.FullyLinearLayoutManager;
import com.zhuazhuale.changsha.module.vital.adapter.AllTrueAdapter;
import com.zhuazhuale.changsha.module.vital.bean.AllUserTrueByDeviceIDBean;
import com.zhuazhuale.changsha.module.vital.model.PlayModel;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.EventBusUtil;
import com.zhuazhuale.changsha.util.FrescoUtil;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;
import com.zhuazhuale.changsha.view.adapter.decoration.LinearDividerDecoration;
import com.zhuazhuale.changsha.view.fragment.base.BaseFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/30 0030.
 */

public class PlayFragment2 extends BaseFragment {
    @BindView(R.id.rv_fra_list)
    RecyclerView rv_fra_list;


    private int mCurrentType;

    public static PlayFragment2 newInstance(int type, DeviceGoodsBean.RowsBean rowsBean) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putSerializable("rowsBean", rowsBean);
        PlayFragment2 fragment = new PlayFragment2();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_play_info2;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void obtainData() {
        mCurrentType = getArguments().getInt("type", 1);
        DeviceGoodsBean.RowsBean bean = (DeviceGoodsBean.RowsBean) getArguments().getSerializable("rowsBean");
        PlayModel playModel = new PlayModel();
        playModel.getGetAllUserTrueByDeviceID(bean.getF_ID(), new ICallListener<String>() {
            @Override
            public void callSuccess(String s) {

                Gson gson = new Gson();
                AllUserTrueByDeviceIDBean trueByDeviceIDBean = gson.fromJson(s, AllUserTrueByDeviceIDBean.class);
                showList(trueByDeviceIDBean);
            }

            @Override
            public void callFailed() {
            }

            @Override
            public void onFinish() {
                LogUtil.e("接口结束");
//                mIView.showFinish();
            }
        });
        EventBusUtil.register(this);//订阅事件
    }

    //EventBus的事件接收，从事件中获取最新的收藏数量并更新界面展示
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(ScrollEvent event) {
        rv_fra_list.setNestedScrollingEnabled(true);
    }


    private void showList(AllUserTrueByDeviceIDBean trueBean) {
        if (trueBean.getCode() == 1) {
            List<AllUserTrueByDeviceIDBean.RowsBean> rowsBeen = new ArrayList<>();
            rowsBeen.addAll(trueBean.getRows());
            rowsBeen.addAll(trueBean.getRows());
            rowsBeen.addAll(trueBean.getRows());
            AllTrueAdapter adapter = new AllTrueAdapter(getContext(), rowsBeen);

//            FullyLinearLayoutManager fullyLinearLayoutManager = new FullyLinearLayoutManager(getContext());
            LinearLayoutManager fullyLinearLayoutManager=new LinearLayoutManager(getContext());
//            rv_fra_list.setNestedScrollingEnabled(false);
            rv_fra_list.setLayoutManager(fullyLinearLayoutManager);
            rv_fra_list.addItemDecoration(new LinearDividerDecoration(getContext(), 1));
            rv_fra_list.setAdapter(adapter);

            rv_fra_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (!recyclerView.canScrollVertically(-1)) {
                        rv_fra_list.setNestedScrollingEnabled(false);

                    }
                }
            });


        } else {
            LogUtil.e(trueBean.getInfo());
        }
    }

    @Override
    protected void initEvent() {

    }
}
