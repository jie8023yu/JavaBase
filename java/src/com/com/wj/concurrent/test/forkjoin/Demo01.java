package com.com.wj.concurrent.test.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Demo01 {


    public static void main(String[] args) throws Exception{
        int[] src = MakeArray.makeArray();
        testSingleThread(src);
        testForkJoin(src);
    }

    public static void testSingleThread(int[] src) throws InterruptedException {
        int count = 0;

        long start = System.currentTimeMillis();
        for (int i = 0; i < src.length; i++) {
            Thread.sleep(1);
            count += src[i];
        }
        System.out.println("耗时为：" + (System.currentTimeMillis() - start) + ",总数为：" + count);
    }

    public static void testForkJoin(int[] src) {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SumTask sumTask = new SumTask(src,0,src.length - 1);
        forkJoinPool.invoke(sumTask);
        System.out.println("耗时为：" + (System.currentTimeMillis() - start) + ",count = " + sumTask.join());
    }

    private static class SumTask extends RecursiveTask<Long> {

        private final static int THRESHOLD = MakeArray.ARRAY_LENGTH / 10; //设置阈值
        private int[] src;
        private int begin;
        private int end;

        public SumTask(int[] src, int begin, int end) {
            this.src = src;
            this.begin = begin;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - begin > THRESHOLD) {
                //拆分任务
                int middle = (end + begin) / 2;
                SumTask left = new SumTask(src,begin,middle);
                SumTask right = new SumTask(src,middle + 1,end);
                invokeAll(left,right);
                return left.join() + right.join();
            } else {
                //进行处理任务
                long count = 0;
                System.out.println("处理的being和end分别为：" + (begin) + "," + end);
                for (int i = begin; i <= end; i++) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count += src[i];
                }
                return count;
            }

        }
    }

}
