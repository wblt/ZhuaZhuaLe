package com.zhuazhuale.changsha.module.home.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.AddressBean;
import com.zhuazhuale.changsha.module.home.adapter.AddressAdapter;
import com.zhuazhuale.changsha.module.home.presenter.AddressPresenter;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;
import com.zhuazhuale.changsha.view.widget.loadlayout.OnLoadListener;
import com.zhuazhuale.changsha.view.widget.loadlayout.State;

import java.util.ArrayList;
import java.util.List;

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
    private Intent intent;
    private AddressPresenter presenter;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_address);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void obtainData() {
        showLoadingDialog();
        presenter = new AddressPresenter(this);
        presenter.initQueryUserAddress(0);
        //状态为加载时的监听,请求网络
        getLoadLayout().setOnLoadListener(new OnLoadListener() {
            @Override
            public void onLoad() {
                presenter.initQueryUserAddress(0);
            }
        });

    }

    @Override
    protected void initEvent() {
        tv_address_add.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_address_add:
                intent = new Intent(AddressActivity.this, EditAddressActivity.class);
                startActivity(intent);
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
        startActivity(intent);
    }

    /**
     * 请求成功
     *
     * @param addressBean
     */
    @Override
    public void showQueryUserAddress(AddressBean addressBean) {
        if (addressBean.getCode() == 0) {
            getLoadLayout().setLayoutState(State.NO_DATA);
            ToastUtil.show(addressBean.getInfo());
        } else {
            getLoadLayout().setLayoutState(State.SUCCESS);
            AddressAdapter addressAdapter = new AddressAdapter(this, addressBean.getRows());
            rv_address_list.setLayoutManager(new LinearLayoutManager(this));
            rv_address_list.setHasFixedSize(false);
            rv_address_list.setAdapter(addressAdapter);
        }
    }

    /**
     * 请求失败
     */
    @Override
    public void showFailed() {
        getLoadLayout().setLayoutState(State.FAILED);
    }

    /**
     * 请求结束
     */
    @Override
    public void showFinish() {
        dismissLoadingDialog();
    }
}
