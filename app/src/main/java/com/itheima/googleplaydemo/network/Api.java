package com.itheima.googleplaydemo.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Leon on 2017/1/20.
 */

public interface Api {

    @GET("recommend")
    Call<List<String>> listRecommend();

    @GET("hot")
    Call<List<String>> listHot();
}
