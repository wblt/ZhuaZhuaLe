package com.zhuazhuale.changsha.module.vital.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;

import com.zhuazhuale.changsha.util.DensityUtil;

import java.util.Map;

import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.android.AndroidDisplayer;
import master.flame.danmaku.danmaku.model.android.BaseCacheStuffer;

/**
 * Created by Administrator on 2018/3/21 0021.
 */
public class MyCacheStuffer extends BaseCacheStuffer {
    private int AVATAR_DIAMETER; //头像直径
    private int AVATAR_PADDING; // 头像边框宽度
    private int TEXT_LEFT_PADDING; // 文字和头像间距
    private int TEXT_RIGHT_PADDING; // 文字和右边线距离
    private int TEXT_SIZE; // 文字大小

    private int NICK_COLOR = 0xffff1874;//昵称 红色
    private int TEXT_COLOR = 0xffeeeeee;  //文字内容  白色
    private int TEXT_BG_COLOR = 0x66000000; // 文字灰色背景色值
    private int TEXT_BG_RADIUS; // 文字灰色背景圆角

    public MyCacheStuffer(Context context) {
        // 初始化固定参数，这些参数可以根据自己需求自行设定
        AVATAR_DIAMETER = DensityUtil.dp2px(context,33);
        AVATAR_PADDING = DensityUtil.dp2px(context,1);
        TEXT_LEFT_PADDING = DensityUtil.dp2px(context,2);
        TEXT_RIGHT_PADDING =DensityUtil.dp2px(context,8);
        TEXT_SIZE =DensityUtil.dp2px(context,13);
        TEXT_BG_RADIUS = DensityUtil.dp2px(context,8);
    }

    @Override
    public void measure(BaseDanmaku danmaku, TextPaint paint, boolean fromWorkerThread) {
        // 初始化数据
        Map<String, Object> map = (Map<String, Object>) danmaku.tag;
        String name = (String) map.get("name");
        String content = (String) map.get("content");

        // 设置画笔
        paint.setTextSize(TEXT_SIZE);

        // 计算名字和内容的长度，取最大值
        float nameWidth = paint.measureText(name);
        float contentWidth = paint.measureText(content);
        float maxWidth = Math.max(nameWidth, contentWidth);

        // 设置弹幕区域的宽高
        danmaku.paintWidth = maxWidth + AVATAR_DIAMETER + AVATAR_PADDING * 2 + TEXT_LEFT_PADDING + TEXT_RIGHT_PADDING; // 设置弹幕区域的宽度
        danmaku.paintHeight = AVATAR_DIAMETER + AVATAR_PADDING * 2; // 设置弹幕区域的高度
    }



    @Override
    public void clearCaches() {
    }

    @Override
    public void drawDanmaku(BaseDanmaku danmaku, Canvas canvas, float left, float top, boolean fromWorkerThread, AndroidDisplayer.DisplayerConfig displayerConfig) {
        // 初始化数据
        Map<String, Object> map = (Map<String, Object>) danmaku.tag;
        String name = (String) map.get("name");
        String content = (String) map.get("content");
        Bitmap bitmap = (Bitmap) map.get("bitmap");

        // 设置画笔
        Paint paint = new Paint();
        paint.setTextSize(TEXT_SIZE);

        // 绘制文字灰色背景
        Rect rect = new Rect();
        paint.getTextBounds(content, 0, content.length(), rect);
        paint.setColor(TEXT_BG_COLOR);
        paint.setAntiAlias(true);
        float bgLeft = left + AVATAR_DIAMETER / 2 + AVATAR_PADDING;
        float bgTop = top + AVATAR_DIAMETER / 2 + AVATAR_PADDING;
        float bgRight = left + AVATAR_DIAMETER + AVATAR_PADDING * 2 + TEXT_LEFT_PADDING + rect.width() + TEXT_RIGHT_PADDING;
        float bgBottom = top + AVATAR_DIAMETER + AVATAR_PADDING;
        canvas.drawRoundRect(new RectF(bgLeft, bgTop, bgRight, bgBottom), TEXT_BG_RADIUS, TEXT_BG_RADIUS, paint);

        // 绘制头像背景
        paint.setColor(Color.WHITE);
        float centerX = left + AVATAR_DIAMETER / 2 + AVATAR_PADDING;
        float centerY = left + AVATAR_DIAMETER / 2 + AVATAR_PADDING;
        float radius = AVATAR_DIAMETER / 2 + AVATAR_PADDING; // 半径
        canvas.drawCircle(centerX, centerY, radius, paint);

        // 绘制头像
        float avatorLeft = left + AVATAR_PADDING;
        float avatorTop = top + AVATAR_PADDING;
        float avatorRight = left + AVATAR_PADDING + AVATAR_DIAMETER;
        float avatorBottom = top + AVATAR_PADDING + AVATAR_DIAMETER;
        canvas.drawBitmap(bitmap, null, new RectF(avatorLeft, avatorTop, avatorRight, avatorBottom), paint);

        // 绘制名字
        paint.setColor(NICK_COLOR);
        float nameLeft = left + AVATAR_DIAMETER + AVATAR_PADDING * 2 + TEXT_LEFT_PADDING;
        float nameBottom = top + rect.height() + AVATAR_PADDING + (AVATAR_DIAMETER / 2 - rect.height()) / 2;
        canvas.drawText(name, nameLeft, nameBottom, paint);

        // 绘制弹幕内容
        paint.setColor(TEXT_COLOR);
        float contentLeft = nameLeft;
        float contentBottom = top + AVATAR_PADDING + AVATAR_DIAMETER / 2 + rect.height() + (AVATAR_DIAMETER / 2 - rect.height()) / 2;
        canvas.drawText(content, contentLeft, contentBottom, paint);
    }


}
