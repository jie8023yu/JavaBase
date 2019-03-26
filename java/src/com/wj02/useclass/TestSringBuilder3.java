package com.wj02.useclass;

/**
 * 测试可变字符序列和不可变字符序列使用的陷阱
 */
public class TestSringBuilder3 {

    public static void main(String[] args) {

        /*使用String进行字符的拼接*/
       /* String str = "";
        //本质上使用StringBuilder拼接，但是每次循环都会生成一个StringBuilder对象
        long num1 = Runtime.getRuntime().freeMemory(); //获取系统剩余内存空间
        long time1 = System.currentTimeMillis();
        for(int i = 0;i < 5000;i++){
            str = str + i; //相当于产生10000个对象
        }

        long num2 = Runtime.getRuntime().freeMemory();
        long time2 = System.currentTimeMillis();
        System.out.println("String占用内存为：" + (num1 - num2));
        System.out.println("耗时为：" + (time2 - time1));*/

        StringBuilder sb = new StringBuilder();
        long num3 = Runtime.getRuntime().freeMemory();
        long time3 = System.currentTimeMillis();
        for(int i = 0;i < 5000;i++) {
            sb.append(i);
        }
        long num4 = Runtime.getRuntime().freeMemory();
        long time4 = System.currentTimeMillis();
        System.out.println("StringBuilder占用内存为：" + (num3 - num4));
        System.out.println("耗时为：" + (time4 - time3));
    }
}
