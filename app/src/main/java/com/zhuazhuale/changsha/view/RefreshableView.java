package com.zhuazhuale.changsha.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.util.log.LogUtil;


/**
 * Created by cjh on 16-9-6.
 */
public class RefreshableView extends LinearLayout implements View.OnTouchListener {

    private static final String TAG = RefreshableView.class.getSimpleName();

    private static final int REFRESHING = 0;//正在刷新
    private static final int ORIGINAL = REFRESHING + 1;//初始状态
    private static final int RELEASE_TO_REFRESHING = ORIGINAL + 1;//释放即将刷新的状态

    private int current_status = ORIGINAL;//当前最新状态

    private LinearLayout headView;//刷新layout
    private TextView textView;//刷新layout中的文字提示
    private ImageView imageView;//刷新layout中的箭头
    private ProgressBar progressBar;//刷新layout中的进度条

    private View view;//手指控制的下拉的View

    private int hideHeight;//刷新layout要隐藏的高度

    private boolean isablePull;//是否可以下拉，例如当 current_status = REFRESHIING 时是不可以下拉拖拽的

    private float yDown;//手指按下的坐标

    private int touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();//界限值，防止手指误触，过于灵敏

    private boolean firstLayout = true;//第一次调用onLayout的时候置为false

    private int maxMarginTop;//刷新Layout能拉下的最大距离

    private MarginLayoutParams marginLayoutParams;//刷新layout的MarginLayoutParams

    private String pull_to_refresh = "下拉可以刷新";

    private String release_to_refresh = "松开立即刷新";

    private String refreshing = "正在刷新…";


    private int original_margin = 0;//针对下拉的View存在MarginTop这中特殊值的处理

    public interface PullToRefreshListener {

        void onRefresh();
    }

    private PullToRefreshListener pullToRefreshListener;

    public void addPullToRefreshListener(PullToRefreshListener pullToRefreshListener) {
        this.pullToRefreshListener = pullToRefreshListener;
    }

    public RefreshableView(Context context) {
        super(context);
        init();
    }

