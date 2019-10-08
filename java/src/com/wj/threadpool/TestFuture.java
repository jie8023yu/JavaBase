package com.wj.threadpool;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestFuture {


    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    /**
     * 多线程执行 有返回值的情况
     */
    public static void main(String[] args) throws Exception {
        new TestFuture().testByQueue();
        countDownLatch.await();
        new TestFuture().testByCompletion();
    }


    private final int POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private final int TOTAL_TASK = Runtime.getRuntime().availableProcessors() * 10;


    //方法1 ：自己写集合实现获取线程池中任务的返回结果
    public void testByQueue() throws Exception {
        long start = System.currentTimeMillis();
        //统计所有任务休眠的总时长
        AtomicInteger count = new AtomicInteger(0);
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(POOL_SIZE);
        //容器存放提交给线程池的任务
        BlockingQueue<Future<Integer>> queue = new LinkedBlockingQueue<>();
        for (int i = 0; i < TOTAL_TASK; i++) {
            Future<Integer> future = executorService.submit(new WorkTask("ExecTask" + i));
            queue.add(future);  //i = 0 的任务先进队列，i=1的任务跟着进
        }

        for (int i = 0; i < TOTAL_TASK; i++) {
            int sleepTime = queue.take().get(); //i = 0的先取到，i=1的任务跟着进
            System.out.println(" sleep " + sleepTime + " ms......");
            count.addAndGet(sleepTime);
        }

        executorService.shutdown();
        System.out.println("-----------------------------------------tasks sleep time " + count.get() +
                "ms,and spend time " + (System.currentTimeMillis() - start) + "ms");
        countDownLatch.countDown();
    }

    /**
     * 方法2： 通过CompleteService来实现获取线程池中的任务结果
     *
     * 会按照执行的时间 来 获取 结果
     * 如果先执行完 会先获取到结果
     * 不会像Future一样，按顺序等待，即使先执行完的结果，如果顺序排在后面，也要等待前面的结果返回后，才能获取到这个结果
     * 可以充分利用CPU的资源
     */

    public void testByCompletion() throws Exception {
        long start = System.currentTimeMillis();
        AtomicInteger count = new AtomicInteger(0);
        ExecutorService executorService = Executors.newFixedThreadPool(POOL_SIZE);

        CompletionService<Integer> completionService = new ExecutorCompletionService(executorService);
        for (int i = 0; i < TOTAL_TASK; i++) {
            completionService.submit(new WorkTask("ExecTask" + i));
        }

        for (int i = 0; i < TOTAL_TASK; i++) {
            int sleepTime = completionService.take().get(); //i = 0的先取到，i=1的任务跟着进
            System.out.println(" sleep " + sleepTime + " ms......");
            count.addAndGet(sleepTime);
        }


        executorService.shutdown();
        System.out.println("-----------------------------------------tasks sleep time " + count.get() +
                "ms,and spend time " + (System.currentTimeMillis() - start) + "ms");
    }


}


class WorkTask implements Callable<Integer> {

    private String name;

    public WorkTask(String name) {
        this.name = name;
    }


    @Override
    public Integer call() throws Exception {
        Random random = new Random();
        int i = random.nextInt(1000);
//        System.out.println(name + " sleep " + i + "ms");
        Thread.sleep(i);
        return i;
    }
}