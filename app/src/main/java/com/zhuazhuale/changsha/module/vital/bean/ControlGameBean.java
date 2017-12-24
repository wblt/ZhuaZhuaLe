package com.zhuazhuale.changsha.module.vital.bean;

/**
 * Created by Administrator on 2017/12/24 0024.
 */

public class ControlGameBean {

    /**
     * Code : 0
     * Info :
     * rows : {"GrabID":"5F9D0EE2-EC22-40FD-91F3-D57005E7C690"}
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
         * GrabID : 5F9D0EE2-EC22-40FD-91F3-D57005E7C690
         */

        private String GrabID;

        public String getGrabID() {
            return GrabID;
        }

        public void setGrabID(String GrabID) {
            this.GrabID = GrabID;
        }
    }
}
