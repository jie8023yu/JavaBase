package com.test;

import java.util.Map;

public class Test {

    public static void main(String[] args) throws Exception {
//        System.out.println(1);
//        Demo01.get();
//        Class<?> cls = Class.forName("com.test.Demo01");
//        cls.newInstance();
//        System.out.println(Demo01.num);
//        Demo01[] array = new Demo01[10];


       /* int i = -3;

        long l = (long)i;
        long num = l;
        long count = 0;
        for(; num > 0; count++) {
            num &= (num - 1);
        }
        System.out.println(count);*/

        /*long result = (long) Math.pow(2,48);
        System.out.println(result );
        result = (long) Math.pow(2,49);
        System.out.println(result);
        result = (long) Math.pow(2,50);
        System.out.println(result);*/

//        test(32,48);
//        test(31,33);
        test(48,64);

        //byte最大正数
        byte b = 0b01111111;
        System.out.println(1);

        int i = 0xffffffff;
        System.out.println(1);

        Map map ;

        //设置线程处理异常的类
        Thread.setDefaultUncaughtExceptionHandler((t,e) -> {
            System.out.println(t.getId() + "----error:" + e);
        });
        Thread t = new Thread(() -> {
            int err = 1 / 0;
        });
        t.setUncaughtExceptionHandler((tt,e) -> {
            System.err.println(t.getId() + "----error:" + e);
        });
        t.start();

    }

    public static void test(int start,int end) {
        long result = 0;
        for (int i = start; i < end; i++) {
            result += Math.pow(2,i);
        }
        System.out.println(result);
    }
}
