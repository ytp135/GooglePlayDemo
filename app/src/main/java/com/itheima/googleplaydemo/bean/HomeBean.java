package com.itheima.googleplaydemo.bean;

import java.util.List;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/20 17:51
 * 描述： TODO
 */
public class HomeBean {
    private static final String TAG = "HomeBean";


    private List<String> picture;
    /**
     * id : 1525489
     * name : 黑马程序员
     * packageName : com.itheima.www
     * iconUrl : app/com.itheima.www/icon.jpg
     * stars : 5
     * size : 91767
     * downloadUrl : app/com.itheima.www/com.itheima.www.apk
     * des : 产品介绍：google市场app测试。
     */

    private List<AppListItem> list;

    public List<String> getPicture() {
        return picture;
    }

    public void setPicture(List<String> picture) {
        this.picture = picture;
    }

    public List<AppListItem> getList() {
        return list;
    }

    public void setList(List<AppListItem> list) {
        this.list = list;
    }

}
