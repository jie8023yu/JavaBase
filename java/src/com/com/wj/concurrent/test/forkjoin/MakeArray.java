package com.com.wj.concurrent.test.forkjoin;

import java.util.Random;

/**
 * 随机产生一个整型数组
 */
public class MakeArray {

    public static final int ARRAY_LENGTH = 4000;

    public static int[] makeArray() {

        Random random = new Random();
        int[] result = new int[ARRAY_LENGTH];
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            result[i] = random.nextInt(ARRAY_LENGTH * 3);
        }

        return result;
    }

}
