package com.com.wj.concurrent.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 线程常用的三种启动方式
 * 继承Thread
 * 实现接口Runnable
 * 实现接口Callable（这种方式可以有返回值）
 */
public class TestCallable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        UseCall useCall = new UseCall();
        //注意：Callable无法直接被Thread启动，需要对实现Callable接口的类进行包装，使用FutureTask类进行包装，调用
        FutureTask<String> futureTask = new FutureTask<>(useCall);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }


    private static class UseCall implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("callable");
            return "callable";
        }
    }
}
