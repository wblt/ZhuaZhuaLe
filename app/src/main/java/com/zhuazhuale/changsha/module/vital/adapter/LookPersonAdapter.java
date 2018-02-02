package com.zhuazhuale.changsha.module.vital.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tencent.imsdk.TIMUserProfile;
import com.zhuazhuale.changsha.R;
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

public class LookPersonAdapter extends RecyclerBaseAdapter<TIMUserProfile> {


    public LookPersonAdapter(@NonNull Context context, @NonNull List<TIMUserProfile> mDataList) {
        super(context, mDataList);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, final TIMUserProfile rowsBean, final int position) {
        //initView
        SimpleDraweeView sdv_look_person_face=holder.getView(R.id.sdv_look_person_face);

        //obtainData
        FrescoUtil.getInstance().loadNetImage(sdv_look_person_face, rowsBean.getFaceUrl());//加载网络图片


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_look_person, parent, false);
        return new ViewHolder(view);
    }

}
