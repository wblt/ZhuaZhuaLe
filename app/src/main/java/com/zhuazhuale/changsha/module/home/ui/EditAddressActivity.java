package com.zhuazhuale.changsha.module.home.ui;

import android.view.View;
import android.widget.ImageView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import butterknife.BindView;

/**
 * 添加和编辑地址
 * Created by 丁琪 on 2017/12/15.
 */

public class EditAddressActivity extends AppBaseActivity implements View.OnClickListener {


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_edit_address);
    }

    @Override
    protected void initView() {

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
