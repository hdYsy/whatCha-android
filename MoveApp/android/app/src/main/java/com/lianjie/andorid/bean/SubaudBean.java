package com.lianjie.andorid.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/5
 * Time: 16:58
 */
public class SubaudBean implements Serializable{

    @Override
    public String toString() {
        return "SubaudBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * code : 1000
     * msg : 获取信息成功
     * data : {"btype":"1","list":[{"id":"11","catname":"通用","num":"5","exlist":[["http://oss.lianjieshenghuo.com/public/photo-example/default/1.jpg","","http://oss.lianjieshenghuo.com/public/photo-example/default/1.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example/default/2.jpg","","http://oss.lianjieshenghuo.com/public/photo-example/default/2.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example/default/3.jpg","","http://oss.lianjieshenghuo.com/public/photo-example/default/3.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example/default/4.jpg","","http://oss.lianjieshenghuo.com/public/photo-example/default/4.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example/default/5.png","","http://oss.lianjieshenghuo.com/public/photo-example/default/5.png?x-oss-process=image/quality,q_30/resize,h_156,w_156"]],"required":"1","type":"1"},{"id":"12","catname":"厨房","num":"11","exlist":[["http://oss.lianjieshenghuo.com/public/photo-example-new/room/11.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/11.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/room/12.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/12.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/room/13.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/13.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"]],"required":"1","type":"3"},{"id":"13","catname":"客厅","num":"5","exlist":[["http://oss.lianjieshenghuo.com/public/photo-example-new/room/11.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/11.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/room/12.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/12.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/room/13.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/13.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"]],"required":"1","type":"2"},{"id":"14","catname":"卫生间","num":"15","exlist":[["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/11.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/11.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/12.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/12.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/13.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/13.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/14.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/14.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/15.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/15.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/20.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/20.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/21.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/21.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/16.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/16.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/17.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/17.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/18.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/18.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/19.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/19.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/110.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/110.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/111.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/111.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/112.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/112.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/113.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/113.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"]],"required":"1","type":"4"}],"allow":1,"upload":{"type":2,"sign":[]},"multiService":{"str":"去添加","flag":1}}
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
        @Override
        public String toString() {
            return "DataBean{" +
                    "btype='" + btype + '\'' +
                    ", allow=" + allow +
                    ", upload=" + upload +
                    ", multiService=" + multiService +
                    ", list=" + list +
                    '}';
        }

        /**
         * btype : 1
         * list : [{"id":"11","catname":"通用","num":"5","exlist":[["http://oss.lianjieshenghuo.com/public/photo-example/default/1.jpg","","http://oss.lianjieshenghuo.com/public/photo-example/default/1.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example/default/2.jpg","","http://oss.lianjieshenghuo.com/public/photo-example/default/2.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example/default/3.jpg","","http://oss.lianjieshenghuo.com/public/photo-example/default/3.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example/default/4.jpg","","http://oss.lianjieshenghuo.com/public/photo-example/default/4.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example/default/5.png","","http://oss.lianjieshenghuo.com/public/photo-example/default/5.png?x-oss-process=image/quality,q_30/resize,h_156,w_156"]],"required":"1","type":"1"},{"id":"12","catname":"厨房","num":"11","exlist":[["http://oss.lianjieshenghuo.com/public/photo-example-new/room/11.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/11.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/room/12.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/12.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/room/13.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/13.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"]],"required":"1","type":"3"},{"id":"13","catname":"客厅","num":"5","exlist":[["http://oss.lianjieshenghuo.com/public/photo-example-new/room/11.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/11.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/room/12.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/12.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/room/13.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/13.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/room/14.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"]],"required":"1","type":"2"},{"id":"14","catname":"卫生间","num":"15","exlist":[["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/11.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/11.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/12.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/12.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/13.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/13.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/14.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/14.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/15.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/15.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/20.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/20.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/21.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/21.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/16.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/16.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/17.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/17.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/18.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/18.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/19.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/19.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/110.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/110.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/111.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/111.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/112.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/112.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/113.jpg","","http://oss.lianjieshenghuo.com/public/photo-example-new/toilet/113.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"]],"required":"1","type":"4"}]
         * allow : 1
         * upload : {"type":2,"sign":[]}
         * multiService : {"str":"去添加","flag":1}
         */

        private String btype;
        private int allow;
        private UploadBean upload;
        private MultiServiceBean multiService;
        private List<ListBean> list;
        private AboutReminderList aboutReminderList;

        public AboutReminderList getAboutReminderList() {
            return aboutReminderList;
        }

        public void setAboutReminderList(AboutReminderList aboutReminderList) {
            this.aboutReminderList = aboutReminderList;
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


        public String getBtype() {
            return btype;
        }

        public void setBtype(String btype) {
            this.btype = btype;
        }

        public int getAllow() {
            return allow;
        }

        public void setAllow(int allow) {
            this.allow = allow;
        }

        public UploadBean getUpload() {
            return upload;
        }

        public void setUpload(UploadBean upload) {
            this.upload = upload;
        }

        public MultiServiceBean getMultiService() {
            return multiService;
        }

        public void setMultiService(MultiServiceBean multiService) {
            this.multiService = multiService;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class UploadBean {
            @Override
            public String toString() {
                return "UploadBean{" +
                        "type=" + type +
                        ", sign=" + sign +
                        '}';
            }

            /**
             * type : 2
             * sign : []
             */

            private int type;
            private List<?> sign;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public List<?> getSign() {
                return sign;
            }

            public void setSign(List<?> sign) {
                this.sign = sign;
            }
        }

        public static class MultiServiceBean {
            @Override
            public String toString() {
                return "MultiServiceBean{" +
                        "str='" + str + '\'' +
                        ", flag=" + flag +
                        '}';
            }

            /**
             * str : 去添加
             * flag : 1
             */

            private String str;
            private int flag;

            public String getStr() {
                return str;
            }

            public void setStr(String str) {
                this.str = str;
            }

            public int getFlag() {
                return flag;
            }

            public void setFlag(int flag) {
                this.flag = flag;
            }
        }

        public static class ListBean {
            @Override
            public String toString() {
                return "ListBean{" +
                        "id='" + id + '\'' +
                        ", catname='" + catname + '\'' +
                        ", num='" + num + '\'' +
                        ", required='" + required + '\'' +
                        ", type='" + type + '\'' +
                        ", exlist=" + exlist +
                        '}';
            }

            /**
             * id : 11
             * catname : 通用
             * num : 5
             * exlist : [["http://oss.lianjieshenghuo.com/public/photo-example/default/1.jpg","","http://oss.lianjieshenghuo.com/public/photo-example/default/1.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example/default/2.jpg","","http://oss.lianjieshenghuo.com/public/photo-example/default/2.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example/default/3.jpg","","http://oss.lianjieshenghuo.com/public/photo-example/default/3.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example/default/4.jpg","","http://oss.lianjieshenghuo.com/public/photo-example/default/4.jpg?x-oss-process=image/quality,q_30/resize,h_156,w_156"],["http://oss.lianjieshenghuo.com/public/photo-example/default/5.png","","http://oss.lianjieshenghuo.com/public/photo-example/default/5.png?x-oss-process=image/quality,q_30/resize,h_156,w_156"]]
             * required : 1
             * type : 1
             */

            private String id;
            private String catname;
            private String num;
            private String required;
            private String type;
            private List<List<String>> exlist;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCatname() {
                return catname;
            }

            public void setCatname(String catname) {
                this.catname = catname;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getRequired() {
                return required;
            }

            public void setRequired(String required) {
                this.required = required;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<List<String>> getExlist() {
                return exlist;
            }

            public void setExlist(List<List<String>> exlist) {
                this.exlist = exlist;
            }
        }
    }
}
