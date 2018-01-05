package com.zhuazhuale.changsha.module.vital.bean;

/**
 * Created by Administrator on 2018/1/5.
 */

public class UploadBean {

    /**
     * Code : 1
     * Info :
     * rows : {"vToken":"smJYutexu12tRZ/wvQuoH4kaOsdzZWNyZXRJZD1BS0lEc2VLdU5meGlUMlBhZVptNFp0MUphamhYQmV2SDY2T1YmY3VycmVudFRpbWVTdGFtcD0xNTE1MTE2OTY0JmV4cGlyZVRpbWU9MTUxNTI4OTc2NCZyYW5kb209NTYzNA=="}
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
         * vToken : smJYutexu12tRZ/wvQuoH4kaOsdzZWNyZXRJZD1BS0lEc2VLdU5meGlUMlBhZVptNFp0MUphamhYQmV2SDY2T1YmY3VycmVudFRpbWVTdGFtcD0xNTE1MTE2OTY0JmV4cGlyZVRpbWU9MTUxNTI4OTc2NCZyYW5kb209NTYzNA==
         */

        private String vToken;

        public String getVToken() {
            return vToken;
        }

        public void setVToken(String vToken) {
            this.vToken = vToken;
        }
    }
}
