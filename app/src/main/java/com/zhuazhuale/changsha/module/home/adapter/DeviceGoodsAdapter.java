package com.zhuazhuale.changsha.module.home.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.model.entity.res.MovieRes;
import com.zhuazhuale.changsha.model.entity.table.MovieCollect;
import com.zhuazhuale.changsha.module.home.Bean.DeviceGoodsBean;
import com.zhuazhuale.changsha.module.home.fragment.HomeFragment;
import com.zhuazhuale.changsha.module.home.ui.HomeActivity;
import com.zhuazhuale.changsha.util.FrescoUtil;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.view.activity.CollectActivity;
import com.zhuazhuale.changsha.view.adapter.base.RecyclerBaseAdapter;
import com.zhuazhuale.changsha.view.adapter.base.ViewHolder;

import java.util.List;

/**
 * author:  丁琪
 * date:    2017/12/13
 * description: 娃娃机的列表适配器
 */

public class DeviceGoodsAdapter extends RecyclerBaseAdapter<DeviceGoodsBean.RowsBean> {

    private OnHomeClickListener clickListener;

    public DeviceGoodsAdapter(@NonNull Context context, @NonNull List<DeviceGoodsBean.RowsBean> mDataList) {
        super(context, mDataList);
    }

    public void setClickListener(OnHomeClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    protected void bindDataForView(ViewHolder holder, final DeviceGoodsBean.RowsBean rowsBean, final int position) {
        //initView
        ImageView sdvMovie = holder.getView(R.id.sdv_devicegoods);
        TextView tv_device_name = holder.getView(R.id.tv_device_name);
        TextView tv_device_price = holder.getView(R.id.tv_device_price);
        ImageView iv_status = holder.getView(R.id.iv_item_devicegoods_status);
//        TextView tv_status = holder.getView(R.id.tv_item_devicegoods_status);
        switch (rowsBean.getF_Status()) {
            case 1:
                iv_status.setImageResource(R.mipmap.freestatus);
//                tv_status.setText("空闲中");
                break;
            case 2:
                iv_status.setImageResource(R.mipmap.workstatus);
//                tv_status.setText("游戏中");
                break;
            case 3:
                iv_status.setImageResource(R.mipmap.unworkstatus);
//                tv_status.setText("维修中");
                break;
            case 4:
                iv_status.setImageResource(R.mipmap.changestatus);
//                tv_status.setText("更换中");
                break;
        }

        //obtainData
       /* Uri imageUri = Uri.parse(rowsBean.getF_ImgA());
        //开始下载
        sdvMovie.setImageURI(imageUri);*/
//        FrescoUtil.getInstance().loadNetImage(sdvMovie, rowsBean.getF_ImgA());//加载网络图片
        Glide.with(MyApplication.getInstance())
                .load( rowsBean.getF_ImgA())
                .placeholder(R.mipmap.ic_image_load)
                .error(R.mipmap.ic_image_load)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(sdvMovie);
        tv_device_name.setText(rowsBean.getF_Name());
        tv_device_price.setText(rowsBean.getF_Price() + "/次");

        //initEvent
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (rowsBean.getF_Status()) {
                    case 3:
                        ToastUtil.show("机器正在维修中...");
                        break;
                    default:

                        clickListener.onHomeClick(rowsBean);
                        break;

                }

            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_devicegoods, parent, false);
        return new ViewHolder(view);
    }

    public interface OnHomeClickListener {
        void onHomeClick(DeviceGoodsBean.RowsBean rowsBean);
    }
}
