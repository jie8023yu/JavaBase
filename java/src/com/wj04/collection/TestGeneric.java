package com.wj04.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 测试泛型
 */
public class TestGeneric {

    public static void main(String[] args) {
        MyCollection<String> collection = new MyCollection<>();
        collection.set("搞起",0);
        collection.set("1",1);

        String b = collection.get(0);

        List list = new ArrayList();

        Map map;
    }

}

class MyCollection<E> {

    Object[] objs = new Object[5];

    public void set(E obj,int index) {
        objs[index] = obj;
    }

    public E get(int index) {
        return (E)objs[index];
    }
}
