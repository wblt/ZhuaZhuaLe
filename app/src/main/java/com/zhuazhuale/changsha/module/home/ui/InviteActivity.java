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
import com.zhuazhuale.changsha.module.home.adapter.InviteAdapter;
import com.zhuazhuale.changsha.util.CountdownUtil;
import com.zhuazhuale.changsha.util.ToastUtil;
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
    }


    @Override
    protected void obtainData() {
        if (MyApplication.getInstance().getRowsBean().getF_Code1() != null) {
            code = MyApplication.getInstance().getRowsBean().getF_Code1();// 邀请码
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

                String url = MyApplication.getInstance().getRowsBean().getF_FxUrl();
                String title = "长沙抓抓乐";
                String desc = "亲，欢迎使用长沙抓抓乐，分享即可免费获得抓取娃娃的机会，还在等什么，赶紧行动起来吧！！！";
                if (url == null || url.isEmpty()) {
                    ToastUtil.show("分享链接不存在!");
                    return;
                }
                wechatShare(0, url, title, desc);
            }
        });
        ll_invite_pyq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                String url = MyApplication.getInstance().getRowsBean().getF_FxUrl();
                String title = "长沙抓抓乐";
                String desc = "亲，欢迎使用长沙抓抓乐，分享即可免费获得抓取娃娃的机会，还在等什么，赶紧行动起来吧！！！";
                if (url == null || url.isEmpty()) {
                    ToastUtil.show("分享链接不存在!");
                    return;
                }
                wechatShare(1, url, title, desc);
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


    private void wechatShare(int flag, String url, String title, String desc) {

        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = title;
        msg.description = desc;
        //这里替换一张自己工程里的图片资源
        Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.mipmap.share);
        //并且还要创建图片的缩略图，因为微信限制了图片的大小
        msg.setThumbImage(thumb);
        msg.thumbData = bmpToByteArray(thumb, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = flag == 0 ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
        api.sendReq(req);
    }

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        int i;
        int j;
        if (bmp.getHeight() > bmp.getWidth()) {
            i = bmp.getWidth();
            j = bmp.getWidth();
        } else {
            i = bmp.getHeight();
            j = bmp.getHeight();
        }

        Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.RGB_565);
        Canvas localCanvas = new Canvas(localBitmap);

        while (true) {
            localCanvas.drawBitmap(bmp, new Rect(0, 0, i, j), new Rect(0, 0, i, j), null);
            if (needRecycle)
                bmp.recycle();
            ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
            localBitmap.compress(Bitmap.CompressFormat.JPEG, 100,
                    localByteArrayOutputStream);
            localBitmap.recycle();
            byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
            try {
                localByteArrayOutputStream.close();
                return arrayOfByte;
            } catch (Exception e) {
                // F.out(e);
            }
            i = bmp.getHeight();
            j = bmp.getHeight();
        }
    }
}
