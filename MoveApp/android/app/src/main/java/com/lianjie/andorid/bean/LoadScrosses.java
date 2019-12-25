package com.lianjie.andorid.bean;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/4
 * Time: 15:51
 */
public class LoadScrosses {

    /**
     * code : 1001
     * msg : 请选择或者填写无法完成的原因
     */

    private int code;
    private String msg;

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

    @Override
    public String toString() {
        return "LoadScrosses{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
