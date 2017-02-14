package com.itheima.googleplaydemo.app;

import android.app.Application;

import com.itheima.googleplaydemo.network.HeiMaRetrofit;

/**
 * Created by Leon on 2017/2/14.
 */

public class GooglePlayApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HeiMaRetrofit.getInstance().init(getApplicationContext());
    }
}
