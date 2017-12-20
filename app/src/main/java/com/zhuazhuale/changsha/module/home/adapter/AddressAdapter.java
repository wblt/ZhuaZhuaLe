package com.zhuazhuale.changsha.module.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.AddressBean;
import com.zhuazhuale.changsha.module.home.ui.AddressActivity;
import com.zhuazhuale.changsha.util.FrescoUtil;
import com.zhuazhuale.changsha.view.adapter.base.RecyclerBaseAdapter;
import com.zhuazhuale.changsha.view.adapter.base.ViewHolder;

import java.util.List;

/**
 * author:  丁琪
 * date:    2017/12/15
 * description: 用户地址列表的适配器
 */

public class AddressAdapter extends RecyclerBaseAdapter<AddressBean.RowsBean> {


    public AddressAdapter(@NonNull Context context, @NonNull List<AddressBean.RowsBean> mDataList) {
        super(context, mDataList);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, final AddressBean.RowsBean rowsBean, final int position) {
        //initView
        TextView tv_item_address_name = holder.getView(R.id.tv_item_address_name);
        TextView tv_item_address_phone = holder.getView(R.id.tv_item_address_phone);
        TextView tv_item_address_dz = holder.getView(R.id.tv_item_address_dz);
        TextView tv_item_address_bj = holder.getView(R.id.tv_item_address_bj);
        TextView tv_item_address_sc = holder.getView(R.id.tv_item_address_sc);

        //obtainData
//        FrescoUtil.getInstance().loadNetImage(sdvMovie, rowsBean.getF_ImgA());//加载网络图片
        tv_item_address_name.setText(rowsBean.getF_Consignee());
        tv_item_address_phone.setText(rowsBean.getF_Mobile());
        tv_item_address_dz.setText(rowsBean.getF_Address());
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
        tv_item_address_bj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddressActivity mActivity = (AddressActivity) getContext();
                mActivity.goToChangge(rowsBean, position);
            }
        });
        tv_item_address_sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddressActivity mActivity = (AddressActivity) getContext();
                mActivity.deleteAddress(rowsBean, position);
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_address, parent, false);
        return new ViewHolder(view);
    }

}
