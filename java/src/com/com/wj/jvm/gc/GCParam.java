package com.com.wj.jvm.gc;

/**
 * -verbose:gc 输出虚拟机的详细的垃圾回收参数
 * -Xms20M
 * -Xmx20M
 * -Xmn10M
 * -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8  eden:survivor比例 8 ： 1
 *
 *
 *
 * PSYoungGen：Parallel Scavenge(新生代垃圾收集器)
 * ParOldGen: Parallel Old(老年代垃圾收集器)
 *
 */
public class GCParam {
    public static void main(String[] args) {
//        test();
//        test2();
//        test3();
//        test4();
//        test5();
        test6();
    }

    /**
     * 设置如下参数
     * -verbose:gc 输出虚拟机的详细的垃圾回收参数
     *  * -Xms20M
     *  * -Xmx20M
     *  * -Xmn10M
     *  * -XX:+PrintGCDetails
     *  * -XX:SurvivorRatio=8
     *  GC日志如下：
     *  [GC (Allocation Failure) [PSYoungGen: 5915K->776K(9216K)] 5915K->4880K(19456K), 0.0033214 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * hello world
     * Heap
     *  PSYoungGen      total 9216K, used 4169K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
     *   eden space 8192K, 41% used [0x00000000ff600000,0x00000000ff950508,0x00000000ffe00000)
     *   from space 1024K, 75% used [0x00000000ffe00000,0x00000000ffec2020,0x00000000fff00000)
     *   to   space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
     *  ParOldGen       total 10240K, used 4104K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
     *   object space 10240K, 40% used [0x00000000fec00000,0x00000000ff002020,0x00000000ff600000)
     *  Metaspace       used 3133K, capacity 4496K, committed 4864K, reserved 1056768K
     *   class space    used 343K, capacity 388K, committed 512K, reserved 1048576K
     *
     *   观察日志可以发现，新生代经过垃圾回收由5915k -> 776k 总堆大小由5915k -> 4880k
     *   可以发现Heap中的ParOldGen老年代空间被占用了4104k，这个数据怎么来的
     *   新生代经过垃圾回收，回收了5915-776= 5139k,总堆回收了：5915-4880=1035k
     *   5139-1035=4104k 刚好是等于这个老年代使用的容量
     */
    public static void test() {
        int size = 1024 * 1024;

        byte[] myAlloc = new byte[2 * size];
        byte[] myAlloc2 = new byte[2 * size];
        byte[] myAlloc3 = new byte[3 * size];
//        byte[] myAlloc4 = new byte[1 * size];
        System.out.println("hello world");
    }

    /**
     *参数跟上面设置一样 -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * [GC (Allocation Failure) [PSYoungGen: 8131K->840K(9216K)] 8131K->6992K(19456K), 0.0041913 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [Full GC (Ergonomics) [PSYoungGen: 840K->0K(9216K)] [ParOldGen: 6152K->6772K(10240K)] 6992K->6772K(19456K), [Metaspace: 3128K->3128K(1056768K)], 0.0053257 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
     * hello world
     * Heap
     *  PSYoungGen      total 9216K, used 2371K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
     *   eden space 8192K, 28% used [0x00000000ff600000,0x00000000ff850c50,0x00000000ffe00000)
     *   from space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
     *   to   space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
     *  ParOldGen       total 10240K, used 6772K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
     *   object space 10240K, 66% used [0x00000000fec00000,0x00000000ff29d278,0x00000000ff600000)
     *  Metaspace       used 3169K, capacity 4496K, committed 4864K, reserved 1056768K
     *   class space    used 344K, capacity 388K, committed 512K, reserved 1048576K
     *
     *   FullGC会出现STW，实际调优都是尽量避免FullGC
     */
    public static void test2() {
        int size = 1024 * 1024;

        byte[] myAlloc = new byte[2 * size];
        byte[] myAlloc2 = new byte[2 * size];
        byte[] myAlloc3 = new byte[2 * size];
        byte[] myAlloc4 = new byte[2 * size];
        System.out.println("hello world");
    }

