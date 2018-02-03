package com.zhuazhuale.changsha.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.app.MyApplication;

import java.io.ByteArrayOutputStream;

import static com.zhuazhuale.changsha.app.MyApplication.api;

/**
 * 微信分享
 * Created by Administrator on 2018/2/3.
 */

public class WXShareUtils {


    public static void creatMyDialog(Context context) {
         final Dialog  dialog = new Dialog(context, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.dialog_invite, null);
        LinearLayout ll_invite_hy = (LinearLayout) root.findViewById(R.id.ll_invite_hy);
        LinearLayout ll_invite_pyq = (LinearLayout) root.findViewById(R.id.ll_invite_pyq);
        dialog.setContentView(root);

        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) context.getResources().getDisplayMetrics().widthPixels; // 宽度
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
                WXShareUtils.wechatShare(0, url, title, desc);
//                wechatShare(0, url, title, desc);
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
                WXShareUtils.wechatShare(1, url, title, desc);
//                wechatShare(1, url, title, desc);
            }
        });
    }

    public static void wechatShare(int flag, String url, String title, String desc) {

        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = title;
        msg.description = desc;
        //这里替换一张自己工程里的图片资源
        Bitmap thumb = BitmapFactory.decodeResource(MyApplication.getInstance().getResources(), R.mipmap.share);
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
