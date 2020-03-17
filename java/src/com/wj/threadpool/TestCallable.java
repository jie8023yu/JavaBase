package com.wj.threadpool;

import java.util.concurrent.*;

public class TestCallable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = new Callable() {

            @Override
            public Object call() throws Exception {
                return "hello callable";
            }

        };

        ExecutorService service = Executors.newCachedThreadPool();

        Future<String> future = service.submit(callable); //异步

        System.out.println(future.get());

        service.shutdown();
    }
}
