package com.com.wj.jvm.jvm01;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

/**
 */
public class TestWeakRef {


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
        WeakReference<User> userSoftReference = new WeakReference<>(u);

        u = null;

        System.out.println(userSoftReference.get());
        System.gc();
        System.out.println("AfterGc");
        System.out.println(userSoftReference.get());


    }
}
