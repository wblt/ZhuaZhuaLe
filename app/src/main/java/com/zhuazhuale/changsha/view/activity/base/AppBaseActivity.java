package com.zhuazhuale.changsha.view.activity.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.view.widget.loadlayout.LoadLayout;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * author:  ljy
 * date:    2017/9/13
 * description:
 * 不含ToolBar、加载布局（正文，加载中，加载失败，无数据）的activity基类
 * 子类不需要再绑定ButterKnife
 * 实现setContentLayout来设置布局ID，
 * 实现initView来做视图相关的初始化，
 * 实现obtainData来做数据的初始化
 * 实现initEvent来做事件监听的初始化
 * <p>
 * http://www.jianshu.com/p/3d9ee98a9570
 */
public abstract class AppBaseActivity extends BaseActivity {

    LoadLayout mLoadLayout;//加载布局，可以显示各种状态的布局, 如加载中，加载成功, 加载失败, 无数据
    @BindView(R.id.ll_home_back)
    LinearLayout ll_home_back;
    @BindView(R.id.tv_header_right)
    TextView tv_header_right;
    @BindView(R.id.base_header_layout_app)
    RelativeLayout base_header_layout_app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setContentView(int layoutResId) {

        super.setContentView(R.layout.activity_base_app);

        addViewToContainer(layoutResId);
        init();
    }

    //该方法由子类DrawerBaseActivity调用，设置内容布局
    //由于视图初始化的顺序问题，所以init()改为在DrawerBaseActivity执行
    public void setContentViewByDrawer(int layoutResId) {
        super.setContentView(R.layout.activity_base_toolbar);

        addViewToContainer(layoutResId);
    }

    //将布局加入到LoadLayout中
    public void addViewToContainer(int layoutResId) {
        mLoadLayout = (LoadLayout) findViewById(R.id.base_content_layout_app);
        View view = getLayoutInflater().inflate(layoutResId, null);
        mLoadLayout.removeAllViews();
        mLoadLayout.addSuccessView(view);
    }


    public void init() {
        ButterKnife.bind(this);//butterknife绑定
        ll_home_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 获取toolbar
     */
    public RelativeLayout getToolbar() {
        return base_header_layout_app;
    }


    //设置toolbar右侧文字控件的内容
    public void setToolbarRightTv(String text) {
        if (tv_header_right != null) {
            tv_header_right.setText(text);
        }
    }

    /**
     * 获取toolbar右侧的文字控件
     */
    public TextView getTvToolbarRight() {
        return tv_header_right;
    }


    /**
     * 获取加载布局，从而设置各种加载状态
     */
    public LoadLayout getLoadLayout() {
        return mLoadLayout;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mLoadLayout != null) {
            mLoadLayout.closeAnim();
        }
    }


}
