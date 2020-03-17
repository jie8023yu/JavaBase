package com.wj.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Future不常用
 * 常用的是FutureTask
 *
 * FutureTask
 *  implements
 *      RunnableFuture
 *          extends Runnable Future
 *
 */
public class TestFutureTask {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(() ->{

            TimeUnit.SECONDS.sleep(1);
            return 1000;
        });

        new Thread(futureTask).start();

        System.out.println(futureTask.get());
    }

}
