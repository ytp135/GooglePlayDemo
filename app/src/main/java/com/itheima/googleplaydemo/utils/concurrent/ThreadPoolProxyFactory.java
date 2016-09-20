package com.itheima.googleplaydemo.utils.concurrent;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/20 17:26
 * 描述： TODO
 */
public class ThreadPoolProxyFactory {

    private static final String TAG = "ThreadPoolProxyFactory";

    private static ThreadPoolProxy mThreadPoolProxy;

    public static ThreadPoolProxy getNormalThreadPoolProxy() {
        if (mThreadPoolProxy == null) {
            synchronized (ThreadPoolProxyFactory.class) {
                if (mThreadPoolProxy == null) {
                    mThreadPoolProxy = new ThreadPoolProxy();
                }
            }
        }
        return mThreadPoolProxy;
    }
}
