package com.zhuazhuale.changsha.module.home.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/12.
 */

public class BaseDataBean {

    /**
     * Code : 1
     * Info :
     * PageCount : 0
     * rows : [{"F_ContentUrl":"http://img.zhuazhuale.com/banner/FX.png","F_CreateTime":"2017-11-26T22:46:05.42","F_ID":"14316d59-415c-4fda-b54f-331319c5929c","F_ImgUrl":"http://img.zhuazhuale.com/banner/YQJL.png","F_Sort":1,"F_Title":"分享","F_Type":1,"F_Valid":true},{"F_ContentUrl":"http://img.zhuazhuale.com/banner/CZ.png","F_CreateTime":"2017-10-09T15:14:19.223","F_ID":"e98c6fdd-dfe2-4dd7-b20a-9851c4f04340","F_ImgUrl":"http://img.zhuazhuale.com/banner/CZ.png","F_Sort":2,"F_Title":"首页-Banlan2","F_Type":2,"F_Valid":true}]
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
         * F_ContentUrl : http://img.zhuazhuale.com/banner/FX.png
         * F_CreateTime : 2017-11-26T22:46:05.42
         * F_ID : 14316d59-415c-4fda-b54f-331319c5929c
         * F_ImgUrl : http://img.zhuazhuale.com/banner/YQJL.png
         * F_Sort : 1
         * F_Title : 分享
         * F_Type : 1
         * F_Valid : true
         */

        private String F_ContentUrl;
        private String F_CreateTime;
        private String F_ID;
        private String F_ImgUrl;
        private int F_Sort;
        private String F_Title;
        private int F_Type;
        private boolean F_Valid;

        public String getF_ContentUrl() {
            return F_ContentUrl;
        }

        public void setF_ContentUrl(String F_ContentUrl) {
            this.F_ContentUrl = F_ContentUrl;
        }

        public String getF_CreateTime() {
            return F_CreateTime;
        }

        public void setF_CreateTime(String F_CreateTime) {
            this.F_CreateTime = F_CreateTime;
        }

        public String getF_ID() {
            return F_ID;
        }

        public void setF_ID(String F_ID) {
            this.F_ID = F_ID;
        }

        public String getF_ImgUrl() {
            return F_ImgUrl;
        }

        public void setF_ImgUrl(String F_ImgUrl) {
            this.F_ImgUrl = F_ImgUrl;
        }

        public int getF_Sort() {
            return F_Sort;
        }

        public void setF_Sort(int F_Sort) {
            this.F_Sort = F_Sort;
        }

        public String getF_Title() {
            return F_Title;
        }

        public void setF_Title(String F_Title) {
            this.F_Title = F_Title;
        }

        public int getF_Type() {
            return F_Type;
        }

        public void setF_Type(int F_Type) {
            this.F_Type = F_Type;
        }

        public boolean isF_Valid() {
            return F_Valid;
        }

        public void setF_Valid(boolean F_Valid) {
            this.F_Valid = F_Valid;
        }
    }
}
