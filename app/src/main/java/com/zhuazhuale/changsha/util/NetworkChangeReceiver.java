package com.zhuazhuale.changsha.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.zhuazhuale.changsha.module.home.Bean.NetworkMonitor;

/**
 * 网络变化和强度的广播类
 * Created by Administrator on 2018/1/2 0002.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent ) {
        boolean avaiable = Network.isAvailable(context);
        NetworkMonitor.setNetworkStatus(avaiable);
        Log.e("test", ""+avaiable);
    }
}
