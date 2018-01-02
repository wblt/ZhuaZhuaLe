package com.zhuazhuale.changsha.module.vital.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.AddressBean;
import com.zhuazhuale.changsha.module.home.ui.AddressActivity;
import com.zhuazhuale.changsha.module.vital.bean.AllUserTrueByDeviceIDBean;
import com.zhuazhuale.changsha.module.vital.ui.PlayActivity;
import com.zhuazhuale.changsha.util.FrescoUtil;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.view.adapter.base.RecyclerBaseAdapter;
import com.zhuazhuale.changsha.view.adapter.base.ViewHolder;

import java.util.List;

/**
 * author:  丁琪
 * date:    2017/12/15
 * description: 用户抓取成功记录
 */

public class AllTrueAdapter extends RecyclerBaseAdapter<AllUserTrueByDeviceIDBean.RowsBean> {


    public AllTrueAdapter(@NonNull Context context, @NonNull List<AllUserTrueByDeviceIDBean.RowsBean> mDataList) {
        super(context, mDataList);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, final AllUserTrueByDeviceIDBean.RowsBean rowsBean, final int position) {
        //initView
        SimpleDraweeView sdv_item_alltrueimg=holder.getView(R.id.sdv_item_alltrueimg);
        TextView tv_item_alltrue_name = holder.getView(R.id.tv_item_alltrue_name);
        TextView tv_item_alltrue_time = holder.getView(R.id.tv_item_alltrue_time);
        TextView tv_item_alltrue_bo = holder.getView(R.id.tv_item_alltrue_bo);


        //obtainData
        FrescoUtil.getInstance().loadNetImage(sdv_item_alltrueimg, rowsBean.getF_UserImg());//加载网络图片
        tv_item_alltrue_name.setText(rowsBean.getF_UserName());
        tv_item_alltrue_time.setText(rowsBean.getF_CreateTime());
        //initEvent

       tv_item_alltrue_bo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rowsBean.getF_VideoUrl().isEmpty()){
                    ToastUtil.show("没有视频!");
                    return;
                }else {
                    PlayActivity mActivity = (PlayActivity) getContext();
                    mActivity.LookMovie(rowsBean);
                }

            }
        });


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_alltrue, parent, false);
        return new ViewHolder(view);
    }

}
