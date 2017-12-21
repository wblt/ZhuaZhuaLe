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
import com.zhuazhuale.changsha.util.ToastUtil;
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
    @BindView(R.id.iv_record_details_watch)
    ImageView iv_watch;
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
            if (rowsBean.getF_Result() == 0) {
                tv_result.setText("抓取失败");
            } else {
                tv_result.setText("抓取成功");
            }
            switch (rowsBean.getF_VideoUrl()) {

            }
            switch (rowsBean.getF_Appeal()) {
                case 0:
                    tv_shensu.setText("未申诉");
                    break;
                default:
                    tv_shensu.setText("已申诉");
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
        iv_watch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_record_details_shensu:
                switch (rowsBean.getF_Appeal()) {
                    case 0:
                        goToDetails();
                        break;
                    default:
                        ToastUtil.show("您已申诉,请等待工作人员反馈!");
                        break;
                }

                break;
            case R.id.iv_record_details_watch:
                if (rowsBean.getF_VideoUrl().isEmpty()) {
                    ToastUtil.show("没有游戏视频!");
                } else {
                    ToastUtil.show(rowsBean.getF_VideoUrl());
                }
                break;
        }
    }

    /**
     * 申诉
     */
    public void goToDetails() {
        Intent intent = new Intent(RecordDetailsActivity.this, ShenSuActivity.class);
        intent.putExtra("rowsBean", rowsBean);
        startActivityForResult(intent, 110);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 110 && resultCode == 2) {
            tv_shensu.setText("已申诉");
            rowsBean.setF_Appeal(1);
        }
    }
}
