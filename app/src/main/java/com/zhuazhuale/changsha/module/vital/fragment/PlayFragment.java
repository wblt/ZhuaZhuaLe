package com.zhuazhuale.changsha.module.vital.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.DeviceGoodsBean;
import com.zhuazhuale.changsha.util.FrescoUtil;
import com.zhuazhuale.changsha.view.fragment.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/30 0030.
 */

public class PlayFragment extends BaseFragment {
    @BindView(R.id.sdv_fra_play_img)
    SimpleDraweeView sdv_fra_play_img;
    @BindView(R.id.tv_fra_play_name)
    TextView tv_fra_play_name;
    @BindView(R.id.tv_fra_play_size)
    TextView tv_fra_play_size;
    @BindView(R.id.tv_fra_play_coin)
    TextView tv_fra_play_coin;


    private int mCurrentType;

    public static PlayFragment newInstance(int type, DeviceGoodsBean.RowsBean rowsBean) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putSerializable("rowsBean", rowsBean);
        PlayFragment fragment = new PlayFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_play_info;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void obtainData() {
        mCurrentType = getArguments().getInt("type", 1);
        DeviceGoodsBean.RowsBean bean = (DeviceGoodsBean.RowsBean) getArguments().getSerializable("rowsBean");
        FrescoUtil.getInstance().loadNetImage(sdv_fra_play_img, bean.getF_ImgB());
        tv_fra_play_name.setText(bean.getF_Name());
        String[] sizes = bean.getF_Name().split("-");
        tv_fra_play_size.setText("30cm");
        tv_fra_play_coin.setText(bean.getF_Price()+"");
    }

    @Override
    protected void initEvent() {

    }
}