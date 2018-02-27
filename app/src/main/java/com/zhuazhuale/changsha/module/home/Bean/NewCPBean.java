package com.zhuazhuale.changsha.module.home.Bean;

/**
 * Created by Administrator on 2017/12/19.
 */

public class NewCPBean {


    /**
     * Code : 1
     * Info :
     * rows : {"CP":23,"F_BagNumber":0}
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
         * CP : 23
         * F_BagNumber : 0.0
         */

        private int CP;
        private double F_BagNumber;

        public int getCP() {
            return CP;
        }

        public void setCP(int CP) {
            this.CP = CP;
        }

        public double getF_BagNumber() {
            return F_BagNumber;
        }

        public void setF_BagNumber(double F_BagNumber) {
            this.F_BagNumber = F_BagNumber;
        }
    }
}
