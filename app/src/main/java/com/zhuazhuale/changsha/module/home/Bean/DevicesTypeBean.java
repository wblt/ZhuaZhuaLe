package com.zhuazhuale.changsha.module.home.Bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class DevicesTypeBean {

    /**
     * Code : 1
     * Info :
     * rows : [{"F_ID":"0a259991-027b-4b27-bcad-c5a05ed4ff03","F_Status":1},{"F_ID":"4f287fe1-61f1-4c1a-9b74-d5157c34c59e","F_Status":1},{"F_ID":"e8c5f693-89b3-42ba-868f-36c43623b6c5","F_Status":1},{"F_ID":"c890f4f2-4851-4ae7-8a6e-5a07f50e9ae4","F_Status":1},{"F_ID":"5ed4e9c4-c1fa-4718-80bb-6582c0b9fdf1","F_Status":1},{"F_ID":"70d152f4-ae1f-40f9-8e1f-cb7c8391173b","F_Status":1},{"F_ID":"7de286f8-f0c4-4d7e-a666-f52dec0b9ad4","F_Status":1}]
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
         * F_ID : 0a259991-027b-4b27-bcad-c5a05ed4ff03
         * F_Status : 1
         */

        private String F_ID;
        private int F_Status;

        public String getF_ID() {
            return F_ID;
        }

        public void setF_ID(String F_ID) {
            this.F_ID = F_ID;
        }

        public int getF_Status() {
            return F_Status;
        }

        public void setF_Status(int F_Status) {
            this.F_Status = F_Status;
        }
    }
}
