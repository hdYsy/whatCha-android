package com.lianjie.andorid.bean;

import java.util.List;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/20
 * Time: 16:41
 */
public class Work_SuccesBean {

    /**
     * code : 1000
     * msg : 获取数据成功
     * data : {"count":2,"list":[{"id":"38087","gid":"64","yid":"1244","btype":"1","villageaddress":"#1244 顶秀欣园东苑7-1905","roomnumber":"","status":"3","wholeRent":"2","rentFlag":0,"lat":"39.845188","lng":"116.433665","servicestarttime":"1560907656","planservicetime":"1560873600","serviceEndTime":"1560907765","onlineReservation":null,"duration":"16","stateTag":"","cleanarea":"2","suiteLock":"1","suiteSource":"蛋壳","servicePeriod":"0","channel":"4","btypename":"返工工单","statusname":"已完成","delay":"","rentTag":"","statTag":"","delayTag":"","doingTag":"","orderTag":"","lockTag":"","areaTag":"公共区域","timeTag":"","onlineTag":"","contactTag":"","hoursTag":"","doing":0},{"id":"38088","gid":"44","yid":"1228","btype":"3","villageaddress":"#1228 姚家园东里8号院7-1-602","roomnumber":"","status":"3","wholeRent":"2","rentFlag":0,"lat":"39.954732","lng":"116.524903","servicestarttime":"1560916622","planservicetime":"1560873600","serviceEndTime":"1560916711","onlineReservation":null,"duration":"18","stateTag":"","cleanarea":"2","suiteLock":"1","suiteSource":"蛋壳","servicePeriod":"0","channel":"4","btypename":"返工工单","statusname":"已完成","delay":"","rentTag":"","statTag":"","delayTag":"","doingTag":"","orderTag":"","lockTag":"","areaTag":"","timeTag":"","onlineTag":"","contactTag":"","hoursTag":"","doing":0}]}
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
         * count : 2
         * list : [{"id":"38087","gid":"64","yid":"1244","btype":"1","villageaddress":"#1244 顶秀欣园东苑7-1905","roomnumber":"","status":"3","wholeRent":"2","rentFlag":0,"lat":"39.845188","lng":"116.433665","servicestarttime":"1560907656","planservicetime":"1560873600","serviceEndTime":"1560907765","onlineReservation":null,"duration":"16","stateTag":"","cleanarea":"2","suiteLock":"1","suiteSource":"蛋壳","servicePeriod":"0","channel":"4","btypename":"返工工单","statusname":"已完成","delay":"","rentTag":"","statTag":"","delayTag":"","doingTag":"","orderTag":"","lockTag":"","areaTag":"公共区域","timeTag":"","onlineTag":"","contactTag":"","hoursTag":"","doing":0},{"id":"38088","gid":"44","yid":"1228","btype":"3","villageaddress":"#1228 姚家园东里8号院7-1-602","roomnumber":"","status":"3","wholeRent":"2","rentFlag":0,"lat":"39.954732","lng":"116.524903","servicestarttime":"1560916622","planservicetime":"1560873600","serviceEndTime":"1560916711","onlineReservation":null,"duration":"18","stateTag":"","cleanarea":"2","suiteLock":"1","suiteSource":"蛋壳","servicePeriod":"0","channel":"4","btypename":"返工工单","statusname":"已完成","delay":"","rentTag":"","statTag":"","delayTag":"","doingTag":"","orderTag":"","lockTag":"","areaTag":"","timeTag":"","onlineTag":"","contactTag":"","hoursTag":"","doing":0}]
         */

        private int count;
        private List<ListBean> list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 38087
             * gid : 64
             * yid : 1244
             * btype : 1
             * villageaddress : #1244 顶秀欣园东苑7-1905
             * roomnumber :
             * status : 3
             * wholeRent : 2
             * rentFlag : 0
             * lat : 39.845188
             * lng : 116.433665
             * servicestarttime : 1560907656
             * planservicetime : 1560873600
             * serviceEndTime : 1560907765
             * onlineReservation : null
             * duration : 16
             * stateTag :
             * cleanarea : 2
             * suiteLock : 1
             * suiteSource : 蛋壳
             * servicePeriod : 0
             * channel : 4
             * btypename : 返工工单
             * statusname : 已完成
             * delay :
             * rentTag :
             * statTag :
             * delayTag :
             * doingTag :
             * orderTag :
             * lockTag :
             * areaTag : 公共区域
             * timeTag :
             * onlineTag :
             * contactTag :
             * hoursTag :
             * doing : 0
             */

            private String id;
            private String gid;
            private String yid;
            private String btype;
            private String villageaddress;
            private String roomnumber;
            private String status;
            private String wholeRent;
            private int rentFlag;
            private String lat;
            private String lng;
            private String servicestarttime;
            private String planservicetime;
            private String serviceEndTime;
            private Object onlineReservation;
            private String duration;
            private String stateTag;
            private String cleanarea;
            private String suiteLock;
            private String suiteSource;
            private String servicePeriod;
            private String channel;
            private String btypename;
            private String statusname;
            private String delay;
            private String rentTag;
            private String statTag;
            private String delayTag;
            private String doingTag;
            private String orderTag;
            private String lockTag;
            private String areaTag;
            private String timeTag;
            private String onlineTag;
            private String contactTag;
            private String hoursTag;
            private int doing;
            private String batteryStatus;
            private String bedTag;//床品信息

