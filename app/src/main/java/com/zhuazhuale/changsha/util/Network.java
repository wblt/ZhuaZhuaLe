package com.zhuazhuale.changsha.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.zhuazhuale.changsha.module.home.Bean.WifiData;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/1/2 0002.
 */

public class Network {

    public static final int CHINA_MOBILE = 1; // 中国移动
    public static final int CHINA_UNICOM = 2; // 中国联通
    public static final int CHINA_TELECOM = 3; // 中国电信

    public static final int SIM_OK = 0;
    public static final int SIM_NO = -1;
    public static final int SIM_UNKNOW = -2;

    public static boolean proxy = false;
    private static BroadcastReceiver connChangerRvr;

    public static final String CONN_TYPE_WIFI = "wifi";
    public static final String CONN_TYPE_GPRS = "gprs";
    public static final String CONN_TYPE_NONE = "none";

    /**
     * 判断网络连接有效
     *
     * @param context
     *            Context对象
     * @return 网络处于连接状态（3g or wifi)
     */
    public static boolean isAvailable(Context context) {
        boolean result = false;
        if (getNetworkType(context) >= 0)
            result = true;
        return result;
    }

    /**
     * 获取网络类型
     *
     * @param context
     *            Context对象
     * @return 当前处于连接状态的网络类型
     */
    public static int getNetworkType(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) (context
                .getSystemService(Context.CONNECTIVITY_SERVICE));
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnectedOrConnecting()) {
                return info.getType();
            }
        }
        return -9;
    }

    /**
     * 获取网络类型
     *
     * @param context
     *            Context对象
     * @return 当前处于连接状态的网络类型
     */
    public static String getNetworkInfo(Context context) {
        String result = null;
        ConnectivityManager connectivity = (ConnectivityManager) (context
                .getSystemService(Context.CONNECTIVITY_SERVICE));
        if (connectivity == null) {
            result = null;
        } else {

            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null) {
                NetworkInfo.State tem = info.getState();
                if ((tem == NetworkInfo.State.CONNECTED || tem == NetworkInfo.State.CONNECTING)) {
                    result = info.getTypeName() + " " + info.getSubtypeName()
                            + info.getExtraInfo();
                }
            }
        }
        return result;
    }

    /**
     * 获取SIM卡状态
     *
     * @return SIM_OK sim卡正常 SIM_NO 不存在sim卡 SIM_UNKNOW sim卡状态未知
     */
    public static int getSimState(Context context) {
        TelephonyManager telMgr = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        int simState = telMgr.getSimState();
        if (simState == TelephonyManager.SIM_STATE_READY) {
            return SIM_OK;
        } else if (simState == TelephonyManager.SIM_STATE_ABSENT) {
            return SIM_NO;
        } else {
            return SIM_UNKNOW;
        }
    }

    /**
     * 判断当前的网络状态 wifi或者gprs
     *
     * @param context
     * @return
     */
    public static String getNetConnType(Context context) {
        // 获得网络连接服务
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null == connManager) {
            Log.w("Network", "can not get Context.CONNECTIVITY_SERVICE");
            return CONN_TYPE_NONE;
        }

        NetworkInfo info = null;
        // wifi的网络状态
        info = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (null != info) {
            NetworkInfo.State wifiState = info.getState();
            if (NetworkInfo.State.CONNECTED == wifiState) { // 判断是否正在使用WIFI网络
                return CONN_TYPE_WIFI;
            }
        } else {
            Log.w("Network", "can not get ConnectivityManager.TYPE_WIFI");
        }

        // gprs的网络状态
        info = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (null != info) {
            NetworkInfo.State mobileState = info.getState();
            if (NetworkInfo.State.CONNECTED == mobileState) { // 判断是否正在使用GPRS网络
                return CONN_TYPE_GPRS;
            }
        } else {
            Log.w("Network", "can not get ConnectivityManager.TYPE_MOBILE");
        }
        return CONN_TYPE_NONE;
    }

    /**
     * 获取wifi的信号强度，网速等信息<br>
     * 参考：http://blog.csdn.net/way_ping_li/article/details/8777478<br/>
     * wifi信号强度发生变化的时候会发出广播：RSSI_CHANGED_ACTION = "android.net.wifi.RSSI_CHANGED"
     *
     * @param context
     * @return
     */
    public static WifiData getWifiInfo(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if(wifiManager == null){
            return null;
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if(wifiInfo == null){
            return null;
        }
        if (wifiInfo.getBSSID() == null) {
            return null;
        }
        //wifi名称
        String ssid = wifiInfo.getSSID();
        //wifi信号强度 ,[0,numLevels-1]
        int signalLevel = WifiManager.calculateSignalLevel(wifiInfo.getRssi(), 5);
        //wifi速度
        int speed = wifiInfo.getLinkSpeed();
        //wifi速度单位
        String units = WifiInfo.LINK_SPEED_UNITS;
        return new WifiData(ssid, signalLevel, speed, units);
    }

    /**
     * 获取运营商类型,通过运营商类型和imsi判断
     *
     * @return CHINA_MOBILE 中国移动 CHINA_TELECOM 中国电信 CHINA_UNICOM 中国联通
     */
    /*public static int getNSP(Context context) {

        if (getSimState(context) == SIM_OK) {
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String operator = tm.getNetworkOperatorName().replaceAll(" ", ""); // honghua.jcc

            String numeric = tm.getNetworkOperator();

            // String test = tm.getSimOperator();
            Log.d("NSP: ", "operator = " + operator + "  type = " + numeric);
            // operator 取不到数据时，numeric是有数据的可以用numeric做判断 2011 1022 byjiuwan
            if ((operator == null || "".equals("")) && numeric != null) {
                operator = numeric;
            }
            if (operator == null || operator.length() == 0) {
                return SIM_UNKNOW;
            }

            // honghua.jcc 获得的运营商字符串可能含有不确定个数的多个空格，会导致比较比对失败。故去掉空格后再比较
            if (operator.compareToIgnoreCase("中国移动") == 0
                    || operator.compareToIgnoreCase("CMCC") == 0
                    || operator.compareToIgnoreCase("ChinaMobile") == 0
                    || operator.compareToIgnoreCase("46000") == 0) {

                return CHINA_MOBILE;
            } else if (operator.compareToIgnoreCase("中国电信") == 0
                    || operator.compareToIgnoreCase("ChinaTelecom") == 0
                    || operator.compareToIgnoreCase("46003") == 0
                    || operator.compareToIgnoreCase("ChinaTelcom") == 0
                    || operator.compareToIgnoreCase("460003") == 0) {
                return CHINA_TELECOM;
            } else if (operator.compareToIgnoreCase("中国联通") == 0
                    || operator.compareToIgnoreCase("ChinaUnicom") == 0
                    || operator.compareToIgnoreCase("46001") == 0
                    || operator.compareToIgnoreCase("CU-GSM") == 0
                    || operator.compareToIgnoreCase("CHN-CUGSM") == 0
                    || operator.compareToIgnoreCase("CHNUnicom") == 0) {
                return CHINA_UNICOM;
            } else {
                String imsi = PhoneInfo.getImsi(context);
                if (imsi.startsWith("46000") || imsi.startsWith("46002")
                        || imsi.startsWith("46007"))
                    return CHINA_MOBILE;
                else if (imsi.startsWith("46001"))
                    return CHINA_UNICOM;
                else if (imsi.startsWith("46003"))
                    return CHINA_TELECOM;
                else
                    return SIM_UNKNOW;
            }
        } else {
            return SIM_NO;
        }
    }*/

    /**
     * 清空wap代理设置 注销广播回调
     *
     * @param context
     *            Context实例
     */
    public static void unRegNetWorkRev(Context context) {
        setProxy(null, null);
        try {
            if (connChangerRvr != null)
                context.unregisterReceiver(connChangerRvr);
        } catch (Exception e) { // register未完成时调用此方法可能报错
            e.printStackTrace();
            return;
        }
    }

    /**
     * 设置代理服务器，在wap拨号网络时手动设定 if host == null or host's length is 0 set unuse
     * proxy
     *
     * @param host
     *            代理服务器地址
     * @param port
     *            代理端口
     */
    public static void setProxy(String host, String port) {
        if (host == null || host.length() == 0) {
            System.getProperties().put("proxySet", "false");
            proxy = false;
        } else {
            proxy = true;
            System.getProperties().put("proxySet", "true");
            System.getProperties().put("proxyHost", host);
            if (port != null && port.length() > 0)
                System.getProperties().put("proxyPort", port);
            else
                System.getProperties().put("proxyPort", "80");
        }
    }

    /**
     * 获取当前网络的代理信息 如果当前无网络 、网络为wifi 或mobile umts 则返回null 如果当前apn找不到 则返回null
     */
    public static HashMap<String, String> getProxyInfo(Context context, Uri uri) {
        String result = Network.getNetworkInfo(context);
        HashMap<String, String> proxy = new HashMap<String, String>();
        if (result == null)
            return null;
        Log.d("getProxyInfo", "current network:" + result);
        if (result.indexOf("WIFI") != -1
                || result.compareToIgnoreCase("MOBILE UMTS") == 0) {
            return proxy;
        }
        Cursor cr = null;
        try {

            cr = context.getContentResolver().query(uri, null, "mcc ='460'",
                    null, null);
            if (cr.moveToFirst()) {
                do {
                    if (cr.getCount() > 0) {

                        // TaoHelper.APN apn = new TaoHelper.APN();
                        proxy.put("host",
                                cr.getString(cr.getColumnIndex("proxy")));
                        proxy.put("port",
                                cr.getString(cr.getColumnIndex("port")));
                        String apn = cr.getString(cr.getColumnIndex("apn"));
                        Log.d("getProxyInfo", "apn:" + apn);
                        if (result.contains(apn)) {
                            return proxy;
                        }
                    }

                } while (cr.moveToNext());
            }
        } catch (Exception e) {

        } finally {
            if (cr != null) {
                cr.close();
            }

        }
        return null;
    }

    /**
     * 获取当前网络的https代理信息 如果无代理 则返回null
     */
   /* public static HttpHost getHttpsProxyInfo(Context context) {

        HttpHost proxy = null;
        if (android.os.Build.VERSION.SDK_INT < 11) {
            NetworkInfo info = null;
            try {
                ConnectivityManager cm = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                info = cm.getActiveNetworkInfo();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (info != null && info.isAvailable()
                    && info.getType() == ConnectivityManager.TYPE_MOBILE) {
                String proxyHost = Proxy.getDefaultHost();
                int port = Proxy.getDefaultPort();
                if (proxyHost != null)
                    proxy = new HttpHost(proxyHost, port);
            }

            return proxy;
        } else {

            String httpsproxyhost = System.getProperty("https.proxyHost");
            String proxyport = System.getProperty("https.proxyPort");

            if (!TextUtils.isEmpty(httpsproxyhost)) {

                int port = Integer.parseInt(proxyport);
                proxy = new HttpHost(httpsproxyhost, port);
            }

            return proxy;
        }
    }*/
}