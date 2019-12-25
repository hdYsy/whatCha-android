package com.lianjie.andorid.utils.internetutils;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/11
 * Time: 15:40
 */

import android.util.Log;

import com.google.gson.Gson;
import com.lianjie.andorid.utils.GlobalInfoUtils;
import com.lianjie.andorid.utils.LogUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by yangxiaoling on 2019/3/29.
 */


public class RetrofitUtils implements INetWork {

    private static RetrofitUtils sRetrofitUtils;
    private final MyApiService mMyApiService;

    private RetrofitUtils() {
        OkHttpClient build = new OkHttpClient.Builder()
                .connectTimeout(GlobalInfoUtils.SECONDS, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(build)
                .baseUrl(URLConstant.Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mMyApiService = retrofit.create(MyApiService.class);
    }

    public static RetrofitUtils getInstance() {
        if (sRetrofitUtils == null) {
            synchronized (RetrofitUtils.class) {
                if (sRetrofitUtils == null) {
                    sRetrofitUtils = new RetrofitUtils();
                }
            }
        }
        return sRetrofitUtils;
    }

    @Override
    public <T> void get(String url, final NetWorkCallBack<T> callBack) {
        mMyApiService.get(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody value) {
                        fromJson(value, callBack);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public <T> void get(String url, Map<String, Object> map, final NetWorkCallBack<T> callBack) {
        mMyApiService.get(url, map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody value) {
                        fromJson(value, callBack);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public <T> void post(String url, final NetWorkCallBack<T> callBack) {
        mMyApiService.post(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody value) {
                        fromJson(value, callBack);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public <T> void post(String url, Map<String, Object> map, final NetWorkCallBack<T> callBack) {
        mMyApiService.post(url, map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(), true)
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody value) {

                        fromJson(value, callBack);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



    private <T> void fromJson(ResponseBody responseBody, NetWorkCallBack<T> callBack) {
        Type[] genericInterfaces = callBack.getClass().getGenericInterfaces();
        Type[] actualTypeArguments = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments();
        Type actualTypeArgument = actualTypeArguments[0];
        Gson gson = new Gson();

        try {
            T result = gson.fromJson(responseBody.string(), actualTypeArgument);
            callBack.onSucess(result);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                LogUtils.e(responseBody.string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
