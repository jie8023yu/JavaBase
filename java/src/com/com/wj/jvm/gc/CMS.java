package com.com.wj.jvm.gc;

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

    public static void main(String[] args) throws InterruptedException {
        int size = 1024 * 1024;
        byte[] myAlloc1 = new byte[4 * size];
        System.out.println("1111");
        byte[] myAlloc2 = new byte[4 * size];
        System.out.println("2222");
        byte[] myAlloc3 = new byte[4 * size];
        System.out.println("3333");
        byte[] myAlloc4 = new byte[2 * size];
        Thread.sleep(1000);
        System.out.println("4444");

    }
}
