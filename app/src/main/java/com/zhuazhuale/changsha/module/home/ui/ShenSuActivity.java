package com.zhuazhuale.changsha.module.home.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.LiYouBean;
import com.zhuazhuale.changsha.module.home.adapter.ShenSuAdapter;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 申诉页面
 * Created by Administrator on 2017/12/19.
 */

public class ShenSuActivity extends AppBaseActivity implements View.OnClickListener {
    @BindView(R.id.rv_shensu_list)
    RecyclerView rv_shensu_list;
    private List<LiYouBean> liYouBeen;
    private ShenSuAdapter shenSuAdapter;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_shensu);
    }

    @Override
    protected void initView() {
        liYouBeen = new ArrayList<>();
        liYouBeen.add(new LiYouBean(false, "游戏工作人员补货"));
        liYouBeen.add(new LiYouBean(false, "没有成功上机就扣币"));
        liYouBeen.add(new LiYouBean(false, "爪子没反应,无法移动"));
        liYouBeen.add(new LiYouBean(false, "弹出'机器正在维护'等提示后断线"));
        liYouBeen.add(new LiYouBean(false, "侧面摄像头故障,(遇到此情况不要重复上机)"));
        liYouBeen.add(new LiYouBean(false, "摄像头歪到无法使用"));
        liYouBeen.add(new LiYouBean(false, "娃娃掉进洞却显示失败"));
        liYouBeen.add(new LiYouBean(false, "娃娃被爪子吊在洞口正上方"));
        liYouBeen.add(new LiYouBean(false, "其他原因"));
        showLiYouList(liYouBeen);
    }

    /**
     * 理由列表
     *
     * @param liYouBeen
     */
    private void showLiYouList(List<LiYouBean> liYouBeen) {
        shenSuAdapter = new ShenSuAdapter(this, liYouBeen);
        rv_shensu_list.setLayoutManager(new LinearLayoutManager(this));
        rv_shensu_list.setAdapter(shenSuAdapter);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void onClick(View v) {

    }

    public void goToChangge(int position) {
        for (LiYouBean bean : liYouBeen) {
            bean.setClick(false);
        }
        liYouBeen.get(position).setClick(true);
        shenSuAdapter.replaceData(liYouBeen);
    }
}
