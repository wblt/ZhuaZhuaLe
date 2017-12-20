package com.zhuazhuale.changsha.module.home.ui;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.AddressBean;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import butterknife.BindView;

/**
 * 添加和编辑地址
 * Created by 丁琪 on 2017/12/15.
 */

public class EditAddressActivity extends AppBaseActivity implements View.OnClickListener {
    @BindView(R.id.et_edit_address_name)
    EditText et_edit_address_name;
    @BindView(R.id.et_edit_address_phone)
    EditText et_edit_address_phone;
    @BindView(R.id.et_edit_address_address)
    EditText et_edit_address_address;


    private AddressBean.RowsBean rowsBean;

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
        }

    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
