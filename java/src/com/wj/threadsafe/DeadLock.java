package com.wj.threadsafe;

public class DeadLock {



    private static Object a = new Object();
    private static Object b = new Object();



    public void testA() throws InterruptedException {
        synchronized (a) {
            Thread.sleep(2000);
            synchronized (b) {
                System.out.println("ok了");
            }
        }
    }

    public void testB() throws Exception {
        synchronized (b) {
            Thread.sleep(2000);
            synchronized (a) {
                System.out.println("ok了");
            }
        }
    }


    public static void main(String[] args) throws Exception {
        DeadLock deadLock = new DeadLock();
//        deadLock.testA();
//        deadLock.testB();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    deadLock.testA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    deadLock.testB();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }









}
