package com.zhuazhuale.changsha.module.vital.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMValueCallBack;
import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.app.MyApplication;
import com.zhuazhuale.changsha.model.entity.eventbus.CPfreshEvent;
import com.zhuazhuale.changsha.module.vital.adapter.ChatAdapter;
import com.zhuazhuale.changsha.module.vital.bean.MsgBean;
import com.zhuazhuale.changsha.module.vital.bean.MsgInfoListBean;
import com.zhuazhuale.changsha.util.EventBusUtil;
import com.zhuazhuale.changsha.util.ToastUtil;
import com.zhuazhuale.changsha.util.log.LogUtil;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import tencent.tls.platform.TLSErrInfo;
import tencent.tls.platform.TLSPwdLoginListener;
import tencent.tls.platform.TLSStrAccRegListener;
import tencent.tls.platform.TLSUserInfo;

/**
 * Created by Administrator on 2018/1/26.
 */

public class IMchatActivity extends AppBaseActivity {

    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    @BindView(R.id.et_info)
    EditText et_info;
    @BindView(R.id.bt_fs)
    Button bt_fs;
    private List<MsgBean> msgBeen = new ArrayList<>();
    private ChatAdapter chatAdapter;
    private TIMConversation conversation;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_imchat);
    }

    @Override
    protected void initView() {
        chatAdapter = new ChatAdapter(this, msgBeen);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setAdapter(chatAdapter);

    }

    @Override
    protected void obtainData() {
        IMChat.getInstance().changeGroup();//创建监听
        EventBusUtil.register(this);
        IMChat.getInstance().joinGroup("@TGS#aHVZSSBFX");
        IMChat.getInstance().getGroupMembers("@TGS#aHVZSSBFX", new TIMValueCallBack() {
            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onSuccess(Object o) {

            }
        });
    }

    //EventBus的事件接收，从事件中获取最新的收藏数量并更新界面展示
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(MsgInfoListBean event) {
        chatAdapter.replaceData(event.getMsgBeen());
    }

    @Override
    protected void initEvent() {
        //会话类型：群组
        if (conversation == null) {
            conversation = TIMManager.getInstance().getConversation(
                    TIMConversationType.Group,      //会话类型：群组
                    "@TGS#aHVZSSBFX");
          /*  conversation = TIMManager.getInstance().getConversation(
                    TIMConversationType.C2C,    //会话类型：单聊
                    "q454216935");*/
        }

        bt_fs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = et_info.getText().toString().trim();
                IMChat.getInstance().sendMessage(conversation, msg);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusUtil.unregister(this);
        IMChat.getInstance().quitGroup("@TGS#aHVZSSBFX");

    }
}
