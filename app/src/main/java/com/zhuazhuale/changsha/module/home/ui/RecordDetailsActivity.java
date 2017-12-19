package com.zhuazhuale.changsha.module.home.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.GradWaterBean;
import com.zhuazhuale.changsha.module.home.adapter.RecordAdapter;
import com.zhuazhuale.changsha.module.home.adapter.WaWaBiAdapter;
import com.zhuazhuale.changsha.util.FrescoUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 抓取详情
 * Created by 丁琪 on 2017/12/18.
 */

public class RecordDetailsActivity extends AppBaseActivity implements View.OnClickListener {

    @BindView(R.id.sdv_record_details_img)
    SimpleDraweeView sdv_img;
    @BindView(R.id.tv_record_details_goodsname)
    TextView tv_goodsname;
    @BindView(R.id.tv_record_details_result)
    TextView tv_result;
    @BindView(R.id.tv_record_details_creattime)
    TextView tv_creattime;
    @BindView(R.id.tv_record_details_deviceno)
    TextView tv_deviceno;
    @BindView(R.id.tv_record_details_shensu)
    TextView tv_shensu;
    private GradWaterBean.RowsBean rowsBean;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_record_detais);
        Intent intent = getIntent();
        rowsBean = (GradWaterBean.RowsBean) intent.getSerializableExtra("rowsBean");

    }

    @Override
    protected void initView() {
        if (rowsBean != null) {
            FrescoUtil.getInstance().loadNetImage(sdv_img, rowsBean.getF_GoodsImgA());//加载网络图片
            tv_goodsname.setText(rowsBean.getF_GoodsName());
            tv_creattime.setText(rowsBean.getF_CreateTime());
            tv_deviceno.setText(rowsBean.getF_DeviceNo());
            if (rowsBean.isF_Valid()) {
                tv_result.setText("抓取失败");
            } else {
                tv_result.setText("抓取成功");
            }
            switch (rowsBean.getF_VideoUrl()) {

            }
            switch (rowsBean.getF_Result()) {
                case 0:
                    tv_shensu.setText("未申诉");
                    break;
                case 1:
                    tv_shensu.setText("申诉中");
                    break;
            }

        }
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {
        tv_shensu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_record_details_shensu:
                goToDetails();
                break;
        }
    }

    /**
     * 申诉
     */
    public void goToDetails() {
        Intent intent = new Intent(RecordDetailsActivity.this, ShenSuActivity.class);
        startActivity(intent);
    }
}
