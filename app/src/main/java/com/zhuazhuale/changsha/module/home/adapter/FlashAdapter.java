package com.zhuazhuale.changsha.module.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.BaseDataBean;
import com.zhuazhuale.changsha.util.FrescoUtil;
import com.zhuazhuale.changsha.util.IItemOnClickListener;

import java.util.List;

/**
 * flash
 * Created by 丁琪 on 2017/7/25.
 */

public class FlashAdapter extends StaticPagerAdapter {
    private Context context;
    private List<Integer> newsList;
    private IItemOnClickListener onItemClick;

    public FlashAdapter(Context context, List<Integer> imgList) {
        this.context = context;
        this.newsList = imgList;
    }


    public void setOnItemClickListener(IItemOnClickListener listener) {
        this.onItemClick = listener;
    }

    @Override
    public View getView(ViewGroup container, final int position) {

        LayoutInflater mInflater = LayoutInflater.from(context);

        View view2 = mInflater.inflate(R.layout.item_flash,
                container, false);
        final SimpleDraweeView view = (SimpleDraweeView) view2.findViewById(R.id.sdv_flash_img);
        TextView tv_flash_go_login = (TextView) view2.findViewById(R.id.tv_flash_go_login);

        FrescoUtil.getInstance().loadResourceImage(view, newsList.get(position));//加载app资源图片
        if (position == newsList.size() - 1) {
            tv_flash_go_login.setVisibility(View.VISIBLE);
        } else {
            tv_flash_go_login.setVisibility(View.GONE);
        }
        tv_flash_go_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.itemOnClick(v, position);
            }
        });
        /*view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view2.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));*/

        return view2;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }
}