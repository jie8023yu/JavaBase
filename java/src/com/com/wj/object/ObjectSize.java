package com.com.wj.object;

import com.wj.jvm.agent.ObjectSizeAgent;

import java.lang.instrument.Instrumentation;

/**
 * 对象内存分配
 * 设置虚拟机参数：
 * -javaagent:打的jar包对应的路径
 * -javaagent:I:\ws\component\component\ObjectSize\out\artifacts\ObjectSize_jar\ObjectSize.jar
 * -javaagent:I:\ws\JavaBase\JavaBase\java\src\com\com\wj\object\ObjectSize.jar
 *
 * I:\ws\JavaBase\JavaBase\java\src\com\com\wj\object\ObjectSize.jar
 */
public class ObjectSize {

    //对字节码进行调试
    private static Instrumentation inst;

    public static void premain(String agentArgs,Instrumentation _inst) {
        inst = _inst;
    }

    public static long sizeOf(Object o) {
        return inst.getObjectSize(o);
    }

    public static void main(String[] args) {
        /**
         * 默认情况下开启了-XX:+UseCompressedClassPointers
         * 16字节
         * 对象头：8字节
         * Class指针占了4个字节
         * padding（填充内容） 总的大小是8的倍数，所以占4个字节
         * 一共16个字节
         */
        System.out.println(ObjectSizeAgent.sizeOf(new Object()));
        /**
         * 16字节
         * 对象头：8字节
         * Class指针：4字节
         * 数组长度：4字节
         */
        System.out.println(ObjectSizeAgent.sizeOf(new int[] {}));
        /**
         * 32字节
         * 对象头：8字节
         * Class指针：4字节
         * int:字节 4字节 * 2
         * String（引用类型），正常占用8字节（因为是64位的），
         * 有这样一个参数-XX:+UseCompressedOops默认也是开启的，所以为进行一个压缩变为4字节
         * Oops->ordinary object pointers 普通对象指针
         * byte:1个字节 * 3
         * Object：4个字节
         *
         * 31字节
         *
         *32字节
         */
        System.out.println(ObjectSizeAgent.sizeOf(new P()));
    }

    static class P {
        int id;
        String name;
        int age;
        byte b1;
        byte b2;

        Object o;
        byte b3;
    }




}
