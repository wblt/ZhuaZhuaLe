package com.zhuazhuale.changsha.module.home.ui;

import android.content.Intent;
import android.location.Address;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.AddressBean;
import com.zhuazhuale.changsha.module.home.Bean.SpoilsBean;
import com.zhuazhuale.changsha.module.home.adapter.DeliveryAdapter;
import com.zhuazhuale.changsha.module.home.presenter.DeliveryPresenter;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import java.util.List;

import butterknife.BindView;

/**
 * 申请发货界面
 * Created by 丁琪 on 2017/12/22 0022.
 */

public class DeliveryActivity extends AppBaseActivity implements View.OnClickListener, IDeliveryView {
    @BindView(R.id.rv_delivery_list)
    RecyclerView rv_delivery_list;
    private SpoilsBean spoilsBean;
    @BindView(R.id.tv_delivery_address)
    TextView tv_delivery_address;
    @BindView(R.id.tv_delivery_name)
    TextView tv_delivery_name;
    @BindView(R.id.tv_delivery_phone)
    TextView tv_delivery_phone;
    @BindView(R.id.tv_delivery_num)
    TextView tv_delivery_num;
    @BindView(R.id.ll_delivery_address)
    LinearLayout ll_delivery_address;
    private List<AddressBean.RowsBean> beans;
    private AddressBean address;
    private DeliveryPresenter presenter;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_delivery);


    }

    @Override
    protected void initView() {
    }

    @Override
    protected void obtainData() {
        presenter = new DeliveryPresenter(this);
        showLoadingDialog();
        presenter.initUserAddress(0);
        Intent intent = getIntent();
        spoilsBean = (SpoilsBean) intent.getSerializableExtra("SpoilsBean");
        tv_delivery_num.setText(spoilsBean.getRows().size() + " 件商品");
        showDelivery(spoilsBean);

    }

    private void showDelivery(SpoilsBean spoilsBean) {
        DeliveryAdapter adapter = new DeliveryAdapter(this, spoilsBean.getRows());
        rv_delivery_list.setLayoutManager(new LinearLayoutManager(this));
        rv_delivery_list.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {
        ll_delivery_address.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_delivery_address:
                Intent intent = new Intent(getContext(), AddressListActivity.class);
                intent.putExtra("address", address);
                startActivityForResult(intent, 110);
                break;
        }
    }

    /**
     * 地址请求成功
     *
     * @param addressBean
     */
    @Override
    public void showUserAddress(AddressBean addressBean) {
        if (addressBean.getCode() == 1) {
            address = addressBean;
            beans = addressBean.getRows();
            if (beans.size() > 0) {
                AddressBean.RowsBean bean = beans.get(0);
                tv_delivery_name.setText(bean.getF_Consignee());
                tv_delivery_phone.setText(bean.getF_Mobile());
                tv_delivery_address.setText(bean.getF_Address());
            }
        } else {
            ToastUtil.show(addressBean.getInfo());
        }
    }

    @Override
    public void showFailed() {
        ToastUtil.show("地址获取失败,请检查网络!");
    }

    @Override
    public void showFinish() {
        dismissLoadingDialog();
    }
}
