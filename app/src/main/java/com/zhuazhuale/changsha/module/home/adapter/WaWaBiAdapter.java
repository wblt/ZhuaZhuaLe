package com.zhuazhuale.changsha.module.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.BanlanceWaterBean;
import com.zhuazhuale.changsha.util.FrescoUtil;
import com.zhuazhuale.changsha.view.adapter.base.RecyclerBaseAdapter;
import com.zhuazhuale.changsha.view.adapter.base.ViewHolder;

import java.util.List;

/**
 * author:  丁琪
 * date:    2017/12/15
 * description: 娃娃币
 */

public class WaWaBiAdapter extends RecyclerBaseAdapter<BanlanceWaterBean.RowsBean> {


    public WaWaBiAdapter(@NonNull Context context, @NonNull List<BanlanceWaterBean.RowsBean> mDataList) {
        super(context, mDataList);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, final BanlanceWaterBean.RowsBean rowsBean, final int position) {
        //initView
        SimpleDraweeView sdv_item_wawabi_img = holder.getView(R.id.sdv_item_wawabi_img);
        TextView tv_item_wawabi_goodsname = holder.getView(R.id.tv_item_wawabi_goodsname);
        TextView tv_item_wawabi_amout = holder.getView(R.id.tv_item_wawabi_amout);
        TextView tv_item_wawabi_creattime = holder.getView(R.id.tv_item_wawabi_creattime);
        TextView tv_item_wawabi_bi = holder.getView(R.id.tv_item_wawabi_bi);
        TextView tv_item_wawabi_type = holder.getView(R.id.tv_item_wawabi_type);

        //obtainData
        FrescoUtil.getInstance().loadNetImage(sdv_item_wawabi_img, rowsBean.getF_GoodsImgA());//加载网络图片
        tv_item_wawabi_goodsname.setText(rowsBean.getF_GoodsName());
        tv_item_wawabi_amout.setText(rowsBean.getF_Amout() + "");
        tv_item_wawabi_creattime.setText(rowsBean.getF_CreateTime() + "");

        switch (rowsBean.getF_Type()) {
            case 1:
                //充值
                tv_item_wawabi_type.setText("微信充值  +   ");
                tv_item_wawabi_bi.setTextColor(Color.parseColor("#7CD237"));
                tv_item_wawabi_bi.setText("+ " + rowsBean.getF_Amout());
                break;
            case 2:
                //充值
                tv_item_wawabi_type.setText("支付宝充值  +   ");
                tv_item_wawabi_bi.setTextColor(Color.parseColor("#7CD237"));
                tv_item_wawabi_bi.setText("+ " + rowsBean.getF_Amout());
                break;
            case 3:
                //充值
                tv_item_wawabi_type.setText("好友赠送  +   ");
                tv_item_wawabi_bi.setTextColor(Color.parseColor("#7CD237"));
                tv_item_wawabi_bi.setText("+ " + rowsBean.getF_Amout());
                break;
            case 4:
                //消费
                tv_item_wawabi_bi.setTextColor(Color.parseColor("#FF4081"));
                tv_item_wawabi_bi.setText("- " + rowsBean.getF_Amout());
                break;
            case 5:
                //游戏退换
                tv_item_wawabi_type.setText("游戏退换  +   ");
                tv_item_wawabi_bi.setTextColor(Color.parseColor("#7CD237"));
                tv_item_wawabi_bi.setText("+ " + rowsBean.getF_Amout());
                break;
            case 6:
                //游戏退换
                tv_item_wawabi_type.setText("游戏兑换  +   ");
                tv_item_wawabi_bi.setTextColor(Color.parseColor("#7CD237"));
                tv_item_wawabi_bi.setText("+ " + rowsBean.getF_Amout());
                break;
            case 7:
                //游戏退换
                tv_item_wawabi_type.setText("邀请奖励  +   ");
                tv_item_wawabi_bi.setTextColor(Color.parseColor("#7CD237"));
                tv_item_wawabi_bi.setText("+ " + rowsBean.getF_Amout());
                break;
            case 8:
                //游戏退换
                tv_item_wawabi_type.setText("签到奖励  +   ");
                tv_item_wawabi_bi.setTextColor(Color.parseColor("#7CD237"));
                tv_item_wawabi_bi.setText("+ " + rowsBean.getF_Amout());
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

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_wawabi, parent, false);
        return new ViewHolder(view);
    }

}
