package com.lianjie.andorid.service;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/10/23
 * Time: 11:33
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.lianjie.andorid.utils.GlobalInfoUtils;


/**
 * Created by 75213 on 2017/11/7.
 */

public class InitApkBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (Intent.ACTION_PACKAGE_ADDED.equals(intent.getAction())) {
            comm.rmoveFile( GlobalInfoUtils.apkName);
            Toast.makeText(context , "监听到系统广播添加" , Toast.LENGTH_LONG).show();
        }

        if (Intent.ACTION_PACKAGE_REMOVED.equals(intent.getAction())) {
            comm.rmoveFile( GlobalInfoUtils.apkName);
            Toast.makeText(context , "监听到系统广播移除" , Toast.LENGTH_LONG).show();
        }

        if (Intent.ACTION_PACKAGE_REPLACED.equals(intent.getAction())) {
            comm.rmoveFile( GlobalInfoUtils.apkName);
        }
    }
}
