package com.lianjie.andorid.service;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/10/23
 * Time: 11:34
 */

import android.os.Environment;

import java.io.File;

/**
 * Created by 75213 on 2017/11/1.
 */

public class comm{
    public static File getPathFile(String path){
        String apkName = path.substring(path.lastIndexOf("/"));
        File outputFile = new File(Environment.getExternalStoragePublicDirectory
                (Environment.DIRECTORY_DOWNLOADS), apkName);
        return outputFile;
    }

    public static void rmoveFile(String path){
        File file = getPathFile(path);
        file.delete();
    }
}