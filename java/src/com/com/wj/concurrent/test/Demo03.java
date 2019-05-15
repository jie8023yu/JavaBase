package com.com.wj.concurrent.test;

public class Demo03 {


    private Object object = new Object();


    public static void main(String[] args) {
        final Demo03 d = new Demo03();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                d.m1();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                d.m2();
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                d.m3();
            }
        });
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                d.m4();
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();


    }


    public void m1() {
        synchronized (object) {
            System.out.println("m1()");
        }
    }

    public void m2() {
        synchronized (object) {
            System.out.println("m2()");
        }
    }

    public void m3() {
        synchronized (object) {
            System.out.println("m3()");
        }
    }

    public void m4() {
        synchronized (object) {
            System.out.println("m4()");
        }
    }

}
