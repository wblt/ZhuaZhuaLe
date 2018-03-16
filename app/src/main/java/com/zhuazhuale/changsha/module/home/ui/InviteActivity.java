package com.zhuazhuale.changsha.module.home.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.app.constant.BaseConstants;
import com.zhuazhuale.changsha.module.home.adapter.InviteAdapter;
import com.zhuazhuale.changsha.util.CountdownUtil;
import com.zhuazhuale.changsha.util.PreferenceUtil;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.util.WXShareUtils;
import com.zhuazhuale.changsha.util.log.LogUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.zhuazhuale.changsha.R.id.ll_play_open;
import static com.zhuazhuale.changsha.app.MyApplication.api;


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
    private Dialog dialog;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_invite);
    }

    @Override
    protected void initView() {
        creatMyDialog();
//        WXShareUtils.creatMyDialog(this);
    }


    @Override
    protected void obtainData() {
        String F_Code1 = PreferenceUtil.getString(MyApplication.getInstance(), BaseConstants.F_Code1, "");

        if (F_Code1 != null) {
            code = F_Code1;// 邀请码
        }

        if (code.isEmpty() || code == null) {
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
        getTvToolbarRight().setText("获取奖励");
        getTvToolbarRight().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), InputCodeActivity.class));
            }
        });
    }

    private void creatMyDialog() {
        dialog = new Dialog(this, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.dialog_invite, null);
        LinearLayout ll_invite_hy = (LinearLayout) root.findViewById(R.id.ll_invite_hy);
        LinearLayout ll_invite_pyq = (LinearLayout) root.findViewById(R.id.ll_invite_pyq);
        dialog.setContentView(root);

        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);

        ll_invite_hy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                String url = PreferenceUtil.getString(MyApplication.getInstance(), BaseConstants.F_FxUrl, "");
                String title = "城市抓抓乐";
                String desc = "亲，欢迎使用城市抓抓乐，分享即可免费获得抓取娃娃的机会，还在等什么，赶紧行动起来吧！！！";
                if (url == null || url.isEmpty()) {
                    ToastUtil.show("分享链接不存在!");
                    return;
                }
                WXShareUtils.wechatShare(0, url, title, desc);
//                wechatShare(0, url, title, desc);
            }
        });
        ll_invite_pyq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                String url = PreferenceUtil.getString(MyApplication.getInstance(), BaseConstants.F_FxUrl, "");
                String title = "城市抓抓乐";
                String desc = "亲，欢迎使用城市抓抓乐，分享即可免费获得抓取娃娃的机会，还在等什么，赶紧行动起来吧！！！";
                if (url == null || url.isEmpty()) {
                    ToastUtil.show("分享链接不存在!");
                    return;
                }
                WXShareUtils.wechatShare(1, url, title, desc);
//                wechatShare(1, url, title, desc);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_invite_fxyqm:
                dialog.show();
                break;

        }
    }

}
