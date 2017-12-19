package com.zhuazhuale.changsha.module.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.ui.AddressActivity;
import com.zhuazhuale.changsha.module.home.ui.OrderActivity;
import com.zhuazhuale.changsha.view.adapter.base.RecyclerBaseAdapter;
import com.zhuazhuale.changsha.view.adapter.base.ViewHolder;

import java.util.List;

/**
 * author:  丁琪
 * date:    2017/12/15
 * description: 订单列表的适配器
 */

public class OrderAdapter extends RecyclerBaseAdapter<String> {


    public OrderAdapter(@NonNull Context context, @NonNull List<String> mDataList) {
        super(context, mDataList);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, final String s, final int position) {
        //initView
        TextView tv_details = holder.getView(R.id.tv_item_order_details);
        /*
        TextView tv_item_address_phone = holder.getView(R.id.tv_item_address_phone);
        TextView tv_item_address_dz = holder.getView(R.id.tv_item_address_dz);
        TextView tv_item_address_bj = holder.getView(R.id.tv_item_address_bj);
        TextView tv_item_address_sc = holder.getView(R.id.tv_item_address_sc);*/

        //obtainData
      /*  FrescoUtil.getInstance().loadNetImage(sdvMovie, rowsBean.getF_ImgA());//加载网络图片
        tv_device_name.setText(rowsBean.getF_Name());
        tv_device_price.setText(rowsBean.getF_Price() + "/次");*/
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
                mActivity.goToDetails(s, position);
            }
        });

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

}
