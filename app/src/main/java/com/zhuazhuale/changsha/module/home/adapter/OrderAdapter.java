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
import com.zhuazhuale.changsha.module.home.ui.AddressActivity;
import com.zhuazhuale.changsha.module.home.ui.OrderActivity;
import com.zhuazhuale.changsha.util.FrescoUtil;
import com.zhuazhuale.changsha.view.adapter.base.RecyclerBaseAdapter;
import com.zhuazhuale.changsha.view.adapter.base.ViewHolder;

import java.util.List;

/**
 * author:  丁琪
 * date:    2017/12/15
 * description: 订单列表的适配器
 */

public class OrderAdapter extends RecyclerBaseAdapter<OrderBean.RowsBean> {


    public OrderAdapter(@NonNull Context context, @NonNull List<OrderBean.RowsBean> mDataList) {
        super(context, mDataList);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, final OrderBean.RowsBean rowsBean, final int position) {
        //initView
        TextView tv_details = holder.getView(R.id.tv_item_order_details);
        TextView tv_item_order_no = holder.getView(R.id.tv_item_order_no);
        TextView tv_item_order_time = holder.getView(R.id.tv_item_order_time);
        TextView tv_item_order_name = holder.getView(R.id.tv_item_order_name);
        TextView tv_item_order_type = holder.getView(R.id.tv_item_order_type);
        SimpleDraweeView sdv_item_order_img = holder.getView(R.id.sdv_item_order_img);

        //obtainData
        if (rowsBean.getDetail() != null && rowsBean.getDetail().size() > 0) {
            FrescoUtil.getInstance().loadNetImage(sdv_item_order_img, rowsBean.getDetail().get(0).getF_Img());//加载网络图片
            tv_item_order_name.setText(rowsBean.getDetail().get(0).getF_Name());
        } else {
            FrescoUtil.getInstance().loadNetImage(sdv_item_order_img, "");//加载网络图片
            tv_item_order_name.setText("");
        }

        tv_item_order_no.setText(rowsBean.getF_OrderNo());
        tv_item_order_time.setText(rowsBean.getF_CreateTime());
        switch (rowsBean.getF_Status()) {
            case 1:
                tv_item_order_type.setText("未发货");
                break;
            default:
                tv_item_order_type.setText("已发货");
                break;
        }
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
        tv_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderActivity mActivity = (OrderActivity) getContext();
                mActivity.goToDetails(rowsBean, position);
            }
        });

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

}
