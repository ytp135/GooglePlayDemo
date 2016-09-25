package com.itheima.googleplaydemo.network;

import com.itheima.googleplaydemo.app.Constant;
import com.itheima.googleplaydemo.bean.CategoryBean;

import java.util.List;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/25 22:14
 * 描述： TODO
 */
public class CategoryRequest extends GooglePlayRequest<List<CategoryBean>> {
    private static final String TAG = "CategoryRequest";

    public CategoryRequest(NetworkListener<List<CategoryBean>> listener) {
        super(Constant.URL_CATEGORY, listener);
    }
}
