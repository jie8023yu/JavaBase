package com.com.wj.concurrent.test.container;

import java.util.concurrent.ConcurrentHashMap;

public class TestConcurrentHashMap {

    public static void main(String[] args) {
        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put("key1","value1");
        map.get("key1");
        System.out.println(map.size());
    }
}
