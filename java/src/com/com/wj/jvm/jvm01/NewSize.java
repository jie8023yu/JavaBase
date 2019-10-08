package com.com.wj.jvm.jvm01;

/**
 * 演示新生代
 *
 * 设置参数
 *      -Xms20m -Xmx20m -XX:+PrintGCDetails -Xmn3m -XX:SurvivorRatio=2   注意：此处设置为2m时，会发生GC，原因是1024k不够Eden使用
 *          没有发生垃圾回收 数组都在老年代
 *      -Xms20m -Xmx20m -XX:+PrintGCDetails -Xmn7m -XX:SurvivorRatio=2
 *          发生了垃圾回收
 *          新生代存了部分数组，老年带也保存了部分数组
 *      -Xms20m -Xmx20m -XX:+PrintGCDetails -Xmn16m -XX:SurvivorRatio=8
 *          新生代可以放下所有数组，老年代为空
 *      -Xms20m -Xmx20m -XX:+PrintGCDetails -XX:NewRatio=2
 *           表示新生代与老年代 的比例 为 1 ： 2
 *
 *
 */
public class NewSize {


    public static void main(String[] args) {
        int cap = 1024 * 1024 * 1;
        byte[] b1 = new byte[cap];
        byte[] b2 = new byte[cap];
        byte[] b3 = new byte[cap];
        byte[] b4 = new byte[cap];
        byte[] b5 = new byte[cap];
        byte[] b6 = new byte[cap];
        byte[] b7 = new byte[cap];
        byte[] b8 = new byte[cap];
        byte[] b9 = new byte[cap];
        byte[] b0 = new byte[cap];
    }
}
