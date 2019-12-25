package com.lianjie.andorid.bean;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/12
 * Time: 17:23
 */
public class LoginBean {


    /**
     * code : 1000
     * msg : 登录成功
     * data : {"username":"18790936220","realname":"姜欠欠","phone":"18790936220","thumb":"http://oss.lianjieshenghuo.com/public/thumb/2019/05/28/100397/20190528141354_5cecd1a2a34eb.png","status":"1","tid":"5253","cityid":null,"provinceid":"1","cleantype":"1","rank":"3","token":"WBHOFDMiLykh4zEJNfn8xv5V92rbXIAS","face":0,"userid":"100397"}
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
         * username : 18790936220
         * realname : 姜欠欠
         * phone : 18790936220
         * thumb : http://oss.lianjieshenghuo.com/public/thumb/2019/05/28/100397/20190528141354_5cecd1a2a34eb.png
         * status : 1
         * tid : 5253
         * cityid : null
         * provinceid : 1
         * cleantype : 1
         * rank : 3
         * token : WBHOFDMiLykh4zEJNfn8xv5V92rbXIAS
         * face : 0
         * userid : 100397
         */

        private String username;
        private String realname;
        private String phone;
        private String thumb;
        private String status;
        private String tid;
        private Object cityid;
        private String provinceid;
        private String cleantype;
        private String rank;
        private String token;
        private int face;
        private String userid;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public Object getCityid() {
            return cityid;
        }

        public void setCityid(Object cityid) {
            this.cityid = cityid;
        }

        public String getProvinceid() {
            return provinceid;
        }

        public void setProvinceid(String provinceid) {
            this.provinceid = provinceid;
        }

        public String getCleantype() {
            return cleantype;
        }

        public void setCleantype(String cleantype) {
            this.cleantype = cleantype;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getFace() {
            return face;
        }

        public void setFace(int face) {
            this.face = face;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }
    }
}
