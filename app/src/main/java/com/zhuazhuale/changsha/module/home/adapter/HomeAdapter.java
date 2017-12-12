package com.zhuazhuale.changsha.module.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.util.FrescoUtil;
import com.zhuazhuale.changsha.util.IItemOnClickListener;

import java.util.List;

/**
 * Created by Administrator on 2017/7/25.
 */

public class HomeAdapter extends StaticPagerAdapter {
    private Context context;
    private List<String> newsList;
    private IItemOnClickListener onItemClick;

    public HomeAdapter(Context context, List<String> imgList) {
        this.context = context;
        this.newsList = imgList;
    }


    public void setOnItemClickListener(IItemOnClickListener listener) {
        this.onItemClick = listener;
    }

    @Override
    public View getView(ViewGroup container, final int position) {

        LayoutInflater mInflater = LayoutInflater.from(context);

        View view2 = mInflater.inflate(R.layout.item_home,
                container, false);
        final SimpleDraweeView view = (SimpleDraweeView) view2.findViewById(R.id.iv_home_image);

//        Tools.GlideCircleImageUrlMall(context, newsList.get(position), view);
        FrescoUtil.getInstance().loadNetImage(view, newsList.get(position));//加载网络图片
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view2.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        return view2;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }
}