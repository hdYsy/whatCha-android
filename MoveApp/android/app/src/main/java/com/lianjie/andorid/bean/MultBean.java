package com.lianjie.andorid.bean;

import java.util.List;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/25
 * Time: 22:18
 */
public class MultBean {

    /**
     * cateId : 0
     * cateName : 双周帮忙给租户做卧室卫生
     * imgList : ["http://oss.lianjieshenghuo.com/public/uploads/2019/07/25/20190725171842_5d3973f23c706.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wNy0yNSAxNzoxODo0Mg==,color_ffffff,shadow_100/watermark,text_5pyq6I635Y-W5Yiw57uP57qs5bqm5L-h5oGv,color_ffffff,size_20,shadow_100","http://oss.lianjieshenghuo.com/public/uploads/2019/07/25/20190725171910_5d39740eb7b36.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wNy0yNSAxNzoxOToxMA==,color_ffffff,shadow_100/watermark,text_5pyd6Ziz5Yy65pS_5bqc5YaFLOWMl-S6rOW4guacnemYs-WMuue7n-iuoeWxgOmZhOi_kTDnsbM=,color_ffffff,size_20,shadow_100"]
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

    @Override
    public String toString() {
        return "{" +
                "cateId='" + cateId + '\'' +
                ", cateName='" + cateName + '\'' +
                ", imgList=" + imgList +
                '}';
    }
}
