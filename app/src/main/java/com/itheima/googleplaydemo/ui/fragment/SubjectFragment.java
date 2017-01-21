package com.itheima.googleplaydemo.ui.fragment;

import android.widget.BaseAdapter;

import com.itheima.googleplaydemo.adapter.SubjectListAdapter;
import com.itheima.googleplaydemo.bean.SubjectBean;
import com.itheima.googleplaydemo.network.HeiMaRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/15 13:13
 * 描述： TODO
 */
public class SubjectFragment extends BaseLoadMoreListFragment {

    private List<SubjectBean> mSubjects = new ArrayList<SubjectBean>();

    @Override
    protected void startLoadData() {
        Call<List<SubjectBean>> listCall = HeiMaRetrofit.getInstance().getApi().listSubject(0);
        listCall.enqueue(new Callback<List<SubjectBean>>() {
            @Override
            public void onResponse(Call<List<SubjectBean>> call, Response<List<SubjectBean>> response) {
                mSubjects.addAll(response.body());
                onDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<List<SubjectBean>> call, Throwable t) {
                onDataLoadedError();
            }
        });
    }

    @Override
    protected BaseAdapter onCreateAdapter() {
        return new SubjectListAdapter(getContext(), mSubjects);
    }

    @Override
    protected void onStartLoadMore() {
        Call<List<SubjectBean>> listCall = HeiMaRetrofit.getInstance().getApi().listSubject(mSubjects.size());
        listCall.enqueue(new Callback<List<SubjectBean>>() {
            @Override
            public void onResponse(Call<List<SubjectBean>> call, Response<List<SubjectBean>> response) {
                mSubjects.addAll(response.body());
                getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<SubjectBean>> call, Throwable t) {

            }
        });
    }
}
