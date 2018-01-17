package com.zhuazhuale.changsha.module.home.ui;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.app.constant.BaseConstants;
import com.zhuazhuale.changsha.model.entity.eventbus.CPfreshEvent;
import com.zhuazhuale.changsha.module.home.Bean.EditAddressBean;
import com.zhuazhuale.changsha.module.home.Bean.NewCPBean;
import com.zhuazhuale.changsha.module.home.presenter.MinePresenter;
import com.zhuazhuale.changsha.util.DataUtils;
import com.zhuazhuale.changsha.util.EventBusUtil;
import com.zhuazhuale.changsha.util.FrescoUtil;
import com.zhuazhuale.changsha.util.PreferenceUtil;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;

/**
 * 个人中心
 * Created by Administrator on 2017/12/13.
 */

public class MineActivity extends AppBaseActivity implements View.OnClickListener, IMineView {

    @BindView(R.id.ic_cz)
    View ic_cz;
    @BindView(R.id.ic_wwb)
    View ic_wwb;
    @BindView(R.id.ic_zqjl)
    View ic_zqjl;
    @BindView(R.id.ic_zlp)
    View ic_zlp;
    @BindView(R.id.ic_dd)
    View ic_dd;
    @BindView(R.id.ic_dz)
    View ic_dz;
    @BindView(R.id.sdv_mine_face)
    SimpleDraweeView sdv_mine_face;
    @BindView(R.id.tv_mine_name)
    TextView tv_mine_name;
    @BindView(R.id.tv_mine_yue)
    TextView tv_mine_yue;
    @BindView(R.id.tv_mine_id)
    TextView tv_mine_id;

    private Intent intent;
    private MinePresenter presenter;
    private String id;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_mine);
    }

    @Override
    protected void initView() {
        FrescoUtil.getInstance().loadNetImage(sdv_mine_face, MyApplication.getInstance().getRowsBean().getF_Img());
        tv_mine_name.setText(MyApplication.getInstance().getRowsBean().getF_Name());
        TextView tv_cz = (TextView) ic_cz.findViewById(R.id.tv_list_n);
        TextView tv_wwb = (TextView) ic_wwb.findViewById(R.id.tv_list_n);
        TextView tv_zqjl = (TextView) ic_zqjl.findViewById(R.id.tv_list_n);
        TextView tv_zlp = (TextView) ic_zlp.findViewById(R.id.tv_list_n);
        TextView tv_dd = (TextView) ic_dd.findViewById(R.id.tv_list_n);
        TextView tv_dz = (TextView) ic_dz.findViewById(R.id.tv_list_n);
        tv_cz.setText("充值");
        tv_wwb.setText("我的娃娃币");
        tv_zqjl.setText("我的抓取记录");
        tv_zlp.setText("我的战利品");
        tv_dd.setText("我的订单");
        tv_dz.setText("我的地址");
        if (MyApplication.getInstance().getRowsBean().getF_Code1() != null) {
            // 邀请码
            id = MyApplication.getInstance().getRowsBean().getF_Code1();
        } else {
            id = "";
        }
        tv_mine_id.setText(id);
        String signTime = PreferenceUtil.getString(getContext(), BaseConstants.SignTime, "");
        if (signTime.isEmpty()) {
            getTvToolbarRight().setText("签到");
        } else {
            try {
                boolean b = DataUtils.IsToday(signTime);
                if (b) {
                    getTvToolbarRight().setText("已签到");
                } else {
                    getTvToolbarRight().setText("签到");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    protected void obtainData() {
        presenter = new MinePresenter(this);
        presenter.initNewCP();
        EventBusUtil.register(this);//订阅事件
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅
        EventBusUtil.unregister(this);
    }

    @Override
    protected void initEvent() {
        ic_cz.setOnClickListener(this);
        ic_wwb.setOnClickListener(this);
        ic_zqjl.setOnClickListener(this);
        ic_zlp.setOnClickListener(this);
        ic_dd.setOnClickListener(this);
        ic_dz.setOnClickListener(this);

        getTvToolbarRight().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sign = getTvToolbarRight().getText().toString();
                if ("签到".equals(sign)) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                    String str = formatter.format(curDate);
                    LogUtil.e(str);
                    presenter.initUserSign(str);
                } else {
                    ToastUtil.show("您已签过到了!");
                }

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.ic_cz:
                intent = new Intent(MineActivity.this, RechargeActivity.class);
                startActivity(intent);
                break;
            case R.id.ic_wwb:
                intent = new Intent(MineActivity.this, WaWaBiActivity.class);
                startActivity(intent);
                break;
            case R.id.ic_zqjl:
                intent = new Intent(MineActivity.this, RecordActivity.class);
                startActivity(intent);
                break;
            case R.id.ic_zlp:
                intent = new Intent(MineActivity.this, SpoilsActivity.class);
                startActivity(intent);
                break;
            case R.id.ic_dd:
                intent = new Intent(MineActivity.this, OrderActivity.class);
                startActivity(intent);
                break;
            case R.id.ic_dz:
                intent = new Intent(MineActivity.this, AddressActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 我的余额
     *
     * @param newCPBean
     */
    @Override
    public void showNewCP(NewCPBean newCPBean) {
        if (newCPBean.getRows() != null) {
            tv_mine_yue.setText(newCPBean.getRows().getCP() + "");
        }
    }

    @Override
    public void showSignSuccess(EditAddressBean bean, String time) {
        ToastUtil.show(bean.getInfo());
        switch (bean.getCode()) {
            case -1:
                PreferenceUtil.putString(getContext(), BaseConstants.SignTime, time);
                break;
            case 0:
                break;
            case 1:
                getTvToolbarRight().setText("已签到");
                PreferenceUtil.putString(getContext(), BaseConstants.SignTime, time);
                break;
        }
    }
}
