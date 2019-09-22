package com.com.wj.jvm.jvm01;

import java.lang.ref.SoftReference;
import java.util.LinkedList;
import java.util.List;

/**
 * 设置参数
 *  -Xms10m -Xmx10m -XX:+PrintGC
 */
public class TestSoftRef {


    public static class User {
        public int id = 0;
        public String name = "";

        public User(int id,String name) {
            super();
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        User u = new User(1,"mark");
        SoftReference<User> userSoftReference = new SoftReference<>(u);

        u = null;

        System.out.println(userSoftReference.get());
        System.gc();
        System.out.println("AfterGc");
        System.out.println(userSoftReference.get());

        List<byte[]> list = new LinkedList();

        try {
            for (int i = 0; i < 100; i++) {
                System.out.println("****" + userSoftReference.get());
                list.add(new byte[1024 * 1024 * 1]);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println(userSoftReference.get());
        }



    }
}
