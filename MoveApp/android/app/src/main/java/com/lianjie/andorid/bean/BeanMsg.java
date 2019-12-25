package com.lianjie.andorid.bean;

import java.util.List;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/20
 * Time: 15:11
 */
public class BeanMsg {

    private List<ImagelistBean> imagelist;

    public List<ImagelistBean> getImagelist() {
        return imagelist;
    }

    public void setImagelist(List<ImagelistBean> imagelist) {
        this.imagelist = imagelist;
    }

    public static class ImagelistBean {
        public ImagelistBean(String cateId, String cateName, List<String> imgList) {
            this.cateId = cateId;
            this.cateName = cateName;
            this.imgList = imgList;
        }

        public ImagelistBean() {
        }

        /**
         * cateId : 3
         * cateName : 清洗厨房厨具
         * imgList : ["",""]
         */

        private String cateId;
        private String cateName;
        private List<String> imgList;

        public String getCateId() {
            return cateId;
        }

        public void setCateId(String cateId) {
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
