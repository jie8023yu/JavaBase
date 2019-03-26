package com.wj01.object.oop;

import java.util.Arrays;

/**
 * 测试java.util.Arrays工具类的使用
 *
 */
public class TestArrays {

    public static void main(String[] args) {
        int[] a = {11,2,9};

        System.out.println(a);

        //使用Arrays.toString方法打印数组中的内容
        System.out.println(Arrays.toString(a));
        //对数组中的内容排序，引用的对象排序需要重写相关方法
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));

        //查找数据，并返回索引位置
        System.out.println(Arrays.binarySearch(a,11));
    }
}
