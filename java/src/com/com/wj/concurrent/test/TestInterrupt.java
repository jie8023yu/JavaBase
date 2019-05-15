package com.com.wj.concurrent.test;

import java.util.concurrent.TimeUnit;

public class TestInterrupt {

    public static void main(String[] args) throws Exception{
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        break;
                    }
                }
            }
        });


        thread.start();

        Thread.sleep(1000);

        thread.interrupt();
        boolean interrupted = thread.isInterrupted();
        System.out.println(interrupted);
        TimeUnit.SECONDS.sleep(2);
    }
}
