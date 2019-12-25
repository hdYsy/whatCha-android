package com.lianjie.andorid.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by My on 2019/11/22.
 */

public class VideoListBean implements Serializable{

    /**
     * code : 1000
     * msg : 获取培训资料成功
     * data : {"videoTrainLists":[{"courseId":"20","title":"93.4MB视频","type":["公司简介","人员礼仪","工资制度","工具介绍","服务内容","服务流程"],"filetype":"1","releaseTime":"2019-08-05 14:45:36","releasePerson":"小号","status":"3"},{"courseId":"36","title":"保洁服务要求","type":["服务流程","工具介绍","服务内容"],"filetype":"1","releaseTime":"2019-08-05 16:57:10","releasePerson":"好笑","status":"3"},{"courseId":"37","title":"71.6MB视频","type":["人员礼仪","工资制度"],"filetype":"1","releaseTime":"2019-08-05 17:01:34","releasePerson":"陈好","status":"3"},{"courseId":"38","title":"公司主要产品介绍","type":["公司简介"],"filetype":"1","releaseTime":"2019-08-05 17:09:26","releasePerson":"陈扬","status":"3"},{"courseId":"39","title":"1","type":["公司简介","人员礼仪"],"filetype":"1","releaseTime":"2019-08-06 11:44:41","releasePerson":"2","status":"3"},{"courseId":"44","title":"范","type":["公司简介"],"filetype":"1","releaseTime":"2019-10-12 18:10:48","releasePerson":"啊","status":"3"}],"imageTextTrainLists":[{"courseId":"13","title":"7月案例","type":["公司简介","人员礼仪","工资制度","工具介绍"],"filetype":"2","releaseTime":"2019-08-03 15:12:35","releasePerson":"张炎","status":"3"},{"courseId":"15","title":"8月保洁培训","type":["工具介绍"],"filetype":"2","releaseTime":"2019-08-03 15:23:09","releasePerson":"瑞杰","status":"3"},{"courseId":"22","title":"晚霞中的城","type":["服务内容"],"filetype":"2","releaseTime":"2019-08-05 14:51:36","releasePerson":"史蒂夫","status":"3"},{"courseId":"40","title":"2","type":["公司简介"],"filetype":"2","releaseTime":"2019-08-06 11:45:05","releasePerson":"1","status":"3"},{"courseId":"42","title":"认识他很高","type":["公司简介"],"filetype":"2","releaseTime":"2019-08-21 16:27:04","releasePerson":"电饭锅","status":"3"},{"courseId":"43","title":"撒旦法","type":["公司简介"],"filetype":"2","releaseTime":"2019-10-12 18:08:07","releasePerson":"复试","status":"3"}]}
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
        private List<VideoTrainListsBean> videoTrainLists;
        private List<ImageTextTrainListsBean> imageTextTrainLists;

        public List<VideoTrainListsBean> getVideoTrainLists() {
            return videoTrainLists;
        }

        public void setVideoTrainLists(List<VideoTrainListsBean> videoTrainLists) {
            this.videoTrainLists = videoTrainLists;
        }

        public List<ImageTextTrainListsBean> getImageTextTrainLists() {
            return imageTextTrainLists;
        }

        public void setImageTextTrainLists(List<ImageTextTrainListsBean> imageTextTrainLists) {
            this.imageTextTrainLists = imageTextTrainLists;
        }

        public static class VideoTrainListsBean  {
            /**
             * courseId : 20
             * title : 93.4MB视频
             * type : ["公司简介","人员礼仪","工资制度","工具介绍","服务内容","服务流程"]
             * filetype : 1
             * releaseTime : 2019-08-05 14:45:36
             * releasePerson : 小号
             * status : 3
             */

            private String courseId;
            private String title;
            private String filetype;
            private String releaseTime;
            private String releasePerson;
            private String status;
            private List<String> type;

            public String getCourseId() {
                return courseId;
            }

            public void setCourseId(String courseId) {
                this.courseId = courseId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getFiletype() {
                return filetype;
            }

            public void setFiletype(String filetype) {
                this.filetype = filetype;
            }

            public String getReleaseTime() {
                return releaseTime;
            }

            public void setReleaseTime(String releaseTime) {
                this.releaseTime = releaseTime;
            }

            public String getReleasePerson() {
                return releasePerson;
            }

            public void setReleasePerson(String releasePerson) {
                this.releasePerson = releasePerson;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public List<String> getType() {
                return type;
            }

            public void setType(List<String> type) {
                this.type = type;
            }
        }

        public static class ImageTextTrainListsBean implements Serializable{
            /**
             * courseId : 13
             * title : 7月案例
             * type : ["公司简介","人员礼仪","工资制度","工具介绍"]
             * filetype : 2
             * releaseTime : 2019-08-03 15:12:35
             * releasePerson : 张炎
             * status : 3
             */

            private String courseId;
            private String title;
            private String filetype;
            private String releaseTime;
            private String releasePerson;
            private String status;
            private List<String> type;

            public String getCourseId() {
                return courseId;
            }

            public void setCourseId(String courseId) {
                this.courseId = courseId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getFiletype() {
                return filetype;
            }

            public void setFiletype(String filetype) {
                this.filetype = filetype;
            }

            public String getReleaseTime() {
                return releaseTime;
            }

            public void setReleaseTime(String releaseTime) {
                this.releaseTime = releaseTime;
            }

            public String getReleasePerson() {
                return releasePerson;
            }

            public void setReleasePerson(String releasePerson) {
                this.releasePerson = releasePerson;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public List<String> getType() {
                return type;
            }

            public void setType(List<String> type) {
                this.type = type;
            }
        }
    }
}
