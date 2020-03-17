package com.com.wj.concurrent.test.container;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class PerformanceTest {


    private static int threadNum = 100;
    private static int count = 10000000;

    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];


    static {
        for (int i = 0 ; i < count ; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    static class MyThread extends Thread {
        int start;
        int gap = count / threadNum;

        Map map;
        public MyThread(int start, Map map) {
            this.start = start;
            this.map = map;
        }

        @Override
        public void run() {
            for (int i = start ; i < start + gap ; i++) {
                map.put(keys[i],values[i]);
            }
        }
    }

    static class MyThread2 extends Thread {

        int start;
        int gap = count / threadNum;

        Map map;
        public MyThread2(int start, Map map) {
            this.start = start;
            this.map = map;
        }

        @Override
        public void run() {
            for (int i = start ; i < start + gap ; i++) {
                map.get(keys[10]);
            }
        }
    }


    public static void test(Map map,String name,String opr) {
        long start = System.currentTimeMillis();

        Thread[] threads = new Thread[threadNum];
        for (int i = 0 ; i < threadNum ; i++) {
            switch (opr) {
                case("w") :
                    threads[i] = new MyThread(i * (count / threadNum),map);
                    break;
                case("r") :
                    threads[i] = new MyThread2(i * (count / threadNum),map);
                    break;
                    default:
                    throw new RuntimeException("类型不能为空");
            }

        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(name + "耗时为：" + (end - start));
    }


    public static void main(String[] args) {
        Map map = new Hashtable();
        test(map,"HashTable","w");
        Map map2 = Collections.synchronizedMap(new HashMap());
        test(map2,"SynchronizedMap","w");
        Map map3 = new ConcurrentHashMap();
        test(map3,"ConcurrentHashMap","w");
        System.out.println("1");
        //test read

        test(map,"HashTable","r");

        test(map2,"SynchronizedMap","r");
        test(map3,"ConcurrentHashMap","r");


    }

}
