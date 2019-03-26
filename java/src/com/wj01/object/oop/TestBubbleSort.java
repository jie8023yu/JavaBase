package com.wj01.object.oop;

import java.util.Arrays;

/**
 * 冒泡排序
 *  原理：比较两个相邻的元素，将值大的元素交换至右端   或者，将值最小的元素交换之左端
 *  循环次数依次减1
 *  冒泡排序优化：
 *      定义一个变量，确定某次执行完成后，是否已经排序完成，如果完成，终止循环
 */
public class TestBubbleSort {

    public static void main(String[] args) {

        int[] a = {8,9,10,14, 32, 13, 18, 11};

        //这种写法，会有问题，会多进一次循环体，但是第二个循环不会执行
        /*for (int i = 0; i < a.length; i++) {
            System.out.println("第" + i + "次循环");
            for (int j = i + 1; j < a.length; j++) {
                int temp = a[i];
                if (a[i] > a[j]) {
                    a[i] = a[j];
                    a[j] = temp;
                }
                System.out.println(Arrays.toString(a));
            }
        }*/

        //正确写法
        for(int i = 0;i < a.length - 1;i++) {
            System.out.println("第" + i + "次循环");
            boolean flag = true;
            for(int j = 0;j < a.length - 1 - i;j++){
                if(a[j] > a[j+1]) {
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                    flag = false;
                }
                System.out.println(Arrays.toString(a));
            }
            //冒泡排序优化
            if(flag) break;
        }

    }
}
