package com.com.wj.concurrent.test.lock;

import com.com.wj.concurrent.test.ThreadA;

import java.util.concurrent.locks.LockSupport;

public class Demo01 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
//                LockSupport.park(this);
                long start = System.currentTimeMillis();
                int millons = 1000 * 10;
               while (! Thread.currentThread().isInterrupted()) {
                    if (System.currentTimeMillis() - start - millons > 0) break;
               }

               if (Thread.currentThread().isInterrupted()) {
                   System.out.println("逻辑执行");
               } else {
                   System.out.println("超时了");
               }

            }


            public void test() {
                System.out.println("hello it's me");
            }
        });
        t1.start();
        Thread.sleep(1000);
//        LockSupport.unpark(t1);
        t1.interrupt();
    }
}
