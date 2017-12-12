package com.zhuazhuale.changsha.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.model.entity.table.MovieCollect;
import com.zhuazhuale.changsha.util.FrescoUtil;
import com.zhuazhuale.changsha.view.activity.CollectActivity;
import com.zhuazhuale.changsha.view.adapter.base.RecyclerBaseAdapter;
import com.zhuazhuale.changsha.view.adapter.base.ViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * author:  ljy
 * date:    2017/9/28
 * description: 我的收藏列表的适配器
 */

public class CollectAdapter extends RecyclerBaseAdapter<MovieCollect> {

    public CollectAdapter(@NonNull Context context, @NonNull List<MovieCollect> mDataList) {
        super(context, mDataList);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, final MovieCollect movieCollect, final int position) {
        //initView
        SimpleDraweeView sdvMovie = holder.getView(R.id.sdv_movie);
        TextView tvTitle = holder.getView(R.id.tv_title);
        TextView tvYear = holder.getView(R.id.tv_year);

        //obtainData
        FrescoUtil.getInstance().loadNetImage(sdvMovie, movieCollect.getMovieImage());//加载网络图片
        tvTitle.setText(movieCollect.getTitle());
        tvYear.setText(movieCollect.getYear());

        //initEvent
        //点击该项后，从数据表中删除，并且从界面中移除
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectActivity mActivity = (CollectActivity) getContext();
                mActivity.deleteCollect(movieCollect);
                removeItem(position);
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_collect, parent, false);
        return new ViewHolder(view);
    }

}
