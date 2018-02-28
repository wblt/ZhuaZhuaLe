package com.zhuazhuale.changsha.module.home.ui;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.module.home.Bean.EditAddressBean;
import com.zhuazhuale.changsha.module.home.presenter.InputCodePresenter;
import com.zhuazhuale.changsha.util.Constant;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import butterknife.BindView;

/**
 * 输入福利码
 * Created by 丁琪 on 2017/12/14.
 */

public class InputFlmActivity extends AppBaseActivity implements View.OnClickListener {
    @BindView(R.id.et_inputcode_num)
    EditText et_inputcode_num;
    @BindView(R.id.iv_inputcode_submit)
    ImageView iv_inputcode_submit;
    private String vCode;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_inputflm);
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void obtainData() {
    }

    @Override
    protected void initEvent() {
        iv_inputcode_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_inputcode_submit:
                vCode = et_inputcode_num.getText().toString().trim();
                if (vCode.isEmpty()) {
                    ToastUtil.show("请输入福利码!");
                } else {
                    showLoadingDialog("");
                    initData();
                }

                break;
        }
    }

    private void initData() {
        OkGo.<String>post(Constant.WelfareCode)
                .tag(this)
                .params("F_Code", vCode)
                .params("F_UserID", MyApplication.getInstance().getRowsBean().getF_ID())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtil.e("请求成功" + response.body());
                        Gson gson = new Gson();
                        EditAddressBean bean = gson.fromJson(response.body(), EditAddressBean.class);
                        if (bean.getCode() == 0) {
                            ToastUtil.show(bean.getInfo());
                        } else {
                            ToastUtil.show(bean.getInfo());
                            finish();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        LogUtil.e("请求失败" + response.toString());
                            ToastUtil.show("提交失败,请检查网络!");
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissLoadingDialog();
                    }
                });
    }

}
