package com.lianjie.andorid.utils;

import android.text.format.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/14
 * Time: 16:51
 */
public class SystemDateUtils {
    //返回当前年月日
    public static String getNowDate() {
        Date date = new Date();
//        String nowDate = new SimpleDateFormat("yyyy年MM月dd日").format(date);
        String nowDate = new SimpleDateFormat("MM月dd日").format(date);
        return nowDate;
    }

    //返回当前年份
    public static int getYear() {
        Date date = new Date();
        String year = new SimpleDateFormat("yyyy").format(date);
        return Integer.parseInt(year);
    }

    //返回当前月份
    public static int getMonth() {
        Date date = new Date();
        String month = new SimpleDateFormat("MM").format(date);
        return Integer.parseInt(month);
    }

    //判断闰年
    public static boolean isLeap(int year) {
        if (((year % 100 == 0) && year % 400 == 0) || ((year % 100 != 0) && year % 4 == 0))
            return true;
        else
            return false;
    }

    //返回当月天数
    public static int getDays() {
        int month = SystemDateUtils.getMonth();
        int year = SystemDateUtils.getYear();
        int days;
        int FebDay = 28;
        if (isLeap(year))
            FebDay = 29;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days = 30;
                break;
            case 2:
                days = FebDay;
                break;
            default:
                days = 0;
                break;
        }
        return days;
    }

    //返回当月星期天数
    public static int getSundays() {
        int sundays = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Calendar setDate = Calendar.getInstance();
        //从第一天开始
        int day;
        for (day = 1; day <= getDays(); day++) {
            setDate.set(Calendar.DATE, day);
            String str = sdf.format(setDate.getTime());
            if (str.equals("星期日")) {
                sundays++;
            }
        }
        return sundays;
    }

    //
//    public static void main(String[] args)
//    {
//        DateUtils du = new DateUtils();
//        System.out.println("今天日期是：" + du.getNowDate());
//        System.out.println("本月有" + du.getDays(du.getYear(), du.getMonth()) + "天");
//        System.out.println("本月有" + du.getSundays(du.getYear(), du.getMonth()) + "个星期天");
//    }
//返回当前是几号
    public static int getDay() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 转换为时分秒
     * @param mss
     * @return
     */
    public static String formatDateTime(long mss) {
        String DateTimes = null;
//        long days = mss / (60 * 60 * 24);
        long hours = (mss % (60 * 60 * 24)) / (60 * 60);
        long minutes = (mss % (60 * 60)) / 60;
        long seconds = mss % 60;
//        if (days > 0) {
//            DateTimes = days + "天" + hours + "小时" + minutes + "分钟"
//                    + seconds + "秒";
//        } else
            if (hours > 0) {
            DateTimes = hours + "小时" + minutes + "分钟"
                    + seconds + "秒";
        } else if (minutes > 0) {
            DateTimes = minutes + "分钟"
                    + seconds + "秒";
        } else {
            DateTimes = seconds + "秒";
        }

        return DateTimes;
    }
    /**
     * 转换为地址
     * @param mss
     * @return
     */
    public static String formatmmm(float mss) {
     String timimm = null;
        float v = mss / 1000;
        if (v !=0) {
            timimm="（"+v+"公里）";
        }else {
            timimm="距离过近!";
        }
        return timimm;
    }

}
