package com.wj.threadperformance;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 减少锁的范围
 */
public class ReduceLock {


    private Map<String,String> matchMap = new HashMap<>();


    public synchronized boolean isMatch(String name,String regexp) {
        String key = "user." + name;
        String job = matchMap.get(key);
        if (job == null) {
            return  false;
        } else {
            return Pattern.matches(regexp,job); //很消耗时间
        }
    }

    public boolean isMatchReduce(String name,String regexp) {
        String key = "user." + name;
        String job;
        //减少锁的持有时间
        synchronized (this) {
            job = matchMap.get(key);
        }

        if (job == null) {
            return  false;
        } else {
            return Pattern.matches(regexp,job);
        }
    }


}
