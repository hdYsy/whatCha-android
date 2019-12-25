package com.lianjie.andorid.utils.internetutils;

import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/4
 * Time: 11:27
 */
public interface ApiService{
    @Multipart
    @POST
    Observable<ResponseBody> upLoadImage(@Url String url, @QueryMap Map<String,Object> map, @Part MultipartBody.Part file);

}
