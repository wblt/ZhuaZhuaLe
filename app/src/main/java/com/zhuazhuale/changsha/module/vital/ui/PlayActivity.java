package com.zhuazhuale.changsha.module.vital.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.app.constant.BaseConstants;
import com.zhuazhuale.changsha.model.entity.eventbus.CPfreshEvent;
import com.zhuazhuale.changsha.model.entity.eventbus.ScrollEvent;
import com.zhuazhuale.changsha.module.home.Bean.DeviceGoodsBean;
import com.zhuazhuale.changsha.module.home.Bean.EditAddressBean;
import com.zhuazhuale.changsha.module.home.Bean.NewCPBean;
import com.zhuazhuale.changsha.module.home.Bean.QueryGameBean;
import com.zhuazhuale.changsha.module.home.ui.RechargeActivity;
import com.zhuazhuale.changsha.module.home.ui.RecordActivity;
import com.zhuazhuale.changsha.module.vital.adapter.AllTrueAdapter;
import com.zhuazhuale.changsha.module.vital.adapter.PlayFragmentPagerAdapter;
import com.zhuazhuale.changsha.module.vital.bean.AllUserTrueByDeviceIDBean;
import com.zhuazhuale.changsha.module.vital.bean.ControlGameBean;
import com.zhuazhuale.changsha.module.vital.bean.StartGameBean;
import com.zhuazhuale.changsha.module.vital.presenter.PlayPresenter;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.CountdownUtil;
import com.zhuazhuale.changsha.util.EventBusUtil;
import com.zhuazhuale.changsha.util.FrescoUtil;
import com.zhuazhuale.changsha.util.PreferenceUtil;
import com.zhuazhuale.changsha.util.ScreenRecorder;
import com.zhuazhuale.changsha.util.SoundUtils;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;
import com.zhuazhuale.changsha.view.ScrollBottomScrollView;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;
import com.zhuazhuale.changsha.view.widget.loadlayout.OnLoadListener;
import com.zhuazhuale.changsha.view.widget.loadlayout.State;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

/**
 * 游戏页面
 * Created by dingqi on 2017/12/23 0023.
 */

public class PlayActivity extends AppBaseActivity implements View.OnClickListener, View.OnTouchListener, IPlayView {
    @BindView(R.id.iv_play_startgame)
    ImageView iv_play_startgame;
    @BindView(R.id.iv_play_up)
    ImageView iv_play_up;
    @BindView(R.id.iv_play_left)
    ImageView iv_play_left;
    @BindView(R.id.iv_play_right)
    ImageView iv_play_right;
    @BindView(R.id.iv_play_down)
    ImageView iv_play_down;
    @BindView(R.id.ll_play_caozuo)
    LinearLayout ll_play_caozuo;
    @BindView(R.id.iv_play_catch)
    ImageView iv_play_catch;
    @BindView(R.id.ll_play_open)
    LinearLayout ll_play_open;
    @BindView(R.id.iv_play_change)
    ImageView iv_play_change;
    @BindView(R.id.tv_play_cp)
    TextView tv_play_cp;
    @BindView(R.id.tv_play_bi)
    TextView tv_play_bi;
    @BindView(R.id.iv_play_recharge)
    ImageView iv_play_recharge;
    @BindView(R.id.sdv_play_fece)
    SimpleDraweeView sdv_play_fece;
    @BindView(R.id.iv_play_wifi)
    ImageView iv_play_wifi;
    @BindView(R.id.tv_play_name)
    TextView tv_play_name;
    @BindView(R.id.view_play)
    LinearLayout view_play;
    @BindView(R.id.tv_play_djs)
    TextView tv_play_djs;
    @BindView(R.id.rv_play_list)
    RecyclerView rv_play_list;
    @BindView(R.id.tv_play_info)
    TextView tv_play_info;
    @BindView(R.id.iv_play_back)
    ImageView iv_play_back;
    @BindView(R.id.sdv_play_video_bg)
    SimpleDraweeView sdv_play_video_bg;
    @BindView(R.id.iv_play_loading_bg)
    ImageView iv_play_loading_bg;
    @BindView(R.id.tv_play_mian_type)
    TextView tv_play_mian_type;
    @BindView(R.id.tl_play_title)
    TabLayout tl_play_title;
    @BindView(R.id.vp_play_info)
    ViewPager vp_play_info;
    private PlayFragmentPagerAdapter pagerAdapter;


