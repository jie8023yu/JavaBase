package com.com.wj.jdk8;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 方法引用:若Lambda体中的内容有方法已经实现了，可以使用方法引用
 * 主要有三种语法格式：
 *  对象::实例方法名
 *  类::静态方法名
 *  类::实例方法名
 */
public class LambdaDemo5 {

    @Test
    public void test1(){
        Consumer<String> con = (x) -> System.out.println(x);
        //改为方法引用完成，注意使用的方法参数和返回值与接口中的方法保持一致
        Consumer<String> con2 = System.out::println;

    }

    @Test
    public void test2(){
        Employee e = new Employee();
        e.setAge(14);
        e.setName("test");
        Supplier<String> sup = () -> e.getName();
        System.out.println(sup.get());
        Supplier<Integer> sup2 = e::getAge;
        System.out.println(sup2.get());
    }
}
