package com.lianjie.andorid.bean;

import java.util.List;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/18
 * Time: 10:47
 */
public class MsgPushBean {

    /**
     * code : 1000
     * msg : 成功
     * data : {"closeFlag":1,"type":"list","data":[{"id":"7032","status":"2","createtime":"2019-07-18 16:16:49","content":"ssssssssssssssssss","title":"sssssssssssss","images":["http://oss.lianjieshenghuo.com/public/uploads/2019/07/5d25a1d71545c6266.png"],"videos":[]},{"id":"7033","status":"2","createtime":"2019-07-18 16:16:49","content":"nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn","title":"nnnnnnnnnnnnnnnnnnn","images":["http://oss.lianjieshenghuo.com/public/uploads/video/2019/07/20190710163124_5d25a25c2e11f.mp4","http://oss.lianjieshenghuo.com/public/uploads/2019/07/5d25a2607e5db4067.png"],"videos":[]},{"id":"7039","status":"2","createtime":"2019-07-18 16:16:49","content":"水电费技术多了几分代理商","title":"消息推送哈哈多好","images":["http://oss.lianjieshenghuo.com/public/uploads/2019/07/5d2fcb6e3bdef1807.png"],"videos":[]}]}
     */

    private int code;
    private String msg;
    private DataBeanX data;

    @Override
    public String toString() {
        return "MsgPushBean{" +
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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        @Override
        public String toString() {
            return "DataBeanX{" +
                    "closeFlag=" + closeFlag +
                    ", type='" + type + '\'' +
                    ", data=" + data +
                    '}';
        }

        /**
         * closeFlag : 1
         * type : list
         * data : [{"id":"7032","status":"2","createtime":"2019-07-18 16:16:49","content":"ssssssssssssssssss","title":"sssssssssssss","images":["http://oss.lianjieshenghuo.com/public/uploads/2019/07/5d25a1d71545c6266.png"],"videos":[]},{"id":"7033","status":"2","createtime":"2019-07-18 16:16:49","content":"nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn","title":"nnnnnnnnnnnnnnnnnnn","images":["http://oss.lianjieshenghuo.com/public/uploads/video/2019/07/20190710163124_5d25a25c2e11f.mp4","http://oss.lianjieshenghuo.com/public/uploads/2019/07/5d25a2607e5db4067.png"],"videos":[]},{"id":"7039","status":"2","createtime":"2019-07-18 16:16:49","content":"水电费技术多了几分代理商","title":"消息推送哈哈多好","images":["http://oss.lianjieshenghuo.com/public/uploads/2019/07/5d2fcb6e3bdef1807.png"],"videos":[]}]
         */

        private int closeFlag;
        private String type;
        private List<DataBean> data;

        public int getCloseFlag() {
            return closeFlag;
        }

        public void setCloseFlag(int closeFlag) {
            this.closeFlag = closeFlag;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            @Override
            public String toString() {
                return "DataBean{" +
                        "id='" + id + '\'' +
                        ", status='" + status + '\'' +
                        ", createtime='" + createtime + '\'' +
                        ", content='" + content + '\'' +
                        ", title='" + title + '\'' +
                        ", images=" + images +
                        ", videos=" + videos +
                        '}';
            }

            /**
             * id : 7032
             * status : 2
             * createtime : 2019-07-18 16:16:49
             * content : ssssssssssssssssss
             * title : sssssssssssss
             * images : ["http://oss.lianjieshenghuo.com/public/uploads/2019/07/5d25a1d71545c6266.png"]
             * videos : []
             */

            private String id;
            private String status;
            private String createtime;
            private String content;
            private String title;
            private List<String> images;
            private List<String> videos;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
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

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }

            public List<String> getVideos() {
                return videos;
            }

            public void setVideos(List<String> videos) {
                this.videos = videos;
            }
        }
    }
}
