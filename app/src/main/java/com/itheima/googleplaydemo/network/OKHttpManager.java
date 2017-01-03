package com.itheima.googleplaydemo.network;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;

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
public class OKHttpManager {

    private static final String TAG = "OKHttpManager";
    private static OKHttpManager sOKHttpManager;
    private OkHttpClient mOkHttpClient;
    private Handler mHandler;

    public static synchronized OKHttpManager getInstance() {
        if (sOKHttpManager == null) {
            sOKHttpManager = new OKHttpManager();
            sOKHttpManager.mOkHttpClient = new OkHttpClient();
            sOKHttpManager.mHandler = new Handler(Looper.getMainLooper());
        }
        return sOKHttpManager;
    }

    public void sendRequest(GooglePlayRequest<?> request) {
        RequestTask requestTask = new RequestTask(request);
        ThreadPoolProxyFactory.getNormalThreadPoolProxy().execute(requestTask);
    }

    private class RequestTask implements Runnable {

        private GooglePlayRequest mRequest;

        public RequestTask(GooglePlayRequest request) {
            mRequest = request;
        }

        @Override
        public void run() {
            try {
                SystemClock.sleep(2000);//模拟网络延时
                //发起同步请求
                String url = mRequest.getUrl();
                Log.d(TAG, "run: request url " + url);
                Request request = new Request.Builder().get().url(url).build();
                Response response = mOkHttpClient.newCall(request).execute();
                String result = response.body().string();
                Log.d(TAG, "run: response result " + result);
                postResponse(mRequest.parseNetworkResponse(result));
            } catch (IOException e) {
                postError(e.getLocalizedMessage());
            }
        }

        private void postResponse(final GooglePlayResponse result) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mRequest.getNetworkListener().onResponse(result.getResponse());
                }
            });
        }

        private void postError(final String error) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mRequest.getNetworkListener().onFailure(error);
                }
            });
        }
    }

}
