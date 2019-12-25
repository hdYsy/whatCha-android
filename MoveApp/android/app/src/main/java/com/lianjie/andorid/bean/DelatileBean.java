package com.lianjie.andorid.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/19
 * Time: 15:58
 */
public class DelatileBean {

    /**
     * code : 1000
     * msg : 获取数据成功
     * data : {"id":"55","gid":"307860","yid":"1238","roomid":"4043","roomlist":"[{\"room_id\":4041,\"room_status\":\"\\u5df2\\u51fa\\u79df\",\"room_rentType\":\"\\u5408\\u79df\",\"room_code\":\"1238-A\",\"room_number\":\"A\"},{\"room_id\":4042,\"room_status\":\"\\u5df2\\u51fa\\u79df\",\"room_rentType\":\"\\u5408\\u79df\",\"room_code\":\"1238-B\",\"room_number\":\"B\"},{\"room_id\":4043,\"room_status\":\"\\u5df2\\u51fa\\u79df\",\"room_rentType\":\"\\u5408\\u79df\",\"room_code\":\"1238-C\",\"room_number\":\"C\"}]","status":"3","roomarea":"87.00","timeperiod":"12","btype":"3","planservicetime":"1970年01月01日","cleanarea":"个人区域","roomnumber":"","contactInfo":[],"lat":"39.854273","lng":"116.44781","servicestarttime":"1561097273","des":"","villageaddress":"金第润苑A2-甲-801","wholeRent":"1","village":"金第润苑","bnames":"小黑","bid":"99999","collecthousecontact":{"name":"","phone":""},"createPerson":{"name":"","phone":""},"rentFlag":0,"channel":"1","gtype":"民宅平层","duration":"16","updatetime":"1561097303","stateTag":"","onlineReservation":null,"suiteLock":"1","suiteSource":"蛋壳","servicePeriod":"0","show":0,"delay":"逾期3天","contact":0,"doing":0,"rentTag":"","statTag":"","delayTag":"逾期3天","doingTag":"","orderTag":"","lockTag":"","areaTag":"","timeTag":"","onlineTag":"","contactTag":"","hoursTag":"","btypename":"开荒保洁","distance":15074,"rentType":"整租","officialAddress":"东铁营定向安置房项目小区A2号楼甲单元801","officialAddressLat":"39.846954","officialAddressLng":"116.442863","suitDes":"","orderRevokeReason":"","orderRevokeImage":[],"imagelist":{"sum":5,"list":[{"name":"通用","list":["http://oss.lianjieshenghuo.com/public/uploads/2019/06/21/55/20190621140820_5d0c745418f12.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wNi0yMSAxNDowODoyMA==,color_ffffff,shadow_100/watermark,text_5pyd6Ziz5Yy65pS_5bqc5YaFLOacnemYs-WMuuS6uuawkeaUv-W6nC3ljZfmpbzlhoUw57Gz,color_ffffff,size_20,shadow_100","http://oss.lianjieshenghuo.com/public/uploads/2019/06/21/55/20190621140820_5d0c74541d663.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wNi0yMSAxNDowODoyMA==,color_ffffff,shadow_100/watermark,text_5pyd6Ziz5Yy65pS_5bqc5YaFLOacnemYs-WMuuS6uuawkeaUv-W6nC3ljZfmpbzlhoUw57Gz,color_ffffff,size_20,shadow_100","http://oss.lianjieshenghuo.com/public/uploads/2019/06/21/55/20190621140820_5d0c74542533e.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wNi0yMSAxNDowODoyMA==,color_ffffff,shadow_100/watermark,text_5pyd6Ziz5Yy65pS_5bqc5YaFLOacnemYs-WMuuS6uuawkeaUv-W6nC3ljZfmpbzlhoUw57Gz,color_ffffff,size_20,shadow_100","http://oss.lianjieshenghuo.com/public/uploads/2019/06/21/55/20190621140820_5d0c74542ae72.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wNi0yMSAxNDowODoyMA==,color_ffffff,shadow_100/watermark,text_5pyd6Ziz5Yy65pS_5bqc5YaFLOacnemYs-WMuuS6uuawkeaUv-W6nC3ljZfmpbzlhoUw57Gz,color_ffffff,size_20,shadow_100","http://oss.lianjieshenghuo.com/public/uploads/2019/06/21/55/20190621140820_5d0c74542c6e2.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wNi0yMSAxNDowODoyMA==,color_ffffff,shadow_100/watermark,text_5pyd6Ziz5Yy65pS_5bqc5YaFLOacnemYs-WMuuS6uuawkeaUv-W6nC3ljZfmpbzlhoUw57Gz,color_ffffff,size_20,shadow_100"]}]},"cancel":0}
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
         * id : 55
         * gid : 307860
         * yid : 1238
         * roomid : 4043
         * roomlist : [{"room_id":4041,"room_status":"\u5df2\u51fa\u79df","room_rentType":"\u5408\u79df","room_code":"1238-A","room_number":"A"},{"room_id":4042,"room_status":"\u5df2\u51fa\u79df","room_rentType":"\u5408\u79df","room_code":"1238-B","room_number":"B"},{"room_id":4043,"room_status":"\u5df2\u51fa\u79df","room_rentType":"\u5408\u79df","room_code":"1238-C","room_number":"C"}]
         * status : 3
         * battery:{"status":1,"content":"sadf","title":"换电视}
         * roomarea : 87.00
         * timeperiod : 12
         * btype : 3
         * planservicetime : 1970年01月01日
         * cleanarea : 个人区域
         * roomnumber :
         * contactInfo : []
         * lat : 39.854273
         * lng : 116.44781
         * servicestarttime : 1561097273
         * des :
         * villageaddress : 金第润苑A2-甲-801
         * wholeRent : 1
         * village : 金第润苑
         * bnames : 小黑
         * bid : 99999
         * collecthousecontact : {"name":"","phone":""}
         * createPerson : {"name":"","phone":""}
         * rentFlag : 0
         * channel : 1
         * gtype : 民宅平层
         * duration : 16
         * updatetime : 1561097303
         * stateTag :
         * onlineReservation : null
         * suiteLock : 1
         * suiteSource : 蛋壳
         * servicePeriod : 0
         * show : 0
         * delay : 逾期3天
         * contact : 0
         * doing : 0
         * rentTag :
         * statTag :
         * delayTag : 逾期3天
         * doingTag :
         * orderTag :
         * lockTag :
         * areaTag :
         * timeTag :
         * onlineTag :
         * contactTag :
         * hoursTag :
         * btypename : 开荒保洁
         * distance : 15074
         * rentType : 整租
         * officialAddress : 东铁营定向安置房项目小区A2号楼甲单元801
         * officialAddressLat : 39.846954
         * officialAddressLng : 116.442863
         * suitDes :
         * orderRevokeReason :
         * orderRevokeImage : []
         * imagelist : {"sum":5,"list":[{"name":"通用","list":["http://oss.lianjieshenghuo.com/public/uploads/2019/06/21/55/20190621140820_5d0c745418f12.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wNi0yMSAxNDowODoyMA==,color_ffffff,shadow_100/watermark,text_5pyd6Ziz5Yy65pS_5bqc5YaFLOacnemYs-WMuuS6uuawkeaUv-W6nC3ljZfmpbzlhoUw57Gz,color_ffffff,size_20,shadow_100","http://oss.lianjieshenghuo.com/public/uploads/2019/06/21/55/20190621140820_5d0c74541d663.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wNi0yMSAxNDowODoyMA==,color_ffffff,shadow_100/watermark,text_5pyd6Ziz5Yy65pS_5bqc5YaFLOacnemYs-WMuuS6uuawkeaUv-W6nC3ljZfmpbzlhoUw57Gz,color_ffffff,size_20,shadow_100","http://oss.lianjieshenghuo.com/public/uploads/2019/06/21/55/20190621140820_5d0c74542533e.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wNi0yMSAxNDowODoyMA==,color_ffffff,shadow_100/watermark,text_5pyd6Ziz5Yy65pS_5bqc5YaFLOacnemYs-WMuuS6uuawkeaUv-W6nC3ljZfmpbzlhoUw57Gz,color_ffffff,size_20,shadow_100","http://oss.lianjieshenghuo.com/public/uploads/2019/06/21/55/20190621140820_5d0c74542ae72.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wNi0yMSAxNDowODoyMA==,color_ffffff,shadow_100/watermark,text_5pyd6Ziz5Yy65pS_5bqc5YaFLOacnemYs-WMuuS6uuawkeaUv-W6nC3ljZfmpbzlhoUw57Gz,color_ffffff,size_20,shadow_100","http://oss.lianjieshenghuo.com/public/uploads/2019/06/21/55/20190621140820_5d0c74542c6e2.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wNi0yMSAxNDowODoyMA==,color_ffffff,shadow_100/watermark,text_5pyd6Ziz5Yy65pS_5bqc5YaFLOacnemYs-WMuuS6uuawkeaUv-W6nC3ljZfmpbzlhoUw57Gz,color_ffffff,size_20,shadow_100"]}]}
         * cancel : 0
         */

