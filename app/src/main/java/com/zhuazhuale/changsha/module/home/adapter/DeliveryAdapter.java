package com.zhuazhuale.changsha.module.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.GradWaterBean;
import com.zhuazhuale.changsha.module.home.Bean.SpoilsBean;
import com.zhuazhuale.changsha.module.home.ui.RecordActivity;
import com.zhuazhuale.changsha.util.FrescoUtil;
import com.zhuazhuale.changsha.view.adapter.base.RecyclerBaseAdapter;
import com.zhuazhuale.changsha.view.adapter.base.ViewHolder;

import java.util.List;

/**
 * author:  丁琪
 * date:    2017/12/15
 * description: 申请发货列表
 */

public class DeliveryAdapter extends RecyclerBaseAdapter<SpoilsBean.RowsBean> {


    public DeliveryAdapter(@NonNull Context context, @NonNull List<SpoilsBean.RowsBean> mDataList) {
        super(context, mDataList);
    }


    @Override
    protected void bindDataForView(ViewHolder holder, final SpoilsBean.RowsBean rowsBean, final int position) {
        //initView
        SimpleDraweeView sdv_img = holder.getView(R.id.sdv_item_delivery_img);
        TextView tv_goodsname = holder.getView(R.id.tv_item_delivery_goodsname);
        TextView tv_type = holder.getView(R.id.tv_item_delivery_type);
        TextView tv_cp = holder.getView(R.id.tv_item_delivery_cp);
        TextView tv_num = holder.getView(R.id.tv_item_delivery_num);

        //obtainData
        FrescoUtil.getInstance().loadNetImage(sdv_img, rowsBean.getF_Img());//加载网络图片
        tv_goodsname.setText(rowsBean.getF_Name());
        tv_type.setText("大");
        tv_cp.setText(rowsBean.getF_ExChangePrice()+"");
        tv_num.setText("x1");

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

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_delivery, parent, false);
        return new ViewHolder(view);
    }

}
