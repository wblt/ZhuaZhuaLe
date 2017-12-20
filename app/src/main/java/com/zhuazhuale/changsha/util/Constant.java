package com.zhuazhuale.changsha.util;

/**
 * Created by Administrator on 2017/12/12.
 */

public class Constant {

    //请求数据时的不同触发时机
    public static final int INIT = 3;//初始化数据
    public static final int REFRESH = 4;//刷新数据
    public static final int LOADMORE = 5;//加载更多数据


    public static String URL = "http://106.14.192.166:8088";

    //  首页的循环广告
    public static String BaseData = URL + "/api/Account/BaseData";
    //  获取设备商品信息
    public static String GetDeviceGoods = URL + "/api/Device/GetDeviceGoods";
    //获取账号余额信息
    public static String GetNewCP = URL + "/api/Account/GetNewCP";
    //获取充值兑换比例
    public static String GetAllPriceProduct = URL + "/api/Account/GetAllPriceProduct";
    //我的娃娃币
    public static String BanlanceWater = URL + "/api/Account/BanlanceWater";
    //我的抓取记录
    public static String GrabWater = URL + "/api/Account/GrabWater";
    //邮箱登录
    public static String LoginMain = URL + "/api/Account/LoginMain";
    //我的战利品
    public static String QueryUserGoods = URL + "/api/Account/QueryUserGoods";
    //我的订单
    public static String GetOrders = URL + "/api/Order/GetOrders";


}
