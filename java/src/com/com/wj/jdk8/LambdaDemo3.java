package com.com.wj.jdk8;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 1.调用Collections.sort()方法，通过定制排序比较两个Employee（先按年龄比，年龄相同按姓名比）
 * 2.声明函数式接口，接口中声明抽象方法public String getValue(String str)
 *  声明类TestLambda,类中编写方法使用接口作为参数，将第一个字符串转为大写，并作为方法的返回值
 *  再将一个字符串的第2个和第4个索引位置进行截取子串
 * 3.声明一个带有两个泛型的函数式接口，泛型类型为<T,R> T为参数，R为返回值
 *  接口中声明对应抽象方法
 *  在TestLambda类中声明方法，使用接口作为参数，计算两个long型参数的和
 *  再计算两个long类型的乘积
 *
 */
public class LambdaDemo3 {


    @Test
    public void test1(){
        List<Employee> employeeList = Arrays.asList(new Employee("test1",15,300),
                new Employee("test4",45,3300),
                new Employee("test2",36,3200),
                new Employee("test3",45,3100));
        Collections.sort(employeeList,(e1,e2) -> {
            if(e1.getAge() == e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else{
                return Integer.compare(e1.getAge(),e2.getAge());
            }
        });

        for (Employee e : employeeList){
            System.out.println(e);
        }

    }

    @Test
    public void test2(){
        String s = handler("tab",(str) -> str.toUpperCase());
        System.out.println(s);
    }

    public String handler(String str,MyFunction function){
        return function.getValue(str);
    }


}

@FunctionalInterface
interface MyFunction{

    String getValue(String str);
}
