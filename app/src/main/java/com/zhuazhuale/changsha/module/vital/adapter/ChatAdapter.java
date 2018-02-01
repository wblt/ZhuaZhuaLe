package com.zhuazhuale.changsha.module.vital.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tencent.imsdk.TIMTextElem;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.vital.bean.AllUserTrueByDeviceIDBean;
import com.zhuazhuale.changsha.module.vital.bean.MsgBean;
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

public class ChatAdapter extends RecyclerBaseAdapter<MsgBean> {


    public ChatAdapter(@NonNull Context context, @NonNull List<MsgBean> mDataList) {
        super(context, mDataList);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, final MsgBean rowsBean, final int position) {
        //initView
        TextView tv_msg = holder.getView(R.id.tv_msg);
        TextView tv_name = holder.getView(R.id.tv_name);
        tv_name.setText(rowsBean.getGrpSendName());
        TIMTextElem timTextElem = (TIMTextElem) rowsBean.getContext();
        tv_msg.setText(timTextElem.getText());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_chat, parent, false);
        return new ViewHolder(view);
    }

}
