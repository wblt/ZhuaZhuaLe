package com.zhuazhuale.changsha.module.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.model.entity.table.MovieCollect;
import com.zhuazhuale.changsha.module.home.Bean.DeviceGoodsBean;
import com.zhuazhuale.changsha.util.FrescoUtil;
import com.zhuazhuale.changsha.view.activity.CollectActivity;
import com.zhuazhuale.changsha.view.adapter.base.RecyclerBaseAdapter;
import com.zhuazhuale.changsha.view.adapter.base.ViewHolder;

import java.util.List;

/**
 * author:  ljy
 * date:    2017/9/28
 * description: 我的收藏列表的适配器
 */

public class DeviceGoodsAdapter extends RecyclerBaseAdapter<DeviceGoodsBean.RowsBean> {

    public DeviceGoodsAdapter(@NonNull Context context, @NonNull List<DeviceGoodsBean.RowsBean> mDataList) {
        super(context, mDataList);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, final DeviceGoodsBean.RowsBean rowsBean, final int position) {
        //initView
        SimpleDraweeView sdvMovie = holder.getView(R.id.sdv_devicegoods);
        TextView tv_device_name = holder.getView(R.id.tv_device_name);
        TextView tv_device_price = holder.getView(R.id.tv_device_price);

        //obtainData
        FrescoUtil.getInstance().loadNetImage(sdvMovie, rowsBean.getF_ImgA());//加载网络图片
        tv_device_name.setText(rowsBean.getF_Name());
        tv_device_price.setText(rowsBean.getF_Price() + "/次");

      /*  //initEvent
        //点击该项后，从数据表中删除，并且从界面中移除
        holder.itemView.setOnClickListener(new View.OnClickListener() {
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
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_devicegoods, parent, false);
        return new ViewHolder(view);
    }

}
