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
public class Test {
    public static void main(String[] args) {
//        test();
//        test2();
        test3();
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
     */
    public static void test4() {
        int size = 1024 * 1024;
        byte[] myAlloc = new byte[size];
    }
}
