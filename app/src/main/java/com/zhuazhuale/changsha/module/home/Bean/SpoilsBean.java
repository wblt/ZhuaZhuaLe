package com.zhuazhuale.changsha.module.home.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/20.
 */

public class SpoilsBean {


    /**
     * Code : 1
     * Info :
     * rows : [{"F_ID":"3c32ffd3-c508-4092-acf0-7ef8cd69ae79","F_UserID":"d734b0cc-721d-4430-b153-596acfaed97f","F_GoodsID":"6bbeae9a-5b36-4621-9e1d-09b16cc61e6f","F_DeviceID":"73dacdaa-abeb-4edb-a18c-dc1f831b5981","F_Name":"超级小飞侠-20CM","F_Img":"http://img.zhuazhuale.com/Goods/xiaofeixia.jpg","F_Check":true,"F_ExChangePrice":500}]
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

    public static class RowsBean {
        /**
         * F_ID : 3c32ffd3-c508-4092-acf0-7ef8cd69ae79
         * F_UserID : d734b0cc-721d-4430-b153-596acfaed97f
         * F_GoodsID : 6bbeae9a-5b36-4621-9e1d-09b16cc61e6f
         * F_DeviceID : 73dacdaa-abeb-4edb-a18c-dc1f831b5981
         * F_Name : 超级小飞侠-20CM
         * F_Img : http://img.zhuazhuale.com/Goods/xiaofeixia.jpg
         * F_Check : true
         * F_ExChangePrice : 500
         */

        private String F_ID;
        private String F_UserID;
        private String F_GoodsID;
        private String F_DeviceID;
        private String F_Name;
        private String F_Img;
        private boolean F_Check;
        private int F_ExChangePrice;

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
    }
}
