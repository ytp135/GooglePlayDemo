package com.itheima.googleplaydemo.network;

import com.itheima.googleplaydemo.bean.AppDetailBean;
import com.itheima.googleplaydemo.bean.AppListItem;
import com.itheima.googleplaydemo.bean.CategoryBean;
import com.itheima.googleplaydemo.bean.HomeBean;
import com.itheima.googleplaydemo.bean.SubjectBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Leon on 2017/1/20.
 */

public interface Api {

    @GET("recommend")
    Call<List<String>> listRecommend();

    @Headers("Cache-Control: max-age=300")
    @GET("hot")
    Call<List<String>> listHot();

    @GET("category")
    Call<List<CategoryBean>> categories();

    @GET("subject")
    Call<List<SubjectBean>> listSubject(@Query("index") int index);

    @GET("game")
    Call<List<AppListItem>> listGames(@Query("index") int index);

    @GET("app")
    Call<List<AppListItem>> listApps(@Query("index") int index);

    @GET("home")
    Call<HomeBean> listHome(@Query("index") int index);

    @GET("detail")
    Call<AppDetailBean> appDetail(@Query("packageName") String packageName);
}