    private DeviceGoodsBean.RowsBean rowsBean;
    private TXLivePlayer mLivePlayer1;
    private PlayPresenter presenter;
    private StartGameBean.RowsBean gameBeanRows;
    private boolean isPlay = false;//是否可以开始操作
    private boolean isMovie = false;//是否可以开始操作
    private String url1;
    private String url2;
    private TXLivePlayer mLivePlayer2;
    private boolean isURL = false;  // 判断是否 是直播视频1
    private TXCloudVideoView mView2;
    private TXCloudVideoView mView1;
    private boolean isFirst = true; //判断是否第一次进入,主要为了创建直播视频二
    //  FORWARD BACKWARD LEFT  RIGHT
    private String up = "FORWARD";
    private String down = "BACKWARD";
    private String left = "LEFT";
    private String right = "RIGHT";
    private int newCP = 0;
    private boolean isOpen = false;// 判断游戏机器的状态,能否开始游戏
    private ColorMatrixColorFilter colorFilter;
    private SoundUtils soundUtils;
    private int bgvoice = 0;
    private int readygo = 1;
    private int move = 2;
    private int fail = 3;
    private int success = 4;
    private int start = 5;
    private int take = 6;
    private Dialog dialog;
    private MyThread mutliThread;
    private AlertDialog isExit;
    private TextView tv_dialog_ok;

