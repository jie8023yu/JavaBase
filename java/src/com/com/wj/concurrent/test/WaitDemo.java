package com.com.wj.concurrent.test;

/**
 * 测试wait
 */
public class WaitDemo {


    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadA1(),"t1");
        synchronized (t1) {
            try {
                System.out.println(Thread.currentThread().getName() + " start t1");
                t1.start();
                System.out.println(Thread.currentThread().getName() + " call wait");
                t1.wait(3);
                System.out.println(Thread.currentThread().getName() + " continue");
                t1.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class ThreadA1 implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " run");
        while (true) {}
    }
}
