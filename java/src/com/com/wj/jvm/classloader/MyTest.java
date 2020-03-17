package com.com.wj.jvm.classloader;

import java.util.UUID;

/**
 * 对于静态字段来说，只有直接定义该字段的类才会被初始化
 * 当一个类在初始化时，要求其父类全部都已经初始化
 * -XX:+TraceClassLoading 用于追踪虚拟机类的加载情况并打印出来
 *
 * -XX:+<option> 表示开启option选项
 * -XX:-<option> 表示关闭option选项
 * -XX:<option>=<value> 表示将option选项的值设置为value
 *
 *
 */
public class MyTest {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
        test6();
    }

    public static void test1() {
        System.out.println(Child.str);
        /**
         * 注意：这样访问，是不会初始化Child的
         */
//        System.out.println(Child.str2);
    }

    public static void test2() {
        System.out.println(Parent2.str);
    }

    public static void test3() {
        System.out.println(Parent3.str);
    }

    public static void test4() {
        Parent4 parent4 = new Parent4();
    }

    public static void test5() {
        /**
         * 注意：直接new数组的方式，不会引发类的初始化的
         */
        Parent4[] parent4s = new Parent4[2];
        /*
        虚拟机在运行期生成的（类似于动态代理）
        动态生成的class的父类是Object
         */
        System.out.println(parent4s.getClass()); //class [Lcom.com.wj.jvm.classloader.Parent4
        System.out.println(parent4s.getClass().getSuperclass());
    }

    public static void test6() {
        System.out.println(Singleton.getInstance().a);
        System.out.println(Singleton.getInstance().b);

        System.out.println("----------理解类加载器，初始化的例子");
        System.out.println(Singleton2.getInstance().a);
        System.out.println(Singleton2.getInstance().b);
    }
}

class Parent {

    public static String str = "hello world";

    static {
        System.out.println("parent block");
    }
}

class Child extends Parent {

    public static String str2 = "hello world2";

    static {
        System.out.println("child block");
    }
}

class Parent2 {
//    public static String str = "hello world";
    /*
    此处加final和不加final的区别
    常量在编译阶段会存入到调用这个常量的方法所在的类的常量池中，本质上，调用类并没有直接引用到定义常量的类，因此不会触发定义常量的类的初始化。
    注意：这里指的是将常量存放到了Test的常量池中，之后Test与Parent2就没有任何关系了
    甚至，我们可以将Parent2的class删除

    ldc  表示将int，float或者String类型的常量值从常量池推送至栈顶
    bipush  表示将单字节（-128-127）的常量值推送至栈顶
    sipush 表示将一个短整型常量值推送至栈顶
    iconst_1 表示将int类型1推送至栈顶（iconst_1-icosnt_5）

     */
    public static final String str = "hello world";

    public static final short s = 7;

    static {
        System.out.println("parent2 block");
    }
}

class Parent3 {
    /**
     * 编译期间，是否能确定常量值
     * 当一个常量的值并非编译期可以确定的，那么这个值就不会放入调用类的常量池中
     *
     */
    public static final String str = UUID.randomUUID().toString();
    static {
        System.out.println("parent3 block");
    }
}

class Parent4 {
    static {
        System.out.println("parent4 block");
    }
}

class Singleton {
    public static int a;
    public static int b = 0;

    public static Singleton singleton = new Singleton();

    private Singleton() {
        a++;
        b++; //准备阶段的意义
    }

    public static Singleton getInstance() {
        return singleton;
    }
}

class Singleton2 {
    public static int a;

    public static Singleton2 singleton = new Singleton2();

    private Singleton2() {
        a++;
        b++;
    }
    public static int b = 0;

    public static Singleton2 getInstance() {
        return singleton;
    }
}