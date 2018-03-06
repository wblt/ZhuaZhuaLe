package com.zhuazhuale.changsha.module.home.Bean;

/**
 * Created by Administrator on 2018/3/6.
 */

public class AlipayBean {


    /**
     * Code : 1
     * Info :
     * PageCount : 0
     * rows : {"body":"120币+0.2张包邮券","out_trade_no":"Ali_20180306150229536","subject":"长沙抓抓乐信息科技有限公司","total_amout":10,"vKey":"MIIEpAIBAAKCAQEA6ezhtxfejO9w2ZIHCKT6nGSzHBUkkc/NVNKDOo1DVrBRARmAmczCb8PaJ77BDG8LlWRiS8qdKOgvnn+vClNhFZhP5rBvH12iUgeRDXOUA5rGt3QbfnEufN4ijXRAVQobBqvoqWyxc+BU9szhel4h8MZyP3L6stZXpMwAwnz43xL6hcBtBuxggyOH0ZiZ4IMIwFyK79+JUDOuOMl1IgVbnC1m+Exu5SBkNnXXmxhXOoqCiflW0NRwRrLh2pxa18oRX+E3P0ui5Q26NF+lmgQqOXlDW8gOG3rmtZ/w0VjI41nSXQyb73tGFprXFW6HuQkgjiwsEeIAsba7nzkTDD8J+QIDAQABAoIBAGAa5xze3JsQYE+y09r0VNXd9UwLT6vsMNKxbiKnGMxLwevuu0v0uMlMMJt1nkBqetrUlCHJS0ocIjw+4CBXfmzKTdRbogMOj8nNZm7p+QfiWuuQD4pP2+EXoGEcweS74w8HRy6HDP8Hg48kXg2JbgIN36cg75Sqa0/Xbp+yFCFMDKj9IFWTYyH7PGHF7xgiEaN7bT8LeRyu2rr4WLnDlpgYHdw61c1SQiGyDBCPQufqecmX2tw5sdgK6Ud0qpGb6DvytNoyXM9NpI6vCpruLzOG8w6KWBT/3WjovfHx0UTROduk381XSxNkUCz3pBI5zJI9QLi39ZKIGhhiu6ZGUCgYEA9SOJKzZKoZ60lwXAFybPwfBIbdtF+Y5RyLU9DQqPU8W3zaF1hb2R/fvz45RCSSjLth/Zbs0l1e/VKMFZOt56EiL6/rnfbxa4RzcDVq32jkX3YaChuJn7UNRIPOs1346rpQyH3EY29tyLe1lyKajcNyfOmpgvFRGlJgGg2pPhk3cCgYEA9Eooa0Eby9itrLYTDxIk1CROQY/TTqvFjuvL8i6cjJ2AChb7XkzQFaZEig2V0Bq0/Odeo25qS6TF0ncHEwNvRi4P9DTdN+jDxcXKBIlMWyYd6YEtRrx1C8bUUas6h3lA2CJUwOtAEwIg0x9wBruKMeX0SoG4U0MmvaozqFGRSg8CgYEAirJIccKOB1OcKbZ/MJotAjXJRR7DQKddboncuPw4i9VaYWXw8HKJn4JmQ2Um++2xFKKAi0hXF+xSVnTO35fwyUIxcN4JpPjd2q0tnmVfErBTf1E7Lw8wOlNha9ZI7Ov0AbZNSDwR7SprYKcDCL8ZmbtUEfP475rELfTN5d21y5ECgYAbLGyWkaV/5VhcT9yK3p/OIUO7Mz50D9Nn9H65L8omMhbeCHRppu22msog+SXjAjWDtFDoqQ2V4HxIzwiwar9j9Re9OGwrHe84/qlcG9Gsie7l3IKmzavPZYyUkRFvpGM8J4V5+DEmbCQul96kQRQ8PLIR36AbUurqShZ48KnSrQKBgQDe219/6feSdGDyt2Pn3bfXvtkdufnr9lur5cvRNbLieTgHR9h0qKWGA4D80qBngZ858A2mVidz+gBJCM8TYS0vsUDwS3RHVgaeso2LS3mRDh9JlReS1Mb6vpGQ/uTqhm6TGeEM385zbNAvPT5fvejzEjUYk/I6iVSqyaOorjWpVg=="}
     * total : 0
     */

    private int Code;
    private String Info;
    private int PageCount;
    private RowsBean rows;
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

    public int getPageCount() {
        return PageCount;
    }

    public void setPageCount(int PageCount) {
        this.PageCount = PageCount;
    }

