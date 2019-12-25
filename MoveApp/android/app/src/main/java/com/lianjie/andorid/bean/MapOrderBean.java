package com.lianjie.andorid.bean;

import java.util.List;

/**
 * Created by My on 2019/12/9.
 */

public class MapOrderBean {

    /**
     * code : 1000
     * msg : 获取数据成功
     * data : {"total":1,"list":[{"id":"8253049","yid":"143487","btype":"13","channel":"1","lat":"40.171201","lng":"116.265999"}],"isLine":0}
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
         * total : 1
         * list : [{"id":"8253049","yid":"143487","btype":"13","channel":"1","lat":"40.171201","lng":"116.265999"}]
         * isLine : 0
         */

        private int total;
        private int isLine;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getIsLine() {
            return isLine;
        }

        public void setIsLine(int isLine) {
            this.isLine = isLine;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 8253049
             * yid : 143487
             * btype : 13
             * channel : 1
             * lat : 40.171201
             * lng : 116.265999
             */

            private String id;
            private String yid;
            private String btype;
            private String channel;
            private String lat;
            private String lng;

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

            public String getBtype() {
                return btype;
            }

            public void setBtype(String btype) {
                this.btype = btype;
            }

            public String getChannel() {
                return channel;
            }

            public void setChannel(String channel) {
                this.channel = channel;
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
        }
    }
}
