package com.zhuazhuale.changsha.module.home.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/13.
 */

public class MineActivity extends AppBaseActivity implements View.OnClickListener {
    @BindView(R.id.iv_home_back)
    ImageView iv_home_back;
    @BindView(R.id.ic_cz)
    View ic_cz;
    @BindView(R.id.ic_wwb)
    View ic_wwb;
    @BindView(R.id.ic_zqjl)
    View ic_zqjl;
    @BindView(R.id.ic_zlp)
    View ic_zlp;
    @BindView(R.id.ic_dd)
    View ic_dd;
    @BindView(R.id.ic_dz)
    View ic_dz;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_mine);
    }

    @Override
    protected void initView() {
        TextView tv_cz = (TextView) ic_cz.findViewById(R.id.tv_list_n);
        TextView tv_wwb = (TextView) ic_wwb.findViewById(R.id.tv_list_n);
        TextView tv_zqjl = (TextView) ic_zqjl.findViewById(R.id.tv_list_n);
        TextView tv_zlp = (TextView) ic_zlp.findViewById(R.id.tv_list_n);
        TextView tv_dd = (TextView) ic_dd.findViewById(R.id.tv_list_n);
        TextView tv_dz = (TextView) ic_dz.findViewById(R.id.tv_list_n);
        tv_cz.setText("充值");
        tv_wwb.setText("我的娃娃币");
        tv_zqjl.setText("我的抓取记录");
        tv_zlp.setText("我的战利品");
        tv_dd.setText("我的订单");
        tv_dz.setText("我的地址");

    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {
        iv_home_back.setOnClickListener(this);
        ic_cz.setOnClickListener(this);
        ic_wwb.setOnClickListener(this);
        ic_zqjl.setOnClickListener(this);
        ic_zlp.setOnClickListener(this);
        ic_dd.setOnClickListener(this);
        ic_dz.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_home_back:
                finish();
                break;
            case R.id.ic_cz:
                break;
            case R.id.ic_wwb:
                break;
            case R.id.ic_zqjl:
                break;
            case R.id.ic_zlp:
                break;
            case R.id.ic_dd:
                break;
            case R.id.ic_dz:
                break;
        }
    }
}