    public RowsBean getRows() {
        return rows;
    }

    public void setRows(RowsBean rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public static class RowsBean {
        /**
         * body : 120币+0.2张包邮券
         * out_trade_no : Ali_20180306150229536
         * subject : 长沙抓抓乐信息科技有限公司
         * total_amout : 10
         * vKey : MIIEpAIBAAKCAQEA6ezhtxfejO9w2ZIHCKT6nGSzHBUkkc/NVNKDOo1DVrBRARmAmczCb8PaJ77BDG8LlWRiS8qdKOgvnn+vClNhFZhP5rBvH12iUgeRDXOUA5rGt3QbfnEufN4ijXRAVQobBqvoqWyxc+BU9szhel4h8MZyP3L6stZXpMwAwnz43xL6hcBtBuxggyOH0ZiZ4IMIwFyK79+JUDOuOMl1IgVbnC1m+Exu5SBkNnXXmxhXOoqCiflW0NRwRrLh2pxa18oRX+E3P0ui5Q26NF+lmgQqOXlDW8gOG3rmtZ/w0VjI41nSXQyb73tGFprXFW6HuQkgjiwsEeIAsba7nzkTDD8J+QIDAQABAoIBAGAa5xze3JsQYE+y09r0VNXd9UwLT6vsMNKxbiKnGMxLwevuu0v0uMlMMJt1nkBqetrUlCHJS0ocIjw+4CBXfmzKTdRbogMOj8nNZm7p+QfiWuuQD4pP2+EXoGEcweS74w8HRy6HDP8Hg48kXg2JbgIN36cg75Sqa0/Xbp+yFCFMDKj9IFWTYyH7PGHF7xgiEaN7bT8LeRyu2rr4WLnDlpgYHdw61c1SQiGyDBCPQufqecmX2tw5sdgK6Ud0qpGb6DvytNoyXM9NpI6vCpruLzOG8w6KWBT/3WjovfHx0UTROduk381XSxNkUCz3pBI5zJI9QLi39ZKIGhhiu6ZGUCgYEA9SOJKzZKoZ60lwXAFybPwfBIbdtF+Y5RyLU9DQqPU8W3zaF1hb2R/fvz45RCSSjLth/Zbs0l1e/VKMFZOt56EiL6/rnfbxa4RzcDVq32jkX3YaChuJn7UNRIPOs1346rpQyH3EY29tyLe1lyKajcNyfOmpgvFRGlJgGg2pPhk3cCgYEA9Eooa0Eby9itrLYTDxIk1CROQY/TTqvFjuvL8i6cjJ2AChb7XkzQFaZEig2V0Bq0/Odeo25qS6TF0ncHEwNvRi4P9DTdN+jDxcXKBIlMWyYd6YEtRrx1C8bUUas6h3lA2CJUwOtAEwIg0x9wBruKMeX0SoG4U0MmvaozqFGRSg8CgYEAirJIccKOB1OcKbZ/MJotAjXJRR7DQKddboncuPw4i9VaYWXw8HKJn4JmQ2Um++2xFKKAi0hXF+xSVnTO35fwyUIxcN4JpPjd2q0tnmVfErBTf1E7Lw8wOlNha9ZI7Ov0AbZNSDwR7SprYKcDCL8ZmbtUEfP475rELfTN5d21y5ECgYAbLGyWkaV/5VhcT9yK3p/OIUO7Mz50D9Nn9H65L8omMhbeCHRppu22msog+SXjAjWDtFDoqQ2V4HxIzwiwar9j9Re9OGwrHe84/qlcG9Gsie7l3IKmzavPZYyUkRFvpGM8J4V5+DEmbCQul96kQRQ8PLIR36AbUurqShZ48KnSrQKBgQDe219/6feSdGDyt2Pn3bfXvtkdufnr9lur5cvRNbLieTgHR9h0qKWGA4D80qBngZ858A2mVidz+gBJCM8TYS0vsUDwS3RHVgaeso2LS3mRDh9JlReS1Mb6vpGQ/uTqhm6TGeEM385zbNAvPT5fvejzEjUYk/I6iVSqyaOorjWpVg==
         */

        private String body;
        private String out_trade_no;
        private String subject;
        private int total_amout;
        private String vKey;

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

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public int getTotal_amout() {
            return total_amout;
        }

        public void setTotal_amout(int total_amout) {
            this.total_amout = total_amout;
        }

        public String getVKey() {
            return vKey;
        }

        public void setVKey(String vKey) {
            this.vKey = vKey;
        }
    }
}
