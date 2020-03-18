package com.com.wj.jvm.classloader;

public class MyCat {

    public MyCat() {
        System.out.println(this.getClass().getClassLoader());

        /**
         * 此处修改一下，加入一个去获取MySmaple类的
         * 每个类加载器都有自己的命名空间，命名空间由该加载器及所有父类加载器所加载的类组成。
         * 父加载器是不能访问到子加载器加载的Class对象
         */
        System.out.println(MySample.class);
    }
}