    /**
     * 注意观察，如果按照下面这样写，跟test2中的结果就不一样
     * [GC (Allocation Failure) [PSYoungGen: 6083K->840K(9216K)] 6083K->4944K(19456K), 0.0033348 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * hello world
     * Heap
     *  PSYoungGen      total 9216K, used 7305K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
     *   eden space 8192K, 78% used [0x00000000ff600000,0x00000000ffc50588,0x00000000ffe00000)
     *   from space 1024K, 82% used [0x00000000ffe00000,0x00000000ffed2020,0x00000000fff00000)
     *   to   space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
     *  ParOldGen       total 10240K, used 4104K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
     *   object space 10240K, 40% used [0x00000000fec00000,0x00000000ff002020,0x00000000ff600000)
     *  Metaspace       used 3189K, capacity 4496K, committed 4864K, reserved 1056768K
     *   class space    used 344K, capacity 388K, committed 512K, reserved 1048576K
     *
     *   可以发现没有出现fullGC，当新生代无法容纳一个新对象，新对象直接在老年代分配
     */
    public static void test3() {
        int size = 1024 * 1024;

        byte[] myAlloc = new byte[2 * size];
        byte[] myAlloc2 = new byte[2 * size];
        byte[] myAlloc3 = new byte[3 * size];
        byte[] myAlloc4 = new byte[3 * size];
        System.out.println("hello world");
    }

    /**
     * java -XX:+PrintCommandLinFlags -version
     * 保持上述参数的同时，增加-XX:PretenureSizeThreshold=4194304 （4M，单位是kb） 阈值
     * 新创建的对象大小大于这个设置，直接在老年代创建，不在新生代创建
     * 这个参数必须配合串行处理器使用 -XX:+UseSerialGC
     */
    public static void test4() {
        int size = 1024 * 1024;
        byte[] myAlloc = new byte[5 * size];
    }

    /**
     * -XX:MaxTenuringThreshold=5 调节对象晋升（promote）到老年代阈值的GC中，设置阈值的最大值
     *          参数默认值为15，CMS默认值为6，G1默认是15（在JVM中，该数值是由4个bit表示的，所以最大值1111,即15）
     * 经历了多少次GC后，存活的对象会在from survivor和to survivor中来回存放，前提是这两个空间有足够的大小来存放这些数据，
     * 在GC算法中，当进行交换时，如果对象数据大于survivor的50%，会将所有的对象都晋升到老年代，不会再按照阈值来计算
     */
    public static void test5() {
        int size = 1024 * 1024;
        byte[] myAlloc = new byte[2 * size];
        byte[] myAlloc2 = new byte[2 * size];
        byte[] myAlloc3 = new byte[2 * size];
        byte[] myAlloc4 = new byte[2 * size];
        byte[] myAlloc5 = new byte[2 * size];
    }

