package com.com.wj.concurrent.test;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class UnsafeLazyInitialization {

    private static MyObject instance;

    public static MyObject getInstance() {
        if (null == instance)      // A线程执行
            instance = new MyObject();  // B线程执行
        return instance;
    }

    /**
     * 提供一个较为安全的方法来做
     * 使用了synchronized来实现同步，导致性能降低
     */
    public synchronized static MyObject getInstance2() {
        if (null == instance)
            instance = new MyObject();
        return instance;
    }

    /**
     * 有问题的双重检测锁方式
     *
     */
    public static MyObject getInstance3() {
        if (null == instance) {
            synchronized (MyObject.class) {
                if (null == instance) {
                    instance = new MyObject();
                }
            }
        }

        return instance;
    }


    public static void main(String[] args) {
        List<Thread> threadList = new ArrayList<>();
//        long begin = System.currentTimeMillis();
        /*for (int i = 0;i < 25;i++) {
            threadList.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(UnsafeLazyInitialization.getInstance());
                }
            }));
        }
        for (Thread thread : threadList) {
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
//        long end = System.currentTimeMillis();
//        System.out.println((end - begin));
//        begin = System.currentTimeMillis();
        threadList.clear();
        /*for (int i = 0;i < 25;i++) {
            threadList.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(UnsafeLazyInitialization.getInstance2());
                }
            }));
        }
        for (Thread thread : threadList) {
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        end = System.currentTimeMillis();
        System.out.println((end - begin));*/

        for (int i = 0;i < 10;i++) {
            threadList.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(UnsafeLazyInitialization.getInstance3());
                }
            }));
        }
        for (Thread thread : threadList) {
            thread.start();
        }

    }


    /**
     * Junit单元测试无法写多线程？？？？
     */
    @Test
    public void test1() {

    }
}
class MyObject {

    public MyObject() {
        try {
            TimeUnit.MILLISECONDS.sleep(10);  //模拟可能的情况
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
