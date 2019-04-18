package com.com.wj.concurrent.test;


import java.util.concurrent.TimeUnit;

public class Demo02 {

    private int i = 0;

    public static void main(String[] args) throws InterruptedException {
        final Demo02 d = new Demo02();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    d.get(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    d.set();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/

    }


    public synchronized int get(long mills) throws InterruptedException {
        int result = 0;
        long future = System.currentTimeMillis() + mills;
        long remaining = mills;
        while (i == 0 && remaining > 0) {
            wait(3000);
            System.out.println(1);
            remaining = future - System.currentTimeMillis();
        }

        if (i != 0) result = i;
        System.out.println(result);
        return result;
    }

    public  void set() throws InterruptedException {
        synchronized (this) {
            notifyAll();
            i = 1;
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
