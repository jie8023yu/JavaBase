package com.wj01.object.oop;

public class Test2DimensionArray {

    public static void main(String[] args) {

        int[] a = new int[3];

        //定义二维数组
        int[][] a2 = new int[3][];
        a2[0] = new int[]{20,30};
        a2[1] = new int[]{30,40,50};
        a2[2] = new int[]{10,20,50,60};

        System.out.println(a2[0][1]);

        //静态初始化数组
        int[][] b = {
                {20,30},
                {20,30},
                {20,30}
        };

    }
}
