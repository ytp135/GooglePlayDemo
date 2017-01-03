package com.itheima.googleplaydemo.network;

import com.itheima.googleplaydemo.app.Constant;
import com.itheima.googleplaydemo.bean.AppDetailBean;

/**
 * Created by Leon on 2017/1/3.
 */

public class AppDetailRequest extends GooglePlayRequest<AppDetailBean> {

    public AppDetailRequest(String packageName, NetworkListener<AppDetailBean> listener) {
        super(Constant.URL_DETAIL + "?packagename=" + packageName, listener);
    }
}
