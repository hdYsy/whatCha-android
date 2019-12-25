package com.lianjie.andorid.bean;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/29
 * Time: 18:26
 */
public class GetPasswordBean {

    /**
     * code : 1000
     * msg : success
     * data : {"pass":"无需获取密码"}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pass : 无需获取密码
         */

        private String pass;

        public String getPass() {
            return pass;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }
    }
}
