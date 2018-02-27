package com.zhuazhuale.changsha.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * @author dengmengxin
 *         Automatic Height Follower Width
 *         这里我继承系统的帧布局，当然你可以继承其它的布局，自己试下不就知道了
 */
public class MyFrameLayout extends android.widget.FrameLayout {
    public MyFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFrameLayout(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //核心就是下面这块代码块啦
        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
        ViewGroup.LayoutParams lp = getLayoutParams();
        lp.height = width;
        setLayoutParams(lp);
    }
}