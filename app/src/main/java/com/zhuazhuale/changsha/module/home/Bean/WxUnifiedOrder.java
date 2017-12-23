package com.zhuazhuale.changsha.module.home.Bean;

import com.google.gson.annotations.SerializedName;

/**
 * 微信支付订单信息
 * Created by 丁琪 on 2017/12/23 0023.
 */

public class WxUnifiedOrder {

    /**
     * Code : 1
     * Info :
     * rows : {"appid":"wx6a6349cd0ccf0b09","partnerid":"1491005862","noncestr":"374f7cc828a9444fb7c7e1634e4dca7f","prepayid":"wx201712231136179a7de4818d0222684280","package":"Sign=WXPay","timestamp":1514000177,"sign":"A68FF3BB1F419DFD0FEED12CC19DD478"}
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
         * appid : wx6a6349cd0ccf0b09
         * partnerid : 1491005862
         * noncestr : 374f7cc828a9444fb7c7e1634e4dca7f
         * prepayid : wx201712231136179a7de4818d0222684280
         * package : Sign=WXPay
         * timestamp : 1514000177
         * sign : A68FF3BB1F419DFD0FEED12CC19DD478
         */

        private String appid;
        private String partnerid;
        private String noncestr;
        private String prepayid;
        @SerializedName("package")
        private String packageX;
        private int timestamp;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
