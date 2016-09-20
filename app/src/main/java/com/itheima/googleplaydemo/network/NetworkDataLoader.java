package com.itheima.googleplaydemo.network;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.itheima.googleplaydemo.app.Constant;
import com.itheima.googleplaydemo.bean.HomeBean;
import com.itheima.googleplaydemo.utils.LogUtils;
import com.itheima.googleplaydemo.utils.concurrent.ThreadPoolProxyFactory;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/19 16:50
 * 描述： TODO
 */
public class NetworkDataLoader {
    private static final String TAG = "NetworkDataLoader";
    private static NetworkDataLoader sNetworkDataLoader;
    private OkHttpClient mOkHttpClient;
    private Gson mGson;

    private NetworkListener mNetworkListener;

    public static synchronized NetworkDataLoader getInstance() {
        if (sNetworkDataLoader == null) {
            sNetworkDataLoader = new NetworkDataLoader();
            sNetworkDataLoader.mOkHttpClient = new OkHttpClient();
            sNetworkDataLoader.mGson = new Gson();
        }
        return sNetworkDataLoader;
    }

    public void loadHomeData(NetworkListener listener) {
        String url = Constant.URL_HOME + "?index=0";
/*        //发起异步请求
        Request request = new Request.Builder().get().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                LogUtils.d(TAG, "onResponse: " + result);
            }
        });*/
//        new Thread(new RequestTask(url)).start();
//        new ThreadPoolProxy().execute(new RequestTask(url));
        mNetworkListener = listener;
        ThreadPoolProxyFactory.getNormalThreadPoolProxy().execute(new RequestTask(url));
    }

    private class RequestTask implements Runnable {

        private String mUrl;

        public RequestTask(String url) {
            mUrl = url;
        }

        @Override
        public void run() {
            try {
                //发起同步请求
                Request request = new Request.Builder().get().url(mUrl).build();
                Response response = mOkHttpClient.newCall(request).execute();
                String result = response.body().string();
                final HomeBean bean = mGson.fromJson(result, HomeBean.class);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        mNetworkListener.onResponse(bean);
                        LogUtils.d(TAG, "loadHomeData: " + bean.getList().get(0).getName());

                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
                mNetworkListener.onFailure(e.getLocalizedMessage());
            }
        }
    }

}
