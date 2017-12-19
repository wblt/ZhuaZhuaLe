package com.zhuazhuale.changsha.module.home.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.adapter.AddressAdapter;
import com.zhuazhuale.changsha.module.home.adapter.SpoilsAdapter;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 我的战利品
 * Created by 丁琪 on 2017/12/15.
 */

public class SpoilsActivity extends AppBaseActivity implements View.OnClickListener {

    @BindView(R.id.rv_spoils_list)
    RecyclerView rv_spoils_list;
    @BindView(R.id.tv_spoils_fahuo)
    TextView tv_spoils_fahuo;
    private Intent intent;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_spoils);
    }

    @Override
    protected void initView() {
        getTvToolbarRight().setText("申请发货");
    }

    @Override
    protected void obtainData() {
        showAddressList();

    }

    @Override
    protected void initEvent() {

        tv_spoils_fahuo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_spoils_fahuo:
                intent = new Intent(SpoilsActivity.this, EditAddressActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 抓取记录列表
     */
    private void showAddressList() {
        List<String> strings = new ArrayList<>();
        strings.add("  ");
        strings.add("  ");
        strings.add("  ");
        strings.add("  ");
        strings.add("  ");
        strings.add("  ");
        SpoilsAdapter addressAdapter = new SpoilsAdapter(this, strings);
        rv_spoils_list.setLayoutManager(new LinearLayoutManager(this));
        rv_spoils_list.setHasFixedSize(false);
        rv_spoils_list.setAdapter(addressAdapter);

    }

    /**
     * 进入编辑
     *
     * @param s
     * @param position
     */
    public void goToChangge(String s, int position) {
        intent = new Intent(SpoilsActivity.this, EditAddressActivity.class);
        startActivity(intent);
    }
}
