package com.itheima.googleplaydemo.utils;

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

    private ThreadPoolExecutor mThreadPoolExecutor;

    private static final int DEFAULT_CORE_POOL_SIZE = 2;
    private static final int DEFAULT_MAXIMUM_POOL_SIZE = 4;

    private static ThreadPoolProxy sThreadPoolProxy;

    public static ThreadPoolProxy getInstance() {
        if (sThreadPoolProxy == null) {
            synchronized (ThreadPoolProxy.class) {
                if (sThreadPoolProxy == null) {
                    sThreadPoolProxy = new ThreadPoolProxy();
                }
            }
        }
        return sThreadPoolProxy;
    }


    /**
     * 初始化线程池
     */

    private ThreadPoolProxy() {
        long keepAliveTime = 3000;
        TimeUnit unit = TimeUnit.MILLISECONDS;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<Runnable>();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
        mThreadPoolExecutor = new ThreadPoolExecutor(DEFAULT_CORE_POOL_SIZE, DEFAULT_MAXIMUM_POOL_SIZE, keepAliveTime, unit, workQueue, threadFactory, handler);
        //上述代码基本等价于
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
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
