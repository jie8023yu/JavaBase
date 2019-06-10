package com.com.wj.concurrent.test.container;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ConcurrentHashMap的问题,此处如果如果使用常规的Integer，如果先读后设置会有问题，使用原子操作类不会有问题
 */
public class ConcurrentHashMapDemo2 {

    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String,AtomicInteger> map = new ConcurrentHashMap<>();
        AtomicInteger integer = new AtomicInteger(0);
        map.put("key",integer);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
//                    Integer key = map.get("key") + 1;
//                    map.put("key",map.get("key") + 1);
                    map.get("key").incrementAndGet();
                }
            });
        }
        Thread.sleep(3000);
        System.out.println(map.get("key"));
        executorService.shutdown();

    }
}
