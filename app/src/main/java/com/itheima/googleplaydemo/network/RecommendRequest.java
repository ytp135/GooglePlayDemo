package com.itheima.googleplaydemo.network;

import com.itheima.googleplaydemo.app.Constant;

import java.util.List;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/25 21:45
 * 描述： TODO
 */
public class RecommendRequest extends GooglePlayRequest<List<String>> {

    public RecommendRequest(NetworkListener<List<String>> listener) {
        super(Constant.URL_RECOMMEND, listener);
    }
}
