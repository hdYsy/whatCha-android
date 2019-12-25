package com.lianjie.andorid.bean;

import java.util.List;

/**
 * Created by My on 2019/11/19.
 */

public class LodSizeImageBean {

    /**
     * code : 1000
     * msg : success
     * data : {"url":"http://oss.lianjieshenghuo.com/public/uploads/2019/11/19/8252012/20191119112429_5dd3606dc71fb.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0xMS0xOSAxMToyNDozMw==,color_ffffff,shadow_100/watermark,text_5pyq55-l5L2N572u,color_ffffff,size_20,shadow_100","isDiscernSuccess":2,"identificationData":[]}
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
         * url : http://oss.lianjieshenghuo.com/public/uploads/2019/11/19/8252012/20191119112429_5dd3606dc71fb.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0xMS0xOSAxMToyNDozMw==,color_ffffff,shadow_100/watermark,text_5pyq55-l5L2N572u,color_ffffff,size_20,shadow_100
         * isDiscernSuccess : 2
         * identificationData : []
         */

        private String url;
        private int isDiscernSuccess;//1和 2  1：识别成功  2：识别失败
        private List<Bean_Success> identificationData;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getIsDiscernSuccess() {
            return isDiscernSuccess;
        }

        public void setIsDiscernSuccess(int isDiscernSuccess) {
            this.isDiscernSuccess = isDiscernSuccess;
        }

        public List<Bean_Success> getIdentificationData() {
            return identificationData;
        }

        public void setIdentificationData(List<Bean_Success> identificationData) {
            this.identificationData = identificationData;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "url='" + url + '\'' +
                    ", isDiscernSuccess=" + isDiscernSuccess +
                    ", identificationData=" + identificationData +
                    '}';
        }
        public static class Bean_Success {
          private int  classId;
          private String className;
          private int  imgIndex;

            public int getClassid() {
                return classId;
            }

            public void setClassid(int classid) {
                this.classId = classid;
            }

            public String getClassName() {
                return className;
            }

            public void setClassName(String className) {
                this.className = className;
            }

            public int getImgindex() {
                return imgIndex;
            }

            public void setImgindex(int imgindex) {
                this.imgIndex = imgindex;
            }
        }
    }

    @Override
    public String toString() {
        return "LodSizeImageBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
