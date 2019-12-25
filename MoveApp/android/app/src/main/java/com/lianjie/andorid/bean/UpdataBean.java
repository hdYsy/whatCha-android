package com.lianjie.andorid.bean;

import java.util.List;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/10/16
 * Time: 16:26
 */
public  class UpdataBean{
    /**
     * code : 1000
     * msg : 获取版本信息成功
     * data : {"versionCode":"1","versionName":"1.0","apkUrl":"https://app.lianjieshenghuo.com/downloadApp?apk=美好连接生活.apk","updateTitle":"xxx","changeLog":"1.修复xxx Bug;2.更新了某某UI界面.","isMandatory":"0"}
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
         * versionCode : 1
         * versionName : 1.0
         * apkUrl : https://app.lianjieshenghuo.com/downloadApp?apk=美好连接生活.apk
         * updateTitle : xxx
         * changeLog : 1.修复xxx Bug;2.更新了某某UI界面.
         * isMandatory : 0
         */

        private String versionCode;
        private String versionName;
        private String apkUrl;
        private String updateTitle;
        private List<String> changeLog;
        private String isMandatory;
        /**
         * code : 1000
         * msg : 获取版本信息成功
         * data : {"versionCode":"2","versionName":"1.0","apkUrl":"https://app.lianjieshenghuo.com/downloadApp?apk=app-release.apk","updateTitle":"xxx","changeLog":"[\"\\u4fee\\u590dxxx Bug\",\"\\u66f4\\u65b0\\u4e86\\u67d0\\u67d0UI\\u754c\\u9762\",\"\\u5f3a\\u63d0\\u9192\\u4e0d\\u5361\\u8fdb\\u884c\\u4e2d\\u7684\\u5355\\u5b50\",\"\\u79df\\u6237\\u8054\\u7cfb\\u4eba\\u8054\\u7cfb\\u65b9\\u5f0f\\u6570\\u636e\\u7ed3\\u6784\\u53d8\\u66f4\"]","isMandatory":"0"}
         */

        private int code;
        private String msg;
        private DataBean data;

        public String getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getApkUrl() {
            return apkUrl;
        }

        public void setApkUrl(String apkUrl) {
            this.apkUrl = apkUrl;
        }

        public String getUpdateTitle() {
            return updateTitle;
        }

        public void setUpdateTitle(String updateTitle) {
            this.updateTitle = updateTitle;
        }

        public List<String>  getChangeLog() {
            return changeLog;
        }

        public void setChangeLog(List<String>  changeLog) {
            this.changeLog = changeLog;
        }

        public String getIsMandatory() {
            return isMandatory;
        }

        public void setIsMandatory(String isMandatory) {
            this.isMandatory = isMandatory;
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
    }

    /**
     * versionCode : 2
     * versionName : 2.0
     * apkUrl : ...app
     * updateTitle : 更新提示
     * changeLog : 1.修复xxx Bug;2.更新了某某UI界面.
     * isMandatory  : 0   //是否强制更新
     */

}
