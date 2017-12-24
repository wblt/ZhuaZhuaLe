package com.zhuazhuale.changsha.module.home.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.module.home.adapter.InviteAdapter;
import com.zhuazhuale.changsha.util.log.LogUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 邀请奖励
 * Created by 丁琪 on 2017/12/14.
 */

public class InviteActivity extends AppBaseActivity implements View.OnClickListener {


    @BindView(R.id.tv_invite_1)
    TextView tv_invite_1;
    @BindView(R.id.tv_invite_2)
    TextView tv_invite_2;
    @BindView(R.id.tv_invite_3)
    TextView tv_invite_3;
    @BindView(R.id.tv_invite_4)
    TextView tv_invite_4;
    @BindView(R.id.tv_invite_5)
    TextView tv_invite_5;
    @BindView(R.id.tv_invite_6)
    TextView tv_invite_6;
    @BindView(R.id.iv_invite_fxyqm)//分享邀请
            ImageView iv_invite_fxyqm;
    @BindView(R.id.iv_invite_ss)//搜索
            ImageView iv_invite_ss;
    @BindView(R.id.rv_invite_num)
    RecyclerView rv_invite_num;
    private String code;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_invite);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void obtainData() {
        if ( MyApplication.getInstance().getRowsBean().getF_Code1()!=null){
            code = MyApplication.getInstance().getRowsBean().getF_Code1();// 邀请码
        }else {
            code = "684983";

        }
        char[] chars = code.toCharArray();
        List<String> strings = new ArrayList<>();
        for (char c : chars) {
            LogUtil.e("字符:      " + c);
            strings.add(c + "");
        }
        showNum(strings);
    }

    private void showNum(List<String> strings) {
        InviteAdapter adapter = new InviteAdapter(this, strings);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_invite_num.setLayoutManager(layoutManager);
        rv_invite_num.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {
        iv_invite_fxyqm.setOnClickListener(this);
        iv_invite_ss.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_invite_fxyqm:
                //分享
                Intent intent1 = new Intent(Intent.ACTION_SEND);
                intent1.putExtra(Intent.EXTRA_TEXT, "快来和我一起玩 抓抓乐,输入邀请码:" + code + "  马上送你88个游戏币!");
                intent1.setType("text/plain");
                startActivity(Intent.createChooser(intent1, "share"));
                break;

        }
    }
}
