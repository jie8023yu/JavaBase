package com.com.wj.jvm.jvm01;

/**
 * 演示栈上分配
 *
 * 设置虚拟机参数
 *  -server -Xms10m -Xmx10m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations -XX:-UseTLAB
 *   -XX:+ 开启
 *   -XX:- 关闭
 *
 *  测试不开启 -XX:+DoEscapeAnalysis ---》-XX:-DoEscapeAnalysis
 *
 *      演示栈上分配带来的性能提升
 *  参数说明：
 *      -server  JVM运行的模式   只有在server模式下才能进行逃逸分析
 *          client/mix（混合模式）
 *      -XX:+DoEscapeAnalysis ： 是否启动逃逸分析
 *      -XX:+PrintGC ： 打印GC日志
 *      -XX:+EliminateAllocations ： 变量替换
 *              比如说User对象的两个属性是否可以进行拆分，分开在栈上进行分配
 *      -XX:-UseTLAB ：
 *          TLAB：threa local allocate buffer  线程本地分配缓冲
 *
 *          大量线程同时来申请内存，为了保证不同线程申请到的内存空间不是同一快，要保证线程安全，采用加锁的方式就会影响性能。
 *          事先在堆里面为每个线程申请私有内存，每个线程只去自己分配好的Buffer里面取申请线程，可以降低不同线程竞争带来的性能影响。
 *          分配这个buffer的操作要保证原子性和安全性。
 *
 *          
 *
 */
public class StackAllocate {




    public static class User {
        private int id;
        private String username;


    }


    public static void alloc() {
        User u = new User();
        u.id = 5;
        u.username = "test";
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}
