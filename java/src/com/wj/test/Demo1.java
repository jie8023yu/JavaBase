package com.wj.test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo1 {

    int i11 = 0;

    AtomicInteger i1 = new AtomicInteger();


    public void incr() {
//        i1++;
        i1.incrementAndGet();
    }


    Lock lock = new ReentrantLock();

    /**
     * 通过锁实现原子性
     */
    public void incrByLock() {
        try {
            lock.lock();
            i11++;
        } finally {
            lock.unlock();
        }
    }


    public static void main(String...args) throws Exception{

        final Demo1 test = new Demo1();


       /* for (int j = 0;j < 2;j++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0;i < 10000;i++)
                    test.incr();
                }
            }).start();
        }*/


        Thread t1 = new Thread(new Runnable(){

            public void run()  {
                for(int i = 0;i < 10000;i++){
                    test.incrByLock();
                }
            }
        });

        Thread t2 = new Thread(new Runnable(){

            public void run() {
                for(int i = 0;i < 10000;i++)
                    test.incrByLock();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();


//        Thread.sleep(1000);


        System.out.println(test.i11);
    }

}
