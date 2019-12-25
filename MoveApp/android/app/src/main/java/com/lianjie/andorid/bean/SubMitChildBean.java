package com.lianjie.andorid.bean;

import java.util.List;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/15
 * Time: 15:10
 */
public class SubMitChildBean {
    private String id;
    private List<String> list;

    public SubMitChildBean(String id, List<String> list) {
        this.id = id;
        this.list = list;
    }

    public SubMitChildBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
