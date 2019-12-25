package com.lianjie.andorid.bean;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/9/26
 * Time: 18:07
 */
public class SderBean {
    private int code ;
    private String msg;
    private Data data;

    public SderBean(int code, String msg, Data data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

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


    public class Data {
        private int flag;

        public Data(int flag) {
            this.flag = flag;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }
    }
}
