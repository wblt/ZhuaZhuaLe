package com.zhuazhuale.changsha.module.home.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.EditAddressBean;
import com.zhuazhuale.changsha.module.home.Bean.GradWaterBean;
import com.zhuazhuale.changsha.module.home.Bean.LiYouBean;
import com.zhuazhuale.changsha.module.home.adapter.ShenSuAdapter;
import com.zhuazhuale.changsha.module.home.presenter.ShenSuPresenter;
import com.zhuazhuale.changsha.util.FrescoUtil;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 申诉页面
 * Created by Administrator on 2017/12/19.
 */

public class ShenSuActivity extends AppBaseActivity implements View.OnClickListener, IShenSuView {
    @BindView(R.id.rv_shensu_list)
    RecyclerView rv_shensu_list;
    @BindView(R.id.sdv_shensu_img)
    SimpleDraweeView sdv_shensu_img;
    @BindView(R.id.tv_shensu_goodsname)
    TextView tv_shensu_goodsname;
    @BindView(R.id.tv_shensu_goodsid)
    TextView tv_shensu_goodsid;
    @BindView(R.id.iv_shensu_submit)
    ImageView iv_shensu_submit;
    @BindView(R.id.tv_shensu_shipin)
    TextView tv_shensu_shipin;

    private List<LiYouBean> liYouBeen;
    private ShenSuAdapter shenSuAdapter;
    private GradWaterBean.RowsBean rowsBean;
    private ShenSuPresenter presenter;
    private String remark = "";


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_shensu);
        Intent intent = getIntent();
        rowsBean = (GradWaterBean.RowsBean) intent.getSerializableExtra("rowsBean");
    }

    @Override
    protected void initView() {
        FrescoUtil.getInstance().loadNetImage(sdv_shensu_img, rowsBean.getF_GoodsImgA());//加载网络图片
        tv_shensu_goodsname.setText(rowsBean.getF_GoodsName());
        tv_shensu_goodsid.setText(rowsBean.getF_DeviceNo());
        if (rowsBean.getF_VideoUrl().isEmpty()) {
            tv_shensu_shipin.setText("无");
        } else {
            tv_shensu_shipin.setText("已有视频");
        }
        liYouBeen = new ArrayList<>();
        liYouBeen.add(new LiYouBean(false, "游戏工作人员补货"));
        liYouBeen.add(new LiYouBean(false, "没有成功上机就扣币"));
        liYouBeen.add(new LiYouBean(false, "爪子没反应,无法移动"));
        liYouBeen.add(new LiYouBean(false, "弹出'机器正在维护'等提示后断线"));
        liYouBeen.add(new LiYouBean(false, "侧面摄像头故障,(遇到此情况不要重复上机)"));
        liYouBeen.add(new LiYouBean(false, "摄像头歪到无法使用"));
        liYouBeen.add(new LiYouBean(false, "娃娃掉进洞却显示失败"));
        liYouBeen.add(new LiYouBean(false, "娃娃被爪子吊在洞口正上方"));
        liYouBeen.add(new LiYouBean(false, "其他原因"));
        showLiYouList(liYouBeen);
    }

    /**
     * 理由列表
     *
     * @param liYouBeen
     */
    private void showLiYouList(List<LiYouBean> liYouBeen) {
        shenSuAdapter = new ShenSuAdapter(this, liYouBeen);
        rv_shensu_list.setLayoutManager(new LinearLayoutManager(this));
        rv_shensu_list.setAdapter(shenSuAdapter);
    }

    @Override
    protected void obtainData() {
        presenter = new ShenSuPresenter(this);
    }

    @Override
    protected void initEvent() {
        iv_shensu_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_shensu_submit:
                submit();
                break;
        }
    }

    /**
     * 提交申诉
     */
    private void submit() {
        if (remark.isEmpty()) {
            ToastUtil.show("请选择一个理由!");
        } else {
            showLoadingDialog("");
            presenter.initAppeal(rowsBean.getF_DeviceID(), rowsBean.getF_ID(), remark, rowsBean.getF_VideoUrl());

        }
    }

    public void goToChangge(LiYouBean liYouBean, int position) {
        for (LiYouBean bean : liYouBeen) {
            bean.setClick(false);
        }
        remark = liYouBean.getLiYou();
        liYouBeen.get(position).setClick(true);
        shenSuAdapter.replaceData(liYouBeen);
    }

    @Override
    public void showSuccess(EditAddressBean newCPBean) {
        if (newCPBean.getCode() == 0) {
            ToastUtil.show(newCPBean.getInfo());
        } else {
            ToastUtil.show(newCPBean.getInfo());
            setResult(2);
            finish();
        }
    }

    @Override
    public void showFinish() {
        dismissLoadingDialog();
    }


    @Override
    public void showFailed() {
        ToastUtil.show("提交失败,请检查网络!");
    }
}
