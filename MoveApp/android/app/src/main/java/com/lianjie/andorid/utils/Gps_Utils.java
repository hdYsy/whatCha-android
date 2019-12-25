package com.lianjie.andorid.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.support.v4.app.ActivityCompat.requestPermissions;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/11
 * Time: 10:15
 */
public class Gps_Utils {
    public static Gps_Utils gps_utils;
    private LocationManager locationManager;
    private double latitude;
    private double longitude;

    private Gps_Utils() {
    }
    public static Gps_Utils getInstance(){
        if(gps_utils == null){
            synchronized (Gps_Utils.class){
                if(gps_utils == null){
                    gps_utils = new Gps_Utils();
                }
            }
        }
        return gps_utils;
    }

    /**
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
     * @param context
     * @return true 表示开启
     * 需要每次都调用  获取是否开启了GPS权限
     */
    public  boolean isOPen(final Context context) {
        // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
//        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }


    /**
     * 检查网络是否连通
     *
     * @return boolean
     * @since V1.0
     */
    public  boolean isNetworkAvailable(Context context) {
        // 创建并初始化连接对象
        ConnectivityManager connMan = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        // 判断初始化是否成功并作出相应处理
        if (connMan != null) {
            // 调用getActiveNetworkInfo方法创建对象,如果不为空则表明网络连通，否则没连通
            NetworkInfo info = connMan.getActiveNetworkInfo();
            if (info != null) {
                return info.isAvailable();
            }
        }
        return false;
    }
    /**
     * 判断是否处于WiFi状态
     * getActiveNetworkInfo 是可用的网络，不一定是链接的，getNetworkInfo 是链接的。
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager manager = (ConnectivityManager)context. getSystemService(CONNECTIVITY_SERVICE);
        //NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        //处于WiFi连接状态
        if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }


}