    public RefreshableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RefreshableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RefreshableView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        headView = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.refresh_layout, null, true);
        imageView = (ImageView) headView.findViewById(R.id.imageView_down);
        progressBar = (ProgressBar) headView.findViewById(R.id.progressBar);
        textView = (TextView) headView.findViewById(R.id.textView);
        progressBar.setVisibility(View.GONE);
        setOrientation(VERTICAL);
        addView(headView, 0);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        LogUtil.e(TAG, "onlayout");
        if (changed && firstLayout) {
            //将View的Touch时间的处理交给RefreshableView去处理
            view = getChildAt(1);
            view.setOnTouchListener(this);

            //刷新layout的 marginTop 的最大值设为刷新头的高度
            maxMarginTop = headView.getHeight();

            //要将控件完全隐藏起来，那么隐藏的高度就设置为控件的高度
            hideHeight = -headView.getHeight();
            marginLayoutParams = (MarginLayoutParams) headView.getLayoutParams();
            marginLayoutParams.topMargin = hideHeight;
            headView.setLayoutParams(marginLayoutParams);

            //这里必须将firstLayout设置为false,否则在处理Touch是件的过程中，headView在怎么调用setLayoutParams都会被置为初始的隐藏状态
            firstLayout = false;

            //如果子View是一个ViewGroup 那么就需要计算出子View的MarginTop的值，因为如果MarginTop不为0，那么子View的Y轴坐标和父View的坐标是不一样的
            if (view instanceof ViewGroup) {
                int[] childLocations = new int[2];
                int[] viewLocations = new int[2];
                view.getLocationOnScreen(viewLocations);
                ((ViewGroup) view).getChildAt(0).getLocationOnScreen(childLocations);
                original_margin = childLocations[1] - viewLocations[1];
                LogUtil.d(TAG, "onLayout viewLocations[1] " + viewLocations[1]);
                LogUtil.d(TAG, "onLayout locations[1] " + childLocations[1]);
                LogUtil.d(TAG, "onLayout original_margin " + original_margin);
            }
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (pullToRefreshListener != null) {
            isAblePull();
            if (isablePull) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        yDown = motionEvent.getRawY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        float yMove = motionEvent.getRawY();
                        float distance = yMove - yDown;

                        //如果手势是向上的，并且手势在Y轴的移动距离小于界限值，那么就不处理
                        if (distance < 0 || Math.abs(distance) < touchSlop)
                            return false;

                        //MarginTop的距离是手势距离的1/2,形成费力延迟的效果
                        marginLayoutParams.topMargin = (int) (distance / 2 + hideHeight);
                        LogUtil.e(TAG, "topMargin " + marginLayoutParams.topMargin);

                        //如果大于最大的MarginTop的值的时候，就将值置为 maxMarginTop
                        if (marginLayoutParams.topMargin >= maxMarginTop)
                            marginLayoutParams.topMargin = maxMarginTop;


                        if (marginLayoutParams.topMargin >= 0) {
                            //当刷新头完全显示的时候，改变状态，置为 释放刷新的状态
                            if (current_status != RELEASE_TO_REFRESHING) {
                                rotate(0, 180);
                                textView.setText(release_to_refresh);
                            }
                            current_status = RELEASE_TO_REFRESHING;
                        } else {
                            //否则就置为初始状态
                            if (current_status != ORIGINAL) {
                                rotate(180, 360);
                                textView.setText(pull_to_refresh);
                            }
                            current_status = ORIGINAL;

                        }
                        headView.setLayoutParams(marginLayoutParams);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        float yUp = motionEvent.getRawY();
                        float dis = yUp - yDown;
                        if (dis > 0 && Math.abs(dis) > touchSlop)
                            switch (current_status) {
                                //释放刷新
                                case RELEASE_TO_REFRESHING:
                                    animateMarginTop(marginLayoutParams.topMargin, 0);
                                    imageView.clearAnimation();
                                    imageView.setVisibility(View.GONE);
                                    progressBar.setVisibility(View.VISIBLE);
                                    textView.setText(refreshing);
                                    pullToRefreshListener.onRefresh();
                                    break;

                                //初始化
                                case ORIGINAL:
                                    reset();
                                    //从当前的MarginTop的值，转变到隐藏所需的 MarginTop
                                    animateMarginTop(marginLayoutParams.topMargin, hideHeight);
                                    break;
                            }
                        else
                            return false;

                        break;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否可以下拉
     *
     * @return
     */
    public boolean isAblePull() {
        //统一判断，其实主要是对于view是普通View的判断
        if (current_status != REFRESHING)
            isablePull = true;
        else
            isablePull = false;


        if (view instanceof ViewGroup) {
            isablePull = false;
            View childView = ((ViewGroup) view).getChildAt(0);
            int[] viewLocations = new int[2];
            int[] childViewLocations = new int[2];
            view.getLocationOnScreen(viewLocations);
            childView.getLocationOnScreen(childViewLocations);

            //这一步中的 original_margin 至关重要，就是用来兼容 子View 有MarginTop属性的值，当childView 的Y轴坐标 和 计算出了 Margin 值后的父View坐标相等时，说明此时处于可下拉的状态
            if (viewLocations[1] + original_margin == childViewLocations[1])
                isablePull = true;
            else
                isablePull = false;
        }

        return isablePull;
    }

    private void rotate(int from, int to) {
        RotateAnimation rotateAnimation = new RotateAnimation(from, to, imageView.getWidth() / 2, imageView.getHeight() / 2);
        rotateAnimation.setDuration(100);
        rotateAnimation.setFillAfter(true);
        imageView.startAnimation(rotateAnimation);
    }

    private void animateMarginTop(int from, int to) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(headView, "cjh", from, to);
        objectAnimator.setDuration(300);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int margin = (int) valueAnimator.getAnimatedValue();
                marginLayoutParams.topMargin = margin;
                headView.setLayoutParams(marginLayoutParams);
            }
        });
        objectAnimator.start();
    }

    public void complete() {
        animateMarginTop(0, hideHeight);
        reset();
    }

    private void reset() {
        rotate(180, 360);
        textView.setText(pull_to_refresh);
        imageView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

}
