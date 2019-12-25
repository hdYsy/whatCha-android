package com.lianjie.andorid.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lianjie.andorid.bean.SubTwoPhotoBean;
import com.lianjie.andorid.rnui.RNApplication;
import com.lianjie.andorid.bean.SubaudBean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * shareParent工具类使用
 * Created by wangxc on 2019/1/29.
 */

public class SPUtil {

    /**
     * 存储数据
     *
     * @param key    存储键值
     * @param object 存储对象
     */
    public static synchronized void addSP(String key, Object object) {
        Context context = RNApplication.myApp.getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //数据存储
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else if (object instanceof List ) {//sp存储list
            Gson gson = new Gson();
            //转换成json数据，再保存list
            String strJson = gson.toJson(object);
            editor.putString(key, strJson);
        }else if(object instanceof SubTwoPhotoBean){
            Gson gson = new Gson();
            //转换成json数据，再保存list
            String strJson = gson.toJson(object);
            editor.putString(key, strJson);
         } else {
            editor.putString(key, object.toString());
//        }//、、、
        }
        editor.commit();
    }




    /**
     * 读取数据
     *
     * @param key           键值对
     * @param defaultObject 初始值
     */
    public static synchronized Object readSP(String key, Object defaultObject) {
        Context context = RNApplication.myApp.getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);

        if (defaultObject instanceof String) {
            return sharedPreferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defaultObject);
        } else if (defaultObject instanceof List)  {//如果是list
            String string = sharedPreferences.getString(key, "");
            if (!string.isEmpty()) {
                Gson gson = new Gson();
                List<List<String>> datalist=gson.fromJson(string, new TypeToken<List<List<String>>>() {}.getType());
                return datalist;
            }
            return null;
        }else if(defaultObject instanceof SubTwoPhotoBean){
            String string = sharedPreferences.getString(key, "");
            if (!string.isEmpty()) {
                Gson gson = new Gson();
                SubTwoPhotoBean bean=gson.fromJson(string, SubTwoPhotoBean.class);
                return bean;
            }
            return sharedPreferences.getString(key, null);
        }else{
            return sharedPreferences.getString(key, null);
        }
    }


    /**
     * 移除某个key值已经对应的值
     *
     * @param key 移除的key
     */
    public static void removeSP(String key) {
        Context context = RNApplication.myApp.getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }

    /**
     * 清除所有数据
     */
    public static void clearSP() {
        Context context = RNApplication.myApp.getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * 查询某个key是否存在
     *
     * @param key 查询的key
     */
    public static Boolean contain(String key) {
        Context context = RNApplication.myApp.getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        return sharedPreferences.contains(key);
    }

    /**
     * 返回所有的键值对
     */
    public static Map<String, ?> getAllSP() {
        Context context = RNApplication.myApp.getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        return sharedPreferences.getAll();
    }
}
