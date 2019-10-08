package com.wj.threadpool;

import java.util.Random;

public class MyThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        MyThreadPool myThreadPool = new MyThreadPool(3,0);
        myThreadPool.execute(new MyTask("testA"));
        myThreadPool.execute(new MyTask("testB"));
        myThreadPool.execute(new MyTask("testC"));
        myThreadPool.execute(new MyTask("testD"));
        myThreadPool.execute(new MyTask("testE"));
        System.out.println(myThreadPool);
        Thread.sleep(10000);
        myThreadPool.destroy();
        System.out.println(myThreadPool);
    }

    static class MyTask implements Runnable {


        private String name;
        private Random r = new Random();

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("随机数为：" + r.nextInt());
        }
    }
}
