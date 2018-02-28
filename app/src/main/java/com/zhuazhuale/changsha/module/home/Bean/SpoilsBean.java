package com.zhuazhuale.changsha.module.home.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/12/20.
 */

public class SpoilsBean implements Serializable{


    /**
     * Code : 1
     * Info :
     * rows : [{"F_ID":"90bdd565-7a13-4f01-9f1f-d3bf31ada620","F_UserID":"bc75c868-0c01-4344-bec5-238fe51e2254","F_GoodsID":"5383288f-c2c1-4125-946b-5f3c64a27932","F_DeviceID":"06d01bc2-93a9-4b3d-9b44-0e490f918db4","F_Name":"小猪佩奇-20CM","F_Img":"http://img.zhuazhuale.com/Goods/peiqi.jpg","F_Check":false,"F_ExChangePrice":30,"F_CreateTime":"2018-02-28 02:38:27","F_ExpiryTime":"2018-03-16"},{"F_ID":"3918c726-69da-4475-8b9c-42313b2e3c72","F_UserID":"bc75c868-0c01-4344-bec5-238fe51e2254","F_GoodsID":"5383288f-c2c1-4125-946b-5f3c64a27932","F_DeviceID":"06d01bc2-93a9-4b3d-9b44-0e490f918db4","F_Name":"小猪佩奇-20CM","F_Img":"http://img.zhuazhuale.com/Goods/peiqi.jpg","F_Check":false,"F_ExChangePrice":30,"F_CreateTime":"2018-02-28 02:38:27","F_ExpiryTime":"2018-03-16"},{"F_ID":"9c84b8ea-de72-4667-9aea-715ca5b5828c","F_UserID":"bc75c868-0c01-4344-bec5-238fe51e2254","F_GoodsID":"5383288f-c2c1-4125-946b-5f3c64a27932","F_DeviceID":"06d01bc2-93a9-4b3d-9b44-0e490f918db4","F_Name":"小猪佩奇-20CM","F_Img":"http://img.zhuazhuale.com/Goods/peiqi.jpg","F_Check":false,"F_ExChangePrice":30,"F_CreateTime":"2018-02-28 02:38:27","F_ExpiryTime":"2018-03-16"},{"F_ID":"50e007ee-f044-4d28-a126-8201874528dc","F_UserID":"bc75c868-0c01-4344-bec5-238fe51e2254","F_GoodsID":"5383288f-c2c1-4125-946b-5f3c64a27932","F_DeviceID":"06d01bc2-93a9-4b3d-9b44-0e490f918db4","F_Name":"小猪佩奇-20CM","F_Img":"http://img.zhuazhuale.com/Goods/peiqi.jpg","F_Check":false,"F_ExChangePrice":30,"F_CreateTime":"2018-02-28 02:38:26","F_ExpiryTime":"2018-03-16"},{"F_ID":"318850ee-d446-4945-8d3b-d356c821d2e2","F_UserID":"bc75c868-0c01-4344-bec5-238fe51e2254","F_GoodsID":"5383288f-c2c1-4125-946b-5f3c64a27932","F_DeviceID":"06d01bc2-93a9-4b3d-9b44-0e490f918db4","F_Name":"小猪佩奇-20CM","F_Img":"http://img.zhuazhuale.com/Goods/peiqi.jpg","F_Check":false,"F_ExChangePrice":30,"F_CreateTime":"2018-02-28 02:38:26","F_ExpiryTime":"2018-03-16"},{"F_ID":"eec1ae00-81d9-4d55-a010-2d41a08df4b5","F_UserID":"bc75c868-0c01-4344-bec5-238fe51e2254","F_GoodsID":"5383288f-c2c1-4125-946b-5f3c64a27932","F_DeviceID":"06d01bc2-93a9-4b3d-9b44-0e490f918db4","F_Name":"小猪佩奇-20CM","F_Img":"http://img.zhuazhuale.com/Goods/peiqi.jpg","F_Check":false,"F_ExChangePrice":30,"F_CreateTime":"2018-02-28 02:38:18","F_ExpiryTime":"2018-03-16"}]
     * PageCount : 0
     * total : 0
     */

    private int Code;
    private String Info;
    private int PageCount;
    private int total;
    private List<RowsBean> rows;

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String Info) {
        this.Info = Info;
    }

    public int getPageCount() {
        return PageCount;
    }

    public void setPageCount(int PageCount) {
        this.PageCount = PageCount;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Serializable{
        /**
         * F_ID : 90bdd565-7a13-4f01-9f1f-d3bf31ada620
         * F_UserID : bc75c868-0c01-4344-bec5-238fe51e2254
         * F_GoodsID : 5383288f-c2c1-4125-946b-5f3c64a27932
         * F_DeviceID : 06d01bc2-93a9-4b3d-9b44-0e490f918db4
         * F_Name : 小猪佩奇-20CM
         * F_Img : http://img.zhuazhuale.com/Goods/peiqi.jpg
         * F_Check : false
         * F_ExChangePrice : 30
         * F_CreateTime : 2018-02-28 02:38:27
         * F_ExpiryTime : 2018-03-16
         */

        private String F_ID;
        private String F_UserID;
        private String F_GoodsID;
        private String F_DeviceID;
        private String F_Name;
        private String F_Img;
        private boolean F_Check;
        private int F_ExChangePrice;
        private String F_CreateTime;
        private String F_ExpiryTime;
        private boolean isCheck;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public String getF_ID() {
            return F_ID;
        }

        public void setF_ID(String F_ID) {
            this.F_ID = F_ID;
        }

        public String getF_UserID() {
            return F_UserID;
        }

        public void setF_UserID(String F_UserID) {
            this.F_UserID = F_UserID;
        }

        public String getF_GoodsID() {
            return F_GoodsID;
        }

        public void setF_GoodsID(String F_GoodsID) {
            this.F_GoodsID = F_GoodsID;
        }

        public String getF_DeviceID() {
            return F_DeviceID;
        }

        public void setF_DeviceID(String F_DeviceID) {
            this.F_DeviceID = F_DeviceID;
        }

        public String getF_Name() {
            return F_Name;
        }

        public void setF_Name(String F_Name) {
            this.F_Name = F_Name;
        }

        public String getF_Img() {
            return F_Img;
        }

        public void setF_Img(String F_Img) {
            this.F_Img = F_Img;
        }

        public boolean isF_Check() {
            return F_Check;
        }

        public void setF_Check(boolean F_Check) {
            this.F_Check = F_Check;
        }

        public int getF_ExChangePrice() {
            return F_ExChangePrice;
        }

        public void setF_ExChangePrice(int F_ExChangePrice) {
            this.F_ExChangePrice = F_ExChangePrice;
        }

        public String getF_CreateTime() {
            return F_CreateTime;
        }

        public void setF_CreateTime(String F_CreateTime) {
            this.F_CreateTime = F_CreateTime;
        }

        public String getF_ExpiryTime() {
            return F_ExpiryTime;
        }

        public void setF_ExpiryTime(String F_ExpiryTime) {
            this.F_ExpiryTime = F_ExpiryTime;
        }
    }
}
