package com.zhuazhuale.changsha.module.home.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/19.
 */

public class BanlanceWaterBean {

    /**
     * Code : 1
     * Info :
     * rows : [{"F_ID":"0ba7d309-d031-4b66-8b03-4c9c0494fc2b","F_UserID":"8c12dc2f-bd6c-4348-88ce-daf4994363bb","F_Type":4,"F_Amout":19,"F_CreateTime":"2017-12-11T11:25:11.287","F_Valid":true,"F_DeviceNo":"1002","F_GoodsImgA":"http://img.zhuazhuale.com/Goods/peiqi.jpg","F_GoodsName":"小猪佩奇-20CM","F_GoodsID":"5383288f-c2c1-4125-946b-5f3c64a27932","F_UserName":"丁琪"},{"F_ID":"efc70985-8785-481d-9a34-a629c59dc344","F_UserID":"8c12dc2f-bd6c-4348-88ce-daf4994363bb","F_Type":4,"F_Amout":29,"F_CreateTime":"2017-12-11T11:23:18.853","F_Valid":true,"F_DeviceNo":"1003","F_GoodsImgA":"http://img.zhuazhuale.com/Goods/houzi.jpg","F_GoodsName":"红色香蕉猴-30CM","F_GoodsID":"884e5f8f-63d3-4cbf-89a6-89aaae63f9a2","F_UserName":"丁琪"},{"F_ID":"97bd837b-1b55-412d-b49c-e80bb2d6db56","F_UserID":"8c12dc2f-bd6c-4348-88ce-daf4994363bb","F_Type":4,"F_Amout":29,"F_CreateTime":"2017-12-11T11:23:13.693","F_Valid":true,"F_DeviceNo":"1003","F_GoodsImgA":"http://img.zhuazhuale.com/Goods/houzi.jpg","F_GoodsName":"红色香蕉猴-30CM","F_GoodsID":"884e5f8f-63d3-4cbf-89a6-89aaae63f9a2","F_UserName":"丁琪"},{"F_ID":"c325ae96-118f-4ca6-9437-bb4ef4ebebe9","F_UserID":"8c12dc2f-bd6c-4348-88ce-daf4994363bb","F_Type":4,"F_Amout":29,"F_CreateTime":"2017-12-11T11:22:31.5","F_Valid":true,"F_DeviceNo":"1003","F_GoodsImgA":"http://img.zhuazhuale.com/Goods/houzi.jpg","F_GoodsName":"红色香蕉猴-30CM","F_GoodsID":"884e5f8f-63d3-4cbf-89a6-89aaae63f9a2","F_UserName":"丁琪"},{"F_ID":"037c062e-5557-4c1f-9273-554fdbd0b04c","F_UserID":"8c12dc2f-bd6c-4348-88ce-daf4994363bb","F_Type":4,"F_Amout":29,"F_CreateTime":"2017-12-09T19:59:54.393","F_Valid":true,"F_DeviceNo":"1003","F_GoodsImgA":"http://img.zhuazhuale.com/Goods/houzi.jpg","F_GoodsName":"红色香蕉猴-30CM","F_GoodsID":"884e5f8f-63d3-4cbf-89a6-89aaae63f9a2","F_UserName":"丁琪"},{"F_ID":"0da52a02-1cf5-4dc8-9a6d-3c36f4a7d9ce","F_UserID":"8c12dc2f-bd6c-4348-88ce-daf4994363bb","F_Type":4,"F_Amout":29,"F_CreateTime":"2017-12-09T19:59:20.403","F_Valid":true,"F_DeviceNo":"1003","F_GoodsImgA":"http://img.zhuazhuale.com/Goods/houzi.jpg","F_GoodsName":"红色香蕉猴-30CM","F_GoodsID":"884e5f8f-63d3-4cbf-89a6-89aaae63f9a2","F_UserName":"丁琪"},{"F_ID":"d1bc55b1-8a88-47f5-8713-4357b4b95b4c","F_UserID":"8c12dc2f-bd6c-4348-88ce-daf4994363bb","F_Type":7,"F_Amout":88,"F_CreateTime":"2017-12-09T19:59:10.243","F_Valid":true,"F_DeviceNo":"888888","F_GoodsImgA":"http://img.zhuazhuale.com/Common/YQJL.png","F_GoodsName":"邀请奖励","F_GoodsID":"5c8a7dc4-2343-4950-99ba-25a7d03211f5","F_UserName":"丁琪"},{"F_ID":"37335a22-b4f3-4558-bcd8-cdc75c6da82e","F_UserID":"8c12dc2f-bd6c-4348-88ce-daf4994363bb","F_Type":7,"F_Amout":88,"F_CreateTime":"2017-12-09T19:59:10.243","F_Valid":true,"F_DeviceNo":"888888","F_GoodsImgA":"http://img.zhuazhuale.com/Common/YQJL.png","F_GoodsName":"邀请奖励","F_GoodsID":"5c8a7dc4-2343-4950-99ba-25a7d03211f5","F_UserName":"丁琪"}]
     * PageCount : 1
     * total : 8
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
         * F_ID : 0ba7d309-d031-4b66-8b03-4c9c0494fc2b
         * F_UserID : 8c12dc2f-bd6c-4348-88ce-daf4994363bb
         * F_Type : 4
         * F_Amout : 19
         * F_CreateTime : 2017-12-11T11:25:11.287
         * F_Valid : true
         * F_DeviceNo : 1002
         * F_GoodsImgA : http://img.zhuazhuale.com/Goods/peiqi.jpg
         * F_GoodsName : 小猪佩奇-20CM
         * F_GoodsID : 5383288f-c2c1-4125-946b-5f3c64a27932
         * F_UserName : 丁琪
         */

        private String F_ID;
        private String F_UserID;
        private int F_Type;
        private int F_Amout;
        private String F_CreateTime;
        private boolean F_Valid;
        private String F_DeviceNo;
        private String F_GoodsImgA;
        private String F_GoodsName;
        private String F_GoodsID;
        private String F_UserName;

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

        public int getF_Type() {
            return F_Type;
        }

        public void setF_Type(int F_Type) {
            this.F_Type = F_Type;
        }

        public int getF_Amout() {
            return F_Amout;
        }

        public void setF_Amout(int F_Amout) {
            this.F_Amout = F_Amout;
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

        public String getF_GoodsImgA() {
            return F_GoodsImgA;
        }

        public void setF_GoodsImgA(String F_GoodsImgA) {
            this.F_GoodsImgA = F_GoodsImgA;
        }

        public String getF_GoodsName() {
            return F_GoodsName;
        }

        public void setF_GoodsName(String F_GoodsName) {
            this.F_GoodsName = F_GoodsName;
        }

        public String getF_GoodsID() {
            return F_GoodsID;
        }

        public void setF_GoodsID(String F_GoodsID) {
            this.F_GoodsID = F_GoodsID;
        }

        public String getF_UserName() {
            return F_UserName;
        }

        public void setF_UserName(String F_UserName) {
            this.F_UserName = F_UserName;
        }
    }
}
