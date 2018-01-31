package com.zhuazhuale.changsha.module.home.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/30.
 */

public class BaseTypeDataBean implements Serializable{

    /**
     * Code : 1
     * Info :
     * rows : [{"F_ID":"6805595c-64a0-4bbe-a48b-1792fa4964a2","F_Name":"全部","F_Sort":0},{"F_ID":"53ada1db-f241-46f0-92b4-14cf7102fbe2","F_Name":"新品","F_Sort":1},{"F_ID":"1aa268c5-1381-49ca-8b31-24599353bded","F_Name":"热门","F_Sort":2},{"F_ID":"a6717e40-0494-4257-968f-16ee402f38a9","F_Name":"特价","F_Sort":3},{"F_ID":"4edcab57-1202-4d54-833b-3ad92d004eff","F_Name":"生活派","F_Sort":4}]
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

    public static class RowsBean implements Serializable{
        /**
         * F_ID : 6805595c-64a0-4bbe-a48b-1792fa4964a2
         * F_Name : 全部
         * F_Sort : 0
         */

        private String F_ID;
        private String F_Name;
        private int F_Sort;

        public String getF_ID() {
            return F_ID;
        }

        public void setF_ID(String F_ID) {
            this.F_ID = F_ID;
        }

        public String getF_Name() {
            return F_Name;
        }

        public void setF_Name(String F_Name) {
            this.F_Name = F_Name;
        }

        public int getF_Sort() {
            return F_Sort;
        }

        public void setF_Sort(int F_Sort) {
            this.F_Sort = F_Sort;
        }
    }
}
