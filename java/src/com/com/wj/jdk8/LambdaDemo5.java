package com.com.wj.jdk8;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.CompareGenerator;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.*;

/**
 * 方法引用:若Lambda体中的内容有方法已经实现了，可以使用方法引用
 * 主要有三种语法格式：
 *  对象::实例方法名
 *  类::静态方法名
 *  类::实例方法名
 *
 *  构造器引用
 *      ClassName::new
 *  数组引用
 *      Type::new
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

    @Test
    public void test3(){
        Comparator<Integer> com = (x,y) -> Integer.compare(x,y);
        Comparator<Integer> com2 = Integer::compareTo;
    }

    @Test
    public void test4(){
        BiPredicate<String,String> bp = (x,y) -> x.equals(y);

        BiPredicate<String,String> bp2 = String::equals;
    }

    @Test
    public void test(){
        Supplier<Employee> sup = () -> new Employee();

        //构造器引用方式
        Supplier<Employee> sup2 = Employee::new;

        System.out.println(sup2.get());

        //一个参数的构造器
        Function<Integer,Employee> fun = (x) -> new Employee(x);
        System.out.println(fun.apply(123));

        Function<Integer,Employee> fun2 = Employee::new;
        System.out.println(fun2.apply(1235));


        //两个参数的构造器
        BiFunction<String,Integer,Employee> biFunction = Employee::new;
        System.out.println(biFunction.apply("test1",123));

    }

    @Test
    public void test6(){
        Function<Integer,String[]> fun = (x) -> new String[x];
        String[] apply = fun.apply(10);
        System.out.println(apply.length);
        Function<Integer,String[]> fun2 = String[]::new;
        System.out.println(fun2.apply(15).length);
    }
}
