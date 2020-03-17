package com.com.wj.concurrent.test.container;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Test {

    public static void main(String[] args) {

        Vector vector = new Vector();
        Hashtable hashtable = new Hashtable();

        Collections.synchronizedMap(new HashMap<>());

        LinkedBlockingQueue queue = new LinkedBlockingQueue<>();
        try {
            ((LinkedBlockingQueue) queue).take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
