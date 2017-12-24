package com.zhuazhuale.changsha.module.home.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.model.entity.eventbus.CPfreshEvent;
import com.zhuazhuale.changsha.module.home.Bean.EditAddressBean;
import com.zhuazhuale.changsha.module.home.Bean.SpoilsBean;
import com.zhuazhuale.changsha.module.home.adapter.SpoilsAdapter;
import com.zhuazhuale.changsha.module.home.presenter.SpoilsPresenter;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.EventBusUtil;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;
import com.zhuazhuale.changsha.view.widget.MaterialDialog;
import com.zhuazhuale.changsha.view.widget.loadlayout.OnLoadListener;
import com.zhuazhuale.changsha.view.widget.loadlayout.OnNoDataListener;
import com.zhuazhuale.changsha.view.widget.loadlayout.State;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 我的战利品
 * Created by 丁琪 on 2017/12/15.
 */

public class SpoilsActivity extends AppBaseActivity implements View.OnClickListener, ISpoilsView {

    @BindView(R.id.rv_spoils_list)
    RecyclerView rv_spoils_list;
    @BindView(R.id.tv_spoils_fahuo)
    TextView tv_spoils_fahuo;
    @BindView(R.id.srl_spoils_fresh)
    SmartRefreshLayout srl_spoils_fresh;
    private Intent intent;
    private SpoilsPresenter presenter;
    private SpoilsAdapter addressAdapter;
    private MaterialDialog mDialog;
    private SpoilsBean.RowsBean bean;
    private int pos;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_spoils);
    }

    @Override
    protected void initView() {
//        getTvToolbarRight().setText("申请发货");
        mDialog = new MaterialDialog(this);
    }

    @Override
    protected void obtainData() {
        showLoadingDialog();
        presenter = new SpoilsPresenter(this);
        presenter.initQueryUserGoods(9, Constant.INIT);
//        showAddressList();
        getLoadLayout().setOnLoadListener(new OnLoadListener() {
            @Override
            public void onLoad() {
                presenter.initQueryUserGoods(9, Constant.INIT);
            }
        });


    }

    @Override
    protected void initEvent() {
        tv_spoils_fahuo.setOnClickListener(this);
        //没有查到数据页面的点击监听事件
        getLoadLayout().setOnNoDataListener(new OnNoDataListener() {
            @Override
            public void onGoTo() {
//                getLoadLayout().setLayoutState(State.LOADING);
                //  回到首页
                getActivityStackManager().exitAllActivityExceptCurrent(HomeActivity.class);
            }
        });
        srl_spoils_fresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //下拉刷新
                presenter.initQueryUserGoods(9, Constant.REFRESH);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_spoils_fahuo:
                List<SpoilsBean.RowsBean> beanList = new ArrayList<>();
                for (SpoilsBean.RowsBean rowsBean : addressAdapter.getDataList()) {
                    if (rowsBean.isCheck()) {
                        beanList.add(rowsBean);
                    }
                }
                SpoilsBean spoilsBean = new SpoilsBean();
                spoilsBean.setRows(beanList);
                if (beanList.size() > 0) {
                    intent = new Intent(SpoilsActivity.this, DeliveryActivity.class);
                    intent.putExtra("SpoilsBean", spoilsBean);
                    startActivityForResult(intent, 110);
                } else {
                    ToastUtil.show("请选择发货的商品!");
                }

                break;
        }
    }


    @Override
    public void showQueryUserGoods(SpoilsBean spoilsBean, int type) {
        srl_spoils_fresh.finishRefresh(true);
        switch (type) {
            case Constant.INIT:
                if (0 == spoilsBean.getCode()) {
                    getLoadLayout().setLayoutState(State.NO_DATA);
                    ToastUtil.show(spoilsBean.getInfo());
                } else {
                    getLoadLayout().setLayoutState(State.SUCCESS);
                    addressAdapter = new SpoilsAdapter(this, spoilsBean.getRows());
                    rv_spoils_list.setLayoutManager(new LinearLayoutManager(this));
                    rv_spoils_list.setHasFixedSize(false);
                    rv_spoils_list.setAdapter(addressAdapter);
                    if (spoilsBean.getRows() == null || spoilsBean.getRows().size() == 0) {
                        //设置页面为“没数据”状态
                        getLoadLayout().setLayoutState(State.NO_DATA);

                    } else {
                        //设置页面为“成功”状态，显示正文布局
                        getLoadLayout().setLayoutState(State.SUCCESS);
                    }
                }
                break;
            case Constant.REFRESH:
                if (0 == spoilsBean.getCode()) {
                    getLoadLayout().setLayoutState(State.NO_DATA);
                    ToastUtil.show(spoilsBean.getInfo());
                } else {
                    addressAdapter.replaceData(spoilsBean.getRows());
                }
                break;
        }

    }

    /**
     * 接口结束
     */
    @Override
    public void showFinish() {
        dismissLoadingDialog();
    }

    @Override
    public void showFailed(int type) {
        srl_spoils_fresh.finishRefresh(false);
        switch (type) {
            case Constant.INIT:
                getLoadLayout().setLayoutState(State.FAILED);
                break;
            case Constant.REFRESH:
                ToastUtil.show("刷新失败,请检查网络!");
                break;
        }
    }

    /**
     * 兑换成功
     *
     * @param bean
     * @param pos
     */
    @Override
    public void showExChangeCP(EditAddressBean bean, int pos) {
        if (bean.getCode() == 0) {
            ToastUtil.show(bean.getInfo());
        } else {
            ToastUtil.show(bean.getInfo());
            addressAdapter.removeItem(pos);
            EventBusUtil.postEvent(new CPfreshEvent("刷新"));
        }
    }

    /**
     * 兑换游戏币
     *
     * @param rowsBean
     * @param position
     */
    public void duiHuan(SpoilsBean.RowsBean rowsBean, int position) {
        bean = rowsBean;
        pos = position;
        mDialog.setTitle("兑换游戏币");
        mDialog.setMessage("确定将当前娃娃兑换成" + rowsBean.getF_ExChangePrice() + "游戏币");

        mDialog.setPositiveButton("确定兑换", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                showLoadingDialog();
                //这里从全局变量bean获取信息来构建对象，
                //如果从rowsBean获取的话，要声明为final类型，但是声明为final类型后，你在这个内部类里面获取到的rowsBean都是不变的（一直是第一次获取到的那个,除非重新new Dialog）

                presenter.initExChangeCP(bean.getF_ID(), bean.getF_DeviceID(), pos);
            }
        });
        mDialog.setNegativeButton("取消兑换", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDialog.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==110&&resultCode==2){
            presenter.initQueryUserGoods(9,Constant.REFRESH);
        }
    }
}
