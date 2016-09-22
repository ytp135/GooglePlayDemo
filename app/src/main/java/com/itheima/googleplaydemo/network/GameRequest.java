package com.itheima.googleplaydemo.network;

import com.itheima.googleplaydemo.app.Constant;
import com.itheima.googleplaydemo.bean.AppListItem;

import java.util.List;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/22 21:41
 * 描述： TODO
 */
public class GameRequest extends GooglePlayRequest<List<AppListItem>> {

    public GameRequest(int index, NetworkListener<List<AppListItem>> listener) {
        super(Constant.URL_GAME + "?index=" + index, listener);
    }
}
