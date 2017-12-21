package com.zhuazhuale.changsha.module.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.GradWaterBean;
import com.zhuazhuale.changsha.module.home.ui.RecordActivity;
import com.zhuazhuale.changsha.util.FrescoUtil;
import com.zhuazhuale.changsha.view.adapter.base.RecyclerBaseAdapter;
import com.zhuazhuale.changsha.view.adapter.base.ViewHolder;

import java.util.List;

/**
 * author:  丁琪
 * date:    2017/12/15
 * description: 抓取记录
 */

public class RecordAdapter extends RecyclerBaseAdapter<GradWaterBean.RowsBean> {


    public RecordAdapter(@NonNull Context context, @NonNull List<GradWaterBean.RowsBean> mDataList) {
        super(context, mDataList);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, final GradWaterBean.RowsBean rowsBean, final int position) {
        //initView
        SimpleDraweeView sdv_img = holder.getView(R.id.sdv_item_record_img);
        TextView tv_goodsname = holder.getView(R.id.tv_item_record_goodsname);
        TextView tv_valid = holder.getView(R.id.tv_item_record_valid);
        TextView tv_result = holder.getView(R.id.tv_item_record_result);
        TextView tv_creattime = holder.getView(R.id.tv_item_record_creattime);
        TextView tv_go = holder.getView(R.id.tv_item_record_go);

        //obtainData
        FrescoUtil.getInstance().loadNetImage(sdv_img, rowsBean.getF_GoodsImgA());//加载网络图片
        tv_goodsname.setText(rowsBean.getF_GoodsName());
        if (rowsBean.getF_Result() == 0) {
            tv_valid.setText("抓取失败");
        } else {
            tv_valid.setText("抓取成功");
        }
        switch (rowsBean.getF_VideoUrl()) {

        }
        switch (rowsBean.getF_Appeal()) {
            case 0:
                tv_result.setText("未申诉");
                break;
            default:
                tv_result.setText("已申诉");
                break;
        }
        tv_creattime.setText(rowsBean.getF_CreateTime() + "");
        //initEvent
        //点击该项后，从数据表中删除，并且从界面中移除
       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectActivity mActivity = (CollectActivity) getContext();
                mActivity.deleteCollect(rowsBean);
                removeItem(position);
            }
        });*/
        tv_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordActivity mActivity = (RecordActivity) getContext();
                mActivity.goToDetails(rowsBean, position);
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_record, parent, false);
        return new ViewHolder(view);
    }

}
