package com.lianjie.andorid.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.security.acl.Permission;
import java.util.List;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/15
 * Time: 9:48
 */
public class SubTwoPhotoBean implements Serializable {

    /**
     * code : 1000
     * msg : 获取信息成功
     * data : {"btype":"8","list":[{"id":"16","catname":"通用","num":"5","list":[]}],"allow":2,"upload":{"type":2,"sign":[]}}
     */

    private int code;
    private String msg;
    private DataBean data;

    @Override
    public String toString() {
        return "SubTwoPhotoBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
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

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "btype='" + btype + '\'' +
                    ", allow=" + allow +
                    ", upload=" + upload +
                    ", list=" + list +
                    '}';
        }

        /**
         * btype : 8
         * list : [{"id":"16","catname":"通用","num":"5","list":[]}]
         * allow : 2
         * upload : {"type":2,"sign":[]}
         */

        private String btype;
        private int allow;
        private UploadBean upload;
        private List<ListBean> list;
        private AboutReminderList aboutReminderList;

        public AboutReminderList getAboutReminderList() {
            return aboutReminderList;
        }

        public void setAboutReminderList(AboutReminderList aboutReminderList) {
            this.aboutReminderList = aboutReminderList;
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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class UploadBean {
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
        public static class ListBean {
            /**
             * id : 16
             * catname : 通用
             * num : 5
             * list : []
             */

            private String id;
            private String catname;
            private String num;
            private List<String> list;
            private List<String> examples;

            public List<String> getExamples() {
                return examples;
            }

            public void setExamples(List<String> examples) {
                this.examples = examples;
            }

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

            public List<String> getList() {
                return list;
            }

            public void setList(List<String> list) {
                this.list = list;
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "id='" + id + '\'' +
                        ", catname='" + catname + '\'' +
                        ", num='" + num + '\'' +
                        ", list=" + list +
                        '}';
            }
        }
    }

}
