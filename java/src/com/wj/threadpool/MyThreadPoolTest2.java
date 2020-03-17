package com.wj.threadpool;

import java.io.IOException;
import java.util.concurrent.*;

public class MyThreadPoolTest2 {

    public static void main(String[] args) {

        ExecutorService executorService = new ThreadPoolExecutor(1,2,600,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue(4)
        );
        for (int i = 0 ; i < 2 ; i++) {
            new Thread(() -> {
                executorService.submit(() -> {
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }).start();
        }

    }
}
