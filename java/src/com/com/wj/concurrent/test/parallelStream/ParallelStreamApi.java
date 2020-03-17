package com.com.wj.concurrent.test.parallelStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 流式处理
 */
public class ParallelStreamApi {


    public static void main(String[] args) {
        List<Integer> nums = new ArrayList();
        Random r = new Random();
        for (int i = 0 ; i < 10000 ; i++) nums.add(100000 + r.nextInt(100000));

        long start = System.currentTimeMillis();
        nums.forEach(v->isPrime(v));
        long end = System.currentTimeMillis();
        System.out.println("耗时为：" + (end - start));
        //使用Parallel stream api

        start = System.currentTimeMillis();
        //使用的也是forkJoinPool
        nums.parallelStream().forEach(ParallelStreamApi::isPrime);
        end = System.currentTimeMillis();
        System.out.println("耗时为：" + (end - start));

    }

    public static boolean isPrime(int num) {
        for (int i = 2 ; i <= num / 2 ; i++) {
            if (num % i == 0) return false;
        }

        return true;
    }

}

