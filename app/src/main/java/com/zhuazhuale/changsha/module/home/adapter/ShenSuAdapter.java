package com.zhuazhuale.changsha.module.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.LiYouBean;
import com.zhuazhuale.changsha.module.home.ui.AddressActivity;
import com.zhuazhuale.changsha.module.home.ui.ShenSuActivity;
import com.zhuazhuale.changsha.view.adapter.base.RecyclerBaseAdapter;
import com.zhuazhuale.changsha.view.adapter.base.ViewHolder;

import java.util.List;

/**
 * author:  丁琪
 * date:    2017/12/15
 * description: 申诉列表的适配器
 */

public class ShenSuAdapter extends RecyclerBaseAdapter<LiYouBean> {


    public ShenSuAdapter(@NonNull Context context, @NonNull List<LiYouBean> mDataList) {
        super(context, mDataList);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, final LiYouBean liYouBean, final int position) {
        //initView
        TextView tv_item_liyou = holder.getView(R.id.tv_item_liyou);
        tv_item_liyou.setText(liYouBean.getLiYou());
        if (liYouBean.isClick()) {
            tv_item_liyou.setBackgroundColor(Color.parseColor("#888888"));
        } else {
            tv_item_liyou.setBackgroundColor(Color.parseColor("#ffffff"));

        }

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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShenSuActivity mActivity = (ShenSuActivity) getContext();
                mActivity.goToChangge(liYouBean, position);
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_shensu, parent, false);
        return new ViewHolder(view);
    }

}
