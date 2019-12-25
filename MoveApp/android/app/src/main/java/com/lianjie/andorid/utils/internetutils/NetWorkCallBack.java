package com.lianjie.andorid.utils.internetutils;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/11
 * Time: 15:39
 */
public interface NetWorkCallBack<T>{
    void onSucess(T t);
    void onError(Throwable e);
}
