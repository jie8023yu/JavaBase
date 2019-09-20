package com.com.wj.jvm.jvm01;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示堆溢出
 *      -Xms5m -Xmx5m -XX:+PrintGC
 */
public class OOM {

    public static void main(String[] args) {
        List<Object> list = new ArrayList();
        int i = 0;
        while (true) {
            i++;
            if (i % 10000 == 0) System.out.println("i = " + i);
            list.add(new Object());
        }
    }
}
