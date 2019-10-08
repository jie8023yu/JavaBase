package com.wj.threadsafe;

import java.util.ArrayList;
import java.util.List;

/**
 * 不可变的类
 *
 */
public class ImmutableToo {

    private List<Integer> list = new ArrayList<>();

    public ImmutableToo() {
        list.add(0);
        list.add(1);
        list.add(2);
    }

    public boolean isContain(int i) {
        return list.contains(i);
    }
}
