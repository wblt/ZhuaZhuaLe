package com.zhuazhuale.changsha.module.home.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/12/12.
 */

public class DeviceGoodsBean implements Serializable{

    /**
     * Code : 1
     * Info :
     * PageCount : 2
     * rows : [{"F_Camera1":"rtmp://12937.liveplay.myqcloud.com/live/12937_76858531d64d11e792905cb9018cf0d4","F_Camera2":"rtmp://12937.liveplay.myqcloud.com/live/12937_b38ff0ebd64d11e792905cb9018cf0d4","F_DeviceNo":"1003","F_ID":"d7f190f9-aa3a-465d-bb31-090aebff7242","F_ImgA":"http://img.zhuazhuale.com/Goods/houzi.jpg","F_ImgB":"http://img.zhuazhuale.com/Goods/houzi.jpg","F_Name":"红色香蕉猴-30CM","F_Number":20,"F_Price":29,"F_Sort":1,"F_Status":1,"GoodsID":"00000000-0000-0000-0000-000000000000"},{"F_Camera1":"rtmp://12937.liveplay.myqcloud.com/live/12937_e4bec97bd64c11e792905cb9018cf0d4","F_Camera2":"rtmp://12937.liveplay.myqcloud.com/live/12937_3b2c11c8d64d11e792905cb9018cf0d4","F_DeviceNo":"1002","F_ID":"06d01bc2-93a9-4b3d-9b44-0e490f918db4","F_ImgA":"http://img.zhuazhuale.com/Goods/peiqi.jpg","F_ImgB":"http://img.zhuazhuale.com/Goods/peiqi.jpg","F_Name":"小猪佩奇-20CM","F_Number":20,"F_Price":19,"F_Sort":1,"F_Status":1,"GoodsID":"00000000-0000-0000-0000-000000000000"},{"F_Camera1":"rtmp://12937.liveplay.myqcloud.com/live/12937_6655957fced311e792905cb9018cf0d4","F_Camera2":"rtmp://12937.liveplay.myqcloud.com/live/12937_917a789aced311e792905cb9018cf0d4","F_DeviceNo":"1005","F_ID":"c4432f74-549a-4b81-a203-3abf8bdd27a6","F_ImgA":"http://img.zhuazhuale.com/Goods/yuantongbulangxiong.jpg","F_ImgB":"http://img.zhuazhuale.com/Goods/yuantongbulangxiong.jpg","F_Name":"圆筒布朗熊-20CM","F_Number":20,"F_Price":29,"F_Sort":1,"F_Status":1,"GoodsID":"00000000-0000-0000-0000-000000000000"},{"F_Camera1":"rtmp://12937.liveplay.myqcloud.com/live/12937_553049add64c11e792905cb9018cf0d4","F_Camera2":"rtmp://12937.liveplay.myqcloud.com/live/12937_14abd473ced311e792905cb9018cf0d4 ","F_DeviceNo":"1004","F_ID":"7b536c73-aef3-4a2e-bd89-5484ee3b4d78","F_ImgA":"http://img.zhuazhuale.com/Goods/xiongdaxionger.jpg","F_ImgB":"http://img.zhuazhuale.com/Goods/xiongdaxionger.jpg","F_Name":"熊大熊二-20CM","F_Number":20,"F_Price":28,"F_Sort":1,"F_Status":1,"GoodsID":"00000000-0000-0000-0000-000000000000"}]
     * total : 5
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
         * F_Camera1 : rtmp://12937.liveplay.myqcloud.com/live/12937_76858531d64d11e792905cb9018cf0d4
         * F_Camera2 : rtmp://12937.liveplay.myqcloud.com/live/12937_b38ff0ebd64d11e792905cb9018cf0d4
         * F_DeviceNo : 1003
         * F_ID : d7f190f9-aa3a-465d-bb31-090aebff7242
         * F_ImgA : http://img.zhuazhuale.com/Goods/houzi.jpg
         * F_ImgB : http://img.zhuazhuale.com/Goods/houzi.jpg
         * F_Name : 红色香蕉猴-30CM
         * F_Number : 20
         * F_Price : 29
         * F_Sort : 1
         * F_Status : 1
         * GoodsID : 00000000-0000-0000-0000-000000000000
         */

        private String F_Camera1;
        private String F_Camera2;
        private String F_DeviceNo;
        private String F_ID;
        private String F_ImgA;
        private String F_ImgB;
        private String F_Name;
        private int F_Number;
        private int F_Price;
        private int F_Sort;
        private int F_Status;
        private String GoodsID;

        public String getF_Camera1() {
            return F_Camera1;
        }

        public void setF_Camera1(String F_Camera1) {
            this.F_Camera1 = F_Camera1;
        }

        public String getF_Camera2() {
            return F_Camera2;
        }

        public void setF_Camera2(String F_Camera2) {
            this.F_Camera2 = F_Camera2;
        }

        public String getF_DeviceNo() {
            return F_DeviceNo;
        }

        public void setF_DeviceNo(String F_DeviceNo) {
            this.F_DeviceNo = F_DeviceNo;
        }

        public String getF_ID() {
            return F_ID;
        }

        public void setF_ID(String F_ID) {
            this.F_ID = F_ID;
        }

        public String getF_ImgA() {
            return F_ImgA;
        }

        public void setF_ImgA(String F_ImgA) {
            this.F_ImgA = F_ImgA;
        }

        public String getF_ImgB() {
            return F_ImgB;
        }

        public void setF_ImgB(String F_ImgB) {
            this.F_ImgB = F_ImgB;
        }

        public String getF_Name() {
            return F_Name;
        }

        public void setF_Name(String F_Name) {
            this.F_Name = F_Name;
        }

        public int getF_Number() {
            return F_Number;
        }

        public void setF_Number(int F_Number) {
            this.F_Number = F_Number;
        }

        public int getF_Price() {
            return F_Price;
        }

        public void setF_Price(int F_Price) {
            this.F_Price = F_Price;
        }

        public int getF_Sort() {
            return F_Sort;
        }

        public void setF_Sort(int F_Sort) {
            this.F_Sort = F_Sort;
        }

        public int getF_Status() {
            return F_Status;
        }

        public void setF_Status(int F_Status) {
            this.F_Status = F_Status;
        }

        public String getGoodsID() {
            return GoodsID;
        }

        public void setGoodsID(String GoodsID) {
            this.GoodsID = GoodsID;
        }
    }
}
