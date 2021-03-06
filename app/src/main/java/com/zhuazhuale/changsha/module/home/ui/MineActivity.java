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
import com.zhuazhuale.changsha.view.widget.MaterialDialog;

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
    @BindView(R.id.ic_sc)
    View ic_sc;
    @BindView(R.id.tv_mine_qd)
    TextView tv_mine_qd;
    @BindView(R.id.sdv_mine_face)
    SimpleDraweeView sdv_mine_face;
    @BindView(R.id.tv_mine_name)
    TextView tv_mine_name;
    @BindView(R.id.tv_mine_yue)
    TextView tv_mine_yue;
    @BindView(R.id.tv_mine_id)
    TextView tv_mine_id;
    @BindView(R.id.tv_mine_dhby)
    TextView tv_mine_dhby;
    @BindView(R.id.tv_mine_by)
    TextView tv_mine_by;

    private Intent intent;
    private MinePresenter presenter;
    private String id;
    private MaterialDialog mDialog;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_mine);
    }

    @Override
    protected void initView() {
        String F_Img = PreferenceUtil.getString(MyApplication.getInstance(), BaseConstants.F_Img, "");

        if (F_Img != null) {
            FrescoUtil.getInstance().loadNetImage(sdv_mine_face, F_Img);
        } else {
            FrescoUtil.getInstance().loadResourceImage(sdv_mine_face, R.mipmap.ic_image_load);
        }
        String name = PreferenceUtil.getString(MyApplication.getInstance(), BaseConstants.F_Name, "");

        tv_mine_name.setText(name);
        TextView tv_cz = (TextView) ic_cz.findViewById(R.id.tv_list_n);
        TextView tv_wwb = (TextView) ic_wwb.findViewById(R.id.tv_list_n);
        TextView tv_zqjl = (TextView) ic_zqjl.findViewById(R.id.tv_list_n);
        TextView tv_zlp = (TextView) ic_zlp.findViewById(R.id.tv_list_n);
        TextView tv_dd = (TextView) ic_dd.findViewById(R.id.tv_list_n);
        TextView tv_dz = (TextView) ic_dz.findViewById(R.id.tv_list_n);
        TextView tv_sc = (TextView) ic_sc.findViewById(R.id.tv_list_n);
        tv_cz.setText("充值中心");
        tv_wwb.setText("娃娃币记录");
        tv_zqjl.setText("抓取记录");
        tv_zlp.setText("战利品管理");
        tv_dd.setText("订单中心");
        tv_dz.setText("地址管理");
        tv_sc.setText("网上商城");
        String F_Code1 = PreferenceUtil.getString(MyApplication.getInstance(), BaseConstants.F_Code1, "");

        if (F_Code1 != null) {
            // 邀请码
            id = F_Code1;
        } else {
            id = "";
        }
        tv_mine_id.setText(id);
        String signTime = PreferenceUtil.getString(getContext(), BaseConstants.SignTime, "");
        if (signTime.isEmpty()) {
            tv_mine_qd.setText("签到");
        } else {
            try {
                boolean b = DataUtils.IsToday(signTime);
                if (b) {
                    tv_mine_qd.setText("已签到");
                } else {
                    tv_mine_qd.setText("签到");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void obtainData() {
        mDialog = new MaterialDialog(this);
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
        ic_sc.setOnClickListener(this);
        tv_mine_qd.setOnClickListener(this);
        tv_mine_dhby.setOnClickListener(this);
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
            case R.id.ic_sc:
                intent = new Intent(MineActivity.this, SmallActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_mine_qd:
                String sign = tv_mine_qd.getText().toString();
                if ("签到".equals(sign)) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                    String str = formatter.format(curDate);
                    LogUtil.e(str);
                    presenter.initUserSign(str);
                } else {
                    ToastUtil.show("您已签过到了!");
                }
                break;
            case R.id.tv_mine_dhby:
                mDialog.setTitle("城市抓抓乐温馨提示");
                mDialog.setMessage("您确定使用128游戏币兑换包邮劵么");

                mDialog.setPositiveButton("确定兑换", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                        showLoadingDialog("");
                        presenter.initDuiHuan();
                    }
                });
                mDialog.setNegativeButton("取消兑换", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                    }
                });
                mDialog.show();
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
            tv_mine_by.setText(newCPBean.getRows().getF_BagNumber() + "");
        }
    }

    @Override
    public void showSignSuccess(EditAddressBean bean, String time) {
        ToastUtil.show(bean.getInfo());
        switch (bean.getCode()) {
            case -1:
                PreferenceUtil.putString(getContext(), BaseConstants.SignTime, time);
                tv_mine_qd.setText("已签到");
                break;
            case 0:
                break;
            case 1:
                tv_mine_qd.setText("已签到");
                PreferenceUtil.putString(getContext(), BaseConstants.SignTime, time);
                break;
        }
    }

    @Override
    public void showDuiHuan(NewCPBean newCPBean) {
        ToastUtil.show(newCPBean.getInfo());
        dismissLoadingDialog();
        if (newCPBean.getCode() == 1) {
            presenter.initNewCP();
        }
    }

}
