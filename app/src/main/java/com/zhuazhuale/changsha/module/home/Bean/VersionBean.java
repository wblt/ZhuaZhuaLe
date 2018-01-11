package com.zhuazhuale.changsha.module.home.Bean;

/**
 * Created by Administrator on 2018/1/11.
 */

public class VersionBean {

    /**
     * Code : 1
     * Info :
     * rows : {"vUrl":"","vForce":1}
     * PageCount : 0
     * total : 0
     */

    private int Code;
    private String Info;
    private RowsBean rows;
    private int PageCount;
    private int total;

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

    public RowsBean getRows() {
        return rows;
    }

    public void setRows(RowsBean rows) {
        this.rows = rows;
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

    public static class RowsBean {
        /**
         * vUrl :
         * vForce : 1
         */

        private String vUrl;
        private int vForce;

        public String getVUrl() {
            return vUrl;
        }

        public void setVUrl(String vUrl) {
            this.vUrl = vUrl;
        }

        public int getVForce() {
            return vForce;
        }

        public void setVForce(int vForce) {
            this.vForce = vForce;
        }
    }
}
