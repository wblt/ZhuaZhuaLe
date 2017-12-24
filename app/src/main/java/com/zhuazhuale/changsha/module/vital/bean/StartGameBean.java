package com.zhuazhuale.changsha.module.vital.bean;

/**
 * Created by Administrator on 2017/12/24 0024.
 */

public class StartGameBean {

    /**
     * Code : 1
     * Info : 成功上机
     * rows : {"token":"W5QQrkRcPGE0tfTM7hRQs/osxU9sbZRIE6rlH3MtN5Q6jjRIlHIrOQ==","timestamp":1514045328116}
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
         * token : W5QQrkRcPGE0tfTM7hRQs/osxU9sbZRIE6rlH3MtN5Q6jjRIlHIrOQ==
         * timestamp : 1514045328116
         */

        private String token;
        private long timestamp;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }
    }
}
