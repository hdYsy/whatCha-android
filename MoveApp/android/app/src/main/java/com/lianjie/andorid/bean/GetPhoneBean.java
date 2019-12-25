package com.lianjie.andorid.bean;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/31
 * Time: 14:18
 * 隐私号实体类
 */
public class GetPhoneBean {

    /**
     * code : 1000
     * msg : 获取隐私号成功
     * data : {"ysh":"17071069050"}
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
         * ysh : 17071069050
         */

        private String ysh;

        public String getYsh() {
            return ysh;
        }

        public void setYsh(String ysh) {
            this.ysh = ysh;
        }
    }
}
