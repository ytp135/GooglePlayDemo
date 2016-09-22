package com.itheima.googleplaydemo.network;

import com.itheima.googleplaydemo.app.Constant;
import com.itheima.googleplaydemo.bean.AppListItem;

import java.util.List;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/22 20:37
 * 描述： TODO
 */
public class AppRequest extends GooglePlayRequest<List<AppListItem>> {

    public AppRequest(int index, NetworkListener<List<AppListItem>> listener) {
        super(Constant.URL_APP + "?index=" + index, listener);
    }
}
