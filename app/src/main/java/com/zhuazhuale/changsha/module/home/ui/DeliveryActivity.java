package com.zhuazhuale.changsha.module.home.ui;

import android.content.Intent;
import android.location.Address;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.model.entity.eventbus.AddressEvent;
import com.zhuazhuale.changsha.model.entity.eventbus.CPfreshEvent;
import com.zhuazhuale.changsha.module.home.Bean.AddressBean;
import com.zhuazhuale.changsha.module.home.Bean.EditAddressBean;
import com.zhuazhuale.changsha.module.home.Bean.NewCPBean;
import com.zhuazhuale.changsha.module.home.Bean.SpoilsBean;
import com.zhuazhuale.changsha.module.home.adapter.DeliveryAdapter;
import com.zhuazhuale.changsha.module.home.presenter.DeliveryPresenter;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.EventBusUtil;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;
import com.zhuazhuale.changsha.view.widget.MaterialDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
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
    @BindView(R.id.tv_delivery_submit)
    TextView tv_delivery_submit;
    @BindView(R.id.tv_delivery_dhby)
    TextView tv_delivery_dhby;
    @BindView(R.id.et_delivery_remark)
    EditText et_delivery_remark;
    @BindView(R.id.tv_delivery_by)
    TextView tv_delivery_by;

    private List<AddressBean.RowsBean> beans;
    private AddressBean address;
    private DeliveryPresenter presenter;
    private MaterialDialog mDialog1;
    private MaterialDialog mDialog2;
    private MaterialDialog mDialog3;
    private List<SpoilsBean.RowsBean> beanList;
    private List<String> goodsIDs;
    private String name;
    private String phone;
    private String address1;
    private String remark;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_delivery);
    }

    @Override
    protected void initView() {
        mDialog1 = new MaterialDialog(this);
        mDialog2 = new MaterialDialog(this);
        mDialog3 = new MaterialDialog(this);
        getTvToolbarRight().setText("充值");
        mDialog1.setTitle("城市抓抓乐温馨提示");
        mDialog1.setMessage("申请发货将扣除一次包邮劵");

        mDialog1.setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog1.dismiss();
            }
        });

        mDialog1.show();
    }

    @Override
    protected void obtainData() {
        presenter = new DeliveryPresenter(this);
        showLoadingDialog("");
        presenter.initUserAddress(0);
        Intent intent = getIntent();
        spoilsBean = (SpoilsBean) intent.getSerializableExtra("SpoilsBean");
        tv_delivery_num.setText(spoilsBean.getRows().size() + " 件商品");
        showDelivery(spoilsBean);
        presenter.initNewCP();
        EventBusUtil.register(this);//订阅事件
    }

    //EventBus的事件接收，从事件中获取最新的收藏数量并更新界面展示
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(AddressEvent event) {
        String code = event.getAddressFresh();
        presenter.initUserAddress(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅
        EventBusUtil.unregister(this);
    }

    private void showDelivery(SpoilsBean spoilsBean) {
        DeliveryAdapter adapter = new DeliveryAdapter(this, spoilsBean.getRows());
        rv_delivery_list.setLayoutManager(new LinearLayoutManager(this));
        rv_delivery_list.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {
        ll_delivery_address.setOnClickListener(this);
        tv_delivery_submit.setOnClickListener(this);
        tv_delivery_dhby.setOnClickListener(this);
        getTvToolbarRight().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), RechargeActivity.class));
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_delivery_address:
                if (address != null && address.getRows() != null && address.getRows().size() > 0) {

                    Intent intent = new Intent(getContext(), AddressListActivity.class);
                    intent.putExtra("address", address);
                    startActivityForResult(intent, 110);

                } else {
                    ToastUtil.show("还没有收货地址，赶紧添加一条！");
                    Intent intent = new Intent(getContext(), AddressActivity.class);
                    intent.putExtra("address", address);
                    startActivityForResult(intent, 110);
                }

                break;
            case R.id.tv_delivery_submit:
                name = tv_delivery_name.getText().toString();
                phone = tv_delivery_phone.getText().toString();
                address1 = tv_delivery_address.getText().toString();
                remark = et_delivery_remark.getText().toString();
                if (name.isEmpty()) {
                    ToastUtil.show("收货人不能为空");
                    return;
                }
                if (phone.isEmpty()) {
                    ToastUtil.show("收货人电话不能为空");
                    return;
                }
                if (address1.isEmpty()) {
                    ToastUtil.show("收货人地址不能为空");
                    return;
                }
                if (remark.isEmpty()) {
                    remark = "";
                }
                mDialog2.setTitle("娃娃乐温馨提示");
                mDialog2.setMessage("是否提交订单");

                mDialog2.setPositiveButton("提交", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog2.dismiss();
                        showLoadingDialog("");
                        // 先修改商品的选择状态,再提交订单
                        modifyUserGoods();
                    }
                });
                mDialog2.setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog2.dismiss();
                    }
                });
                mDialog2.show();
                break;
            case R.id.tv_delivery_dhby:
                mDialog3.setTitle("城市抓抓乐温馨提示");
                mDialog3.setMessage("您确定使用88游戏币兑换包邮劵么");

                mDialog3.setPositiveButton("确定兑换", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog3.dismiss();
                        showLoadingDialog("");
                        presenter.initDuiHuan();
                    }
                });
                mDialog3.setNegativeButton("取消兑换", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog3.dismiss();
                    }
                });
                mDialog3.show();
                break;
        }
    }

    /**
     * 先修改商品的选择状态,再提交订单
     */
    private void modifyUserGoods() {
        beanList = spoilsBean.getRows();
        goodsIDs = new ArrayList<>();
        for (SpoilsBean.RowsBean bean : beanList) {
            goodsIDs.add(bean.getF_ID());
        }
        presenter.initModifyUserGoods(goodsIDs);
    }

    /**
     * 提交订单
     */
    private void submitOrder() {

        presenter.initCreateOrder(name, phone, address1, remark);
    }

    /**
     * 地址请求成功
     *
     * @param addressBean
     */
    @Override
    public void showUserAddress(AddressBean addressBean) {
        address = addressBean;

        if (addressBean.getCode() == 1) {
            beans = addressBean.getRows();
            if (beans.size() > 0) {
                AddressBean.RowsBean bean = beans.get(0);
                showNewAddress(bean);

            }
        } else {
            ToastUtil.show(addressBean.getInfo());
            showNoAddress();
           /* getTvToolbarRight().setText("添加收货地址");
            getTvToolbarRight().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getContext(), AddressActivity.class));
                }
            });*/
        }
    }

    private void showNoAddress() {
        tv_delivery_name.setText("");
        tv_delivery_phone.setText("");
        tv_delivery_address.setText("");
    }

    @Override
    public void showFailed() {
        ToastUtil.show("地址获取失败,请检查网络!");
    }

    @Override
    public void showFinish() {
        dismissLoadingDialog();
    }

    /**
     * 修改商品选择状态成功
     *
     * @param bean
     */
    @Override
    public void showModifyUserGoods(EditAddressBean bean) {
        if (bean.getCode() == 1) {
            submitOrder();
        } else {
            ToastUtil.show(bean.getInfo());
        }
    }

    /**
     * 生成订单成功
     *
     * @param bean
     */
    @Override
    public void showCreateOrder(EditAddressBean bean) {
        if (bean.getCode() == 1) {
            ToastUtil.show("√ 生成订单成功");
            CPfreshEvent cPfreshEvent=new CPfreshEvent("刷新");
            EventBusUtil.postEvent(cPfreshEvent);
            setResult(2);
            finish();
            Intent intent=new Intent(this,OrderActivity.class);
            startActivity(intent);
        } else {
            ToastUtil.show("× 生成订单失败");
            ToastUtil.show(bean.getInfo());
        }
    }

    @Override
    public void showDuiHuan(NewCPBean newCPBean) {
        ToastUtil.show(newCPBean.getInfo());
        dismissLoadingDialog();
        if (newCPBean.getCode() == 1) {
            presenter.initNewCP();
            CPfreshEvent cPfreshEvent=new CPfreshEvent("刷新");
            EventBusUtil.postEvent(cPfreshEvent);
        }
    }

    @Override
    public void showNewCP(NewCPBean newCPBean) {
        if (newCPBean.getCode() == 1) {
            tv_delivery_by.setText("包邮: " + newCPBean.getRows().getF_BagNumber());
        } else {
            ToastUtil.show(newCPBean.getInfo());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 110 && resultCode == 2) {
            AddressBean.RowsBean bean = (AddressBean.RowsBean) data.getSerializableExtra("rowsBean");
            showNewAddress(bean);
        }
    }

    private void showNewAddress(AddressBean.RowsBean bean) {
        tv_delivery_name.setText(bean.getF_Consignee());
        tv_delivery_phone.setText(bean.getF_Mobile());
        tv_delivery_address.setText(bean.getF_Address());
//        getTvToolbarRight().setText("");
    }
}
