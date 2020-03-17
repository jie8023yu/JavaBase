package com.wj.threadpool;

import java.util.concurrent.*;

/**
 * 阿里开发手册固定
 * 不要使用Executors去创建线程池，而是通过ThreadPoolExecutor的方式创建
 * 说明：Executors返回的线程池对象的弊端如下：
 * FixedThreadPool和SingleThreadPool：
 * 允许的请求队列长度为Integer.MAX_VALUE，可能会堆积大量的请求，从而导致OOM
 * CachedThreadPool
 * 允许创建的线程数量为Integer.MAX_VALUE,可能会创建大量的线程，从而导致OOM
 */
public class MyThreadPool2 {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,4,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()
                );

        /*
        最后一个参数：拒绝策略，JDK默认提供的有4种
        实际当中，不会使用JDK的默认策略，会自定义策略
        实际会把任务保存到MQ中，或者保存到数据库
         */
    }
}
