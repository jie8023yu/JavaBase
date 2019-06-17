package com.com.wj.concurrent.test.container;

import java.util.concurrent.ConcurrentHashMap;

public class TestConcurrentHashMap {

    public static void main(String[] args) {
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap();
        for (int i = 0; i < 100; i++) {
            map.put("key" + i,"value" + i);
        }
        System.out.println(1);
    }
}
