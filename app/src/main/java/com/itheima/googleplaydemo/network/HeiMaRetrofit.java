package com.itheima.googleplaydemo.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itheima.googleplaydemo.app.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Leon on 2017/1/20.
 */

public class HeiMaRetrofit {

    private static HeiMaRetrofit sHeiMaRetrofit;

    private final Api mApi;

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();


    private HeiMaRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.HOST)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mApi = retrofit.create(Api.class);
    }

    public static HeiMaRetrofit getInstance() {
        if (sHeiMaRetrofit == null) {
            synchronized (HeiMaRetrofit.class) {
                if (sHeiMaRetrofit == null) {
                    sHeiMaRetrofit = new HeiMaRetrofit();
                }
            }
        }
        return sHeiMaRetrofit;
    }

    public Api getApi() {
        return mApi;
    }

}
