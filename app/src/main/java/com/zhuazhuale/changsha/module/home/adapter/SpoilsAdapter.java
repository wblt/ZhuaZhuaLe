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
import com.zhuazhuale.changsha.module.home.Bean.SpoilsBean;
import com.zhuazhuale.changsha.module.home.ui.AddressActivity;
import com.zhuazhuale.changsha.module.home.ui.SpoilsActivity;
import com.zhuazhuale.changsha.util.FrescoUtil;
import com.zhuazhuale.changsha.view.adapter.base.RecyclerBaseAdapter;
import com.zhuazhuale.changsha.view.adapter.base.ViewHolder;

import java.util.List;

import static com.zhuazhuale.changsha.R.id.tv_device_name;

/**
 * author:  丁琪
 * date:    2017/12/15
 * description: 我的战利品的适配器
 */

public class SpoilsAdapter extends RecyclerBaseAdapter<SpoilsBean.RowsBean> {


    public SpoilsAdapter(@NonNull Context context, @NonNull List<SpoilsBean.RowsBean> mDataList) {
        super(context, mDataList);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, final SpoilsBean.RowsBean rowsBean, final int position) {
        //initView
        SimpleDraweeView sdv_spoils_img = holder.getView(R.id.sdv_item_spoils_img);
        ImageView iv_spoils_check = holder.getView(R.id.iv_item_spoils_check);
        TextView tv_spoils_name = holder.getView(R.id.tv_item_spoils_name);
        TextView tv_item_spoils_info = holder.getView(R.id.tv_item_spoils_info);
        TextView tv_item_spoils_dh = holder.getView(R.id.tv_item_spoils_dh);

        //obtainData
        FrescoUtil.getInstance().loadNetImage(sdv_spoils_img, rowsBean.getF_Img());//加载网络图片
        tv_spoils_name.setText(rowsBean.getF_Name());
        tv_item_spoils_info.setText("可以兑换 " + rowsBean.getF_ExChangePrice() + " 娃娃币");

        if (rowsBean.isCheck()) {
            iv_spoils_check.setImageResource(R.mipmap.selector_on);
        } else {
            iv_spoils_check.setImageResource(R.mipmap.selector_off);
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
        iv_spoils_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //改变选择的状态
                if (rowsBean.isCheck()) {
                    rowsBean.setCheck(false);
                } else {
                    rowsBean.setCheck(true);
                }
                updateAll();
            }
        });
        tv_item_spoils_dh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpoilsActivity mActivity = (SpoilsActivity) getContext();
                mActivity.duiHuan(rowsBean, position);
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_spoils, parent, false);
        return new ViewHolder(view);
    }

}
