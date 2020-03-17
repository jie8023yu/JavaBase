package com.wj.threadpool;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 假设有这样一个服务，分别从天猫，京东，拼多多，获取某个牌子的商品的价格，进行一个价格比较
 * 使用爬虫去爬数据
 */
public class TestCompletableFuture {

    public static void main(String[] args) {
        long start,end;

        start = System.currentTimeMillis();
//        singleThreadExecute();
//        System.out.println("耗时为：" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
//        completableFuture();
        end = System.currentTimeMillis();


        completalbeFutureApply();

        System.out.println("耗时为：" + (end - start));

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void completableFuture()  {
        try {
            CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(() -> getPriceFromTM());
            CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(() -> getPriceFromJD());
            CompletableFuture<Double> futurePDD = CompletableFuture.supplyAsync(() -> getPriceFromPDD());
            CompletableFuture.allOf(futureJD,futureTM,futurePDD).join();
            System.out.println(futureTM.get() + "," + futureJD.get() + "," + futurePDD.get());
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    /**
     * 任务组合
     * 最终如何处理结果
     */
    public static void completalbeFutureApply() {
        CompletableFuture.supplyAsync(() -> getPriceFromTM())
                .thenApply(String::valueOf)
                .thenApply(str -> "price" + str)
                .thenAccept(System.out::println);
    }


    public static void singleThreadExecute() {
        double jd = getPriceFromJD();
        double pdd = getPriceFromPDD();
        double tb = getPriceFromTM();
        System.out.println(jd + "," + pdd + "," + tb);
    }





    /**
     * 业务方法
     * **/

    public static double getPriceFromTM() {
        common();
        return 1.0;
    }

    public static double getPriceFromJD() {
        common();
        return 2.0;
    }

    public static double getPriceFromPDD() {
        common();
        return 3.0;
    }

    public static void common() {
        int time = new Random().nextInt(500);
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after " + time);
    }
}
