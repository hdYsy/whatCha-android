package com.lianjie.andorid.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import com.lianjie.andorid.ui.DelatileActivity;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/12
 * Time: 14:36
 */
public class ActionUtils {
    public static  ActionUtils actionUtils;

    /**
     * 跳转到GPSN那个Strtings 里面
     * @param context
     */
    public static void onStartStrting(Context context,String settings){
        Intent intent = new Intent(settings);
        context.startActivity(intent);
    }

    /**
     * 跳转页面
     * 不带值
     * @param context
     */
    public static void onStart(Context context, Class mclass){
        Intent intent = new Intent(context,mclass);
        context.startActivity(intent);
    }

    /**
     * 当用户 没有 高德地图得时候 跳转到官网进行下载
     * @param activity
     */
    public static void onStartGaode(Activity activity) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(GlobalInfoUtils.GaoDe);
        intent.setData(content_url);
        activity.startActivity(intent);
    }

    /**
     * 当用户 没有 高德地图得时候 跳转到官网进行下载
     * @param activity
     */
    public static void onStartTencent(Activity activity) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(GlobalInfoUtils.Tencent);
        intent.setData(content_url);
        activity.startActivity(intent);
    }
    public static void onStartHome(Context mContext) {
        Intent intent = new Intent();// 创建Intent对象
        intent.setAction(Intent.ACTION_MAIN);// 设置Intent动作
        intent.addCategory(Intent.CATEGORY_HOME);// 设置Intent种类
        mContext.startActivity(intent);// 将Intent传递给Activity
    }
//           Intent intent= new Intent();        
//    intent.setAction("android.intent.action.VIEW");    
//    Uri content_url = Uri.parse("https://www.baidu.com");   
//    intent.setData(content_url);  
//    startActivity(intent);

}
