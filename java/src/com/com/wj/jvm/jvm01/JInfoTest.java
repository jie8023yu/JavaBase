package com.com.wj.jvm.jvm01;

/**
 * -Xms20m -Xmx20m -XX:+PrintGC -Xmn2m
 *
 * 这样设置参数 不会打印出GC的详细信息，利用jinfo动态设置参数 使其可以打印出详细的GC信息
 *
 *
 */
public class JInfoTest {

    public static void main(String[] args) {


        try {
            while (true) {
                for (int i = 0; i < 10; i++) {
                    byte[] b = new byte[1024 * 1024 * 1];
                }

                Thread.sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