    /**
     *启动参数：
     * -XX:TargetSurvivorRatio=60 超过60%这个百分比，重新计算晋升阈值
     * -verbose:gc -Xmx200M -Xmn50M -XX:TargetSurvivorRatio=60 -XX:+PrintTenuringDistribution -XX:+PrintGCDetails -XX:+PrintGCDateStamps
     * -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:MaxTenuringThreshold=3
     *
     * 日志：
     * 2020-03-24T22:32:01.148+0800: [GC (Allocation Failure) 2020-03-24T22:32:01.152+0800: [ParNew
     * Desired survivor size 3145728 bytes, new threshold 3 (max 3)
     * - age   1:    1703968 bytes,    1703968 total
     * : 40144K->1716K(46080K), 0.0023322 secs] 40144K->1716K(199680K), 0.0062569 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
     * 1111111
     * 2020-03-24T22:32:02.159+0800: [GC (Allocation Failure) 2020-03-24T22:32:02.159+0800: [ParNew
     * Desired survivor size 3145728 bytes, new threshold 3 (max 3)
     * - age   1:       1096 bytes,       1096 total
     * - age   2:    1696528 bytes,    1697624 total
     * : 42447K->1906K(46080K), 0.0019508 secs] 42447K->1906K(199680K), 0.0020038 secs] [Times: user=0.09 sys=0.05, real=0.00 secs]
     * 22222
     * 2020-03-24T22:32:03.167+0800: [GC (Allocation Failure) 2020-03-24T22:32:03.167+0800: [ParNew
     * Desired survivor size 3145728 bytes, new threshold 3 (max 3)
     * - age   1:         56 bytes,         56 total
     * - age   2:       1096 bytes,       1152 total
     * - age   3:    1696352 bytes,    1697504 total
     * : 42432K->1901K(46080K), 0.0028718 secs] 42432K->1901K(199680K), 0.0029649 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * 3333
     * 2020-03-24T22:32:04.178+0800: [GC (Allocation Failure) 2020-03-24T22:32:04.178+0800: [ParNew
     * Desired survivor size 3145728 bytes, new threshold 3 (max 3)
     * - age   1:         48 bytes,         48 total
     * - age   2:         56 bytes,        104 total
     * - age   3:       1096 bytes,       1200 total
     * : 42629K->162K(46080K), 0.0117894 secs] 42629K->1843K(199680K), 0.0118774 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
     * 4444
     * 2020-03-24T22:32:05.196+0800: [GC (Allocation Failure) 2020-03-24T22:32:05.196+0800: [ParNew
     * Desired survivor size 3145728 bytes, new threshold 1 (max 3)
     * - age   1:    3145824 bytes,    3145824 total
     * - age   2:         48 bytes,    3145872 total
     * - age   3:         56 bytes,    3145928 total
     * : 40894K->3112K(46080K), 0.0060554 secs] 42575K->4795K(199680K), 0.0061467 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
     * 5555
     * 2020-03-24T22:32:06.208+0800: [GC (Allocation Failure) 2020-03-24T22:32:06.208+0800: [ParNew
     * Desired survivor size 3145728 bytes, new threshold 3 (max 3)
     * - age   1:         48 bytes,         48 total
     * : 43847K->6K(46080K), 0.0034150 secs] 45529K->4761K(199680K), 0.0034577 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * 6666
     * hello world
     * Heap
     *  par new generation   total 46080K, used 15962K [0x00000000f3800000, 0x00000000f6a00000, 0x00000000f6a00000)
     *   eden space 40960K,  38% used [0x00000000f3800000, 0x00000000f4794f10, 0x00000000f6000000)
     *   from space 5120K,   0% used [0x00000000f6000000, 0x00000000f6001b10, 0x00000000f6500000)
     *   to   space 5120K,   0% used [0x00000000f6500000, 0x00000000f6500000, 0x00000000f6a00000)
     *  concurrent mark-sweep generation total 153600K, used 4754K [0x00000000f6a00000, 0x0000000100000000, 0x0000000100000000)
     *  Metaspace       used 3223K, capacity 4500K, committed 4864K, reserved 1056768K
     *   class space    used 350K, capacity 388K, committed 512K, reserved 1048576K
     *
     */
    public static void test6()  {
        try {
            byte[] byte1 = new byte[512 * 1024];
            byte[] byte2 = new byte[512 * 1024];
            myGc();
            Thread.sleep(1000);
            System.out.println("1111111");
            myGc();
            Thread.sleep(1000);
            System.out.println("22222");
            myGc();
            Thread.sleep(1000);
            System.out.println("3333");
            myGc();
            Thread.sleep(1000);
            System.out.println("4444");

            byte[] byte3 = new byte[1024 * 1024];
            byte[] byte4 = new byte[1024 * 1024];
            byte[] byte5 = new byte[1024 * 1024];
            myGc();
            Thread.sleep(1000);
            System.out.println("5555");

            myGc();
            Thread.sleep(1000);
            System.out.println("6666");

            System.out.println("hello world");

        } catch (Exception e) {

        }
    }

    private static void myGc() {
        for (int i = 0 ; i < 40 ; i++) {
            byte[] byteArray = new byte[1024 * 1024];
        }
    }

}
