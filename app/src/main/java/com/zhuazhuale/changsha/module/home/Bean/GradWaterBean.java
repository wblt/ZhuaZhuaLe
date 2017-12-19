package com.zhuazhuale.changsha.module.home.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/12/19.
 */

public class GradWaterBean {

    /**
     * Code : 1
     * Info :
     * rows : [{"F_ID":"0a35abd7-e9ce-4b8f-ab94-da3160393797","F_UserID":"8c12dc2f-bd6c-4348-88ce-daf4994363bb","F_DeviceID":"06d01bc2-93a9-4b3d-9b44-0e490f918db4","F_Appeal":0,"F_Result":0,"F_VideoUrl":"","F_CreateTime":"2017-12-11T11:25:46.193","F_Valid":true,"F_DeviceNo":"1002","F_GoodsName":"小猪佩奇-20CM","F_GoodsImgA":"http://img.zhuazhuale.com/Goods/peiqi.jpg","F_UserName":"丁琪","F_UserImg":"http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLlT5FfCicL6zyBzNbY3BpRwTkIkXvlUQfmcucKcX8vW5D9icGlKNH6kW43f4P0GROwaVLvu5nKoh0g/0"},{"F_ID":"36eee37a-6f58-4115-9286-58831fad3c8f","F_UserID":"8c12dc2f-bd6c-4348-88ce-daf4994363bb","F_DeviceID":"d7f190f9-aa3a-465d-bb31-090aebff7242","F_Appeal":0,"F_Result":0,"F_VideoUrl":"","F_CreateTime":"2017-12-11T11:23:13.827","F_Valid":true,"F_DeviceNo":"1003","F_GoodsName":"红色香蕉猴-30CM","F_GoodsImgA":"http://img.zhuazhuale.com/Goods/houzi.jpg","F_UserName":"丁琪","F_UserImg":"http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLlT5FfCicL6zyBzNbY3BpRwTkIkXvlUQfmcucKcX8vW5D9icGlKNH6kW43f4P0GROwaVLvu5nKoh0g/0"},{"F_ID":"cb29fb3f-4886-4f2a-88ca-ac4111d80903","F_UserID":"8c12dc2f-bd6c-4348-88ce-daf4994363bb","F_DeviceID":"d7f190f9-aa3a-465d-bb31-090aebff7242","F_Appeal":0,"F_Result":0,"F_VideoUrl":"","F_CreateTime":"2017-12-11T11:23:11.94","F_Valid":true,"F_DeviceNo":"1003","F_GoodsName":"红色香蕉猴-30CM","F_GoodsImgA":"http://img.zhuazhuale.com/Goods/houzi.jpg","F_UserName":"丁琪","F_UserImg":"http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLlT5FfCicL6zyBzNbY3BpRwTkIkXvlUQfmcucKcX8vW5D9icGlKNH6kW43f4P0GROwaVLvu5nKoh0g/0"},{"F_ID":"3e0b7416-b900-434f-9e93-fe80d9c0c2a2","F_UserID":"8c12dc2f-bd6c-4348-88ce-daf4994363bb","F_DeviceID":"d7f190f9-aa3a-465d-bb31-090aebff7242","F_Appeal":0,"F_Result":0,"F_VideoUrl":"","F_CreateTime":"2017-12-09T19:59:52.467","F_Valid":true,"F_DeviceNo":"1003","F_GoodsName":"红色香蕉猴-30CM","F_GoodsImgA":"http://img.zhuazhuale.com/Goods/houzi.jpg","F_UserName":"丁琪","F_UserImg":"http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLlT5FfCicL6zyBzNbY3BpRwTkIkXvlUQfmcucKcX8vW5D9icGlKNH6kW43f4P0GROwaVLvu5nKoh0g/0"}]
     * PageCount : 1
     * total : 4
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

    public static class RowsBean implements Serializable {
        /**
         * F_ID : 0a35abd7-e9ce-4b8f-ab94-da3160393797
         * F_UserID : 8c12dc2f-bd6c-4348-88ce-daf4994363bb
         * F_DeviceID : 06d01bc2-93a9-4b3d-9b44-0e490f918db4
         * F_Appeal : 0
         * F_Result : 0
         * F_VideoUrl :
         * F_CreateTime : 2017-12-11T11:25:46.193
         * F_Valid : true
         * F_DeviceNo : 1002
         * F_GoodsName : 小猪佩奇-20CM
         * F_GoodsImgA : http://img.zhuazhuale.com/Goods/peiqi.jpg
         * F_UserName : 丁琪
         * F_UserImg : http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLlT5FfCicL6zyBzNbY3BpRwTkIkXvlUQfmcucKcX8vW5D9icGlKNH6kW43f4P0GROwaVLvu5nKoh0g/0
         */

        private String F_ID;
        private String F_UserID;
        private String F_DeviceID;
        private int F_Appeal;
        private int F_Result;
        private String F_VideoUrl;
        private String F_CreateTime;
        private boolean F_Valid;
        private String F_DeviceNo;
        private String F_GoodsName;
        private String F_GoodsImgA;
        private String F_UserName;
        private String F_UserImg;

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

        public String getF_DeviceID() {
            return F_DeviceID;
        }

        public void setF_DeviceID(String F_DeviceID) {
            this.F_DeviceID = F_DeviceID;
        }

        public int getF_Appeal() {
            return F_Appeal;
        }

        public void setF_Appeal(int F_Appeal) {
            this.F_Appeal = F_Appeal;
        }

        public int getF_Result() {
            return F_Result;
        }

        public void setF_Result(int F_Result) {
            this.F_Result = F_Result;
        }

        public String getF_VideoUrl() {
            return F_VideoUrl;
        }

        public void setF_VideoUrl(String F_VideoUrl) {
            this.F_VideoUrl = F_VideoUrl;
        }

        public String getF_CreateTime() {
            return F_CreateTime;
        }

        public void setF_CreateTime(String F_CreateTime) {
            this.F_CreateTime = F_CreateTime;
        }

        public boolean isF_Valid() {
            return F_Valid;
        }

        public void setF_Valid(boolean F_Valid) {
            this.F_Valid = F_Valid;
        }

        public String getF_DeviceNo() {
            return F_DeviceNo;
        }

        public void setF_DeviceNo(String F_DeviceNo) {
            this.F_DeviceNo = F_DeviceNo;
        }

        public String getF_GoodsName() {
            return F_GoodsName;
        }

        public void setF_GoodsName(String F_GoodsName) {
            this.F_GoodsName = F_GoodsName;
        }

        public String getF_GoodsImgA() {
            return F_GoodsImgA;
        }

        public void setF_GoodsImgA(String F_GoodsImgA) {
            this.F_GoodsImgA = F_GoodsImgA;
        }

        public String getF_UserName() {
            return F_UserName;
        }

        public void setF_UserName(String F_UserName) {
            this.F_UserName = F_UserName;
        }

        public String getF_UserImg() {
            return F_UserImg;
        }

        public void setF_UserImg(String F_UserImg) {
            this.F_UserImg = F_UserImg;
        }
    }
}
