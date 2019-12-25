package com.lianjie.andorid.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by My on 2019/11/22.
 */

public class VideoDelatilsBean {

    /**
     * code : 1000
     * msg : 获取培训资料成功
     * data : {"train":{"id":"20","filetype":"1","type":["公司简介","人员礼仪","工资制度","工具介绍","服务内容","服务流程"],"status":"1","author":"小号","title":"93.4MB视频","content":"http://oss.lianjieshenghuo.com/public/uploads/video/2019/08/20190805144226_5d47cfd237e2b.mp4","createtime":"2019-08-05 14:45:36"},"trainRead":{"end":"3","long":"0","createtime":""}}
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
         * train : {"id":"20","filetype":"1","type":["公司简介","人员礼仪","工资制度","工具介绍","服务内容","服务流程"],"status":"1","author":"小号","title":"93.4MB视频","content":"http://oss.lianjieshenghuo.com/public/uploads/video/2019/08/20190805144226_5d47cfd237e2b.mp4","createtime":"2019-08-05 14:45:36"}
         * trainRead : {"end":"3","long":"0","createtime":""}
         */

        private TrainBean train;
        private TrainReadBean trainRead;

        public TrainBean getTrain() {
            return train;
        }

        public void setTrain(TrainBean train) {
            this.train = train;
        }

        public TrainReadBean getTrainRead() {
            return trainRead;
        }

        public void setTrainRead(TrainReadBean trainRead) {
            this.trainRead = trainRead;
        }

        public static class TrainBean {
            /**
             * id : 20
             * filetype : 1
             * type : ["公司简介","人员礼仪","工资制度","工具介绍","服务内容","服务流程"]
             * status : 1
             * author : 小号
             * title : 93.4MB视频
             * content : http://oss.lianjieshenghuo.com/public/uploads/video/2019/08/20190805144226_5d47cfd237e2b.mp4
             * createtime : 2019-08-05 14:45:36
             */

            private String id;
            private String filetype;
            private String status;
            private String author;
            private String title;
            private String content;
            private String createtime;
            private List<String> type;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getFiletype() {
                return filetype;
            }

            public void setFiletype(String filetype) {
                this.filetype = filetype;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public List<String> getType() {
                return type;
            }

            public void setType(List<String> type) {
                this.type = type;
            }
        }

        public static class TrainReadBean {
            /**
             * end : 3
             * long : 0
             * createtime :
             */

            private String end;
            @SerializedName("long")
            private String longX;
            private String createtime;

            public String getEnd() {
                return end;
            }

            public void setEnd(String end) {
                this.end = end;
            }

            public String getLongX() {
                return longX;
            }

            public void setLongX(String longX) {
                this.longX = longX;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }
        }
    }
}
