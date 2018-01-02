package com.zhuazhuale.changsha.module.home.Bean;

/**
 * Created by Administrator on 2018/1/2 0002.
 */

public class NetworkMonitor {

    private boolean networkAvailable;

    private NetworkMonitor(){}

    private static NetworkMonitor instance = new NetworkMonitor();

    public static void setNetworkStatus(boolean networkStatus){
        instance.networkAvailable = networkStatus;
    }

    public static boolean isNetworkAvailable(){
        return instance.networkAvailable;
    }
}