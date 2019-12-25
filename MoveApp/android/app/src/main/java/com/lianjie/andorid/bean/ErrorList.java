package com.lianjie.andorid.bean;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/10
 * Time: 17:52\
 * Event BUs消息工具类
 */
public class ErrorList {
 private String data;
 private boolean br;

    public ErrorList(String data, boolean br) {
        this.data = data;
        this.br = br;
    }

    public ErrorList() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isBr() {
        return br;
    }

    public void setBr(boolean br) {
        this.br = br;
    }
}
