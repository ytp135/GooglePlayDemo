package com.itheima.googleplaydemo.loader;

import android.util.SparseArray;

import com.itheima.googleplaydemo.bean.CategoryBean;
import com.itheima.googleplaydemo.network.CategoryRequest;
import com.itheima.googleplaydemo.network.GooglePlayRequest;
import com.itheima.googleplaydemo.network.NetworkListener;

import java.util.List;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/25 22:16
 * 描述： TODO
 */
public class CategoryDataLoader extends BaseDataLoader<List<CategoryBean>> {

    private static CategoryDataLoader sCategoryDataLoader;

    private SparseArray<String> mTitles;

    private SparseArray<CategoryBean.InfosBean> mInfosBean;

    public static CategoryDataLoader getInstance() {
        if (sCategoryDataLoader == null) {
            synchronized (CategoryDataLoader.class) {
                if (sCategoryDataLoader == null) {
                    sCategoryDataLoader = new CategoryDataLoader();
                }
            }
        }
        return sCategoryDataLoader;
    }

    @Override
    protected GooglePlayRequest<List<CategoryBean>> onCreateRequest(NetworkListener<List<CategoryBean>> listener) {
        return new CategoryRequest(listener);
    }

    @Override
    protected boolean checkIfEmpty() {
        return getData().size() == 0;
    }

    protected void organiseDataForList() {
        mTitles = new SparseArray<String>();
        mInfosBean = new SparseArray<CategoryBean.InfosBean>();
        int position = 0;
        for (int i = 0; i < getData().size(); i++) {
            CategoryBean categoryBean = getData().get(i);
            mTitles.put(position, categoryBean.getTitle());
            position ++;
            for (int j = 0; j < categoryBean.getInfos().size(); j++) {
                mInfosBean.put(position, categoryBean.getInfos().get(j));
                position ++;
            }
        }
    }

    public String getTitle(int position) {
        return mTitles.get(position);
    }

    public CategoryBean.InfosBean getItem(int position) {
        return mInfosBean.get(position);
    }

    public int getListCount() {
        organiseDataForList();
        return mTitles.size() + mInfosBean.size();
    }



}
