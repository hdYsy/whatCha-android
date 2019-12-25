package com.lianjie.andorid.bean;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/3
 * Time: 15:48
 */
public class LoadImage {

    /**
     * code : 1000
     * msg : success
     * data : {"url":"http://oss.lianjieshenghuo.com/public/uploads/2019/07/03/20190703154748_5d1c5da4b8562.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wNy0wMyAxNTo0Nzo1Nw==,color_ffffff,shadow_100/watermark,text_5pyq6I635Y-W5Yiw57uP57qs5bqm5L-h5oGv,color_ffffff,size_20,shadow_100"}
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
         * url : http://oss.lianjieshenghuo.com/public/uploads/2019/07/03/20190703154748_5d1c5da4b8562.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wNy0wMyAxNTo0Nzo1Nw==,color_ffffff,shadow_100/watermark,text_5pyq6I635Y-W5Yiw57uP57qs5bqm5L-h5oGv,color_ffffff,size_20,shadow_100
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
