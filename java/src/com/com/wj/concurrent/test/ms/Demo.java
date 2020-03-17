package com.com.wj.concurrent.test.ms;

import java.util.ArrayList;
import java.util.List;

public class Demo {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add(0,null);
        list.add(1,"str11");
        list.add(2,"str1");
        list.add(3,"str2");
        list.add(4,"str3");
        list.add(5,"str4");
        list.add(6,"str5");
        list.add(7,"str6");
        list.add(8,"str7");

        list.remove(0);
        list.remove(0);
//        list.remove(3);
    }
}
