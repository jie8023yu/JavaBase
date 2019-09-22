package com.com.wj.jvm.jvm01;

/**
 * 演示新生代
 *
 * 设置参数
 *      -Xms20m -Xmx20m -XX:+PrintGCDetails -Xmn2m -XX:SurvivorRatio=2
 *      没有发生垃圾回收 数组都在老年代
 *      -Xms20m -Xmx20m -XX:+PrintGCDetails -Xmn7m -XX:SurvivorRatio=2
 *          发生了垃圾回收
 *
 *
 */
public class NewSize {


    public static void main(String[] args) {
        int cap = 1024 * 1024 * 2;
        byte[] b1 = new byte[cap];
        byte[] b2 = new byte[cap];
        byte[] b3 = new byte[cap];
        byte[] b4 = new byte[cap];
        byte[] b5 = new byte[cap];
        /*byte[] b6 = new byte[cap];
        byte[] b7 = new byte[cap];
        byte[] b8 = new byte[cap];
        byte[] b9 = new byte[cap];
        byte[] b0 = new byte[cap];*/
    }
}
