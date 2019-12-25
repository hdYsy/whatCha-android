package com.lianjie.andorid.bean;

/**
 * Created by My on 2019/11/14.
 */

public class Photos_ID_bean {

    /**
     * code : 1000
     * msg : 身份证无效，请按拍照要求上传有效的身份证件！
     * data : {"cardName":"身份证","cardId":"1","cardStatus":3,"cardStatusValue":"身份证无效，请按拍照要求上传有效的身份证件！","cardUrl":"http://oss.lianjieshenghuo.com/public/uploadCards/2019/11/14//20191114164859_5dcd14fbbb805.png","colorValue":"#ff4037","allowUpload":2}
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
         * cardName : 身份证
         * cardId : 1
         * cardStatus : 3
         * cardStatusValue : 身份证无效，请按拍照要求上传有效的身份证件！
         * cardUrl : http://oss.lianjieshenghuo.com/public/uploadCards/2019/11/14//20191114164859_5dcd14fbbb805.png
         * colorValue : #ff4037
         * allowUpload : 2
         */

        private String cardName;
        private String cardId;
        private int cardStatus;
        private String cardStatusValue;
        private String cardUrl;
        private String colorValue;
        private int allowUpload;

        public String getCardName() {
            return cardName;
        }

        public void setCardName(String cardName) {
            this.cardName = cardName;
        }

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public int getCardStatus() {
            return cardStatus;
        }

        public void setCardStatus(int cardStatus) {
            this.cardStatus = cardStatus;
        }

        public String getCardStatusValue() {
            return cardStatusValue;
        }

        public void setCardStatusValue(String cardStatusValue) {
            this.cardStatusValue = cardStatusValue;
        }

        public String getCardUrl() {
            return cardUrl;
        }

        public void setCardUrl(String cardUrl) {
            this.cardUrl = cardUrl;
        }

        public String getColorValue() {
            return colorValue;
        }

        public void setColorValue(String colorValue) {
            this.colorValue = colorValue;
        }

        public int getAllowUpload() {
            return allowUpload;
        }

        public void setAllowUpload(int allowUpload) {
            this.allowUpload = allowUpload;
        }
    }
}
