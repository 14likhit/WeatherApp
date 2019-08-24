package com.uniqolabel.weatherapp.utils;

import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Utils {

    public static float pxToDp(final Context context, final float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float dpToPx(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public static boolean checkPermissionGranted(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static String getDate(Long timeMillis) {
        Date date = new Date(timeMillis*1000);
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        return format.format(date);
    }

}
