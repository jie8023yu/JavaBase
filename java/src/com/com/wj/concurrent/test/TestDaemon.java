package com.com.wj.concurrent.test;

import java.util.concurrent.TimeUnit;

public class TestDaemon {

    private static class MyDaemon extends Thread {

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
//                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " run");
                }
            } catch (Exception e) {

            } finally {
                System.out.println("finally"); //守护线程的finally语句不能保证一定被执行，没演示出来
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyDaemon myDaemon = new MyDaemon();
        myDaemon.setName("myDaemon");
        myDaemon.setDaemon(true);  //注意：如果要设置为守护线程，一定要在启动前设置，否则无效
        myDaemon.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("main is stop");  //当设置为守护线程时，当前线程执行完毕后，守护线程也会停止
        myDaemon.interrupt();
    }
}