        private String id;
        private String gid;
        private String yid;
        private String roomid;
        private String roomlist;
        private String status;
        private String timeperiod;
        private String btype;
        private String planservicetime;
        private String cleanarea;
        private String roomnumber;
        private String lat;
        private String lng;
        private String servicestarttime;
        private String des;
        private String villageaddress;
        private String wholeRent;
        private String village;
        private String bnames;
        private String bid;
        private CollecthousecontactBean collecthousecontact;
        private CreatePersonBean createPerson;
        private int rentFlag;
        private String channel;
        private String gtype;
        private String duration;
        private String updatetime;
        private String stateTag;
        private String onlineReservation;
        private String suiteLock;
        private String suiteSource;
        private String servicePeriod;
        private int show;
        private String delay;
        private int contact;
        private int doing;
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
        private String btypename;
        private int distance;
        private String rentType;
        private String officialAddress;
        private String officialAddressLat;
        private String officialAddressLng;
        private String suitDes;
        private String orderRevokeReason;
        private ImagelistBean imagelist;
        private int cancel;
        private List<Bean> contactInfo;
        private List<String> orderRevokeImage;
        private String stimeend;
        private String stimestart;
        private String squareMetre;
        private Battery battery;
        private AboutReminderList aboutReminderList;
        private String impressionDes;
        private String bedTag;//床品信息
        private String serviceDetails;//服务明细
        private String photoBeforeWork;//开荒工单判断是服务前还是服务后

