package com.itheima.googleplaydemo.loader;

import com.itheima.googleplaydemo.bean.SubjectBean;
import com.itheima.googleplaydemo.network.GooglePlayRequest;
import com.itheima.googleplaydemo.network.NetworkListener;
import com.itheima.googleplaydemo.network.SubjectRequest;

import java.util.List;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/23 11:06
 * 描述： TODO
 */
public class SubjectDataLoader extends ListDataLoader<List<SubjectBean>> {

    private static SubjectDataLoader sSubjectDataLoader;

    public static SubjectDataLoader getInstance() {
        if (sSubjectDataLoader == null) {
            synchronized (SubjectDataLoader.class) {
                if (sSubjectDataLoader == null) {
                    sSubjectDataLoader = new SubjectDataLoader();
                }
            }
        }
        return sSubjectDataLoader;
    }


    @Override
    protected GooglePlayRequest<List<SubjectBean>> onCreateLoadMoreRequest(NetworkListener<List<SubjectBean>> listener) {
        return new SubjectRequest(getData().size(), listener);
    }

    @Override
    protected void onGetMoreData(List<SubjectBean> result) {
        getData().addAll(result);
    }

    @Override
    protected GooglePlayRequest<List<SubjectBean>> onCreateRequest(NetworkListener<List<SubjectBean>> listener) {
        return new SubjectRequest(0, listener);
    }

    @Override
    protected boolean checkIfEmpty() {
        return getData().size() == 0;
    }
}
