package com.com.wj.jdk8;

import org.junit.Test;
import org.junit.internal.runners.statements.RunAfters;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Lambda表达式的基础语法：Java8引入一个新的操作符"->"，该操作符称为箭头操作符或Lambda操作符，箭头操作符将Lambda表达式分成两部分
 *
 * 左侧：Lambda表达式中的参数列表
 * 右侧：Lambda表达式中所执行的功能，即Lambda体
 * 语法格式1：无参数，无返回值的方法
 *          () -> System.out.println("hello lambda");
 * 语法格式2：有一个参数并且无返回值的方法
 * 语法格式3：只有有一个参数 小括号可以不写
 * 语法格式4：有多个参数，并且Lambda体中有多条语句，体中有多条语句,Lambda体必须使用大括号
 * 语法格式5：若Lambda体中只有一条语句，return和大括号都可以不写
 * 语法格式6：Lambda表达式的参数列表的数据类型可以省略不写，因为JVM编译器可以通过上下文推断出，数据类型，即“类型推断”
 *
 * Lambda表达式需要函数式接口的支持
 *  函数式接口：只有一个抽象方法的接口，称为函数式接口。客户使用注解@FunctionalInterface修饰，可以检查是否是函数式接口
 */
public class LambdaDemo2 {

    @Test
    public void test1(){
        int num = 0; //jdk1.7以前必须是final，实际还是final，不需要手动加
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world" + num);
            }
        };
        r.run();

        Runnable r1 = () -> System.out.println("hello world lambda");
        r1.run();
    }

    @Test
    public void test2(){
//        Consumer<String> con = (x) -> System.out.println(x);
        Consumer<String> con = x -> System.out.println(x);
        con.accept("test");
    }

    @Test
    public void test3(){
        Comparator<Integer> com = (x,y) -> {
            System.out.println("test");
            return Integer.compare(x,y);
        };

        int flag = com.compare(12,13);
        System.out.println(flag);
    }

    @Test
    public void test4(){
        Comparator<Integer> com = (x,y) -> Integer.compare(x,y);
        int flag = com.compare(13,12);
        System.out.println(flag);
    }

    @Test
    public void test5(){
        //上下文推断的例子，拆开写就不行了
        String[] str = {"aaa","bb","cc"};

    }

    @Test
    public void test6(){

    }

    public void showMap(Map<String,String> map){

    }
}