        public String getPhotoBeforeWork() {
            return photoBeforeWork;
        }

        public void setPhotoBeforeWork(String photoBeforeWork) {
            this.photoBeforeWork = photoBeforeWork;
        }

        public String getServiceDetails() {
            return serviceDetails;
        }

        public void setServiceDetails(String serviceDetails) {
            this.serviceDetails = serviceDetails;
        }

        public String getBedTag() {
            return bedTag;
        }

        public void setBedTag(String bedTag) {
            this.bedTag = bedTag;
        }

        public String getImpressionDes() {
            return impressionDes;
        }

        public void setImpressionDes(String impressionDes) {
            this.impressionDes = impressionDes;
        }

        public String getSquareMetre() {
            return squareMetre;
        }

        public void setSquareMetre(String squareMetre) {
            this.squareMetre = squareMetre;
        }

        public AboutReminderList getAboutReminderList() {
            return aboutReminderList;
        }

        public void setAboutReminderList(AboutReminderList aboutReminderList) {
            this.aboutReminderList = aboutReminderList;
        }

        public Battery getBattery() {
            return battery;
        }

        public void setBattery(Battery battery) {
            this.battery = battery;
        }

        public class Battery{
           private int  status;
           private String  content;
           private String  title;

            public Battery(int status, String content, String title) {
                this.status = status;
                this.content = content;
                this.title = title;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
        public String getStimeend() {
            return stimeend;
        }

        public void setStimeend(String stimeend) {
            this.stimeend = stimeend;
        }

        public String getStimestart() {
            return stimestart;
        }

        public void setStimestart(String stimestart) {
            this.stimestart = stimestart;
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

        public String getRoomid() {
            return roomid;
        }

        public void setRoomid(String roomid) {
            this.roomid = roomid;
        }

        public String getRoomlist() {
            return roomlist;
        }

        public void setRoomlist(String roomlist) {
            this.roomlist = roomlist;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }



        public String getTimeperiod() {
            return timeperiod;
        }

        public void setTimeperiod(String timeperiod) {
            this.timeperiod = timeperiod;
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

        public String getCleanarea() {
            return cleanarea;
        }

        public void setCleanarea(String cleanarea) {
            this.cleanarea = cleanarea;
        }

        public String getRoomnumber() {
            return roomnumber;
        }

        public void setRoomnumber(String roomnumber) {
            this.roomnumber = roomnumber;
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

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getVillageaddress() {
            return villageaddress;
        }

        public void setVillageaddress(String villageaddress) {
            this.villageaddress = villageaddress;
        }

        public String getWholeRent() {
            return wholeRent;
        }

        public void setWholeRent(String wholeRent) {
            this.wholeRent = wholeRent;
        }

        public String getVillage() {
            return village;
        }

        public void setVillage(String village) {
            this.village = village;
        }

        public String getBnames() {
            return bnames;
        }

        public void setBnames(String bnames) {
            this.bnames = bnames;
        }

        public String getBid() {
            return bid;
        }

        public void setBid(String bid) {
            this.bid = bid;
        }

        public CollecthousecontactBean getCollecthousecontact() {
            return collecthousecontact;
        }

        public void setCollecthousecontact(CollecthousecontactBean collecthousecontact) {
            this.collecthousecontact = collecthousecontact;
        }

        public CreatePersonBean getCreatePerson() {
            return createPerson;
        }

        public void setCreatePerson(CreatePersonBean createPerson) {
            this.createPerson = createPerson;
        }

        public int getRentFlag() {
            return rentFlag;
        }

        public void setRentFlag(int rentFlag) {
            this.rentFlag = rentFlag;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getGtype() {
            return gtype;
        }

        public void setGtype(String gtype) {
            this.gtype = gtype;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getStateTag() {
            return stateTag;
        }

        public void setStateTag(String stateTag) {
            this.stateTag = stateTag;
        }

        public String getOnlineReservation() {
            return onlineReservation;
        }

        public void setOnlineReservation(String onlineReservation) {
            this.onlineReservation = onlineReservation;
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

        public int getShow() {
            return show;
        }

        public void setShow(int show) {
            this.show = show;
        }

        public String getDelay() {
            return delay;
        }

        public void setDelay(String delay) {
            this.delay = delay;
        }

        public int getContact() {
            return contact;
        }

        public void setContact(int contact) {
            this.contact = contact;
        }

        public int getDoing() {
            return doing;
        }

        public void setDoing(int doing) {
            this.doing = doing;
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

        public String getBtypename() {
            return btypename;
        }

        public void setBtypename(String btypename) {
            this.btypename = btypename;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public String getRentType() {
            return rentType;
        }

        public void setRentType(String rentType) {
            this.rentType = rentType;
        }

        public String getOfficialAddress() {
            return officialAddress;
        }

        public void setOfficialAddress(String officialAddress) {
            this.officialAddress = officialAddress;
        }

        public String getOfficialAddressLat() {
            return officialAddressLat;
        }

        public void setOfficialAddressLat(String officialAddressLat) {
            this.officialAddressLat = officialAddressLat;
        }

        public String getOfficialAddressLng() {
            return officialAddressLng;
        }

        public void setOfficialAddressLng(String officialAddressLng) {
            this.officialAddressLng = officialAddressLng;
        }

        public String getSuitDes() {
            return suitDes;
        }

        public void setSuitDes(String suitDes) {
            this.suitDes = suitDes;
        }

        public String getOrderRevokeReason() {
            return orderRevokeReason;
        }

        public void setOrderRevokeReason(String orderRevokeReason) {
            this.orderRevokeReason = orderRevokeReason;
        }

        public ImagelistBean getImagelist() {
            return imagelist;
        }

        public void setImagelist(ImagelistBean imagelist) {
            this.imagelist = imagelist;
        }

        public int getCancel() {
            return cancel;
        }

        public void setCancel(int cancel) {
            this.cancel = cancel;
        }

        public List<Bean> getContactInfo() {
            return contactInfo;
        }

        public void setContactInfo(List<Bean> contactInfo) {
            this.contactInfo = contactInfo;
        }

        public List<String> getOrderRevokeImage() {
            return orderRevokeImage;
        }

        public void setOrderRevokeImage(List<String> orderRevokeImage) {
            this.orderRevokeImage = orderRevokeImage;
        }

        public static class CollecthousecontactBean {
            /**
             * name :
             * phone :
             */

            private String name;
            private String phone;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }
        }

        public static class CreatePersonBean {
            /**
             * name :
             * phone :
             */

            private String name;
            private String phone;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }
        }
        public static class   AboutReminderList{

            /**
             * 1 : 1
             * 2 : 1
             * 3 : 2
             * 4 : 2
             * 5 : 2
             */

            @SerializedName("1")
            private int _$1;
            @SerializedName("2")
            private int _$2;
            @SerializedName("3")
            private int _$3;
            @SerializedName("4")
            private int _$4;
            @SerializedName("5")
            private int _$5;

            public int get_$1() {
                return _$1;
            }

            public void set_$1(int _$1) {
                this._$1 = _$1;
            }

            public int get_$2() {
                return _$2;
            }

            public void set_$2(int _$2) {
                this._$2 = _$2;
            }

            public int get_$3() {
                return _$3;
            }

            public void set_$3(int _$3) {
                this._$3 = _$3;
            }

            public int get_$4() {
                return _$4;
            }

            public void set_$4(int _$4) {
                this._$4 = _$4;
            }

            public int get_$5() {
                return _$5;
            }

            public void set_$5(int _$5) {
                this._$5 = _$5;
            }
        }
        public static class ImagelistBean {
            /**
             * sum : 5
             * list : [{"name":"通用","list":["http://oss.lianjieshenghuo.com/public/uploads/2019/06/21/55/20190621140820_5d0c745418f12.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wNi0yMSAxNDowODoyMA==,color_ffffff,shadow_100/watermark,text_5pyd6Ziz5Yy65pS_5bqc5YaFLOacnemYs-WMuuS6uuawkeaUv-W6nC3ljZfmpbzlhoUw57Gz,color_ffffff,size_20,shadow_100","http://oss.lianjieshenghuo.com/public/uploads/2019/06/21/55/20190621140820_5d0c74541d663.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wNi0yMSAxNDowODoyMA==,color_ffffff,shadow_100/watermark,text_5pyd6Ziz5Yy65pS_5bqc5YaFLOacnemYs-WMuuS6uuawkeaUv-W6nC3ljZfmpbzlhoUw57Gz,color_ffffff,size_20,shadow_100","http://oss.lianjieshenghuo.com/public/uploads/2019/06/21/55/20190621140820_5d0c74542533e.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wNi0yMSAxNDowODoyMA==,color_ffffff,shadow_100/watermark,text_5pyd6Ziz5Yy65pS_5bqc5YaFLOacnemYs-WMuuS6uuawkeaUv-W6nC3ljZfmpbzlhoUw57Gz,color_ffffff,size_20,shadow_100","http://oss.lianjieshenghuo.com/public/uploads/2019/06/21/55/20190621140820_5d0c74542ae72.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wNi0yMSAxNDowODoyMA==,color_ffffff,shadow_100/watermark,text_5pyd6Ziz5Yy65pS_5bqc5YaFLOacnemYs-WMuuS6uuawkeaUv-W6nC3ljZfmpbzlhoUw57Gz,color_ffffff,size_20,shadow_100","http://oss.lianjieshenghuo.com/public/uploads/2019/06/21/55/20190621140820_5d0c74542c6e2.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wNi0yMSAxNDowODoyMA==,color_ffffff,shadow_100/watermark,text_5pyd6Ziz5Yy65pS_5bqc5YaFLOacnemYs-WMuuS6uuawkeaUv-W6nC3ljZfmpbzlhoUw57Gz,color_ffffff,size_20,shadow_100"]}]
             */

            private int sum;
            private List<ListBean> list;

            public int getSum() {
                return sum;
            }

            public void setSum(int sum) {
                this.sum = sum;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * name : 通用
                 * list : ["http://oss.lianjieshenghuo.com/public/uploads/2019/06/21/55/20190621140820_5d0c745418f12.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wNi0yMSAxNDowODoyMA==,color_ffffff,shadow_100/watermark,text_5pyd6Ziz5Yy65pS_5bqc5YaFLOacnemYs-WMuuS6uuawkeaUv-W6nC3ljZfmpbzlhoUw57Gz,color_ffffff,size_20,shadow_100","http://oss.lianjieshenghuo.com/public/uploads/2019/06/21/55/20190621140820_5d0c74541d663.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wNi0yMSAxNDowODoyMA==,color_ffffff,shadow_100/watermark,text_5pyd6Ziz5Yy65pS_5bqc5YaFLOacnemYs-WMuuS6uuawkeaUv-W6nC3ljZfmpbzlhoUw57Gz,color_ffffff,size_20,shadow_100","http://oss.lianjieshenghuo.com/public/uploads/2019/06/21/55/20190621140820_5d0c74542533e.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wNi0yMSAxNDowODoyMA==,color_ffffff,shadow_100/watermark,text_5pyd6Ziz5Yy65pS_5bqc5YaFLOacnemYs-WMuuS6uuawkeaUv-W6nC3ljZfmpbzlhoUw57Gz,color_ffffff,size_20,shadow_100","http://oss.lianjieshenghuo.com/public/uploads/2019/06/21/55/20190621140820_5d0c74542ae72.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wNi0yMSAxNDowODoyMA==,color_ffffff,shadow_100/watermark,text_5pyd6Ziz5Yy65pS_5bqc5YaFLOacnemYs-WMuuS6uuawkeaUv-W6nC3ljZfmpbzlhoUw57Gz,color_ffffff,size_20,shadow_100","http://oss.lianjieshenghuo.com/public/uploads/2019/06/21/55/20190621140820_5d0c74542c6e2.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wNi0yMSAxNDowODoyMA==,color_ffffff,shadow_100/watermark,text_5pyd6Ziz5Yy65pS_5bqc5YaFLOacnemYs-WMuuS6uuawkeaUv-W6nC3ljZfmpbzlhoUw57Gz,color_ffffff,size_20,shadow_100"]
                 */

                private String name;
                private List<String> list;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public List<String> getList() {
                    return list;
                }

                public void setList(List<String> list) {
                    this.list = list;
                }
            }
        }
    }

    public class Bean{
      private String  uid ;
      private String  room ;
      private String  name ;
      private String  phone ;
      private String  phonestar ;

        @Override
        public String toString() {
            return "Bean{" +
                    "uid='" + uid + '\'' +
                    ", room='" + room + '\'' +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    ", phonestar='" + phonestar + '\'' +
                    '}';
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getRoom() {
            return room;
        }

        public void setRoom(String room) {
            this.room = room;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPhonestar() {
            return phonestar;
        }

        public void setPhonestar(String phonestar) {
            this.phonestar = phonestar;
        }

        public Bean() {
        }

        public Bean(String uid, String room, String name, String phone, String phonestar) {
            this.uid = uid;
            this.room = room;
            this.name = name;
            this.phone = phone;
            this.phonestar = phonestar;
        }
    }



}
