package com.wj02.useclass;

/**
 * 包装类
 */
public class TestWrapperClass {

    public static void main(String[] args) {
        //基本数据类型转换为包装类型
        Integer a = new Integer(30);

        a = Integer.valueOf(311);
        Integer b = new Integer(3);

        //包装类转换为基本类型
        int c = b.intValue();


        //字符串转为包装类对象
        Integer e = new Integer("33");
        Integer f = Integer.parseInt("899");

        //包装类对象转换为字符串对象
        String s = f.toString();

        System.out.println(a == 311);
        System.out.println(a == b);



    }
}
