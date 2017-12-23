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
import com.zhuazhuale.changsha.model.entity.eventbus.AddressEvent;
import com.zhuazhuale.changsha.module.home.Bean.AddressBean;
import com.zhuazhuale.changsha.module.home.Bean.EditAddressBean;
import com.zhuazhuale.changsha.module.home.adapter.AddressAdapter;
import com.zhuazhuale.changsha.module.home.presenter.AddressPresenter;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.EventBusUtil;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;
import com.zhuazhuale.changsha.view.widget.MaterialDialog;
import com.zhuazhuale.changsha.view.widget.loadlayout.OnLoadListener;
import com.zhuazhuale.changsha.view.widget.loadlayout.State;

import butterknife.BindView;

/**
 * 我的地址
 * Created by 丁琪 on 2017/12/15.
 */

public class AddressActivity extends AppBaseActivity implements View.OnClickListener, IAddressView {

    @BindView(R.id.rv_address_list)
    RecyclerView rv_address_list;
    @BindView(R.id.tv_address_add)
    TextView tv_address_add;
    @BindView(R.id.srl_address_fresh)
    SmartRefreshLayout srl_address_fresh;

    private Intent intent;
    private AddressPresenter presenter;
    private AddressAdapter addressAdapter;
    private MaterialDialog mDialog;
    private AddressBean.RowsBean bean;
    private int pos;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_address);
    }

    @Override
    protected void initView() {
        mDialog = new MaterialDialog(this);
    }

    @Override
    protected void obtainData() {
        showLoadingDialog();
        presenter = new AddressPresenter(this);
        presenter.initQueryUserAddress(0, Constant.INIT);
        //状态为加载时的监听,请求网络
        getLoadLayout().setOnLoadListener(new OnLoadListener() {
            @Override
            public void onLoad() {
                presenter.initQueryUserAddress(0, Constant.INIT);
            }
        });

    }

    @Override
    protected void initEvent() {
        tv_address_add.setOnClickListener(this);
        srl_address_fresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //下拉刷新
                presenter.initQueryUserAddress(0, Constant.REFRESH);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_address_add:
                intent = new Intent(AddressActivity.this, EditAddressActivity.class);
                startActivityForResult(intent, 110);
                break;
        }
    }


    /**
     * 进入编辑
     *
     * @param rowsBean
     * @param position
     */
    public void goToChangge(AddressBean.RowsBean rowsBean, int position) {
        intent = new Intent(AddressActivity.this, EditAddressActivity.class);
        intent.putExtra("AddressBean", rowsBean);
        startActivityForResult(intent, 110);
    }

    /**
     * 删除地址
     *
     * @param rowsBean
     * @param position
     */
    public void deleteAddress(AddressBean.RowsBean rowsBean, int position) {
        bean = rowsBean;
        pos = position;
        mDialog.setTitle("删除地址");
        mDialog.setMessage("亲,确定删除地址么");
        mDialog.setPositiveButton("确定删除", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                showLoadingDialog();
                //这里从全局变量mMovieRes获取信息来构建对象，
                //如果从movieRes获取的话，要声明为final类型，但是声明为final类型后，你在这个内部类里面获取到的movieRes都是不变的（一直是第一次获取到的那个,除非重新new Dialog）

                presenter.initDeleteUserAddress(bean.getF_ID(), pos);
            }
        });
        mDialog.setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDialog.show();

    }

    /**
     * 删除成功
     *
     * @param addressBean
     * @param position
     */
    @Override
    public void showDeleteUserAddress(EditAddressBean addressBean, int position) {
        if (addressBean.getCode() == 0) {
            ToastUtil.show(addressBean.getInfo());
        } else {
            ToastUtil.show(addressBean.getInfo());
            addressAdapter.removeItem(position);
            EventBusUtil.postEvent(new AddressEvent("刷新"));
//            presenter.initQueryUserAddress(0, Constant.REFRESH);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 110 && resultCode == 2) {
            presenter.initQueryUserAddress(0, Constant.REFRESH);
            EventBusUtil.postEvent(new AddressEvent("刷新"));
        }
    }

    /**
     * 请求成功
     *
     * @param addressBean
     * @param type
     */
    @Override
    public void showQueryUserAddress(AddressBean addressBean, int type) {
        switch (type) {
            case Constant.INIT:
                if (addressBean.getCode() == 0) {
//                    getLoadLayout().setLayoutState(State.NO_DATA);
                    ToastUtil.show(addressBean.getInfo());
                } else {
                    getLoadLayout().setLayoutState(State.SUCCESS);
                    addressAdapter = new AddressAdapter(this, addressBean.getRows());
                    rv_address_list.setLayoutManager(new LinearLayoutManager(this));
                    rv_address_list.setHasFixedSize(false);
                    rv_address_list.setAdapter(addressAdapter);
                }
                break;
            case Constant.REFRESH:
                if (addressBean.getCode() == 0) {
                    ToastUtil.show(addressBean.getInfo());
                    addressAdapter.removeAll();
                } else {
                    addressAdapter.replaceData(addressBean.getRows());
                }

                break;
        }

    }

    /**
     * 请求失败
     *
     * @param type
     */
    @Override
    public void showFailed(int type) {
        switch (type) {
            case Constant.INIT:
                getLoadLayout().setLayoutState(State.FAILED);
                break;
            case Constant.REFRESH:
                ToastUtil.show("更新失败,请检查网络!");
                break;
        }

    }

    /**
     * 请求结束
     */
    @Override
    public void showFinish() {
        dismissLoadingDialog();
        srl_address_fresh.finishRefresh();
    }


}
