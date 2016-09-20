package com.itheima.googleplaydemo.utils.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/20 15:08
 * 描述： 代理线程池的操作，用户不需要关心具体怎么使用线程池
 */
public class ThreadPoolProxy {

    private static final String TAG = "ThreadPoolProxy";
    private ThreadPoolExecutor mThreadPoolExecutor;

    private int DEFAULT_CORE_POOL_SIZE = 2;
    private int DEFAULT_MAXIMUM_POOL_SIZE = 4;

    private int mCorePoolSize = DEFAULT_CORE_POOL_SIZE;
    private int mMaximumPoolSize = DEFAULT_MAXIMUM_POOL_SIZE;




    /**
     * 初始化线程池
     */

    public ThreadPoolProxy() {
        long keepAliveTime = 3000;
        TimeUnit unit = TimeUnit.MILLISECONDS;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<Runnable>();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
        mThreadPoolExecutor = new ThreadPoolExecutor(mCorePoolSize, mMaximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    public ThreadPoolProxy(int corePoolSize, int maximumPoolSize) {
        int mCorePoolSize = corePoolSize;
        int mMaximumPoolSize = maximumPoolSize;
        long keepAliveTime = 3000;
        TimeUnit unit = TimeUnit.MILLISECONDS;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<Runnable>();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
        mThreadPoolExecutor = new ThreadPoolExecutor(mCorePoolSize, mMaximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }


    /**
     * 执行任务
     */
    public void execute(Runnable task) {
        mThreadPoolExecutor.execute(task);
    }

    /**
     * 移除任务
     */
    public void remove(Runnable task) {
        mThreadPoolExecutor.remove(task);
    }

}
