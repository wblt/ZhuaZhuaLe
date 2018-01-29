/**********************************************************************
 *	湖南长沙阳环科技实业有限公司
 *    @Copyright (c) 2003-2017 yhcloud, Inc. All rights reserved.
 *
 *	This copy of Ice is licensed to you under the terms described in the
 *	ICE_LICENSE file included in this distribution.
 *
 *	@license http://www.yhcloud.com.cn/license/
 **********************************************************************/

package com.zhuazhuale.changsha.module.home.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.ViewGroup;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.tabs;

/**
 * 列表页 的viewpager的adapter
 *
 * @author 丁琪
 * @version 20170301
 */
public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

    //存放Fragment的数组
    private List<Fragment> mFragmentList;
    private List<String> mTitles;
    private Context context;

    public HomeFragmentPagerAdapter(FragmentManager fm, List<String> list) {
        super(fm);
        mFragmentList = new ArrayList<>();
        this.mTitles = list;
        for (int i = 1; i < list.size() + 1; i++) {

            HomeFragment homeFragment = HomeFragment.newInstance(i);
            mFragmentList.add(homeFragment);
        }
    }


    @Override
    public Fragment getItem(int position) {

        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList != null ? mFragmentList.size() : 0;
    }

    /**
     * 如果出现切换回来或不相邻的Tab切换时导致空白界面的问题，解决方法：在 onCreateView中复用布局 + ViewPager 的适配器中复写 destroyItem() 方法去掉 super。
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        context = container.getContext();
    }

    private int[] imageResId = {
            R.mipmap.zhua,
            R.mipmap.down,
            R.mipmap.up,
            R.mipmap.up,
            R.mipmap.up,
    };

    //重写这个方法，将设置每个Tab的标题
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);

    }


}