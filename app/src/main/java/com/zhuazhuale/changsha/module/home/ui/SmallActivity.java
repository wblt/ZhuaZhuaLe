package com.zhuazhuale.changsha.module.home.ui;

import android.support.v7.widget.RecyclerView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import butterknife.BindView;

/**
 * 商城
 * Created by Administrator on 2018/2/28 0028.
 */

public class SmallActivity extends AppBaseActivity{
    @BindView(R.id.rv_small_list)
    RecyclerView rv_small_list;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_small);
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
}
