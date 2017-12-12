package com.zhuazhuale.changsha.module.home.ui;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.view.RefreshableView;
import com.zhuazhuale.changsha.view.activity.base.BaseActivity;

/**
 * Created by 丁琪 on 2017/12/10 0010.
 */

public class HomeActivity extends BaseActivity {

//       @BindView(R.id.rfv_home)
    RefreshableView rfv_home;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_home);
    }



    @Override
    protected void initView() {
        rfv_home = (RefreshableView) findViewById(R.id.rfv_home);
        rfv_home.addPullToRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                rfv_home.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rfv_home.complete();
                    }
                }, 2000);
            }
        });


    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }
}
