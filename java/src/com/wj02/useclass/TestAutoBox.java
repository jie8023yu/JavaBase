package com.wj02.useclass;

/**
 * 自动装箱拆箱
 */
public class TestAutoBox {

    public static void main(String[] args) {
        Integer a = 234; //编译器会转译为 Integer a = Integer.valueOf(234);

        //拆箱
        int b = a;  //编译器会修改成 int b = a.initValue();

        //证明是这样的
        Integer c = null;
        //        int cc = c;  //报空指针
        //避免这种情况
        if(c != null){
           int cc = c;
        }

        Integer in3 = 1234;
        Integer in4 = 1234;
        System.out.println(in3 == in4);
        System.out.println(in3.equals(in4));

        //缓存-128-127之间的数字,实际就是系统初始的时候，创建一个[-128,127]之间的一个数字数组
        Integer in1 = -128;
        Integer in2 = -128;
        System.out.println(in1 == in2);
        System.out.println(in1.equals(in2));






    }
}
