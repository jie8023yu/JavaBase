package com.wj02.useclass;

public class TestString {

    public static void main(String[] args) {

//        String s = new String();

//        String s = "aabbccdd";

//        String str2 = s.substring(2,5);
//        System.out.println(s);
//        System.out.println(str2);


        String str1 = "hello" + " java";  //编译器做了优化，直接在编译的时候讲字符串进行拼接，相当于str1 = "hello java'
        String str2 = "hello java";

        System.out.println(str1 == str2); //true

        String str3 = "hello";
        String str4 = " java";
        //编译的时候不知道变量中存储的是什么，所以没办法在编译的时候优化
        String str5 = str3 + str4;
        System.out.println(str5 == str2); //false，比较的时候使用equals，不要使用==


    }
}
