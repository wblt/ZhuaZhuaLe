package com.zhuazhuale.changsha.module.home.Bean;

/**
 * 查询机器状态
 * Created by Administrator on 2017/12/24 0024.
 */

public class QueryGameBean {


    /**
     * Code : 1
     * Info :
     * rows : {"vStatus":1,"UserName":"","vUserImg":""}
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
         * vStatus : 1
         * UserName :
         * vUserImg :
         */

        private int vStatus;
        private String UserName;
        private String vUserImg;

        public int getVStatus() {
            return vStatus;
        }

        public void setVStatus(int vStatus) {
            this.vStatus = vStatus;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getVUserImg() {
            return vUserImg;
        }

        public void setVUserImg(String vUserImg) {
            this.vUserImg = vUserImg;
        }
    }
}
