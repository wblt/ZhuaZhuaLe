package com.zhuazhuale.changsha.module.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.OrderBean;
import com.zhuazhuale.changsha.module.home.ui.OrderDetailsActivity;
import com.zhuazhuale.changsha.util.FrescoUtil;
import com.zhuazhuale.changsha.view.adapter.base.RecyclerBaseAdapter;
import com.zhuazhuale.changsha.view.adapter.base.ViewHolder;

import java.util.List;

/**
 * author:  丁琪
 * date:    2017/12/15
 * description: 订单详情商品列表
 */

public class OrderDetailsAdapter extends RecyclerBaseAdapter<OrderBean.RowsBean.DetailBean> {


    public OrderDetailsAdapter(@NonNull Context context, @NonNull List<OrderBean.RowsBean.DetailBean> mDataList) {
        super(context, mDataList);
    }


    @Override
    protected void bindDataForView(ViewHolder holder, final OrderBean.RowsBean.DetailBean detailBean, final int position) {
        //initView
        SimpleDraweeView sdv_img = holder.getView(R.id.sdv_item_order_details_img);
        TextView tv_name = holder.getView(R.id.tv_item_order_details_name);


        //obtainData
        FrescoUtil.getInstance().loadNetImage(sdv_img, detailBean.getF_Img());//加载网络图片
        tv_name.setText(detailBean.getF_Name());
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
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_order_details, parent, false);
        return new ViewHolder(view);
    }

}
