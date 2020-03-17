package com.com.wj.concurrent.test.ms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo01 {


    private static final int MAX_SIZE = 10;

    private volatile int size  = 0;

//    private AtomicInteger atomicInteger = new AtomicInteger();

    private AtomicInteger consumerCount = new AtomicInteger();

    private List<String> list = new ArrayList<>();

    private Object consumer = new Object();
    private Object producer = new Object();



    public  int getCount() {
        return size;
    }

    public  void incr(int i) {
        size = size + i;
    }


    public void add(String str) {
//        for (int i = 0 ; i < 20 ; i++) {
            synchronized (producer) {
                while (getCount() == MAX_SIZE) {
//                    synchronized (producer) {
                        try {
                            producer.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//                    }
                }
//                synchronized (consumer) {
//                    incr(1);
                    size++;
                    System.out.println(Thread.currentThread().getName() + "," + str + ",count:" + getCount());
                    list.add(str);

//                }
            }

            synchronized (consumer) {
                consumer.notify();
            }


//        }


    }

    public void get() {
//        for  (int i = 0 ; i < 5 ; i++) {

            synchronized (consumer) {
                while (getCount() == 0) {
//                    synchronized (consumer) {
                        try {
                            consumer.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//                    }
                }
//                synchronized (consumer) {
//                    incr(-1);
                    size--;
//                    consumerCount.incrementAndGet();
                    System.out.println(Thread.currentThread().getName()  + "," + list.remove(size) + ",count:" + getCount());


//                }
            }

            synchronized (producer) {
                producer.notify();
            }




//        }

    }


    public static void main(String[] args) throws InterruptedException {
        Demo01 demo01 = new Demo01();

        //消费者
        for (int i = 0 ; i < 8 ; i++) {
            new Thread(() -> {
                for (int j = 0 ; j < 5 ; j++) {
                    demo01.get();
                }

            },"consumer-" + i).start();
        }

        TimeUnit.SECONDS.sleep(3);

        //生产者
        for (int i = 0 ; i < 2 ; i++) {
            new Thread(() -> {
                for (int j = 0 ; j < 20 ; j++) {
                    demo01.add("product" + new Random().nextInt(100));
                }
                System.out.println("生产者已生产完20个数据：" + Thread.currentThread().getName());
            },"producer-" + i).start();
        }

        TimeUnit.SECONDS.sleep(3);

//        while (true) {
//            Thread.sleep(1000);
//            System.out.println("消费数量为：" + demo01.consumerCount.get());
//        }

    }





}
