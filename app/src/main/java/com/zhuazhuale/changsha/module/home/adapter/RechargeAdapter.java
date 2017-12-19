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
import com.zhuazhuale.changsha.module.home.Bean.AllPriceProductBean;
import com.zhuazhuale.changsha.util.FrescoUtil;
import com.zhuazhuale.changsha.view.adapter.base.RecyclerBaseAdapter;
import com.zhuazhuale.changsha.view.adapter.base.ViewHolder;

import java.util.List;

/**
 * author:  丁琪
 * date:    2017/12/15
 * description: 充值列表
 */

public class RechargeAdapter extends RecyclerBaseAdapter<AllPriceProductBean.RowsBean> {


    public RechargeAdapter(@NonNull Context context, @NonNull List<AllPriceProductBean.RowsBean> mDataList) {
        super(context, mDataList);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, final AllPriceProductBean.RowsBean rowsBean, final int position) {
        //initView
        TextView tv_item_recharge_price3 = holder.getView(R.id.tv_item_recharge_price3);
        TextView tv_item_recharge_price2 = holder.getView(R.id.tv_item_recharge_price2);
        TextView tv_item_recharge_price1 = holder.getView(R.id.tv_item_recharge_price1);
        TextView tv_item_recharge_remark = holder.getView(R.id.tv_item_recharge_remark);
        SimpleDraweeView iv_item_recharge_img = holder.getView(R.id.iv_item_recharge_img);
        //obtainData
        tv_item_recharge_price3.setText("充   ￥" + rowsBean.getF_Price3() + " 币");
        tv_item_recharge_price2.setText("送 + " + rowsBean.getF_Price2() + " 币");
        tv_item_recharge_price1.setText(rowsBean.getF_Price1() + "    币");
        tv_item_recharge_remark.setText(rowsBean.getF_Remark());
        switch (rowsBean.getF_Price3()) {
            case 50:
                FrescoUtil.getInstance().loadResourceImage(iv_item_recharge_img, R.mipmap.icon_jinzhua);
                break;
            case 100:
                FrescoUtil.getInstance().loadResourceImage(iv_item_recharge_img, R.mipmap.icon_jindai);
                break;
            case 300:
                FrescoUtil.getInstance().loadResourceImage(iv_item_recharge_img, R.mipmap.icon_jinguan);
                break;
            case 500:
                FrescoUtil.getInstance().loadResourceImage(iv_item_recharge_img, R.mipmap.icon_jinguan);
                break;
            default:
                FrescoUtil.getInstance().loadResourceImage(iv_item_recharge_img, R.mipmap.icon_jinbi);
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
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_recharge, parent, false);
        return new ViewHolder(view);
    }

}
