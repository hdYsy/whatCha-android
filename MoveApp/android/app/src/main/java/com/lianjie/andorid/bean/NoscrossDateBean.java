package com.lianjie.andorid.bean;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/9
 * Time: 19:16
 */
public class NoscrossDateBean {
    private String day;
    private String time;

    public NoscrossDateBean(String day, String time) {
        this.day = day;
        this.time = time;
    }

    public NoscrossDateBean() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
