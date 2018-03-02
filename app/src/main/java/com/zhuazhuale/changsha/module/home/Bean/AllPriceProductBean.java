package com.zhuazhuale.changsha.module.home.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/12/19.
 */

public class AllPriceProductBean implements Serializable{


    /**
     * Code : 1
     * Info :
     * PageCount : 0
     * rows : [{"F_Activity":0,"F_ID":"c5129e6c-574d-4167-985d-87064580b901","F_Price1":100,"F_Price2":20,"F_Price3":10,"F_Remark":"120币+0.2张包邮券","F_Sort":1},{"F_Activity":0,"F_ID":"b82f452a-43d6-42df-9858-6738d12dd4c1","F_Price1":200,"F_Price2":45,"F_Price3":20,"F_Remark":"245币+0.5张包邮券","F_Sort":2},{"F_Activity":0,"F_ID":"358e75da-4591-495b-96e4-4ea8e2bb0809","F_Price1":500,"F_Price2":125,"F_Price3":50,"F_Remark":"625个币+1.3张包邮券","F_Sort":3},{"F_Activity":1,"F_ID":"1980307e-e491-4661-b557-48a50a5767ef","F_Price1":1000,"F_Price2":250,"F_Price3":100,"F_Remark":"1250币+福袋+2张包邮券","F_Sort":4},{"F_Activity":0,"F_ID":"6fd9191f-51a7-4e37-8481-23829a093542","F_Price1":3000,"F_Price2":900,"F_Price3":300,"F_Remark":"3900币+7张包邮券","F_Sort":5},{"F_Activity":0,"F_ID":"875e60b1-a325-4deb-b858-7ef5209c502d","F_Price1":5000,"F_Price2":1625,"F_Price3":500,"F_Remark":"6625币+18张包邮券","F_Sort":6}]
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

    public static class RowsBean implements Serializable{
        /**
         * F_Activity : 0
         * F_ID : c5129e6c-574d-4167-985d-87064580b901
         * F_Price1 : 100
         * F_Price2 : 20
         * F_Price3 : 10
         * F_Remark : 120币+0.2张包邮券
         * F_Sort : 1
         */

        private int F_Activity;
        private String F_ID;
        private int F_Price1;
        private int F_Price2;
        private int F_Price3;
        private String F_Remark;
        private int F_Sort;

        public int getF_Activity() {
            return F_Activity;
        }

        public void setF_Activity(int F_Activity) {
            this.F_Activity = F_Activity;
        }

        public String getF_ID() {
            return F_ID;
        }

        public void setF_ID(String F_ID) {
            this.F_ID = F_ID;
        }

        public int getF_Price1() {
            return F_Price1;
        }

        public void setF_Price1(int F_Price1) {
            this.F_Price1 = F_Price1;
        }

        public int getF_Price2() {
            return F_Price2;
        }

        public void setF_Price2(int F_Price2) {
            this.F_Price2 = F_Price2;
        }

        public int getF_Price3() {
            return F_Price3;
        }

        public void setF_Price3(int F_Price3) {
            this.F_Price3 = F_Price3;
        }

        public String getF_Remark() {
            return F_Remark;
        }

        public void setF_Remark(String F_Remark) {
            this.F_Remark = F_Remark;
        }

        public int getF_Sort() {
            return F_Sort;
        }

        public void setF_Sort(int F_Sort) {
            this.F_Sort = F_Sort;
        }
    }
}
