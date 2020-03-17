package com.com.wj.concurrent.test.reference;

import java.lang.ref.SoftReference;

/**
 * 软引用，当内存不够用时进行回收
 *
 * 主要用作缓存
 *
 * -Xms20M -Xmx20M
 */
public class T02_SoftReference {

    public static void main(String[] args) {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024 *  1024 * 10]);
        System.out.println(m.get());
//        m = null;
        System.gc();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(m.get());

        byte[] b = new  byte[1024 * 1024 * 11];
        System.out.println(m.get());

    }
}
