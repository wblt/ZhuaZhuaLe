package com.zhuazhuale.changsha.module.home.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.module.home.adapter.InviteAdapter;
import com.zhuazhuale.changsha.util.log.LogUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_invite);
    }

    @Override
    protected void initView() {

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_invite_fxyqm:
                //分享
            /*    Intent intent1 = new Intent(Intent.ACTION_SEND);
//                intent1.putExtra(Intent.EXTRA_TEXT, "快来和我一起玩 抓抓乐,输入邀请码:" + code + "  马上送你88个游戏币!");
                intent1.putExtra(Intent.EXTRA_TEXT, "亲，欢迎使用长沙娃娃乐，分享即可免费获得抓取娃娃的机会，还在等什么，赶紧行动起来吧！！！" + MyApplication.getInstance().getRowsBean().getF_FxUrl());
                intent1.setType("text/plain");
                startActivity(Intent.createChooser(intent1, "share"));*/

//                shareWebPage();
                wechatShare(0);
                break;

        }
    }

    /*
  * 分享链接
  */
    private void shareWebPage() {
        Resources res = getResources();
        Bitmap bmp = BitmapFactory.decodeResource(res, R.mipmap.ic_logo);

        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = MyApplication.getInstance().getRowsBean().getF_FxUrl();
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = "长沙抓抓乐";
        msg.description = "亲，欢迎使用长沙抓抓乐，分享即可免费获得抓取娃娃的机会，还在等什么，赶紧行动起来吧！！！";

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;
        api.sendReq(req);
    }

    private void wechatShare(int flag) {
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = MyApplication.getInstance().getRowsBean().getF_FxUrl();
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = "长沙抓抓乐";
        msg.description = "亲，欢迎使用长沙抓抓乐，分享即可免费获得抓取娃娃的机会，还在等什么，赶紧行动起来吧！！！";
        //这里替换一张自己工程里的图片资源
        Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.mipmap.share);
        //并且还要创建图片的缩略图，因为微信限制了图片的大小
        Bitmap thumbBmp = Bitmap.createScaledBitmap(thumb, 90, 90, true);
        msg.setThumbImage(thumb);

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
