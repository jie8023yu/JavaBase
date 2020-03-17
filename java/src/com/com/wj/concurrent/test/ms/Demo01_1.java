package com.com.wj.concurrent.test.ms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Demo01_1 {

    private List<String> list = new ArrayList<>();

    private int size = 0;

    private final int MAX_SIZE = 10;

    public synchronized void add(String str) {
//        for (int j = 0 ; j < 20 ; j++) {

            while (size == MAX_SIZE) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(str);
            size++;
            System.out.println("生产者开始生产数据：" + str);
            notifyAll();
//        }

    }

    public synchronized void get() {
//        for (int j = 0 ; j < 5 ; j++) {
            while (size == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            size--;
            System.out.println("consumer 消费数据：" + list.remove(size));
            notifyAll();
//        }

    }

    public static void main(String[] args) {

        Demo01_1 demo01_1 = new Demo01_1();

        for (int i = 0 ; i < 8 ; i++) {
            new Thread(() -> {
                for (int j = 0 ; j < 5 ; j++) {
                    demo01_1.get();
                }
            },"cosumer-" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0 ; i < 2 ; i++) {
            new Thread(() -> {
                for (int j = 0 ; j < 20 ; j++) {
                    demo01_1.add("product-" + new Random().nextInt(1000));
                }
            },"producer-" + i).start();
        }

    }


}
