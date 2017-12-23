package com.zhuazhuale.changsha.module.home.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * 地址
 * Created by Administrator on 2017/12/20.
 */

public class AddressBean implements Serializable{

    /**
     * Code : 1
     * Info :
     * rows : [{"F_ID":"0b6448e1-1f61-4b47-8701-089d0373e92d","F_UserID":"d734b0cc-721d-4430-b153-596acfaed97f","F_Consignee":"宁大侠","F_Mobile":"18217317571","F_Address":"湖南省长沙市岳麓区雷锋大道609号天麓小区14栋2102号"},{"F_ID":"a8d4f3f3-efa6-409d-9376-90c898015992","F_UserID":"d734b0cc-721d-4430-b153-596acfaed97f","F_Consignee":"命陪标","F_Mobile":"18217317571","F_Address":"上海市闵行区浦江镇瑞和城555弄40栋1702号"}]
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

    public static class RowsBean implements Serializable {
        /**
         * F_ID : 0b6448e1-1f61-4b47-8701-089d0373e92d
         * F_UserID : d734b0cc-721d-4430-b153-596acfaed97f
         * F_Consignee : 宁大侠
         * F_Mobile : 18217317571
         * F_Address : 湖南省长沙市岳麓区雷锋大道609号天麓小区14栋2102号
         */

        private String F_ID;
        private String F_UserID;
        private String F_Consignee;
        private String F_Mobile;
        private String F_Address;

        public String getF_ID() {
            return F_ID;
        }

        public void setF_ID(String F_ID) {
            this.F_ID = F_ID;
        }

        public String getF_UserID() {
            return F_UserID;
        }

        public void setF_UserID(String F_UserID) {
            this.F_UserID = F_UserID;
        }

        public String getF_Consignee() {
            return F_Consignee;
        }

        public void setF_Consignee(String F_Consignee) {
            this.F_Consignee = F_Consignee;
        }

        public String getF_Mobile() {
            return F_Mobile;
        }

        public void setF_Mobile(String F_Mobile) {
            this.F_Mobile = F_Mobile;
        }

        public String getF_Address() {
            return F_Address;
        }

        public void setF_Address(String F_Address) {
            this.F_Address = F_Address;
        }
    }
}
