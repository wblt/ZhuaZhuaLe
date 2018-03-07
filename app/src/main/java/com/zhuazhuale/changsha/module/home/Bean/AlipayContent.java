package com.zhuazhuale.changsha.module.home.Bean;

/**
 * Created by Administrator on 2018/3/7.
 */

public class AlipayContent {

    /**
     * timeout_express : 30m
     * product_code : QUICK_MSECURITY_PAY
     * total_amount : 0.01
     * subject : 1
     * body : 我是测试数据
     * out_trade_no : 0307101336-1712
     */

    private String timeout_express;
    private String product_code;
    private String total_amount;
    private String subject;
    private String body;
    private String out_trade_no;

    public AlipayContent(String timeout_express, String product_code, String total_amount, String subject, String body, String out_trade_no) {
        this.timeout_express = timeout_express;
        this.product_code = product_code;
        this.total_amount = total_amount;
        this.subject = subject;
        this.body = body;
        this.out_trade_no = out_trade_no;
    }

    public String getTimeout_express() {
        return timeout_express;
    }

    public void setTimeout_express(String timeout_express) {
        this.timeout_express = timeout_express;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
}
