package com.lianjie.andorid.utils.internetutils;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/11
 * Time: 15:37
 */

public interface INetWork {
    <T> void get(String url, NetWorkCallBack<T> callBack);

    <T> void get(String url, Map<String, Object> map, NetWorkCallBack<T> callBack);

    <T> void post(String url, NetWorkCallBack<T> callBack);

    <T> void post(String url, Map<String, Object> map, NetWorkCallBack<T> callBack);

}

