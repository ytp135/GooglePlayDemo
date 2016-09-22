package com.itheima.googleplaydemo.network;

import com.itheima.googleplaydemo.app.Constant;
import com.itheima.googleplaydemo.bean.HomeBean;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/21 17:57
 * 描述： TODO
 */
public class HomeRequest extends GooglePlayRequest<HomeBean> {

    public HomeRequest(int index, NetworkListener<HomeBean> listener) {
        super(Constant.URL_HOME + "?index=" + index, listener);
    }
}
