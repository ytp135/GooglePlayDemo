package com.itheima.googleplaydemo.utils;

import android.util.Log;

import com.itheima.googleplaydemo.BuildConfig;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/14 14:21
 * 描述： TODO
 */
public class LogUtils {
    private static final String TAG = "LogUtils";
    private static final boolean DEBUG = BuildConfig.DEBUG;

    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (DEBUG) {
            Log.w(TAG, msg);
        }
    }
}
