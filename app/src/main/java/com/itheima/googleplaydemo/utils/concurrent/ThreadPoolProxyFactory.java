package com.itheima.googleplaydemo.utils.concurrent;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/20 17:26
 * 描述： TODO
 */
public class ThreadPoolProxyFactory {

    private static final String TAG = "ThreadPoolProxyFactory";

    private static ThreadPoolProxy mNormalThreadPoolProxy;
    private static ThreadPoolProxy mDownloadThreadPoolProxy;

    public static ThreadPoolProxy getNormalThreadPoolProxy() {
        if (mNormalThreadPoolProxy == null) {
            synchronized (ThreadPoolProxyFactory.class) {
                if (mNormalThreadPoolProxy == null) {
                    mNormalThreadPoolProxy = new ThreadPoolProxy();
                }
            }
        }
        return mNormalThreadPoolProxy;
    }

    public static ThreadPoolProxy getDownloadThreadPoolProxy() {
        if (mDownloadThreadPoolProxy == null) {
            synchronized (ThreadPoolProxyFactory.class) {
                if (mDownloadThreadPoolProxy == null) {
                    mDownloadThreadPoolProxy = new ThreadPoolProxy(5, 5);
                }
            }
        }
        return mDownloadThreadPoolProxy;
    }
}
