package com.com.wj.concurrent.test.lockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport
 *
 * 可以唤醒某个指定的线程，这在之前是很难做到的
 *
 * unpark可以先于park调用，如果先调用了unpark,再调用一次park也不会阻塞当前线程
 * 再调用一次就可以阻塞住了
 *
 */
public class TestLockSupport {

    public static void main(String[] args) {

        Thread t = new Thread(() -> {
           for (int i = 0 ; i < 10 ; i++) {
               System.out.println(i);
               if (i == 5) {
                   //当前线程停止（阻塞）
                   LockSupport.park();
//                   LockSupport.park();
               }
               try {
                   TimeUnit.SECONDS.sleep(1);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });

        t.start();
        LockSupport.unpark(t);

        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("after 8s");
        //让阻塞的线程继续运行
        LockSupport.unpark(t);
    }
}
