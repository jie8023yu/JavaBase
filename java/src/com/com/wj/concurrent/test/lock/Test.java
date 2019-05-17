package com.com.wj.concurrent.test.lock;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        Mutex mutex = new Mutex();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " run");
                mutex.lock();
                System.out.println(Thread.currentThread().getName() + " 获取锁成功");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mutex.unlock();
                    System.out.println( Thread.currentThread().getName() + " 释放锁成功");
                }

            }
        }).start();


        Thread.sleep(2000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " run");
                mutex.lock();
                System.out.println(Thread.currentThread().getName() + " 获取锁成功");
            }
        }).start();
        System.out.println("main");
    }
}