    private Drawable drawableup;
    private Drawable drawableleft;
    private Drawable drawableright;
    private Drawable drawabledown;
    private Drawable drawablecatch;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                checkWifiState();
                sendEmptyMessageDelayed(0, 2000);
            }
        }
    };
    private MediaProjectionManager mMediaProjectionManager;
    private ScreenRecorder mRecorder;
    private boolean is_lp;
    private String moviePath;
    private LinearLayout ll_dialog_bg;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_play2);
        Intent intent = getIntent();
        rowsBean = (DeviceGoodsBean.RowsBean) intent.getSerializableExtra("DeviceGoods");
        url1 = rowsBean.getF_Camera1();
        url2 = rowsBean.getF_Camera2();
        //录屏
        is_lp = PreferenceUtil.getBoolean(getContext(), BaseConstants.Is_lp, false);
        if (is_lp) {
            mMediaProjectionManager = (MediaProjectionManager) getSystemService(MEDIA_PROJECTION_SERVICE);
        }
        isHave = true;
        Uri uri = Uri.parse(rowsBean.getF_ImgA());
        FrescoUtil.getInstance().loadImage(sdv_play_video_bg, uri, false, true);
        randomIv();


    }

    private void randomIv() {
        Integer[] integers = {R.mipmap.loading_bg1, R.mipmap.loading_bg2, R.mipmap.loading_bg3};
        Random rand = new Random();
        int i = rand.nextInt(3);
        iv_play_loading_bg.setImageResource(integers[i]);
    }

    @Override
    protected void initView() {
        tv_play_mian_type.setText("观战中");
        int color = getResourceColor(R.color.transparent);
        setBarTranslucent(color, 0, color, 0);
//        showLoadingDialog("");
        getToolbar().setVisibility(View.GONE);
        //mPlayerView即step1中添加的界面view
        mView1 = (TXCloudVideoView) findViewById(R.id.video_view1);
        mView2 = (TXCloudVideoView) findViewById(R.id.video_view2);
        mView2.setVisibility(View.GONE);
        creatTXLivePlayer1();

        iv_play_startgame.setImageResource(R.mipmap.srartgame2);
        tv_play_bi.setText(rowsBean.getF_Price() + "币 / 次");
        creatSoundPool();
        FrescoUtil.getInstance().loadNetImage(sdv_play_fece, MyApplication.getInstance().getRowsBean().getF_Img());
        tv_play_name.setText(MyApplication.getInstance().getRowsBean().getF_Name());
        creatMyDialog();
        // 监听返回按键
        this.setOnKeyListener(new OnKeyClickListener() {
            @Override
            public void clickBack() {
                listen();
            }
        });
        getTvToolbarRight().setText("求助");
        getTvToolbarRight().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), RecordActivity.class));
            }
        });
        drawableup = iv_play_up.getDrawable();
        drawableleft = iv_play_left.getDrawable();
        drawableright = iv_play_right.getDrawable();
        drawabledown = iv_play_down.getDrawable();
        drawablecatch = iv_play_catch.getDrawable();
        List<String> titles = new ArrayList<>();
        titles.add("娃娃详情");
        titles.add("最近抓中");
        pagerAdapter = new PlayFragmentPagerAdapter(getSupportFragmentManager(), titles, rowsBean);
        vp_play_info.setAdapter(pagerAdapter);
        tl_play_title.setupWithViewPager(vp_play_info);

    }

    /**
     * 创建声音池
     */
    private void creatSoundPool() {
        //使用的时候先初始化一个声音播放工具
        soundUtils = new SoundUtils(this, SoundUtils.MEDIA_SOUND);
        //然后添加声音进去,参数是添加声音的编号和资源id
        soundUtils.putSound(bgvoice, R.raw.bgvoice);
        soundUtils.putSound(readygo, R.raw.readygo);
        soundUtils.putSound(move, R.raw.move);
        soundUtils.putSound(fail, R.raw.fail);
        soundUtils.putSound(success, R.raw.success);
        soundUtils.putSound(start, R.raw.start);
        soundUtils.putSound(take, R.raw.take);
    }

    /**
     * 恢复上下左右和抓取的按钮颜色
     */
    private void qingChuIVColor() {
        drawableup.clearColorFilter();
        drawableleft.clearColorFilter();
        drawableright.clearColorFilter();
        drawabledown.clearColorFilter();
        drawablecatch.clearColorFilter();
    }

    /**
     * 让上下左右和抓取的按钮变暗
     */
    private void changeIVColor() {
        drawableup.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        drawableleft.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        drawableright.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        drawabledown.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        drawablecatch.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
    }


    /**
     * 抓到娃娃的提示dialog
     */
    private void creatMyDialog() {
        dialog = new Dialog(this, R.style.Dialog_Fullscreen);
        dialog.setContentView(R.layout.dialog_play);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        TextView tv_dialog_cancel = (TextView) dialog.findViewById(R.id.tv_dialog_cancel);
        tv_dialog_ok = (TextView) dialog.findViewById(R.id.tv_dialog_ok);
        ll_dialog_bg = (LinearLayout) dialog.findViewById(R.id.ll_dialog_bg);
        tv_dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountdownUtil.getInstance().cancel("DOWN");
                presenter.initLowerGame(rowsBean.getF_ID());
                isPlay = false;
                ll_play_open.setVisibility(View.VISIBLE);
                ll_play_caozuo.setVisibility(View.INVISIBLE);
                dialog.dismiss();
            }
        });
        tv_dialog_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingDialog("");
                CountdownUtil.getInstance().cancel("DOWN");
                soundUtils.playSound(start, 0);
                presenter.initUpperGame(rowsBean.getF_ID());
                dialog.dismiss();
            }
        });
    }

    private void creatTXLivePlayer2() {

        //创建player对象 setRenderMode
        mLivePlayer2 = new TXLivePlayer(getContext());
        //将图像等比例缩放，适配最长边，缩放后的宽和高都不会超过显示区域，居中显示，画面可能会留有黑边。
//        mLivePlayer.setRenderMode(TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION;);
        mLivePlayer2.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN);//填充
        mLivePlayer2.setRenderRotation(TXLiveConstants.RENDER_ROTATION_LANDSCAPE);
        //关键player对象与界面view
        mLivePlayer2.setPlayerView(mView2);
        //软解和硬解的切换需要在切换之前先stopPlay，切换之后再startPlay，否则会产生比较严重的花屏问题。
      /*  mLivePlayer2.stopPlay(true);
        mLivePlayer2.enableHardwareDecode(true);*/
        mLivePlayer2.startPlay(url2, TXLivePlayer.PLAY_TYPE_LIVE_RTMP); //推荐FLV
        //监听第二个直播流拉流事件
        playerListen2();
    }

    /**
     * 创建直播视频
     */
    private void creatTXLivePlayer1() {
        //创建player对象 setRenderMode
        mLivePlayer1 = new TXLivePlayer(getContext());
        //将图像等比例缩放，适配最长边，缩放后的宽和高都不会超过显示区域，居中显示，画面可能会留有黑边。
//        mLivePlayer.setRenderMode(TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION;);
        mLivePlayer1.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN);//填充
        mLivePlayer1.setRenderRotation(TXLiveConstants.RENDER_ROTATION_LANDSCAPE);
        //关键player对象与界面view
        mLivePlayer1.setPlayerView(mView1);
        //软解和硬解的切换需要在切换之前先stopPlay，切换之后再startPlay，否则会产生比较严重的花屏问题。
       /* mLivePlayer1.stopPlay(true);
        mLivePlayer1.enableHardwareDecode(true);*/
        mLivePlayer1.startPlay(url1, TXLivePlayer.PLAY_TYPE_LIVE_RTMP); //推荐FLV
    }

    @Override
    protected void obtainData() {
        presenter = new PlayPresenter(this);
        getLoadLayout().setOnLoadListener(new OnLoadListener() {
            @Override
            public void onLoad() {
                mLivePlayer1.startPlay(url1, TXLivePlayer.PLAY_TYPE_LIVE_RTMP); //推荐FLV
                //查询游戏币数量
                presenter.initNewCP();
                //查询游戏的状态
                presenter.initQueryGame(rowsBean.getF_ID(), first);
//                presenter.initGetAllUserTrueByDeviceID(rowsBean.getF_ID(), Constant.REFRESH);

            }
        });
        //查询游戏币数量
        presenter.initNewCP();
        //查询游戏的状态
        presenter.initQueryGame(rowsBean.getF_ID(), first);
        presenter.initGetAllUserTrueByDeviceID(rowsBean.getF_ID(), Constant.INIT);
        // 开启子线程5秒钟检查一次机器状态
        creatThread();

        EventBusUtil.register(this);//订阅事件

        // 初始化wifi 状态检测
        mHandler.sendEmptyMessageDelayed(0, 2000);
    }

    //EventBus的事件接收，从事件中获取最新的收藏数量并更新界面展示
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(CPfreshEvent event) {
        String code = event.getCPisFresh();
        LogUtil.e(code);
        if ("刷新".equals(code)) {
            presenter.initNewCP();
        }

    }


    /**
     * 查询游戏币数量
     *
     * @param newCPBean
     */
    @Override
    public void showNewCP(NewCPBean newCPBean) {
        if (newCPBean.getCode() == 1) {
            getLoadLayout().setLayoutState(State.SUCCESS);
            newCP = newCPBean.getRows().getCP();
            tv_play_cp.setText(newCP + "");
        } else {
            ToastUtil.show(newCPBean.getInfo());
            tv_play_cp.setText(newCP);
        }
    }

    /**
     * 操作返回的数据
     *
     * @param controlGameBean
     * @param vAction
     */
    private static boolean isHave = true;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void showControlGame(final ControlGameBean controlGameBean, String vAction) {
        tv_play_info.setVisibility(View.GONE);
//        boolean isHave = getActivityStackManager().isActivityExist(PlayActivity.class);
        if (controlGameBean.getCode() == -9999) {
            ToastUtil.show("退出房间");
            finish();
            return;
        }
        if (vAction.equals("DOWN")) {
            qingChuIVColor();//让控件恢复颜色
            ll_play_open.setVisibility(View.VISIBLE);
            ll_play_caozuo.setVisibility(View.INVISIBLE);
            if (controlGameBean.getCode() == 1) {
                if (isHave) {
                    //需要播放的地方执行这句即可, 参数分别是声音的编号和循环次数
                    soundUtils.playSound(success, 0);
                    ll_dialog_bg.setBackgroundResource(R.mipmap.play_succes);
//                    tv_dialog_info.setText("恭喜你,抓取成功!");
                    openGame();
                    dialog.show();
                } else {
                    ToastUtil.show("恭喜你,抓取成功!");
                }
                if (is_lp) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            luZhi(controlGameBean.getRows().getGrabID());//抓取结束,停止录屏
                        }
                    }, 2000);

                }
            } else {
                if (isHave) {
                    soundUtils.playSound(fail, 0);
                    ll_dialog_bg.setBackgroundResource(R.mipmap.play_fail);
//                    tv_dialog_info.setText("抓取失败,再接再厉!");
                    openGame();
                    dialog.show();
                } else {
                    ToastUtil.show("抓取失败,再接再厉!");
                }
                if (is_lp) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            luZhi(controlGameBean.getRows().getGrabID());//抓取结束,停止录屏
                        }
                    }, 2000);
                }
            }


        } else {
            //需要播放的地方执行这句即可, 参数分别是声音的编号和循环次数
            soundUtils.playSound(move, 0);
        }

    }


    /**
     * 倒计时开始游戏
     */
    private void openGame() {
        CountdownUtil.getInstance().newTimer(10000, 1000, new CountdownUtil.ICountDown() {
            @Override
            public void onTick(long millisUntilFinished) {
                tv_dialog_ok.setText("继续游戏(" + millisUntilFinished / 1000 + ")");
            }

            @Override
            public void onFinish() {
                tv_dialog_ok.setText("(0)");
                CountdownUtil.getInstance().cancel("DOWN");
                presenter.initLowerGame(rowsBean.getF_ID());
                isPlay = false;
                ll_play_open.setVisibility(View.VISIBLE);
                ll_play_caozuo.setVisibility(View.INVISIBLE);


//                showLoadingDialog();
//                soundUtils.playSound(start, 0);
//                presenter.initLowerGame(rowsBean.getF_ID());
                dialog.dismiss();
            }
        }, "DOWN");
    }

    /**
     * 下机成功
     *
     * @param lowerGame
     */
    @Override
    public void showLowerGame(EditAddressBean lowerGame) {
        tv_play_mian_type.setText("观战中");
        mutliThread.resumeThread();//下机了,继续检查游戏机的状态
        isStart = true;
        LogUtil.e("我下机了,继续检查吧!" + isStart);
        if (lowerGame.getCode() == 0) {
            ToastUtil.show(lowerGame.getInfo());
        }
    }

    /**
     * 网络连接失败
     */
    @Override
    public void showNewCPFailed() {
        //设置页面为“失败”状态
        getLoadLayout().setLayoutState(State.FAILED);
        dismissLoadingDialog();
    }

    /**
     * 展现用户在这台机器抓取成功的记录
     *
     * @param trueBean
     * @param type
     */
    @Override
    public void showAllUserTrues(AllUserTrueByDeviceIDBean trueBean, int type) {
        if (trueBean.getCode() == 1) {
            AllTrueAdapter adapter = new AllTrueAdapter(getContext(), trueBean.getRows());

//            FullyLinearLayoutManager fullyLinearLayoutManager = new FullyLinearLayoutManager(this);
            LinearLayoutManager fullyLinearLayoutManager=new LinearLayoutManager(getContext());
            rv_play_list.setNestedScrollingEnabled(false);
            rv_play_list.setLayoutManager(fullyLinearLayoutManager);
            rv_play_list.setAdapter(adapter);

        } else {
            LogUtil.e(trueBean.getInfo());
        }
    }

    /**
     * 查询机器的状态
     *
     * @param queryGameBean
     * @param type
     */
    @Override
    public void showQueryGame(QueryGameBean queryGameBean, int type) {
        int code = queryGameBean.getCode();
        if (code == 1) {
            // 创建子线程,检查机器的状态

            QueryGameBean.RowsBean rows = queryGameBean.getRows();
            switch (rows.getVStatus()) {
                case 1:
                    //空闲中,可以上机
                    isOpen = true;
                    if (type == startGame) {
                        presenter.initUpperGame(rowsBean.getF_ID());
                    } else {
                        iv_play_startgame.setImageResource(R.mipmap.srartgame);
                    }
                    break;
                case 2:
                    //其他用户正在游戏中
                    isOpen = false;
                    iv_play_startgame.setImageResource(R.mipmap.srartgame2);
                    break;
                case 3://在维修中
                    isOpen = false;
                    iv_play_startgame.setImageResource(R.mipmap.srartgame2);
                    break;
            }
        } else {
            if (type != thread) {
                ToastUtil.show(queryGameBean.getInfo());

            }
        }

    }

    @BindView(R.id.sv_play)
    ScrollBottomScrollView sv_play;

    @Override
    protected void initEvent() {
        //监听第一个直播流拉流事件
        playerListen1();
        iv_play_back.setOnClickListener(this);
        iv_play_startgame.setOnClickListener(this);
        iv_play_up.setOnClickListener(this);
        iv_play_left.setOnClickListener(this);
        iv_play_right.setOnClickListener(this);
        iv_play_down.setOnClickListener(this);
        iv_play_catch.setOnClickListener(this);
        iv_play_change.setOnClickListener(this);
        iv_play_recharge.setOnClickListener(this);

        iv_play_up.setOnTouchListener(this);
        iv_play_right.setOnTouchListener(this);
        iv_play_left.setOnTouchListener(this);
        iv_play_down.setOnTouchListener(this);
        sv_play.registerOnScrollViewScrollToBottom(new ScrollBottomScrollView.OnScrollBottomListener() {
            @Override
            public void srollToBottom() {
//                ToastUtil.show("我是scroll,我到底了");
                ScrollEvent event = new ScrollEvent("di");
                EventBusUtil.postEvent(event);
            }
        });
      /*  sv_play.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        View childView = sv_play.getChildAt(0);
                        if (childView != null && childView.getMeasuredHeight() <= sv_play.getScrollY() + sv_play.getHeight()) {
                            ToastUtil.show("我是scroll,我到底了1");
                            return true;
                        } else if (sv_play.getScrollY() == 0) {
                            ToastUtil.show("我是scroll,我到底了2");
                            return false;
                        }
                        break;
                }
                return true;
            }
        });*/

    }

    private boolean isStart = true;
    private static int first = 1; //第一次时进入检查机器状态
    private static int startGame = 2;//开始游戏时检查机器状态
    private static int thread = 3;//开启子线程检查机器状态

    /**
     * 创建子线程,检查机器的状态
     */
    private void creatThread() {
        mutliThread = new MyThread();
        Thread thread = new Thread(mutliThread);
        thread.start();

    }

    /**
     * 观看抓取成功记录视频
     *
     * @param rowsBean
     */
    public void LookMovie(AllUserTrueByDeviceIDBean.RowsBean rowsBean) {
        Intent intent = new Intent(this, MovieActivity.class);
        intent.putExtra("MoviePath", rowsBean.getF_VideoUrl());
        startActivity(intent);
    }


    /**
     * 子线程,检查机器的状态
     */
    private class MyThread extends Thread {
        private final Object lock = new Object();
        private boolean pause = false;

        /**
         * 调用这个方法实现暂停线程
         */
        public void pauseThread() {
            pause = true;
        }

        /**
         * 调用这个方法实现恢复线程的运行
         */
        public void resumeThread() {
            pause = false;
            synchronized (lock) {
                lock.notifyAll();
            }
        }

        /**
         * 注意：这个方法只能在run方法里调用，不然会阻塞主线程，导致页面无响应
         */
        void onPause() {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void run() {
            super.run();
            try {
                while (true) {
                    // 让线程处于暂停等待状态
                    while (pause) {
                        onPause();
                    }
                    try {
                        LogUtil.e("我是子线程");
                        presenter.initQueryGame(rowsBean.getF_ID(), thread);
                        Thread.sleep(5000);

                    } catch (InterruptedException e) {
                        //捕获到异常之后，执行break跳出循环
                        break;
                    }
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        String fx;
        switch (v.getId()) {
            case R.id.iv_play_up:
                fx = up;
                touchShijian(fx, event);
                break;
            case R.id.iv_play_down:
                fx = down;
                touchShijian(fx, event);
                break;
            case R.id.iv_play_left:
                fx = left;
                touchShijian(fx, event);
                break;
            case R.id.iv_play_right:
                fx = right;
                touchShijian(fx, event);
                break;
        }
        return false;
    }

    /**
     * 触摸监控事件
     *
     * @param fx
     * @param event
     */
    private void touchShijian(String fx, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ControlGame("T" + fx);
                LogUtil.e("T" + fx);
                break;

            case MotionEvent.ACTION_UP:
                ControlGame("S" + fx);
                LogUtil.e("S" + fx);
                break;
        }
    }


    /**
     * 点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_play_back:
                listen();
                break;
            case R.id.iv_play_startgame:
                if (isOpen && isMovie) {
                    if (newCP != 0 && newCP > rowsBean.getF_Price()) {
                        showLoadingDialog("准备 !");
                        iv_play_startgame.setImageResource(R.mipmap.srartgame3);
                        soundUtils.playSound(start, 0);
                        //查询游戏的状态,先查询机器状态,再开始游戏
                        presenter.initQueryGame(rowsBean.getF_ID(), startGame);
//                        presenter.initUpperGame(rowsBean.getF_ID());
                    } else {
                        ToastUtil.show("余额不足,请充值!");
                        startActivity(new Intent(getContext(), RechargeActivity.class));
                    }

                } else {
                    ToastUtil.show("还有其他玩家在玩,请稍等!");
                }

                break;
            case R.id.iv_play_up:

                ControlGame(up);
                break;
            case R.id.iv_play_down:
                ControlGame(down);
                break;
            case R.id.iv_play_left:
                ControlGame(left);
                break;
            case R.id.iv_play_right:
                ControlGame(right);
                break;
            case R.id.iv_play_catch:

                ControlGame("DOWN");
                break;
            case R.id.iv_play_change:

                if (isURL) {
                    isURL = false;
                    mView1.setVisibility(View.VISIBLE);
                    mView2.setVisibility(View.GONE);
                    //  FORWARD BACKWARD LEFT  RIGHT
                    //改变方向
                    up = "FORWARD";
                    down = "BACKWARD";
                    left = "LEFT";
                    right = "RIGHT";
                } else {
                    isURL = true;
                    mView1.setVisibility(View.GONE);
                    mView2.setVisibility(View.VISIBLE);
                    //改变方向
                    up = "LEFT";
                    down = "RIGHT";
                    left = "BACKWARD";
                    right = "FORWARD";
                }
                break;
            case R.id.iv_play_recharge:
                startActivity(new Intent(getContext(), RechargeActivity.class));
                break;
        }

    }


    /**
     * 操作方向
     *
     * @param forward
     */
    private void ControlGame(String forward) {
        if (isPlay) {
            presenter.initControlGame(rowsBean.getF_ID(), forward, gameBeanRows.getToken(), gameBeanRows.getTimestamp() + "");
            if (forward.equals("DOWN")) {
                tv_play_info.setVisibility(View.VISIBLE);
                CountdownUtil.getInstance().cancel("play");
                soundUtils.playSound(take, 0);
                isPlay = false;//正在抓取,不能操作了
                changeIVColor();
            }
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        creatSoundPool();
        //无限播放背景音乐
        soundUtils.playSoundLun(bgvoice, SoundUtils.INFINITE_PLAY);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 停止背景音乐
        soundUtils.stopSound();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onDestroy() {
        super.onDestroy();
        isHave = false;
        CountdownUtil.getInstance().cancelAll();
        if (mutliThread != null) {
            mutliThread.pauseThread();
            mutliThread = null;
        }
        //取消订阅
        EventBusUtil.unregister(this);
        //判断是否有流,再关闭,不然会报错
        if (mLivePlayer1 != null && mLivePlayer1.isPlaying()) {
            mLivePlayer1.stopPlay(true); // true代表清除最后一帧画面

        }
        if (mLivePlayer2 != null && mLivePlayer2.isPlaying()) {
            mLivePlayer2.stopPlay(true); // true代表清除最后一帧画面

        }
        // 停止背景音乐
        soundUtils.stopSound();
//        soundUtils = null;
        mView1.onDestroy();
        mView2.onDestroy();
        if (mRecorder != null) {
            mRecorder.quit();
            mRecorder = null;
        }
    }

    /**
     * 上机成功
     *
     * @param gameBean
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void showStartGame(StartGameBean gameBean) {
        newCP = newCP - rowsBean.getF_Price();
        tv_play_cp.setText(newCP + "");
        gameBeanRows = gameBean.getRows();
        isPlay = false;
        switch (gameBean.getCode()) {
            case 1://成功
                tv_play_mian_type.setText("游戏中");
                isPlay = true;
                if (is_lp) {
                    luZhi("");//开始游戏,录制视频
                }
                mutliThread.pauseThread();//暂停查询
                ToastUtil.show("开始游戏吧!");
                isStart = false;
                //需要播放的地方执行这句即可, 参数分别是声音的编号和循环次数
                soundUtils.playSound(readygo, 0);

                ll_play_open.setVisibility(View.GONE);
                ll_play_caozuo.setVisibility(View.VISIBLE);
                iv_play_startgame.setImageResource(R.mipmap.srartgame);
                //倒计时
                CountdownUtil.getInstance().newTimer(30000, 1000, new CountdownUtil.ICountDown() {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        tv_play_djs.setText(millisUntilFinished / 1000 + "\"");

                    }

                    @Override
                    public void onFinish() {
                        LogUtil.e("我是倒计时,我结束了");
                        tv_play_djs.setText("0\"");
                        ControlGame("DOWN");
                    }
                }, "play");
                break;
            case 0://失败
                ToastUtil.show(gameBean.getInfo());
                break;
            case -9999://当前有用户正在游戏
                ToastUtil.show(gameBean.getInfo());
                break;
        }

    }

    /**
     * 监听返回按键,当还在上机时,就弹出提示,下机
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            listen();
        }

        return false;

    }

    /**
     * 判断是否在游戏,弹出提示
     */
    private void listen() {
        if (isStart) {
            finish();
        } else {
            // 创建退出对话框
            isExit = new AlertDialog.Builder(this).create();
            // 设置对话框标题
            isExit.setTitle("系统提示");
            // 设置对话框消息
            isExit.setMessage("亲,您正在游戏当中确定要提出游戏么(退出游戏将扣除游戏币,并不记录游戏记录)");
            // 添加选择按钮并注册监听
            isExit.setButton("确定", listener);
            isExit.setButton2("取消", listener);
            // 显示对话框
            isExit.show();
        }
    }

    /**
     * 监听对话框里面的button点击事件
     */
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
                    presenter.initLowerGame(rowsBean.getF_ID());
                    isExit.dismiss();
                    finish();
                    break;
                case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
                    isExit.dismiss();
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    public void showFailed() {
        isPlay = false;
        ToastUtil.show("游戏失败,请检查网络!");
    }

    @Override
    public void showFinish() {
        dismissLoadingDialog();
    }


    /**
     * 监听第一个直播流拉流事件
     */
    private void playerListen1() {
        mLivePlayer1.setPlayListener(new ITXLivePlayListener() {
            @Override
            public void onPlayEvent(int i, Bundle bundle) {
                LogUtil.e("       i   " + i + "        bundle  " + bundle.toString());
                switch (i) {
                    case 2004:
//                        ToastUtil.show("欢迎进入游戏间");
                        dismissLoadingDialog();
                        break;
                    case 2002:
                        if (isFirst) {
                            creatTXLivePlayer2();
                            isFirst = false;
                        }
//                        ToastUtil.show("欢迎进入游戏间");
                        //延迟一秒钟
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                sdv_play_video_bg.setVisibility(View.GONE);
                                iv_play_loading_bg.setVisibility(View.GONE);
                            }
                        }, 1000);

                        isMovie = true;
                        dismissLoadingDialog();
                        break;
                    case 2007:
                        ToastUtil.show("有点延迟...");
                        showLoadingDialog("");
                        break;
                    case -2301:
                        ToastUtil.show("多次连接失败,请联系客服!");
                        finish();
                        dismissLoadingDialog();
                }
            }

            @Override
            public void onNetStatus(Bundle bundle) {
//                LogUtil.e(bundle.toString());
            }
        });
    }

    /**
     * 监听第二个直播流拉流事件
     */
    private void playerListen2() {
        mLivePlayer2.setPlayListener(new ITXLivePlayListener() {
            @Override
            public void onPlayEvent(int i, Bundle bundle) {
                LogUtil.e("       i   " + i + "        bundle  " + bundle.toString());
                switch (i) {
                    case 2004:
                        iv_play_change.setClickable(true);
                        dismissLoadingDialog();
                        break;
                    case 2002:
                        //无限播放背景音乐
                        soundUtils.playSoundLun(bgvoice, SoundUtils.INFINITE_PLAY);
                        if (isFirst) {
                            creatTXLivePlayer2();
                            isFirst = false;
                        }
                        iv_play_change.setClickable(true);
                        dismissLoadingDialog();
                        break;
                    case 2007:
                        ToastUtil.show("有点延迟...");
                        showLoadingDialog("");
                        break;

                }
            }

            @Override
            public void onNetStatus(Bundle bundle) {
//                LogUtil.e(bundle.toString());
            }
        });
    }

    /**
     * 检查wifi是否处开连接状态
     *
     * @return
     */
    public boolean isWifiConnect() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifiInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return mWifiInfo.isConnected();
    }

    /**
     * 检查wifi强弱并更改图标显示
     */
    int wifiNum = 0;

    public void checkWifiState() {

        if (isWifiConnect()) {
            WifiManager mWifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo mWifiInfo = mWifiManager.getConnectionInfo();
            int wifi = mWifiInfo.getRssi();//获取wifi信号强度
            if (wifi > -50 && wifi < 0) {//最强
                if (wifiNum != 1) {
                    wifiNum = 1;
                    Log.e("tag", "最强");
                    iv_play_wifi.setImageResource(R.mipmap.wifi_4);
                }

            } else if (wifi > -70 && wifi < -50) {//较强
                if (wifiNum != 2) {
                    wifiNum = 2;
                    Log.e("tag", "较强");
                    iv_play_wifi.setImageResource(R.mipmap.wifi_3);
                }

            } else if (wifi > -80 && wifi < -70) {//较弱
                if (wifiNum != 3) {
                    wifiNum = 3;
                    Log.e("tag", "较弱");
                    iv_play_wifi.setImageResource(R.mipmap.wifi_2);
                }

            } else if (wifi > -100 && wifi < -80) {//微弱
                if (wifiNum != 4) {
                    wifiNum = 4;
                    Log.e("tag", "微弱");
                    iv_play_wifi.setImageResource(R.mipmap.wifi_1);
                }

            }
        } else {
            //无连接
            Log.e("tag", "无连接");
            iv_play_wifi.setImageResource(R.mipmap.wifi_null);
        }
    }

    /**
     * 录制屏幕视频
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        MediaProjection mediaProjection = mMediaProjectionManager.getMediaProjection(resultCode, data);
        if (mediaProjection == null) {
            Log.e("@@", "media projection is null");
            return;
        }
        // video size
        final int width = 1280;
        final int height = 720;
        File file = new File(Environment.getExternalStorageDirectory(),
                "record-" + width + "x" + height + "-" + System.currentTimeMillis() + ".mp4");
        moviePath = file.getAbsolutePath();
        final int bitrate = 4000000;
        mRecorder = new ScreenRecorder(width, height, bitrate, 1, mediaProjection, file.getAbsolutePath());
        mRecorder.start();
    }

    private static final int REQUEST_CODE = 1;

    /**
     * 开始录制
     *
     * @param grabID
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void luZhi(String grabID) {
        if (mRecorder != null) {
            mRecorder.quit();
            mRecorder = null;
            presenter.initgetGetUploadSignature(grabID, moviePath);
        } else {
            if (isPlay) {
                Intent captureIntent = mMediaProjectionManager.createScreenCaptureIntent();
                startActivityForResult(captureIntent, REQUEST_CODE);
            }

        }
    }


}
