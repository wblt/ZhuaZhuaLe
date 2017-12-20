package com.zhuazhuale.changsha.module.home.Bean;

/**
 * Created by Administrator on 2017/12/20.
 */

public class EditAddressBean {

    /**
     * Code : 1
     * Info : 添加成功
     * rows : null
     * PageCount : 0
     * total : 0
     */

    private int Code;
    private String Info;
    private Object rows;
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

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
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
}
