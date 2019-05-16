package com.com.wj.concurrent.test;

public class Demo04 {

    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();
        final Thread lock2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程执行");
            }
        });
        Thread t1 = new Thread(new Thread1(lock2),"t1");
        Thread t2 = new Thread(new Thread2(lock2),"t2");
        t1.start();
        Thread.sleep(1000);
        t2.start();
    }

}


class Thread1 implements Runnable {

    private Object lock;

    public Thread1(Object lock) {
       this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            try {
                System.out.println(Thread.currentThread().getName() + " start ");
                lock.wait();
                System.out.println(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}


class Thread2 implements Runnable {

    private Object lock;

    public Thread2(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " start ");
//            lock.notify();
//            if (lock instanceof Thread) ((Thread) lock).start();
            System.out.println(Thread.currentThread().getName() + " end");
        }
    }
}

