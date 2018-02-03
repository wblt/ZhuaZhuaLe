package com.zhuazhuale.changsha.module.vital.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.module.vital.bean.MsgInfo;
import com.zhuazhuale.changsha.util.ToastUtil;

import org.zackratos.ultimatebar.UltimateBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/2/2 0002.
 */

public class PutMessageActivity extends Activity {

    private String groupId;
    @BindView(R.id.et_play_message)
    EditText et_play_message;
    @BindView(R.id.tv_play_send_msg)
    TextView tv_play_send_msg;
    @BindView(R.id.rl_put_mess)
    RelativeLayout rl_put_mess;
    private Gson gson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_message);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        groupId = intent.getStringExtra("groupId");

        initView();
        initData();
        gson = new Gson();

    }

    private void initView() {
        et_play_message.setFocusable(true);
        et_play_message.setFocusableInTouchMode(true);
        et_play_message.requestFocus();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        UltimateBar ultimateBar = new UltimateBar(this);
        int color = ResourcesCompat.getColor(getResources(), R.color.transparent, null);
        ultimateBar.setTransparentBar(color, 0, color, 0);
        View decorView = getWindow().getDecorView();
        View contentView = findViewById(R.id.ll_play_msg);// 此处的控件ID可以使用界面当中的指定的任意控件
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(getGlobalLayoutListener(decorView, contentView));
    }

    private ViewTreeObserver.OnGlobalLayoutListener getGlobalLayoutListener(final View decorView, final View contentView) {
        return new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                decorView.getWindowVisibleDisplayFrame(r);

                int height = decorView.getContext().getResources().getDisplayMetrics().heightPixels;
                int diff = height - r.bottom;

                if (diff != 0) {
                    if (contentView.getPaddingBottom() != diff) {
                        contentView.setPadding(0, 0, 0, diff);
                    }
                } else {
                    if (contentView.getPaddingBottom() != 0) {
                        contentView.setPadding(0, 0, 0, 0);
                    }
                }
            }
        };
    }

    private void initData() {

        //发送消息
        tv_play_send_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msgt = et_play_message.getText().toString().trim();

                if (msgt.isEmpty()) {
                    ToastUtil.show("请输入...");
                    return;
                }
                MsgInfo msgInfo = new MsgInfo();
                msgInfo.setMsg(msgt);
//                msgInfo.setHeadPic("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517584490096&di=cd3b7dd058b791fba268c078a1033490&imgtype=0&src=http%3A%2F%2Fwww.uuuu.cc%2Fuploads%2Fallimg%2Fc160108%2F145222J62E520-23NR.jpg");
                msgInfo.setHeadPic(MyApplication.getInstance().getRowsBean().getF_Img());
                msgInfo.setNickName(MyApplication.getInstance().getRowsBean().getF_Name());
                msgInfo.setUserId("zhuazhuale" + MyApplication.getInstance().getRowsBean().getF_Code1());
                msgInfo.setUserAction(5);
                String msg = gson.toJson(msgInfo);
                IMChat.getInstance().sendMessage(groupId, msg);
                et_play_message.setText("");
                finish();
            }
        });
        rl_put_mess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
