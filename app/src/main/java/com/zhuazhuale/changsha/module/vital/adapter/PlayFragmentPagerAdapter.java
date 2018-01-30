
package com.zhuazhuale.changsha.module.vital.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.DeviceGoodsBean;
import com.zhuazhuale.changsha.module.vital.fragment.PlayFragment;
import com.zhuazhuale.changsha.module.vital.fragment.PlayFragment2;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表页 的viewpager的adapter
 *
 * @author 丁琪
 * @version 20170301
 */
public class PlayFragmentPagerAdapter extends FragmentPagerAdapter {

    //存放Fragment的数组
    private List<Fragment> mFragmentList;
    private List<String> mTitles;
    private Context context;


    public PlayFragmentPagerAdapter(FragmentManager fm, List<String> titles, DeviceGoodsBean.RowsBean rowsBean) {
        super(fm);
        mFragmentList = new ArrayList<>();
        this.mTitles = titles;
        PlayFragment playFragment1 = PlayFragment.newInstance(1, rowsBean);
        mFragmentList.add(playFragment1);
        PlayFragment2 playFragment2 = PlayFragment2.newInstance(2, rowsBean);
        mFragmentList.add(playFragment2);

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