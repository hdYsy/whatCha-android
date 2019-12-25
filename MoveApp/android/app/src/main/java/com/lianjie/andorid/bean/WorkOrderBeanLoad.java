package com.lianjie.andorid.bean;

import java.util.List;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/26
 * Time: 18:48
 */
public class WorkOrderBeanLoad {

    private List<Integer> person;
    private List<Integer> room;

    public List<Integer> getPerson() {
        return person;
    }

    public void setPerson(List<Integer> person) {
        this.person = person;
    }

    public List<Integer> getRoom() {
        return room;
    }

    public void setRoom(List<Integer> room) {
        this.room = room;
    }
}
