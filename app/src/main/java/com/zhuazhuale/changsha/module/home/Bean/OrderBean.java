package com.zhuazhuale.changsha.module.home.Bean;

import java.util.List;

/**
 * 我的订单列表
 * Created by Administrator on 2017/12/20.
 */

public class OrderBean {

    /**
     * Code : 1
     * Info :
     * rows : [{"Detail":[{"F_Name":"超级小飞侠-20CM","F_Img":"http://img.zhuazhuale.com/Goods/xiaofeixia.jpg"},{"F_Name":"比卡丘","F_Img":"http://106.14.192.166:8089/1.jpg"}],"F_ID":"f97e0f30-cae8-4fe9-8af6-41aa3087c905","F_OrderNo":"ORD_171103000001","F_Status":1,"F_Consignee":"宁大侠","F_Mobile":"18217317571","F_Address":"上海浦东机场国外机场","F_Express":"","F_ExpressNo":"","F_RetTime":null,"F_Remark":"发顺丰快递\n","F_CreateTime":"2017-11-03T00:17:50.183"},{"Detail":[{"F_Name":"超级小飞侠-20CM","F_Img":"http://img.zhuazhuale.com/Goods/xiaofeixia.jpg"},{"F_Name":"超级小飞侠-20CM","F_Img":"http://img.zhuazhuale.com/Goods/xiaofeixia.jpg"},{"F_Name":"超级小飞侠-20CM","F_Img":"http://img.zhuazhuale.com/Goods/xiaofeixia.jpg"},{"F_Name":"超级小飞侠-20CM","F_Img":"http://img.zhuazhuale.com/Goods/xiaofeixia.jpg"}],"F_ID":"7574c500-f83c-4656-a317-7a7828b6797d","F_OrderNo":"ORD_171103000002","F_Status":1,"F_Consignee":"宁大侠","F_Mobile":"18217317571","F_Address":"上海浦东机场国外机场","F_Express":"","F_ExpressNo":"","F_RetTime":null,"F_Remark":"","F_CreateTime":"2017-11-03T18:02:36.69"},{"Detail":[{"F_Name":"比卡丘","F_Img":"http://106.14.192.166:8089/1.jpg"},{"F_Name":"比卡丘","F_Img":"http://106.14.192.166:8089/1.jpg"},{"F_Name":"超级小飞侠-20CM","F_Img":"http://img.zhuazhuale.com/Goods/xiaofeixia.jpg"},{"F_Name":"委屈猫公仔-20CM","F_Img":"http://img.zhuazhuale.com/Goods/xiaolanmao.jpg"},{"F_Name":"超级小飞侠-20CM","F_Img":"http://img.zhuazhuale.com/Goods/xiaofeixia.jpg"},{"F_Name":"猪猪侠","F_Img":"http://106.14.192.166:8089/5.jpg"}],"F_ID":"aff6afab-d400-4f8e-91bb-d1b98867ecca","F_OrderNo":"ORD_171107000001","F_Status":1,"F_Consignee":"宁大侠","F_Mobile":"18217317571","F_Address":"湖南省长沙市岳麓区雷锋大道609号天麓小区14栋2102号","F_Express":"","F_ExpressNo":"","F_RetTime":null,"F_Remark":"年OK了\n","F_CreateTime":"2017-11-07T02:22:18.57"},{"Detail":[{"F_Name":"比卡丘","F_Img":"http://106.14.192.166:8089/1.jpg"},{"F_Name":"超级小飞侠-20CM","F_Img":"http://img.zhuazhuale.com/Goods/xiaofeixia.jpg"},{"F_Name":"超级小飞侠-20CM","F_Img":"http://img.zhuazhuale.com/Goods/xiaofeixia.jpg"},{"F_Name":"超级小飞侠-20CM","F_Img":"http://img.zhuazhuale.com/Goods/xiaofeixia.jpg"},{"F_Name":"超级小飞侠-20CM","F_Img":"http://img.zhuazhuale.com/Goods/xiaofeixia.jpg"},{"F_Name":"超级小飞侠-20CM","F_Img":"http://img.zhuazhuale.com/Goods/xiaofeixia.jpg"},{"F_Name":"超级小飞侠-20CM","F_Img":"http://img.zhuazhuale.com/Goods/xiaofeixia.jpg"},{"F_Name":"超级小飞侠-20CM","F_Img":"http://img.zhuazhuale.com/Goods/xiaofeixia.jpg"},{"F_Name":"超级小飞侠-20CM","F_Img":"http://img.zhuazhuale.com/Goods/xiaofeixia.jpg"},{"F_Name":"超级小飞侠-20CM","F_Img":"http://img.zhuazhuale.com/Goods/xiaofeixia.jpg"},{"F_Name":"比卡丘","F_Img":"http://106.14.192.166:8089/1.jpg"},{"F_Name":"比卡丘","F_Img":"http://106.14.192.166:8089/1.jpg"},{"F_Name":"超级小飞侠-20CM","F_Img":"http://img.zhuazhuale.com/Goods/xiaofeixia.jpg"},{"F_Name":"超级小飞侠-20CM","F_Img":"http://img.zhuazhuale.com/Goods/xiaofeixia.jpg"},{"F_Name":"超级小飞侠-20CM","F_Img":"http://img.zhuazhuale.com/Goods/xiaofeixia.jpg"},{"F_Name":"超级小飞侠-20CM","F_Img":"http://img.zhuazhuale.com/Goods/xiaofeixia.jpg"}],"F_ID":"01258568-931b-486f-88a8-b88062ee97eb","F_OrderNo":"ORD_171120000001","F_Status":1,"F_Consignee":"宁大侠","F_Mobile":"18217317571","F_Address":"湖南省长沙市岳麓区雷锋大道609号天麓小区14栋2102号","F_Express":"","F_ExpressNo":"","F_RetTime":null,"F_Remark":"","F_CreateTime":"2017-11-20T01:01:36.997"},{"Detail":[{"F_Name":"小猪佩奇-20CM","F_Img":"http://img.zhuazhuale.com/Goods/peiqi.jpg"},{"F_Name":"红色香蕉猴-30CM","F_Img":"http://img.zhuazhuale.com/Goods/houzi.jpg"},{"F_Name":"小猪佩奇-20CM","F_Img":"http://img.zhuazhuale.com/Goods/peiqi.jpg"},{"F_Name":"红色香蕉猴-30CM","F_Img":"http://img.zhuazhuale.com/Goods/houzi.jpg"},{"F_Name":"超级小飞侠-20CM","F_Img":"http://img.zhuazhuale.com/Goods/xiaofeixia.jpg"},{"F_Name":"小猪佩奇-20CM","F_Img":"http://img.zhuazhuale.com/Goods/peiqi.jpg"},{"F_Name":"大圣","F_Img":"http://106.14.192.166:8089/3.jpg"},{"F_Name":"红色香蕉猴-30CM","F_Img":"http://img.zhuazhuale.com/Goods/houzi.jpg"},{"F_Name":"小猪佩奇-20CM","F_Img":"http://img.zhuazhuale.com/Goods/peiqi.jpg"},{"F_Name":"大圣","F_Img":"http://106.14.192.166:8089/3.jpg"},{"F_Name":"超级小飞侠-20CM","F_Img":"http://img.zhuazhuale.com/Goods/xiaofeixia.jpg"},{"F_Name":"红色香蕉猴-30CM","F_Img":"http://img.zhuazhuale.com/Goods/houzi.jpg"}],"F_ID":"206bebfc-f55d-490a-af9e-b18bdd8c9f90","F_OrderNo":"ORD_171124000001","F_Status":1,"F_Consignee":"宁大侠","F_Mobile":"18217317571","F_Address":"湖南省长沙市岳麓区雷锋大道609号天麓小区14栋2102号","F_Express":"","F_ExpressNo":"","F_RetTime":null,"F_Remark":"很好 很快 我要预留 德邦物流","F_CreateTime":"2017-11-24T01:57:50.42"},{"Detail":[{"F_Name":"小猪佩奇-20CM","F_Img":"http://img.zhuazhuale.com/Goods/peiqi.jpg"},{"F_Name":"大圣","F_Img":"http://106.14.192.166:8089/3.jpg"},{"F_Name":"小猪佩奇-20CM","F_Img":"http://img.zhuazhuale.com/Goods/peiqi.jpg"}],"F_ID":"dd9def8a-d043-469a-9592-32c4424045e9","F_OrderNo":"ORD_171211000001","F_Status":1,"F_Consignee":"命陪标","F_Mobile":"18217317571","F_Address":"上海市闵行区浦江镇瑞和城555弄40栋1702号","F_Express":"","F_ExpressNo":"","F_RetTime":null,"F_Remark":"","F_CreateTime":"2017-12-11T21:29:47.187"}]
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
         * Detail : [{"F_Name":"超级小飞侠-20CM","F_Img":"http://img.zhuazhuale.com/Goods/xiaofeixia.jpg"},{"F_Name":"比卡丘","F_Img":"http://106.14.192.166:8089/1.jpg"}]
         * F_ID : f97e0f30-cae8-4fe9-8af6-41aa3087c905
         * F_OrderNo : ORD_171103000001
         * F_Status : 1
         * F_Consignee : 宁大侠
         * F_Mobile : 18217317571
         * F_Address : 上海浦东机场国外机场
         * F_Express :
         * F_ExpressNo :
         * F_RetTime : null
         * F_Remark : 发顺丰快递
         * <p>
         * F_CreateTime : 2017-11-03T00:17:50.183
         */

