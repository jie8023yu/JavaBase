package com.com.wj.concurrent.test.threadlocal;

public class TestThreadlocal {

//    private ThreadLocal t = new ThreadLocal();

    public void test1() {
        ThreadLocal t = new My();
        t.set("test");
        t.get();
    }

    public void test2() {
        ThreadLocal t = new My();
        System.out.println(t.get());
    }


    public static void main(String[] args) {
        TestThreadlocal t = new TestThreadlocal();
        for (int i = 0 ; i < 10 ; i++) {
            t.test1();
            System.gc();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t.test2();

    }
}
