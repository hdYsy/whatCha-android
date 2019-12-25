package com.lianjie.andorid.bean;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/9/29
 * Time: 11:02
 */
public class ReminBean {

    /**
     * code : 1000
     * msg : 获取提醒配置内容成功
     * data : {"type":"text","second":"5","content":"为保障您的利益，避免纠纷，请严格按照工单操作。如有贵重物品，请联系建单人和管家并保留联系的截屏证据。备注扔的物品不允许私自拿走，只可以丢，拿走等同于盗窃。","isStatus":"1","tipsType":2,"titleType":"退转换须知"}
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
         * type : text
         * second : 5
         * content : 为保障您的利益，避免纠纷，请严格按照工单操作。如有贵重物品，请联系建单人和管家并保留联系的截屏证据。备注扔的物品不允许私自拿走，只可以丢，拿走等同于盗窃。
         * isStatus : 1
         * tipsType : 2
         * titleType : 退转换须知
         */

        private String type;
        private String second;
        private String content;
        private String isStatus;
        private int tipsType;
        private String titleType;
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSecond() {
            return second;
        }

        public void setSecond(String second) {
            this.second = second;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getIsStatus() {
            return isStatus;
        }

        public void setIsStatus(String isStatus) {
            this.isStatus = isStatus;
        }

        public int getTipsType() {
            return tipsType;
        }

        public void setTipsType(int tipsType) {
            this.tipsType = tipsType;
        }

        public String getTitleType() {
            return titleType;
        }

        public void setTitleType(String titleType) {
            this.titleType = titleType;
        }
    }
}
