package com.zhuazhuale.changsha.view;

/**
 * Created by Administrator on 2018/1/30 0030.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by YH on 2017/10/10.
 */

public class ScrollBottomScrollView extends ScrollView {
    private OnScrollBottomListener _listener;
    private int _calCount;

    public interface OnScrollBottomListener {
        void srollToBottom();
    }

    public void registerOnScrollViewScrollToBottom(OnScrollBottomListener l) {
        _listener = l;

    }

    public void unRegisterOnScrollViewScrollToBottom() {
        _listener = null;

    }

    public ScrollBottomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

   /* @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        View view = this.getChildAt(0);
        if (this.getHeight() + this.getScrollY() == view.getHeight()) {
            _calCount++;
            if (_calCount == 1) {
                if (_listener != null) {


                }

            }

        } else {
            _calCount = 0;

        }
    }
*/
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        int newScrollY = scrollY + deltaY;
        final int bottom = maxOverScrollY + scrollRangeY;
        final int top = -maxOverScrollY;
        if (newScrollY > bottom) {
            _listener.srollToBottom();
            System.out.println("滑动到底端");
        } else if (newScrollY < top) {
            System.out.println("滑动到顶端");
        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }
}
