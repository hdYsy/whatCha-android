package com.lianjie.andorid.bean;

import java.util.List;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/1
 * Time: 15:37
 */
public class NoScrosessBean {

    /**
     * code : 1000
     * msg : 成功获取数据
     * data : {"reason":["租户联系不上，无法开展工作","工单太多做不完","租户取消保洁","维修中无法做保洁","停水停电","本人身体不适","门锁无法打开","租户要求本次不做","租户要求永久不做","租户不要我来做"],"flag":3,"time":["08:00-10:00","10:00-12:00","12:00-14:00","14:00-16:00","16:00-18:00","18:00-20:00"],"date":["07月02日","07月03日","07月04日","07月05日","07月06日","07月07日","07月08日"]}
     */

    private int code;
    private String msg;
    private DataBean data;

    @Override
    public String toString() {
        return "NoScrosessBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "flag=" + flag +
                    ", reason=" + reason +
                    ", time=" + time +
                    ", date=" + date +
                    '}';
        }

        /**
         * reason : ["租户联系不上，无法开展工作","工单太多做不完","租户取消保洁","维修中无法做保洁","停水停电","本人身体不适","门锁无法打开","租户要求本次不做","租户要求永久不做","租户不要我来做"]
         * flag : 3
         * time : ["08:00-10:00","10:00-12:00","12:00-14:00","14:00-16:00","16:00-18:00","18:00-20:00"]
         * date : ["07月02日","07月03日","07月04日","07月05日","07月06日","07月07日","07月08日"]
         */

        private int flag;
        private List<String> reason;
        private List<String> time;
        private List<String> date;

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public List<String> getReason() {
            return reason;
        }

        public void setReason(List<String> reason) {
            this.reason = reason;
        }

        public List<String> getTime() {
            return time;
        }

        public void setTime(List<String> time) {
            this.time = time;
        }

        public List<String> getDate() {
            return date;
        }

        public void setDate(List<String> date) {
            this.date = date;
        }
    }
}
