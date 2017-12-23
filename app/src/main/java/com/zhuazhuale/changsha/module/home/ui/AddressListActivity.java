package com.zhuazhuale.changsha.module.home.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.model.entity.eventbus.AddressEvent;
import com.zhuazhuale.changsha.model.entity.eventbus.LoginEvent;
import com.zhuazhuale.changsha.module.home.Bean.AddressBean;
import com.zhuazhuale.changsha.module.home.Bean.AllPriceProductBean;
import com.zhuazhuale.changsha.module.home.adapter.AddressListAdapter;
import com.zhuazhuale.changsha.module.home.presenter.AddressListPresenter;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.EventBusUtil;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * 申请发货时,地址的选择列表
 * Created by Administrator on 2017/12/23 0023.
 */
public class AddressListActivity extends AppBaseActivity implements View.OnClickListener, IAddressListView {
    @BindView(R.id.rv_addresslist)
    RecyclerView rv_addresslist;
    @BindView(R.id.srv_addresslist)
    SmartRefreshLayout srv_addresslist;
    private AddressListPresenter presenter;
    private AddressListAdapter adapter;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_addresslist);
    }

    @Override
    protected void initView() {
        getTvToolbarRight().setText("管理");
        getTvToolbarRight().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddressActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void obtainData() {
        presenter = new AddressListPresenter(this);
        Intent intent = getIntent();
        AddressBean bean = (AddressBean) intent.getSerializableExtra("address");
        showAddressList(bean);
        EventBusUtil.register(this);//订阅事件
    }

    //EventBus的事件接收，从事件中获取最新的收藏数量并更新界面展示
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(AddressEvent event) {
        String code = event.getAddressFresh();
        presenter.initUserAddress(0, Constant.REFRESH);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅
        EventBusUtil.unregister(this);
    }

    private void showAddressList(AddressBean bean) {
        adapter = new AddressListAdapter(this, bean.getRows());
        rv_addresslist.setLayoutManager(new LinearLayoutManager(this));
        rv_addresslist.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {
        srv_addresslist.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                presenter.initUserAddress(0, Constant.REFRESH);
            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    /**
     * 地址列表刷新
     *
     * @param addressBean
     * @param type
     */
    @Override
    public void showUserAddress(AddressBean addressBean, int type) {
        if (addressBean.getCode() == 1) {
            srv_addresslist.finishRefresh(true);
            adapter.replaceData(addressBean.getRows());

        } else {
            ToastUtil.show(addressBean.getInfo());
            srv_addresslist.finishRefresh(false);
            adapter.removeAll();
        }
    }

    @Override
    public void showFailed(int type) {
        srv_addresslist.finishRefresh(false);
        ToastUtil.show("刷新失败,请检查网络!");
    }

    @Override
    public void showFinish() {

    }

    /**
     * 选择地址
     *
     * @param rowsBean
     */
    public void select(AddressBean.RowsBean rowsBean) {
        Intent intent = new Intent();
        intent.putExtra("rowsBean", rowsBean);
        setResult(2, intent);
        finish();
    }
}
