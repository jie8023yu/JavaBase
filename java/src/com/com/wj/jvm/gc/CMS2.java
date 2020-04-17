package com.com.wj.jvm.gc;

import java.util.concurrent.TimeUnit;

public class CMS2 {

    public static void main(String[] args) throws Exception {

//        test();
        test2();
    }

    /**
     * 参数设置：-Xmn10M -Xms20M -Xmx20M -XX:+UseConcMarkSweepGC -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps
     * @throws InterruptedException
     */
    public static void test() throws InterruptedException {
        int size = 1024 * 1024;
        byte[] myAlloc2 = new byte[10 * size];
        byte[] myAlloc = new byte[512 * 1024];
        Thread.sleep(3000);
//        byte[] myAlloc3 = new byte[2 * size];
//        byte[] myAlloc4 = new byte[2 * size];
        byte[] myAlloc3 = new byte[512 * 1024];
        System.out.println("test");
        Thread.sleep(20000);
    }

    /**
     * 参数设置
     * -Xms20m -Xmx20m -Xmn10m -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
     * -XX:CMSMaxAbortablePrecleanTime=5000 -XX:CMSScheduleRemarkEdenSizeThreshold=1M -XX:CMSScheduleRemarkEdenPenetration=35
     * @throws InterruptedException
     */
    public static void test2() throws InterruptedException {
        byte[] bb = getM(12);
        TimeUnit.SECONDS.sleep(1);
        byte[] b1 = getM(2);
        System.out.println("minor gc");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("----------------phase 2------------------");
        byte[] b5 = getM(2);
        TimeUnit.SECONDS.sleep(2);
        System.out.println("----------------phase 3------------------");
        byte[] b6 = getM(1);
        TimeUnit.SECONDS.sleep(20);
    }

    public static byte[] getM(int m) {
        return new byte[1024 * 1024 * m];
    }
}
