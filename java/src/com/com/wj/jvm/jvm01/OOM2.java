package com.com.wj.jvm.jvm01;

import java.util.LinkedList;
import java.util.List;

/**
 * 演示堆溢出
 *      -Xms5m -Xmx5m -XX:+PrintGC
 *
 *
 *      加参数
 *      -XX:+HeapDumpOnOutOfMemoryError
 *
 */
public class OOM2 {

    public static void main(String[] args) {
        /*
        * 报错信息如下：
        * java.lang.OutOfMemoryError: GC overhead limit exceeded
        *
        * 某个循环里不停的循环的分配对象，但是分配的太多，把堆撑爆了
        *
        * */
        List<Object> list = new LinkedList();
        int i = 0;
        while (true) {
            i++;
            if (i % 10000 == 0) System.out.println("i = " + i);
            list.add(new Object());
        }

        /*
        * 报错信息如下：
        * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        * 在分配对象时，有一个比较大的对象，现有内存不够用
        * */
//        String[] strings = new String[100000000];
    }
}
