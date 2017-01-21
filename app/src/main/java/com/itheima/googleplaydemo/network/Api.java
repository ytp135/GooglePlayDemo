package com.itheima.googleplaydemo.network;

import com.itheima.googleplaydemo.bean.CategoryBean;
import com.itheima.googleplaydemo.bean.SubjectBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Leon on 2017/1/20.
 */

public interface Api {

    @GET("recommend")
    Call<List<String>> listRecommend();

    @GET("hot")
    Call<List<String>> listHot();

    @GET("category")
    Call<List<CategoryBean>> categories();

    @GET("subject")
    Call<List<SubjectBean>> listSubject(@Query("index") int index);
}
