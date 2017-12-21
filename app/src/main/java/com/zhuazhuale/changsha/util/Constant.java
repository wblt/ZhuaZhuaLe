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
    //我的地址
    public static String QueryUserAddress = URL + "/api/Account/QueryUserAddress";
    //添加地址
    public static String ModifyUserAddress = URL + "/api/Account/ModifyUserAddress";
    //删除地址
    public static String DeleteUserAddress = URL + "/api/Account/DeleteUserAddress";
    //输入邀请码
    public static String Invitation = URL + "/api/Account/Invitation";
    //提交申诉
    public static String Appeal = URL + "/api/Account/Appeal";
    //娃娃兑换游戏币
    public static String ExChangeCP = URL + "/api/Account/ExChangeCP";
    //修改商品选中状态
    public static String ModifyUserGoods = URL + "/api/Account/ModifyUserGoods";
    //创建订单
    public static String CreateOrder = URL + "/api/Order/CreateOrder";
    //微信登录
    public static String Login = URL + "/api/Account/Login";


}
