package com.zhuazhuale.changsha.module.home.ui;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.module.home.Bean.EditAddressBean;
import com.zhuazhuale.changsha.module.home.presenter.InputCodePresenter;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import butterknife.BindView;

/**
 * 输入邀请码
 * Created by 丁琪 on 2017/12/14.
 */

public class InputCodeActivity extends AppBaseActivity implements View.OnClickListener, IInputCodeView {
    @BindView(R.id.et_inputcode_num)
    EditText et_inputcode_num;
    @BindView(R.id.iv_inputcode_submit)
    ImageView iv_inputcode_submit;
    private InputCodePresenter presenter;
    private String vCode;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_inputcode);
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void obtainData() {
        presenter = new InputCodePresenter(this);
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
                    ToastUtil.show("请输入邀请码!");
                } else {
                    showLoadingDialog();
                    presenter.initInvitation(vCode);
                }

                break;
        }
    }

    @Override
    public void showSuccess(EditAddressBean bean) {
        if (bean.getCode() == 0) {
            ToastUtil.show(bean.getInfo());
        } else {
            ToastUtil.show(bean.getInfo());
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
