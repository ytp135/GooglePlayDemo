package com.itheima.googleplaydemo.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.itheima.googleplaydemo.R;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/15 12:04
 * 描述： TODO
 */
public abstract class BaseFragment extends Fragment {

    ProgressBar mLoadingProgress;
    ImageView mLoadingEmpty;
    Button mErrorBtnRetry;
    LinearLayout mLoadingError;
    private FrameLayout mBaseView;
    private Handler mHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBaseView = (FrameLayout) inflater.inflate(R.layout.fragment_base, null);
        mLoadingProgress = (ProgressBar) mBaseView.findViewById(R.id.loading_progress);
        mLoadingEmpty = (ImageView) mBaseView.findViewById(R.id.loading_empty);
        mErrorBtnRetry = (Button) mBaseView.findViewById(R.id.error_btn_retry);
        mLoadingError = (LinearLayout) mBaseView.findViewById(R.id.loading_error);
        mErrorBtnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoadData();
            }
        });
        return mBaseView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        startLoadData();
    }

    /**
     * 子类去实现自己的数据加载
     */
    protected abstract void startLoadData();

    /**
     * 当数据加载成功后刷新UI
     */
    protected void onDataLoadedSuccess(){
        mLoadingProgress.setVisibility(View.GONE);
        mLoadingEmpty.setVisibility(View.GONE);
        mLoadingError.setVisibility(View.GONE);

        mBaseView.addView(onCreateContentView());
    }

    /**
     * 当数据加载失败后刷新UI
     */
    protected void onDataLoadedError() {
        mLoadingError.setVisibility(View.VISIBLE);
        mLoadingEmpty.setVisibility(View.GONE);
        mLoadingProgress.setVisibility(View.GONE);
    }

    /**
     * 当数据加载为空后刷新UI
     */
    protected void onDataLoadedEmpty() {
        mLoadingEmpty.setVisibility(View.VISIBLE);
        mLoadingProgress.setVisibility(View.GONE);
        mLoadingError.setVisibility(View.GONE);
    }

    /**
     * 子类必须实现该方法提供内容的视图
     * @return
     */
    protected abstract View onCreateContentView();

    protected void post(Runnable runnable) {
        mHandler.post(runnable);
    }
}
