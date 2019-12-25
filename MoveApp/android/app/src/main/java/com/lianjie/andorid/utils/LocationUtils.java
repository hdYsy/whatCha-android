package com.lianjie.andorid.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/18
 * Time: 14:53
 */
public class LocationUtils  implements AMapLocationListener {

    private static final String TAG = LocationUtils.class.getSimpleName();
    private Context context;

    /**
     * 获取经纬度
     * @param context
     * @return
     */
    @SuppressLint("MissingPermission")
    public static Location getLocation(Context context) {
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = manager.getProviders(true);
        if (providers == null) return null;
        Location lastKnownLocation = null;


        if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            lastKnownLocation = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        if (lastKnownLocation == null && providers.contains(LocationManager.GPS_PROVIDER)) {
            lastKnownLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        }
        if (lastKnownLocation == null && providers.contains(LocationManager.PASSIVE_PROVIDER)) {
            lastKnownLocation = manager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        }

        return lastKnownLocation;
    }

    /**
     * 获取当前APP版本号
     * @param context
     * @return
     */
    public static int getPackageVersionCode(Context context){
        PackageManager manager = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = manager.getPackageInfo(context.getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        if(packageInfo != null){
            return packageInfo.versionCode;
        }else{
            return 1;
        }
    }
    //声明mlocationClient对象
    public static AMapLocationClient mlocationClient;
    //声明mLocationOption对象
    public static AMapLocationClientOption mLocationOption = null;

    /**
     * 获取经纬度
     * @param context
     * @return
     */
    public void getLatLng(Context context){
       this.context = context;
        mlocationClient = new AMapLocationClient(context);
//初始化定位参数
        mLocationOption = new AMapLocationClientOption();
//设置定位监听
        mlocationClient.setLocationListener(this);
//设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(5000);
//设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
// 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
// 在定位结束后，在合适的生命周期调用onDestroy()方法
// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
//启动定位
        mlocationClient.startLocation();
    }
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                GlobalInfoUtils.latitude =     amapLocation.getLatitude();//获取纬度
                GlobalInfoUtils.longitude =     amapLocation.getLongitude();//获取经度
                amapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                df.format(date);//定位时间
                mlocationClient.stopLocation();
            } else {
                mlocationClient.stopLocation();
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
            }

        }

    }


}
