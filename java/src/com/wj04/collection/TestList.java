package com.wj04.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 测试Collection接口中的方法
 */
public class TestList {

    public static void main(String[] args) {
       /* Collection c = new ArrayList();
        c.add(123);
        c.add("test");


        Object[] objs = c.toArray();
        System.out.println(Arrays.toString(objs));*/

       test03();


    }


    public static void test03() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");


        list.remove("D");

        System.out.println(list);
        list.add(2,"高琪");
        System.out.println(list);
        list.remove(2);
        System.out.println(list);
        list.set(2,"高琪");//会直接替换掉
        System.out.println(list);



    }


    /**
     * 集合操作
     */
    public static void test02() {
        //测试集合相关的操作
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        List<String> list2 = new ArrayList<>();
        list2.add("4");
        list2.add("5");
        list2.add("3");

//        list.addAll(list2);
        //删除相同的
//        list.removeAll(list2);

        //是否包含list2中的所有元素
        list.containsAll(list2);

        System.out.println("list01:" + list);


    }
}