        private String F_ID;
        private String F_OrderNo;
        private int F_Status;
        private String F_Consignee;
        private String F_Mobile;
        private String F_Address;
        private String F_Express;
        private String F_ExpressNo;
        private Object F_RetTime;
        private String F_Remark;
        private String F_CreateTime;
        private List<DetailBean> Detail;

        public String getF_ID() {
            return F_ID;
        }

        public void setF_ID(String F_ID) {
            this.F_ID = F_ID;
        }

        public String getF_OrderNo() {
            return F_OrderNo;
        }

        public void setF_OrderNo(String F_OrderNo) {
            this.F_OrderNo = F_OrderNo;
        }

        public int getF_Status() {
            return F_Status;
        }

        public void setF_Status(int F_Status) {
            this.F_Status = F_Status;
        }

        public String getF_Consignee() {
            return F_Consignee;
        }

        public void setF_Consignee(String F_Consignee) {
            this.F_Consignee = F_Consignee;
        }

        public String getF_Mobile() {
            return F_Mobile;
        }

        public void setF_Mobile(String F_Mobile) {
            this.F_Mobile = F_Mobile;
        }

        public String getF_Address() {
            return F_Address;
        }

        public void setF_Address(String F_Address) {
            this.F_Address = F_Address;
        }

        public String getF_Express() {
            return F_Express;
        }

        public void setF_Express(String F_Express) {
            this.F_Express = F_Express;
        }

        public String getF_ExpressNo() {
            return F_ExpressNo;
        }

        public void setF_ExpressNo(String F_ExpressNo) {
            this.F_ExpressNo = F_ExpressNo;
        }

        public Object getF_RetTime() {
            return F_RetTime;
        }

        public void setF_RetTime(Object F_RetTime) {
            this.F_RetTime = F_RetTime;
        }

        public String getF_Remark() {
            return F_Remark;
        }

        public void setF_Remark(String F_Remark) {
            this.F_Remark = F_Remark;
        }

        public String getF_CreateTime() {
            return F_CreateTime;
        }

        public void setF_CreateTime(String F_CreateTime) {
            this.F_CreateTime = F_CreateTime;
        }

        public List<DetailBean> getDetail() {
            return Detail;
        }

        public void setDetail(List<DetailBean> Detail) {
            this.Detail = Detail;
        }

        public static class DetailBean {
            /**
             * F_Name : 超级小飞侠-20CM
             * F_Img : http://img.zhuazhuale.com/Goods/xiaofeixia.jpg
             */

            private String F_Name;
            private String F_Img;

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
        }
    }
}
