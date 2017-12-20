package com.zhuazhuale.changsha.module.home.ui;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.AddressBean;
import com.zhuazhuale.changsha.module.home.Bean.EditAddressBean;
import com.zhuazhuale.changsha.module.home.presenter.EditAddressPresenter;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import butterknife.BindView;

/**
 * 添加和编辑地址
 * Created by 丁琪 on 2017/12/15.
 */

public class EditAddressActivity extends AppBaseActivity implements View.OnClickListener, IEditAddressView {
    @BindView(R.id.et_edit_address_name)
    EditText et_edit_address_name;
    @BindView(R.id.et_edit_address_phone)
    EditText et_edit_address_phone;
    @BindView(R.id.et_edit_address_address)
    EditText et_edit_address_address;
    @BindView(R.id.tv_edit_address_add)
    TextView tv_edit_address_add;


    private AddressBean.RowsBean rowsBean;
    private EditAddressPresenter presenter;
    private String id;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_edit_address);
        Intent intent = getIntent();
        rowsBean = (AddressBean.RowsBean) intent.getSerializableExtra("AddressBean");
    }

    @Override
    protected void initView() {
        if (rowsBean != null) {
            et_edit_address_name.setText(rowsBean.getF_Consignee());
            et_edit_address_phone.setText(rowsBean.getF_Mobile());
            et_edit_address_address.setText(rowsBean.getF_Address());
            id = rowsBean.getF_ID();
        } else {
            id = "";
        }

    }

    @Override
    protected void obtainData() {
        presenter = new EditAddressPresenter(this);


    }

    @Override
    protected void initEvent() {
        tv_edit_address_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_edit_address_add:
                String name = et_edit_address_name.getText().toString().trim();
                String phone = et_edit_address_phone.getText().toString().trim();
                String address = et_edit_address_address.getText().toString().trim();

                if (name.isEmpty()) {
                    ToastUtil.show("请填写姓名!");
                    return;
                }
                if (phone.isEmpty()) {
                    ToastUtil.show("请填写手机号码!");
                    return;
                }
                if (address.isEmpty()) {
                    ToastUtil.show("请填写地址!");
                    return;
                }
                showLoadingDialog();
                //设置不可重复点击
                tv_edit_address_add.setEnabled(false);
                presenter.initModifyUserAddress(address, name, id, phone);

                break;
        }
    }

    /**
     * 添加成功
     *
     * @param editAddressBean
     */
    @Override
    public void showModifyUserAddress(EditAddressBean editAddressBean) {
        if (editAddressBean.getCode() == 0) {
            ToastUtil.show(editAddressBean.getInfo());
        } else {
            ToastUtil.show(editAddressBean.getInfo());
            setResult(2);
            finish();
        }
    }

    /**
     * 接口失败
     */
    @Override
    public void showFailed() {
        ToastUtil.show("网络不稳定,请检查重试!");
    }

    /**
     * 请求结束
     */
    @Override
    public void showFinish() {
        tv_edit_address_add.setEnabled(true);
        dismissLoadingDialog();
    }
}
