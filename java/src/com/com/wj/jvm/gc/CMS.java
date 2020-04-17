package com.com.wj.jvm.gc;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * CMS回收流程
 *
 * 参数设置：
 * -XX:+UseConcMarkSweepGC(老年代使用CMS)
 * -verbose:gc -Xmx20M -Xms20M -Xmn10M -XX:+PrintGCDetails -XX:+UseConcMarkSweepGC
 * 日志信息：
 *1111
 * [GC (Allocation Failure) [ParNew: 6083K->668K(9216K), 0.0029374 secs] 6083K->4766K(19456K), 0.0029984 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * 2222
 * [GC (Allocation Failure) [ParNew: 5003K->98K(9216K), 0.0037579 secs] 9101K->8939K(19456K), 0.0037794 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [GC (CMS Initial Mark) [1 CMS-initial-mark: 8841K(10240K)] 13090K(19456K), 0.0002909 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [CMS-concurrent-mark-start]
 * 3333
 * [CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [CMS-concurrent-preclean-start]
 * [CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [CMS-concurrent-abortable-preclean-start]
 * [CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [GC (CMS Final Remark) [YG occupancy: 6453 K (9216 K)][Rescan (parallel) , 0.0008414 secs][weak refs processing, 0.0000076 secs][class unloading, 0.0001718 secs][scrub symbol table, 0.0005036 secs][scrub string table, 0.0001244 secs][1 CMS-remark: 8841K(10240K)] 15294K(19456K), 0.0016948 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * 我们发现在最终标记阶段，发生了Rescan
 * [CMS-concurrent-sweep-start]
 * [CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [CMS-concurrent-reset-start]
 * [CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * 4444
 * Heap
 *  par new generation   total 9216K, used 6726K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
 *   eden space 8192K,  80% used [0x00000000fec00000, 0x00000000ff279060, 0x00000000ff400000)
 *   from space 1024K,   9% used [0x00000000ff400000, 0x00000000ff418870, 0x00000000ff500000)
 *   to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
 *  concurrent mark-sweep generation total 10240K, used 8832K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
 *  Metaspace       used 3221K, capacity 4496K, committed 4864K, reserved 1056768K
 *   class space    used 350K, capacity 388K, committed 512K, reserved 1048576K
 */
public class CMS {

    public static void main(String[] args) throws Exception {

//        test();
//        test2();
//        test3();

//        test4();

        test8();
    }

    /**
     * 常规测试
     */
    public static void test() throws InterruptedException {
        int size = 1024 * 1024;
        byte[] myAlloc1 = new byte[4 * size];
        System.out.println("1111");
        byte[] myAlloc2 = new byte[4 * size];
        System.out.println("2222");
        byte[] myAlloc3 = new byte[1 * size];
        System.out.println("3333");
//        Thread.sleep(10);
        testT();
        Thread.sleep(1000);
        System.out.println("4444");
        myAlloc3 = null;
//        System.gc();
        Thread.sleep(15000);
    }

    public static void testT() throws InterruptedException {
        byte[] myAlloc4 = getM(5);
        Thread.sleep(3000);
        System.out.println("----------------------end");
    }

    /**
     * 测试CMS CMSScheduleRemarkEdenSizeThreshold、CMSScheduleRemarkEdenPenetration，默认值分别是2M、50%这两个参数
     * 两个参数组合起来的意思是预清理后，eden空间使用超过2M时启动可中断的并发预清理（CMS-concurrent-abortable-preclean），直到eden空间使用率达到50%时中断，进入remark阶段。
     * -XX:CMSMaxAbortablePrecleanTime=1000
     * -XX:CMSScheduleRemarkEdenPenetration=10
     *-XX:CMSScheduleRemarkEdenPenetration=50
     * CMSScavengeBeforeRemark
     *
     * -XX:+CMSScavengeBeforeRemark
     *
     *
     * (未验证通过啊)
     *
     * */
    public static void test2() throws InterruptedException {
//        Thread.sleep(5000);
        int size = 1024 * 1024;
        List list = new ArrayList();
//        list.add(new byte[size]);
//        byte[] myAlloc2 = new byte[5 * size];
//        list.add(myAlloc1);

        list.add(getM(2));
        Thread.sleep(3500);

        System.out.println("------phase1--------------");
        byte[] myAlloc1 = new byte[2 * size];
        byte[] myAlloc2 = new byte[2 * size];
        byte[] myAlloc3 = new byte[2 * size];
        Thread.sleep(5000);
        list.add(getM(3));
        System.out.println("------phase2--------------");

         Thread.sleep(2000);
        Thread.sleep(15000);
    }


    /**
     * -Xms20m -Xmx20m -Xmn10m -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:CMSMaxAbortablePrecleanTime=5000 -XX:CMSScheduleRemarkEdenSizeThreshold=1M
     * @throws InterruptedException
     */
    public static void test3() throws InterruptedException {
        byte[] b1 = getM(2);
        byte[] b2 = getM(2);
        byte[] b3 = getM(3);
        System.out.println("minor gc");
        byte[] b4 = getM(1);
        TimeUnit.SECONDS.sleep(8);
    }

    public static byte[] getM(int size) {
        byte[] btye = new byte[size * 1024 * 1024];
        System.out.println("申请" + size + "内存");
        return btye;
    }

    /**
     * -Xms30m
     * -Xmx30m
     * -Xmn10m
     * -XX:+UseConcMarkSweepGC
     * -XX:+PrintGCDetails
     * -XX:+PrintGCDateStamps
     * -XX:CMSMaxAbortablePrecleanTime=5000
     * -XX:CMSScheduleRemarkEdenSizeThreshold=2M
     * -XX:CMSScheduleRemarkEdenPenetration=20
     * -XX:+CMSScavengeBeforeRemark
     * @throws Exception
     */
    public static void test4() throws Exception {

        byte[] by1 = getM(10);
//        byte[] by2 = new byte[2];
//        byte[] by3 = getM(3);
//        System.out.println("minor gc");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("直接扔10M");
        byte[] by4 = getM(1);
        TimeUnit.SECONDS.sleep(3);
        byte[] by5 = getM(3);
        TimeUnit.SECONDS.sleep(20);
    }

    public static void test5() throws Exception {
        byte[] b1 = getM(2);
        byte[] b2 = getM(2);
        byte[] b3 = getM(3);
        System.out.println("minor gc");
        TimeUnit.SECONDS.sleep(2);
        byte[] b4 = getM(1);
        System.out.println("申请1M内存");
        TimeUnit.SECONDS.sleep(3);
        //byte[] b5 = getM(2);
        System.out.println("------------phase1");
        TimeUnit.SECONDS.sleep(10);
        //byte[] b6 = getM(1);
        System.out.println("--------------phase2");
        byte[] b7 = getM(1);
        TimeUnit.SECONDS.sleep(5);

    }

    public static void testt() {
        WeakReference<byte[]> wf = new WeakReference<byte[]>(getM(2));
    }



    public static void test6() throws Exception {
        byte[] b1 = getM(2);
        byte[] b2 = getM(2);
        byte[] b3 = getM(3);
        System.out.println("minor gc");
        TimeUnit.SECONDS.sleep(2);
        byte[] b4 = getM(1);
        System.out.println("申请1M内存");
        TimeUnit.SECONDS.sleep(3);
        //byte[] b5 = getM(2);
        t();
        System.out.println("------------phase1");
        TimeUnit.SECONDS.sleep(10);
        //byte[] b6 = getM(1);
        System.out.println("--------------phase2");
        byte[] b7 = getM(1);
        TimeUnit.SECONDS.sleep(10);
    }

    private static void t() throws Exception {
        //byte[] b5 = getM(2);
        WeakReference<byte[]> wf = new WeakReference<byte[]>(getM(6));
        System.out.println("----------------t");
        wf = null;
    }

    /**
     * 测试CMS垃圾回收触发
     * -Xmn10M -Xms50M -Xmx50M
     * @throws Exception
     *
     *
     * 经过测试发现，指定-XX:CMSInitiatingOccupancyFraction这个值只要超过了50%后，基本上没用了，一旦使用超过50%，就会发生CMS
     * 默认值测试过程：发现也是一样的，不超过50%不会出现CMS，超过这个值后CMS就开启了
     * 看有个人的博客上写明：触发CMS的老年代比例大小的计算方法：
     * 通过java -XX:+PrintFlagsFinal -version
     * 首先查看CMSInitiatingOccupancyFraction这个值 如果大于0 ，直接%的概率
     * 如果小于0， 在JDK1.8的版本中，这个值是-1，就会走下面的计算方式：
     * io就是CMSInitiatingOccupancyFraction这个值
     * tr就是MinHeapFreeRatio这个值，JDK1.8默认是40
     * assert(io <= 100 && tr <= 100, "Check the arguments");
     *       if (io >= 0) {
     *         _initiating_occupancy = (double)io / 100.0;
     *       } else {
     *         _initiating_occupancy = ((100 - MinHeapFreeRatio) +
     *                                  (double)(tr * MinHeapFreeRatio) / 100.0)
     *                                 / 100.0;
     *       }
     *通过上述两个值，计算出的_initiating_occupancy 就是 （60 + 3200 / 100） / 100 = 92
     * 实际验证并不是这样，不明白为什么
     * 在centos上验证也是这样，采用的jdk版本都是1.8
     */
    public static void test8() throws Exception {

//        byte[] b1 = new byte[1024 * 512 * 11];
        List list = new ArrayList();
        byte[] b2 = getM(10);
        list.add(getM(5));
        list.add(getM(10));
        list.add(getM(2));
//        getM();
//        byte[] b3 = getM(20);

        list.add(new byte[28 * 1024]);
        list.add(new byte[27648 - 139 - 1024 * 20]);
        list.add(getM(2));

        TimeUnit.SECONDS.sleep(5);
    }



}