            public String getBedTag() {
                return bedTag;
            }

            public void setBedTag(String bedTag) {
                this.bedTag = bedTag;
            }

            public String getBatteryStatus() {
                return batteryStatus;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
            }

            public String getYid() {
                return yid;
            }

            public void setYid(String yid) {
                this.yid = yid;
            }

            public String getBtype() {
                return btype;
            }

            public void setBtype(String btype) {
                this.btype = btype;
            }

            public String getVillageaddress() {
                return villageaddress;
            }

            public void setVillageaddress(String villageaddress) {
                this.villageaddress = villageaddress;
            }

            public String getRoomnumber() {
                return roomnumber;
            }

            public void setRoomnumber(String roomnumber) {
                this.roomnumber = roomnumber;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getWholeRent() {
                return wholeRent;
            }

            public void setWholeRent(String wholeRent) {
                this.wholeRent = wholeRent;
            }

            public int getRentFlag() {
                return rentFlag;
            }

            public void setRentFlag(int rentFlag) {
                this.rentFlag = rentFlag;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getServicestarttime() {
                return servicestarttime;
            }

            public void setServicestarttime(String servicestarttime) {
                this.servicestarttime = servicestarttime;
            }

            public String getPlanservicetime() {
                return planservicetime;
            }

            public void setPlanservicetime(String planservicetime) {
                this.planservicetime = planservicetime;
            }

            public String getServiceEndTime() {
                return serviceEndTime;
            }

            public void setServiceEndTime(String serviceEndTime) {
                this.serviceEndTime = serviceEndTime;
            }

            public Object getOnlineReservation() {
                return onlineReservation;
            }

            public void setOnlineReservation(Object onlineReservation) {
                this.onlineReservation = onlineReservation;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getStateTag() {
                return stateTag;
            }

            public void setStateTag(String stateTag) {
                this.stateTag = stateTag;
            }

            public String getCleanarea() {
                return cleanarea;
            }

            public void setCleanarea(String cleanarea) {
                this.cleanarea = cleanarea;
            }

            public String getSuiteLock() {
                return suiteLock;
            }

            public void setSuiteLock(String suiteLock) {
                this.suiteLock = suiteLock;
            }

            public String getSuiteSource() {
                return suiteSource;
            }

            public void setSuiteSource(String suiteSource) {
                this.suiteSource = suiteSource;
            }

            public String getServicePeriod() {
                return servicePeriod;
            }

            public void setServicePeriod(String servicePeriod) {
                this.servicePeriod = servicePeriod;
            }

            public String getChannel() {
                return channel;
            }

            public void setChannel(String channel) {
                this.channel = channel;
            }

            public String getBtypename() {
                return btypename;
            }

            public void setBtypename(String btypename) {
                this.btypename = btypename;
            }

            public String getStatusname() {
                return statusname;
            }

            public void setStatusname(String statusname) {
                this.statusname = statusname;
            }

            public String getDelay() {
                return delay;
            }

            public void setDelay(String delay) {
                this.delay = delay;
            }

            public String getRentTag() {
                return rentTag;
            }

            public void setRentTag(String rentTag) {
                this.rentTag = rentTag;
            }

            public String getStatTag() {
                return statTag;
            }

            public void setStatTag(String statTag) {
                this.statTag = statTag;
            }

            public String getDelayTag() {
                return delayTag;
            }

            public void setDelayTag(String delayTag) {
                this.delayTag = delayTag;
            }

            public String getDoingTag() {
                return doingTag;
            }

            public void setDoingTag(String doingTag) {
                this.doingTag = doingTag;
            }

            public String getOrderTag() {
                return orderTag;
            }

            public void setOrderTag(String orderTag) {
                this.orderTag = orderTag;
            }

            public String getLockTag() {
                return lockTag;
            }

            public void setLockTag(String lockTag) {
                this.lockTag = lockTag;
            }

            public String getAreaTag() {
                return areaTag;
            }

            public void setAreaTag(String areaTag) {
                this.areaTag = areaTag;
            }

            public String getTimeTag() {
                return timeTag;
            }

            public void setTimeTag(String timeTag) {
                this.timeTag = timeTag;
            }

            public String getOnlineTag() {
                return onlineTag;
            }

            public void setOnlineTag(String onlineTag) {
                this.onlineTag = onlineTag;
            }

            public String getContactTag() {
                return contactTag;
            }

            public void setContactTag(String contactTag) {
                this.contactTag = contactTag;
            }

            public String getHoursTag() {
                return hoursTag;
            }

            public void setHoursTag(String hoursTag) {
                this.hoursTag = hoursTag;
            }

            public int getDoing() {
                return doing;
            }

            public void setDoing(int doing) {
                this.doing = doing;
            }
        }
    }
}
