package com.example.zhaogaofei.bodyweightlinechart.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;

public final class Utils {

    private Utils() {
    }

    public static int getScreenWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public static int getScreenHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public static int px2dip(Context cc, float pxValue) {
        final float scale = cc.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dip2px(Context cc, float dipValue) {
        final float scale = cc.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 时间格式为：20190101
     */
    public static int getTimeSpace(String time0, String time1) {
        if (TextUtils.isEmpty(time0) || TextUtils.isEmpty(time1)) {
            return 1;
        }
        int space;
        Date timeDate0 = getTimeDate(time0);
        Date timeDate1 = getTimeDate(time1);
        space = differentDays(timeDate0, timeDate1);

        return space;
    }

    public static Date getTimeDate(String timeStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date parse = null;
        try {
            parse = format.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                    timeDistance += 366;
                } else {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else {
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        }
    }
}
