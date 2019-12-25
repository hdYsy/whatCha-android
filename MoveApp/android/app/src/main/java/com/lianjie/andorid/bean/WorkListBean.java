package com.lianjie.andorid.bean;

import java.util.List;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/17
 * Time: 13:54
 */
public class WorkListBean {
    /****
     * * {
     "code": 1000,
     "msg": "获取数据成功",
     "data": {
     "total": 2,
     "list": [
     {
     "id": "62",
     "gid": "307867",
     "yid": "1242",
     "btype": "4",
     "villageaddress": "#1242 厂洼小区7号院5-10-102",
     "status": "6",
     "wholeRent": "2",
     "lat": "39.964895",
     "lng": "116.311261",
     "servicestarttime": "0",
     "planservicetime": "1560787200",
     "serviceEndTime": "0",
     "channel": "1",
     "distance": 12263,
     "btypename": "日常保洁",
     "orderTerraceTypeName": "蛋壳工单",
     "delay": "逾期28天",
     "rentTag": "",
     "statTag": "",
     "delayTag": "逾期28天",
     "doingTag": "",
     "orderTag": "",
     "lockTag": "",
     "areaTag": "公共区域",
     "timeTag": "18点~20点",
     "onlineTag": "",
     "contactTag": "",
     "hoursTag": "",
     "doing": 0
     },
     {
     "id": "41",
     "gid": "307840",
     "yid": "1227",
     "btype": "1",
     "villageaddress": "#1227 姚家园东里8号院7-1-102",
     "status": "2",
     "wholeRent": "2",
     "lat": "39.913581",
     "lng": "116.402464",
     "servicestarttime": "1563184813",
     "planservicetime": "1560787200",
     "serviceEndTime": "1563273929",
     "channel": "1",
     "distance": 3628,
     "btypename": "双周保洁",
     "orderTerraceTypeName": "蛋壳工单",
     "delay": "逾期28天",
     "rentTag": "",
     "statTag": "",
     "delayTag": "逾期28天",
     "doingTag": "进行中",
     "orderTag": "",
     "lockTag": "",
     "areaTag": "公共区域",
     "timeTag": "",
     "onlineTag": "",
     "contactTag": "",
     "hoursTag": "",
     "doing": 1
     }
     ],
     "time": "03:33:33",
     "delay": 2,
     "toolData": {
     "dataTotal": [
     {
     "btypeName": "日常保洁",
     "num": 1,
     "visitTime": ["18点~20点上门"],
     "delayNum": 1
     },
     {
     "btypeName": "双周保洁",
     "num": 1,
     "visitTime": ["18点~20点上门"],
     "delayNum": 1
     }
     ],
     "btypeNum": 2
     },
     "backOrderNum": 0
     }
     }
     */
    /**
     * code : 1000
     * msg : 获取数据成功
     * data : {"total":10,"list":[{"id":"56","gid":"307861","yid":"1238","btype":"5","villageaddress":"#1238 金第润苑A2-甲-801","roomnumber":"","status":"2","wholeRent":"1","rentFlag":"3","lat":"39.854273","lng":"116.44781","servicestarttime":"1560504589","planservicetime":"1560211200","serviceEndTime":"1560745506","onlineReservation":null,"duration":null,"stateTag":"","cleanarea":"3","suiteLock":"1","suiteSource":"蛋壳","servicePeriod":"0","channel":"1","distance":7537,"btypename":"紧急保洁","statusname":"待上门","delay":"逾期6天","rentTag":"整租","statTag":"已预定","delayTag":"逾期6天","doingTag":"进行中","orderTag":"","lockTag":"","areaTag":"整屋区域","timeTag":"","onlineTag":"","contactTag":"提前联系","hoursTag":"","doing":1},{"id":"43","gid":"307842","yid":"1228","btype":"2","villageaddress":"#1228 姚家园东里8号院7-1-602","roomnumber":"","status":"6","wholeRent":"2","rentFlag":"4","lat":"39.954732","lng":"116.524903","servicestarttime":"0","planservicetime":"1560268800","serviceEndTime":"0","onlineReservation":null,"duration":"18","stateTag":"","cleanarea":"2","suiteLock":"1","suiteSource":"蛋壳","servicePeriod":"0","channel":"1","distance":7847,"btypename":"退转换保洁","statusname":"客户原因未完成","delay":"逾期5天","rentTag":"","statTag":"","delayTag":"逾期5天","doingTag":"","orderTag":"","lockTag":"","areaTag":"","timeTag":"","onlineTag":"","contactTag":"","hoursTag":"","doing":0},{"id":"37","gid":"307836","yid":"1225","btype":"4","villageaddress":"#1225 农光东里28-504","roomnumber":"D","status":"6","wholeRent":"2","rentFlag":"2","lat":"39.887436","lng":"116.470412","servicestarttime":"0","planservicetime":"1560211200","serviceEndTime":"0","onlineReservation":null,"duration":"16","stateTag":"","cleanarea":"1","suiteLock":"1","suiteSource":"蛋壳","servicePeriod":"0","channel":"1","distance":4470,"btypename":"日常保洁","statusname":"客户原因未完成","delay":"逾期6天","rentTag":"","statTag":"D-可出租","delayTag":"逾期6天","doingTag":"","orderTag":"","lockTag":"","areaTag":"","timeTag":"16点~18点","onlineTag":"","contactTag":"","hoursTag":"","doing":0},{"id":"18","gid":"307813","yid":"1213","btype":"7","villageaddress":"#1213 金隅汇星苑1-2-2504","roomnumber":"","status":"6","wholeRent":"2","rentFlag":0,"lat":"39.913581","lng":"116.402464","servicestarttime":"0","planservicetime":"1559750400","serviceEndTime":"0","onlineReservation":null,"duration":null,"stateTag":"","cleanarea":"2","suiteLock":"1","suiteSource":"蛋壳","servicePeriod":"0","channel":"1","distance":3628,"btypename":"维修后保洁","statusname":"客户原因未完成","delay":"逾期11天","rentTag":"","statTag":"","delayTag":"逾期11天","doingTag":"","orderTag":"","lockTag":"","areaTag":"公共区域","timeTag":"","onlineTag":"","contactTag":"","hoursTag":"","doing":0},{"id":"59","gid":"307864","yid":"1241","btype":"1","villageaddress":"#1241 天通苑西二区11-2208","roomnumber":"","status":"6","wholeRent":"2","rentFlag":0,"lat":"40.077585","lng":"116.415726","servicestarttime":"0","planservicetime":"1560211200","serviceEndTime":"0","onlineReservation":null,"duration":"18","stateTag":"","cleanarea":"2","suiteLock":"1","suiteSource":"蛋壳","servicePeriod":"0","channel":"1","distance":17492,"btypename":"双周保洁","statusname":"客户原因未完成","delay":"逾期6天","rentTag":"","statTag":"","delayTag":"逾期6天","doingTag":"","orderTag":"","lockTag":"","areaTag":"公共区域","timeTag":"","onlineTag":"","contactTag":"","hoursTag":"","doing":0},{"id":"64","gid":"307871","yid":"1244","btype":"1","villageaddress":"#1244 顶秀欣园东苑7-1905","roomnumber":"","status":"6","wholeRent":"2","rentFlag":0,"lat":"39.845188","lng":"116.433665","servicestarttime":"0","planservicetime":"1560211200","serviceEndTime":"0","onlineReservation":null,"duration":"16","stateTag":"","cleanarea":"2","suiteLock":"1","suiteSource":"蛋壳","servicePeriod":"0","channel":"1","distance":8581,"btypename":"双周保洁","statusname":"客户原因未完成","delay":"逾期6天","rentTag":"","statTag":"","delayTag":"逾期6天","doingTag":"","orderTag":"","lockTag":"","areaTag":"公共区域","timeTag":"","onlineTag":"","contactTag":"","hoursTag":"","doing":0},{"id":"68","gid":"307875","yid":"1246","btype":"1","villageaddress":"#1246 东会新村2-4-601","roomnumber":"","status":"6","wholeRent":"2","rentFlag":0,"lat":"39.913563","lng":"116.607189","servicestarttime":"0","planservicetime":"1560211200","serviceEndTime":"0","onlineReservation":null,"duration":"18","stateTag":"","cleanarea":"2","suiteLock":"1","suiteSource":"蛋壳","servicePeriod":"0","channel":"1","distance":14002,"btypename":"双周保洁","statusname":"客户原因未完成","delay":"逾期6天","rentTag":"","statTag":"","delayTag":"逾期6天","doingTag":"","orderTag":"","lockTag":"","areaTag":"公共区域","timeTag":"","onlineTag":"","contactTag":"","hoursTag":"","doing":0},{"id":"75","gid":"307892","yid":"1255","btype":"1","villageaddress":"#1255 金隅丽景园7-2-802","roomnumber":"","status":"6","wholeRent":"2","rentFlag":0,"lat":"39.930329","lng":"116.619791","servicestarttime":"0","planservicetime":"1560211200","serviceEndTime":"0","onlineReservation":null,"duration":"18","stateTag":"","cleanarea":"2","suiteLock":"1","suiteSource":"蛋壳","servicePeriod":"0","channel":"1","distance":15075,"btypename":"双周保洁","statusname":"客户原因未完成","delay":"逾期6天","rentTag":"","statTag":"","delayTag":"逾期6天","doingTag":"","orderTag":"","lockTag":"","areaTag":"公共区域","timeTag":"","onlineTag":"","contactTag":"","hoursTag":"","doing":0},{"id":"38","gid":"307837","yid":"1225","btype":"1","villageaddress":"#1225 农光东里28-504","roomnumber":"","status":"6","wholeRent":"2","rentFlag":0,"lat":"39.887436","lng":"116.470412","servicestarttime":"0","planservicetime":"1560268800","serviceEndTime":"0","onlineReservation":null,"duration":"18","stateTag":"","cleanarea":"2","suiteLock":"1","suiteSource":"蛋壳","servicePeriod":"0","channel":"1","distance":4470,"btypename":"双周保洁","statusname":"客户原因未完成","delay":"逾期5天","rentTag":"","statTag":"","delayTag":"逾期5天","doingTag":"","orderTag":"","lockTag":"","areaTag":"公共区域","timeTag":"","onlineTag":"","contactTag":"","hoursTag":"","doing":0},{"id":"44","gid":"307843","yid":"1228","btype":"1","villageaddress":"#1228 姚家园东里8号院7-1-602","roomnumber":"","status":"6","wholeRent":"2","rentFlag":0,"lat":"39.954732","lng":"116.524903","servicestarttime":"0","planservicetime":"1560268800","serviceEndTime":"0","onlineReservation":null,"duration":"18","stateTag":"","cleanarea":"2","suiteLock":"1","suiteSource":"蛋壳","servicePeriod":"0","channel":"1","distance":7847,"btypename":"双周保洁","statusname":"客户原因未完成","delay":"逾期5天","rentTag":"","statTag":"","delayTag":"逾期5天","doingTag":"","orderTag":"","lockTag":"","areaTag":"公共区域","timeTag":"","onlineTag":"","contactTag":"","hoursTag":"","doing":0}],"time":"03:33:33","delay":10,"toolData":{"dataTotal":[{"btypeName":"紧急保洁","num":1,"visitTime":[],"delayNum":1},{"btypeName":"日常保洁","num":1,"visitTime":["16点~18点上门"],"delayNum":1},{"btypeName":"退转换保洁","num":1,"visitTime":["请注意备齐床品"],"delayNum":1},{"btypeName":"维修后保洁","num":1,"visitTime":[],"delayNum":1},{"btypeName":"双周保洁","num":6,"visitTime":["18点~20点上门","18点~20点上门","18点~20点上门","16点~18点上门","18点~20点上门","18点~20点上门"],"delayNum":6}],"btypeNum":5},"backOrderNum":0}
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

    @Override
    public String toString() {
        return "WorkListBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "total=" + total +
                    ", time='" + time + '\'' +
                    ", delay=" + delay +
                    ", toolData=" + toolData +
                    ", backOrderNum=" + backOrderNum +
                    ", list=" + list +
                    '}';
        }

        /**
         * total : 10
         * list : [{"id":"56","gid":"307861","yid":"1238","btype":"5","villageaddress":"#1238 金第润苑A2-甲-801","roomnumber":"","status":"2","wholeRent":"1","rentFlag":"3","lat":"39.854273","lng":"116.44781","servicestarttime":"1560504589","planservicetime":"1560211200","serviceEndTime":"1560745506","onlineReservation":null,"duration":null,"stateTag":"","cleanarea":"3","suiteLock":"1","suiteSource":"蛋壳","servicePeriod":"0","channel":"1","distance":7537,"btypename":"紧急保洁","statusname":"待上门","delay":"逾期6天","rentTag":"整租","statTag":"已预定","delayTag":"逾期6天","doingTag":"进行中","orderTag":"","lockTag":"","areaTag":"整屋区域","timeTag":"","onlineTag":"","contactTag":"提前联系","hoursTag":"","doing":1},{"id":"43","gid":"307842","yid":"1228","btype":"2","villageaddress":"#1228 姚家园东里8号院7-1-602","roomnumber":"","status":"6","wholeRent":"2","rentFlag":"4","lat":"39.954732","lng":"116.524903","servicestarttime":"0","planservicetime":"1560268800","serviceEndTime":"0","onlineReservation":null,"duration":"18","stateTag":"","cleanarea":"2","suiteLock":"1","suiteSource":"蛋壳","servicePeriod":"0","channel":"1","distance":7847,"btypename":"退转换保洁","statusname":"客户原因未完成","delay":"逾期5天","rentTag":"","statTag":"","delayTag":"逾期5天","doingTag":"","orderTag":"","lockTag":"","areaTag":"","timeTag":"","onlineTag":"","contactTag":"","hoursTag":"","doing":0},{"id":"37","gid":"307836","yid":"1225","btype":"4","villageaddress":"#1225 农光东里28-504","roomnumber":"D","status":"6","wholeRent":"2","rentFlag":"2","lat":"39.887436","lng":"116.470412","servicestarttime":"0","planservicetime":"1560211200","serviceEndTime":"0","onlineReservation":null,"duration":"16","stateTag":"","cleanarea":"1","suiteLock":"1","suiteSource":"蛋壳","servicePeriod":"0","channel":"1","distance":4470,"btypename":"日常保洁","statusname":"客户原因未完成","delay":"逾期6天","rentTag":"","statTag":"D-可出租","delayTag":"逾期6天","doingTag":"","orderTag":"","lockTag":"","areaTag":"","timeTag":"16点~18点","onlineTag":"","contactTag":"","hoursTag":"","doing":0},{"id":"18","gid":"307813","yid":"1213","btype":"7","villageaddress":"#1213 金隅汇星苑1-2-2504","roomnumber":"","status":"6","wholeRent":"2","rentFlag":0,"lat":"39.913581","lng":"116.402464","servicestarttime":"0","planservicetime":"1559750400","serviceEndTime":"0","onlineReservation":null,"duration":null,"stateTag":"","cleanarea":"2","suiteLock":"1","suiteSource":"蛋壳","servicePeriod":"0","channel":"1","distance":3628,"btypename":"维修后保洁","statusname":"客户原因未完成","delay":"逾期11天","rentTag":"","statTag":"","delayTag":"逾期11天","doingTag":"","orderTag":"","lockTag":"","areaTag":"公共区域","timeTag":"","onlineTag":"","contactTag":"","hoursTag":"","doing":0},{"id":"59","gid":"307864","yid":"1241","btype":"1","villageaddress":"#1241 天通苑西二区11-2208","roomnumber":"","status":"6","wholeRent":"2","rentFlag":0,"lat":"40.077585","lng":"116.415726","servicestarttime":"0","planservicetime":"1560211200","serviceEndTime":"0","onlineReservation":null,"duration":"18","stateTag":"","cleanarea":"2","suiteLock":"1","suiteSource":"蛋壳","servicePeriod":"0","channel":"1","distance":17492,"btypename":"双周保洁","statusname":"客户原因未完成","delay":"逾期6天","rentTag":"","statTag":"","delayTag":"逾期6天","doingTag":"","orderTag":"","lockTag":"","areaTag":"公共区域","timeTag":"","onlineTag":"","contactTag":"","hoursTag":"","doing":0},{"id":"64","gid":"307871","yid":"1244","btype":"1","villageaddress":"#1244 顶秀欣园东苑7-1905","roomnumber":"","status":"6","wholeRent":"2","rentFlag":0,"lat":"39.845188","lng":"116.433665","servicestarttime":"0","planservicetime":"1560211200","serviceEndTime":"0","onlineReservation":null,"duration":"16","stateTag":"","cleanarea":"2","suiteLock":"1","suiteSource":"蛋壳","servicePeriod":"0","channel":"1","distance":8581,"btypename":"双周保洁","statusname":"客户原因未完成","delay":"逾期6天","rentTag":"","statTag":"","delayTag":"逾期6天","doingTag":"","orderTag":"","lockTag":"","areaTag":"公共区域","timeTag":"","onlineTag":"","contactTag":"","hoursTag":"","doing":0},{"id":"68","gid":"307875","yid":"1246","btype":"1","villageaddress":"#1246 东会新村2-4-601","roomnumber":"","status":"6","wholeRent":"2","rentFlag":0,"lat":"39.913563","lng":"116.607189","servicestarttime":"0","planservicetime":"1560211200","serviceEndTime":"0","onlineReservation":null,"duration":"18","stateTag":"","cleanarea":"2","suiteLock":"1","suiteSource":"蛋壳","servicePeriod":"0","channel":"1","distance":14002,"btypename":"双周保洁","statusname":"客户原因未完成","delay":"逾期6天","rentTag":"","statTag":"","delayTag":"逾期6天","doingTag":"","orderTag":"","lockTag":"","areaTag":"公共区域","timeTag":"","onlineTag":"","contactTag":"","hoursTag":"","doing":0},{"id":"75","gid":"307892","yid":"1255","btype":"1","villageaddress":"#1255 金隅丽景园7-2-802","roomnumber":"","status":"6","wholeRent":"2","rentFlag":0,"lat":"39.930329","lng":"116.619791","servicestarttime":"0","planservicetime":"1560211200","serviceEndTime":"0","onlineReservation":null,"duration":"18","stateTag":"","cleanarea":"2","suiteLock":"1","suiteSource":"蛋壳","servicePeriod":"0","channel":"1","distance":15075,"btypename":"双周保洁","statusname":"客户原因未完成","delay":"逾期6天","rentTag":"","statTag":"","delayTag":"逾期6天","doingTag":"","orderTag":"","lockTag":"","areaTag":"公共区域","timeTag":"","onlineTag":"","contactTag":"","hoursTag":"","doing":0},{"id":"38","gid":"307837","yid":"1225","btype":"1","villageaddress":"#1225 农光东里28-504","roomnumber":"","status":"6","wholeRent":"2","rentFlag":0,"lat":"39.887436","lng":"116.470412","servicestarttime":"0","planservicetime":"1560268800","serviceEndTime":"0","onlineReservation":null,"duration":"18","stateTag":"","cleanarea":"2","suiteLock":"1","suiteSource":"蛋壳","servicePeriod":"0","channel":"1","distance":4470,"btypename":"双周保洁","statusname":"客户原因未完成","delay":"逾期5天","rentTag":"","statTag":"","delayTag":"逾期5天","doingTag":"","orderTag":"","lockTag":"","areaTag":"公共区域","timeTag":"","onlineTag":"","contactTag":"","hoursTag":"","doing":0},{"id":"44","gid":"307843","yid":"1228","btype":"1","villageaddress":"#1228 姚家园东里8号院7-1-602","roomnumber":"","status":"6","wholeRent":"2","rentFlag":0,"lat":"39.954732","lng":"116.524903","servicestarttime":"0","planservicetime":"1560268800","serviceEndTime":"0","onlineReservation":null,"duration":"18","stateTag":"","cleanarea":"2","suiteLock":"1","suiteSource":"蛋壳","servicePeriod":"0","channel":"1","distance":7847,"btypename":"双周保洁","statusname":"客户原因未完成","delay":"逾期5天","rentTag":"","statTag":"","delayTag":"逾期5天","doingTag":"","orderTag":"","lockTag":"","areaTag":"公共区域","timeTag":"","onlineTag":"","contactTag":"","hoursTag":"","doing":0}]
         * time : 03:33:33
         * delay : 10
         * toolData : {"dataTotal":[{"btypeName":"紧急保洁","num":1,"visitTime":[],"delayNum":1},{"btypeName":"日常保洁","num":1,"visitTime":["16点~18点上门"],"delayNum":1},{"btypeName":"退转换保洁","num":1,"visitTime":["请注意备齐床品"],"delayNum":1},{"btypeName":"维修后保洁","num":1,"visitTime":[],"delayNum":1},{"btypeName":"双周保洁","num":6,"visitTime":["18点~20点上门","18点~20点上门","18点~20点上门","16点~18点上门","18点~20点上门","18点~20点上门"],"delayNum":6}],"btypeNum":5}
         * backOrderNum : 0
         */

