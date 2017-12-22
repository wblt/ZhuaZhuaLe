package com.zhuazhuale.changsha.module.home.Bean;

/**
 * 用户信息
 * Created by Administrator on 2017/12/19 0019.
 */

public class LoginInfoBean {

    /**
     * Code : 1
     * Info : 登陆成功
     * rows : {"F_ID":"d734b0cc-721d-4430-b153-596acfaed97f","F_Code":"o_IiX0a1M19WwcnTWMv-KoglIzBc","F_Code1":null,"F_Code2":null,"F_Name":"测试账号","F_CP":10000,"F_Img":"http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIWocVuO317VKlQK4WV3NlRVoFJMbibCNLADWQLIcjWP1GRyjSFCT8m1gSkNKGnibpjvfTicibgLrOedg/0","F_VerCode":"0"}
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
         * F_ID : d734b0cc-721d-4430-b153-596acfaed97f
         * F_Code : o_IiX0a1M19WwcnTWMv-KoglIzBc
         * F_Code1 : null
         * F_Code2 : null
         * F_Name : 测试账号
         * F_CP : 10000
         * F_Img : http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIWocVuO317VKlQK4WV3NlRVoFJMbibCNLADWQLIcjWP1GRyjSFCT8m1gSkNKGnibpjvfTicibgLrOedg/0
         * F_VerCode : 0
         */

        private String F_ID;
        private String F_Code;
        private String F_Code1;
        private String F_Code2;
        private String F_Name;
        private int F_CP;
        private String F_Img;
        private String F_VerCode;

        public RowsBean() {
        }

        public RowsBean(String f_ID, String f_Code, String f_Code1, String f_Code2, String f_Name, int f_CP, String f_Img, String f_VerCode) {
            F_ID = f_ID;
            F_Code = f_Code;
            F_Code1 = f_Code1;
            F_Code2 = f_Code2;
            F_Name = f_Name;
            F_CP = f_CP;
            F_Img = f_Img;
            F_VerCode = f_VerCode;
        }

        public String getF_ID() {
            return F_ID;
        }

        public void setF_ID(String F_ID) {
            this.F_ID = F_ID;
        }

        public String getF_Code() {
            return F_Code;
        }

        public void setF_Code(String F_Code) {
            this.F_Code = F_Code;
        }

        public String getF_Code1() {
            return F_Code1;
        }

        public void setF_Code1(String F_Code1) {
            this.F_Code1 = F_Code1;
        }

        public String getF_Code2() {
            return F_Code2;
        }

        public void setF_Code2(String F_Code2) {
            this.F_Code2 = F_Code2;
        }

        public String getF_Name() {
            return F_Name;
        }

        public void setF_Name(String F_Name) {
            this.F_Name = F_Name;
        }

        public int getF_CP() {
            return F_CP;
        }

        public void setF_CP(int F_CP) {
            this.F_CP = F_CP;
        }

        public String getF_Img() {
            return F_Img;
        }

        public void setF_Img(String F_Img) {
            this.F_Img = F_Img;
        }

        public String getF_VerCode() {
            return F_VerCode;
        }

        public void setF_VerCode(String F_VerCode) {
            this.F_VerCode = F_VerCode;
        }
    }
}
