package com.lianjie.andorid.bean;

/**
 * Created by My on 2019/12/13.
 */

public class Lbs_delatilesBean {

    /**
     * code : 1000
     * msg : 获取数据成功
     * data : {"gid":"3615562","id":"8253049","yid":"143487","btypename":"月度优选保洁","btype":"13","planservicetime":"2019年11月26日","village":"北街家园八区","villageaddress":"北街家园八区小区5楼1单元1502室","distance":31609}
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
         * gid : 3615562
         * id : 8253049
         * yid : 143487
         * btypename : 月度优选保洁
         * btype : 13
         * planservicetime : 2019年11月26日
         * village : 北街家园八区
         * villageaddress : 北街家园八区小区5楼1单元1502室
         * distance : 31609
         */

        private String gid;
        private String id;
        private String yid;
        private String btypename;
        private String btype;
        private String planservicetime;
        private String village;
        private String villageaddress;
        private int distance;
        private String textColor;//颜色色值

        public String getTextColor() {
            return textColor;
        }

        public void setTextColor(String textColor) {
            this.textColor = textColor;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYid() {
            return yid;
        }

        public void setYid(String yid) {
            this.yid = yid;
        }

        public String getBtypename() {
            return btypename;
        }

        public void setBtypename(String btypename) {
            this.btypename = btypename;
        }

        public String getBtype() {
            return btype;
        }

        public void setBtype(String btype) {
            this.btype = btype;
        }

        public String getPlanservicetime() {
            return planservicetime;
        }

        public void setPlanservicetime(String planservicetime) {
            this.planservicetime = planservicetime;
        }

        public String getVillage() {
            return village;
        }

        public void setVillage(String village) {
            this.village = village;
        }

        public String getVillageaddress() {
            return villageaddress;
        }

        public void setVillageaddress(String villageaddress) {
            this.villageaddress = villageaddress;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }
    }
}
