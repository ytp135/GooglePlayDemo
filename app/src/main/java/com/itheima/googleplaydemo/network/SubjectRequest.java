package com.itheima.googleplaydemo.network;

import com.itheima.googleplaydemo.app.Constant;
import com.itheima.googleplaydemo.bean.SubjectBean;

import java.util.List;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/23 12:10
 * 描述： TODO
 */
public class SubjectRequest extends GooglePlayRequest<List<SubjectBean>> {
    private static final String TAG = "SubjectRequest";

    public SubjectRequest(int index, NetworkListener<List<SubjectBean>> listener) {
        super(Constant.URL_SUBJECT + "?index=" + index, listener);
    }
}
