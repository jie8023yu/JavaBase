package com.com.wj.jvm.memory;
/**
 * 通过jcmd pid Thread.print来分析堆栈信息，查找死锁问题
 */

import java.util.concurrent.TimeUnit;

public class DeadLock {

    private Object a = new Object();

    private Object b = new Object();

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        new Thread(() -> {
            synchronized (deadLock.a) {
                System.out.println("a运行");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (deadLock.b) {

                }
            }
        }).start();

        new Thread(() -> {
            synchronized (deadLock.b) {
                System.out.println("b运行");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (deadLock.a) {

                }
            }
        }).start();
    }
}
