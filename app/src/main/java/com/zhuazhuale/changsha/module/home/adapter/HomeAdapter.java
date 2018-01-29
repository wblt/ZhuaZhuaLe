package com.zhuazhuale.changsha.module.home.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.BaseDataBean;
import com.zhuazhuale.changsha.util.FrescoUtil;
import com.zhuazhuale.changsha.util.IItemOnClickListener;

import java.util.List;

/**
 * 首页轮播图
 * Created by 丁琪 on 2017/7/25.
 */

public class HomeAdapter extends StaticPagerAdapter {
    private Context context;
    private List<BaseDataBean.RowsBean> newsList;
    private IItemOnClickListener onItemClick;

    public HomeAdapter(List<BaseDataBean.RowsBean> imgList) {

        this.newsList = imgList;
    }

    public void reFresh(List<BaseDataBean.RowsBean> imgList) {
        this.newsList = imgList;
        notifyDataSetChanged();
    }


    public void setOnItemClickListener(IItemOnClickListener listener) {
        this.onItemClick = listener;
    }

    @Override
    public View getView(ViewGroup container, final int position) {
        this.context = container.getContext();
        LayoutInflater mInflater = LayoutInflater.from(context);

        View view2 = mInflater.inflate(R.layout.item_home,
                container, false);
        final SimpleDraweeView view = (SimpleDraweeView) view2.findViewById(R.id.iv_home_image);
        Uri imageUri = Uri.parse(newsList.get(position).getF_ImgUrl());
        //开始下载
        view.setImageURI(imageUri);
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.itemOnClick(v, position);
            }
        });

        return view2;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }
}