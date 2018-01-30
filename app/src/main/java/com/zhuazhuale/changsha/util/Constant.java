package com.zhuazhuale.changsha.util;

/**
 * Created by Administrator on 2017/12/12.
 */

public class Constant {
    public static String APPID = "wx6a6349cd0ccf0b09";
    public static String secret = "5bd3f329e4df0d79c07181d4e2b9f072";

    public static int IMSDK_APPID = 1400065180;
    public static String ACCOUNT_TYPE = "22202";

    //请求数据时的不同触发时机
    public static final int INIT = 3;//初始化数据
    public static final int REFRESH = 4;//刷新数据
    public static final int LOADMORE = 5;//加载更多数据


    //    public static String URL = "http://106.14.192.166:8088";
    public static String URL = "http://api.zhuazhuale.com";
    public static String URLGAME = "http://api2.crypfx.com:8899";
//    public static String URLGAME = "'http://106.14.192.166:8092";

    //  首页的循环广告
    public static String BaseData = URL + "/api/Account/BaseData";
    public static String BaseTypeData = URL + "/api/Account/BaseTypeData";
    //  获取设备商品信息
    public static String GetDeviceGoods = URL + "/api/Device/GetDeviceGoods";
    //获取账号余额信息
    public static String GetNewCP = URL + "/api/Account/GetNewCP";
    //获取充值兑换比例
    public static String GetAllPriceProduct = URL + "/api/Account/GetAllPriceProduct";
    //我的娃娃币
    public static String BanlanceWater = URL + "/api/Account/BanlanceWater";
    //////////////////////////////////////
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
    //退出登录
    public static String LoginOut = URL + "/api/Account/LoginOut";
    //微信充值
    public static String WxUnifiedOrder = URL + "/api/Common/WxUnifiedOrder";

    //游戏上机
    public static String UpperGame = URLGAME + "/api/Operation/UpperGame";
    //下机
    public static String LowerGame = URLGAME + "/api/Operation/LowerGame";
    //娃娃机状态查询
    public static String QueryGame = URLGAME + "/api/Operation/QueryGame";
    //操控,机器上下左右
    public static String ControlGame = URLGAME + "/api/Operation/ControlGame";
    //查询在这台机器用户抓取成功的记录
    public static String GetAllUserTrueByDeviceID = URL + "/api/Account/GetAllUserTrueByDeviceID";
    //获取视频的签名
    public static String GetUploadSignature = URL + "/api/Common/GetUploadSignature";
    //上传视频的接口
    /*
     "F_GrabWaterID": self.grabID,抓取之后 返回的
            "F_VideoUrl": result.videoURL视频的链接
*/
    public static String ModiflyVideoUrl = URL + "/api/Common/ModiflyVideoUrl";
    //检查版本更新
    public static String VersionCheck = URL + "/api/Common/VersionCheck";
    //签到
    public static String UserSign = URL + "/api/Account/UserSign";


}
