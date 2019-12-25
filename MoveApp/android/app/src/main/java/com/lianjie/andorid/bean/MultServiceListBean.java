package com.lianjie.andorid.bean;

import java.util.List;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/16
 * Time: 11:09
 */
public class MultServiceListBean {
    /**
     * code : 1000
     * msg : 获取多服务照片成功
     * data : {"multiServiceList":[{"cateName":"双周帮忙给租户做卧室卫生","cateId":0,"isSelected":false},{"cateName":"整理床铺","cateId":1,"isSelected":false},{"cateName":"清理冰箱内不要的东西","cateId":2,"isSelected":false},{"cateName":"清洗厨房厨具","cateId":3,"isSelected":true},{"cateName":"宠物笼具周围消毒","cateId":4,"isSelected":false},{"cateName":"将鞋子摆放整齐","cateId":5,"isSelected":false},{"cateName":"其他服务","cateId":6,"isSelected":false},{"cateName":"厨房台面整理","cateId":7,"isSelected":false},{"cateName":"公区桌面桌椅整理","cateId":8,"isSelected":false},{"cateName":"外门内外清洁","cateId":9,"isSelected":false}],"multiServiceImageList":[{"cateId":3,"cateName":"清洗厨房厨具","imgList":["http://oss.lianjieshenghuo.com/public/uploads/2019/08/08/20190808093335_5d4b7bef44feb.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wOC0wOCAwOTozMzozNQ==,color_ffffff,shadow_100/watermark,text_5pyd6Ziz5Yy65pS_5bqc5YaFLOWMl-S6rOW4guacnemYs-WMuue7n-iuoeWxgOmZhOi_kTDnsbM=,color_ffffff,size_20,shadow_100","http://oss.lianjieshenghuo.com/public/uploads/2019/08/08/20190808093332_5d4b7bec15820.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wOC0wOCAwOTozMzozMg==,color_ffffff,shadow_100/watermark,text_5pyd6Ziz5Yy65pS_5bqc5YaFLOWMl-S6rOW4guacnemYs-WMuue7n-iuoeWxgOmZhOi_kTDnsbM=,color_ffffff,size_20,shadow_100"]}]}
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
        private List<MultiServiceListBean> multiServiceList;
        private List<MultiServiceImageListBean> multiServiceImageList;

        public List<MultiServiceListBean> getMultiServiceList() {
            return multiServiceList;
        }

        public void setMultiServiceList(List<MultiServiceListBean> multiServiceList) {
            this.multiServiceList = multiServiceList;
        }

        public List<MultiServiceImageListBean> getMultiServiceImageList() {
            return multiServiceImageList;
        }

        public void setMultiServiceImageList(List<MultiServiceImageListBean> multiServiceImageList) {
            this.multiServiceImageList = multiServiceImageList;
        }

        public static class MultiServiceListBean {
            /**
             * cateName : 双周帮忙给租户做卧室卫生
             * cateId : 0
             * isSelected : false
             */

            private String cateName;
            private int cateId;
            private boolean isSelected;

            public String getCateName() {
                return cateName;
            }

            public void setCateName(String cateName) {
                this.cateName = cateName;
            }

            public int getCateId() {
                return cateId;
            }

            public void setCateId(int cateId) {
                this.cateId = cateId;
            }

            public boolean isIsSelected() {
                return isSelected;
            }

            public void setIsSelected(boolean isSelected) {
                this.isSelected = isSelected;
            }
        }

        public static class MultiServiceImageListBean {
            /**
             * cateId : 3
             * cateName : 清洗厨房厨具
             * imgList : ["http://oss.lianjieshenghuo.com/public/uploads/2019/08/08/20190808093335_5d4b7bef44feb.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wOC0wOCAwOTozMzozNQ==,color_ffffff,shadow_100/watermark,text_5pyd6Ziz5Yy65pS_5bqc5YaFLOWMl-S6rOW4guacnemYs-WMuue7n-iuoeWxgOmZhOi_kTDnsbM=,color_ffffff,size_20,shadow_100","http://oss.lianjieshenghuo.com/public/uploads/2019/08/08/20190808093332_5d4b7bec15820.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wOC0wOCAwOTozMzozMg==,color_ffffff,shadow_100/watermark,text_5pyd6Ziz5Yy65pS_5bqc5YaFLOWMl-S6rOW4guacnemYs-WMuue7n-iuoeWxgOmZhOi_kTDnsbM=,color_ffffff,size_20,shadow_100"]
             */

            private int cateId;
            private String cateName;
            private List<String> imgList;

            public int getCateId() {
                return cateId;
            }

            public void setCateId(int cateId) {
                this.cateId = cateId;
            }

            public String getCateName() {
                return cateName;
            }

            public void setCateName(String cateName) {
                this.cateName = cateName;
            }

            public List<String> getImgList() {
                return imgList;
            }

            public void setImgList(List<String> imgList) {
                this.imgList = imgList;
            }
        }
    }


    /**
     * code : 1000
     * msg : 获取多服务照片成功
     * data : {"multiServiceList":[{"cateName":"双周帮忙给租户做卧室卫生","cateId":0,"isSelected":false},{"cateName":"整理床铺","cateId":1,"isSelected":true},{"cateName":"清理冰箱内不要的东西","cateId":2,"isSelected":false},{"cateName":"清洗厨房厨具","cateId":3,"isSelected":false},{"cateName":"宠物笼具周围消毒","cateId":4,"isSelected":false},{"cateName":"将鞋子摆放整齐","cateId":5,"isSelected":false},{"cateName":"其他服务","cateId":6,"isSelected":false},{"cateName":"厨房台面整理","cateId":7,"isSelected":false},{"cateName":"公区桌面桌椅整理","cateId":8,"isSelected":false},{"cateName":"外门内外清洁","cateId":9,"isSelected":false}],"multiServiceImageList":[{"cateId":1,"cateName":"整理床铺","imgList":["http://oss.lianjieshenghuo.com/public/uploads/2019/07/16/20190716163353_5d2d8bf185dcf.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wNy0xNiAxNjozMzo1Mw==,color_ffffff,shadow_100/watermark,text_5pyd6Ziz5Yy65pS_5bqc5YaFLOWMl-S6rOW4guacnemYs-WMuue7n-iuoeWxgOmZhOi_kTDnsbM=,color_ffffff,size_20,shadow_100","http://oss.lianjieshenghuo.com/public/uploads/2019/07/16/20190716163354_5d2d8bf2ea721.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wNy0xNiAxNjozMzo1NA==,color_ffffff,shadow_100/watermark,text_5pyd6Ziz5Yy65pS_5bqc5YaFLOWMl-S6rOW4guacnemYs-WMuue7n-iuoeWxgOmZhOi_kTDnsbM=,color_ffffff,size_20,shadow_100"]}]}
     */

}
