package com.lianjie.andorid.bean;

import java.util.List;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/13
 * Time: 17:29
 */
public class SubMitaudNoshiliBean {

    /**
     * code : 1000
     * msg : 获取信息成功
     * data : {"btype":"3","list":[{"id":15,"catname":"完成照片","num":0,"list":[]}],"allow":2,"upload":{"type":2,"sign":[]}}
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
         * btype : 3
         * list : [{"id":15,"catname":"完成照片","num":0,"list":[]}]
         * allow : 2
         * upload : {"type":2,"sign":[]}
         */

        private String btype;
        private int allow;
        private UploadBean upload;
        private List<ListBean> list;

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

        public static class ListBean {
            /**
             * id : 15
             * catname : 完成照片
             * num : 0
             * list : []
             */

            private int id;
            private String catname;
            private int num;
            private List<?> list;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCatname() {
                return catname;
            }

            public void setCatname(String catname) {
                this.catname = catname;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public List<?> getList() {
                return list;
            }

            public void setList(List<?> list) {
                this.list = list;
            }
        }
    }
}