        private int total;
        private String time;
        private int delay;
        private ToolDataBean toolData;
        private int backOrderNum;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getDelay() {
            return delay;
        }

        public void setDelay(int delay) {
            this.delay = delay;
        }

        public ToolDataBean getToolData() {
            return toolData;
        }

        public void setToolData(ToolDataBean toolData) {
            this.toolData = toolData;
        }

        public int getBackOrderNum() {
            return backOrderNum;
        }

        public void setBackOrderNum(int backOrderNum) {
            this.backOrderNum = backOrderNum;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ToolDataBean {
            @Override
            public String toString() {
                return "ToolDataBean{" +
                        "btypeNum=" + btypeNum +
                        ", dataTotal=" + dataTotal +
                        '}';
            }

            /**
             * dataTotal : [{"btypeName":"紧急保洁","num":1,"visitTime":[],"delayNum":1},{"btypeName":"日常保洁","num":1,"visitTime":["16点~18点上门"],"delayNum":1},{"btypeName":"退转换保洁","num":1,"visitTime":["请注意备齐床品"],"delayNum":1},{"btypeName":"维修后保洁","num":1,"visitTime":[],"delayNum":1},{"btypeName":"双周保洁","num":6,"visitTime":["18点~20点上门","18点~20点上门","18点~20点上门","16点~18点上门","18点~20点上门","18点~20点上门"],"delayNum":6}]
             * btypeNum : 5
             */

            private int btypeNum;
            private List<DataTotalBean> dataTotal;

            public int getBtypeNum() {
                return btypeNum;
            }

            public void setBtypeNum(int btypeNum) {
                this.btypeNum = btypeNum;
            }

            public List<DataTotalBean> getDataTotal() {
                return dataTotal;
            }

            public void setDataTotal(List<DataTotalBean> dataTotal) {
                this.dataTotal = dataTotal;
            }

            public static class DataTotalBean {
                /**
                 * btypeName : 紧急保洁
                 * num : 1
                 * visitTime : []
                 * delayNum : 1
                 */

                private String btypeName;
                private int num;
                private int delayNum;
                private List<String> visitTime;

                public String getBtypeName() {
                    return btypeName;
                }

                public void setBtypeName(String btypeName) {
                    this.btypeName = btypeName;
                }

                public int getNum() {
                    return num;
                }

                public void setNum(int num) {
                    this.num = num;
                }

                public int getDelayNum() {
                    return delayNum;
                }

                public void setDelayNum(int delayNum) {
                    this.delayNum = delayNum;
                }

                public List<String> getVisitTime() {
                    return visitTime;
                }

                public void setVisitTime(List<String> visitTime) {
                    this.visitTime = visitTime;
                }

                @Override
                public String toString() {
                    return "DataTotalBean{" +
                            "btypeName='" + btypeName + '\'' +
                            ", num=" + num +
                            ", delayNum=" + delayNum +
                            ", visitTime=" + visitTime +
                            '}';
                }
            }
        }

        public static class ListBean {
            @Override
            public String toString() {
                return "ListBean{" +
                        "id='" + id + '\'' +
                        ", gid='" + gid + '\'' +
                        ", yid='" + yid + '\'' +
                        ", btype='" + btype + '\'' +
                        ", villageaddress='" + villageaddress + '\'' +
                        ", roomnumber='" + roomnumber + '\'' +
                        ", status='" + status + '\'' +
                        ", wholeRent='" + wholeRent + '\'' +
                        ", rentFlag='" + rentFlag + '\'' +
                        ", lat='" + lat + '\'' +
                        ", lng='" + lng + '\'' +
                        ", servicestarttime='" + servicestarttime + '\'' +
                        ", planservicetime='" + planservicetime + '\'' +
                        ", serviceEndTime='" + serviceEndTime + '\'' +
                        ", onlineReservation=" + onlineReservation +
                        ", duration=" + duration +
                        ", stateTag='" + stateTag + '\'' +
                        ", cleanarea='" + cleanarea + '\'' +
                        ", suiteLock='" + suiteLock + '\'' +
                        ", suiteSource='" + suiteSource + '\'' +
                        ", servicePeriod='" + servicePeriod + '\'' +
                        ", channel='" + channel + '\'' +
                        ", distance=" + distance +
                        ", btypename='" + btypename + '\'' +
                        ", statusname='" + statusname + '\'' +
                        ", delay='" + delay + '\'' +
                        ", rentTag='" + rentTag + '\'' +
                        ", statTag='" + statTag + '\'' +
                        ", delayTag='" + delayTag + '\'' +
                        ", doingTag='" + doingTag + '\'' +
                        ", orderTag='" + orderTag + '\'' +
                        ", lockTag='" + lockTag + '\'' +
                        ", areaTag='" + areaTag + '\'' +
                        ", timeTag='" + timeTag + '\'' +
                        ", onlineTag='" + onlineTag + '\'' +
                        ", contactTag='" + contactTag + '\'' +
                        ", hoursTag='" + hoursTag + '\'' +
                        ", doing=" + doing +
                        '}';
            }

            /**
             * id : 56
             * gid : 307861
             * yid : 1238
             * btype : 5
             * villageaddress : #1238 金第润苑A2-甲-801
             * roomnumber :
             * status : 2
             * wholeRent : 1
             * rentFlag : 3
             * lat : 39.854273
             * lng : 116.44781
             * servicestarttime : 1560504589
             * planservicetime : 1560211200
             * serviceEndTime : 1560745506
             * onlineReservation : null
             * duration : null
             * stateTag :
             * cleanarea : 3
             * suiteLock : 1
             * suiteSource : 蛋壳
             * servicePeriod : 0
             * channel : 1
             * distance : 7537
             * btypename : 紧急保洁
             * statusname : 待上门
             * delay : 逾期6天
             * rentTag : 整租
             * statTag : 已预定
             * delayTag : 逾期6天
             * doingTag : 进行中
             * orderTag :
             * lockTag :
             * areaTag : 整屋区域
             * timeTag :
             * onlineTag :
             * contactTag : 提前联系
             * hoursTag :
             * doing : 1
             */

            private String id;
            private String gid;
            private String yid;
            private String btype;
            private String villageaddress;
            private String roomnumber;
            private String status;
            private String wholeRent;
            private String rentFlag;
            private String lat;
            private String lng;
            private String servicestarttime;
            private String planservicetime;
            private String serviceEndTime;
            private Object onlineReservation;
            private Object duration;
            private String stateTag;
            private String cleanarea;
            private String suiteLock;
            private String suiteSource;
            private String servicePeriod;
            private String channel;
            private int distance;
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

            public void setBatteryStatus(String batteryStatus) {
                this.batteryStatus = batteryStatus;
            }

            private int doing;

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

            public String getRentFlag() {
                return rentFlag;
            }

            public void setRentFlag(String rentFlag) {
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

            public Object getDuration() {
                return duration;
            }

            public void setDuration(Object duration) {
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

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
                this.distance = distance;
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
