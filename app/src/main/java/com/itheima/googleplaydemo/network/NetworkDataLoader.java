package com.itheima.googleplaydemo.network;

import com.itheima.googleplaydemo.app.Constant;
import com.itheima.googleplaydemo.utils.LogUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
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

    public static synchronized NetworkDataLoader getInstance() {
        if (sNetworkDataLoader == null) {
            sNetworkDataLoader = new NetworkDataLoader();
            sNetworkDataLoader.mOkHttpClient = new OkHttpClient();
        }
        return sNetworkDataLoader;
    }

    public void loadHomeData() {
        String url = Constant.URL_HOME + "?index=0";
        //发起异步请求
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
        });
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
                String result = response.body().toString();
                LogUtils.d(TAG, "loadHomeData: " + result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
