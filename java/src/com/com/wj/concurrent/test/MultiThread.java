package com.com.wj.concurrent.test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Java中main方法启动 ，同时伴随的有多个线程
 */
public class MultiThread {

    public static void main(String[] args) throws Exception{

//        Thread.sleep(1000);
//        new Thread().join();
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo.getThreadId() + "," + threadInfo.getThreadName());
        }
    }



}
