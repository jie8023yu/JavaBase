package com.wj.threadpool;

import java.util.concurrent.*;

public class MyRejectHandler implements RejectedExecutionHandler {


    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        for (int i = 0 ; i < 3 ; i++) {
            if (executor.getQueue().size() < 100) {

            }
        }

    }

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(4,4,0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(6),
                Executors.defaultThreadFactory(),
                new MyRejectHandler()
                );

        executorService.submit(() -> {
            System.out.println(1);
        });
    }



}
