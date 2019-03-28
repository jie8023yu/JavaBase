package com.com.wj.jdk8;

import org.junit.Test;

import java.util.*;

/**
 * JDK1.8新增的Lamda表达式
 *
 * 语法：
 *  () -> {}
 *  括号就是接口方法的括号，接口方法如果有参数，也需要写参数。只有一个参数时，括号可以省略。
 *  -> 分隔左右部分的
 *  {} 要实现的方法体。只有一行代码时，可以不加括号，可以不写return
 */
public class LambdaDemo {

    public static void main(String[] args) {

        /*//匿名内部类
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        TreeSet<Integer> ts = new TreeSet<>(com);

        //使用lambda表达式
        Comparator<Integer> com2 = (x,y) -> Integer.compare(x,y);
        TreeSet<Integer> ts2 = new TreeSet<>(com2);*/

        //需求：获取当前公司中员工年龄大于35的员工信息
        List<Employee> employeeList = Arrays.asList(new Employee("test1",15,300),
                new Employee("test4",25,3300),
                new Employee("test2",36,3200),
                new Employee("test3",45,3100));


       /* List<Employee> employees = filterEmployee(employeeList);
        for(Employee e : employees){
            System.out.println(e);
        }*/
        //需求：获取当前公司中员工工资大于3100的员工

        List<Employee> employees = filterEmployee(employeeList,new FilterEmployeeByAge());
        for(Employee e : employees){
            System.out.println(e);
        }

    }

    //需求：获取当前公司中员工年龄大于35的员工信息
    public static List<Employee> filterEmployee(List<Employee> list){
        List<Employee> emps = new ArrayList<>();
        for(Employee e : list){
            if(e.getAge() > 35){
                emps.add(e);
            }
        }

        return emps;
    }
    //需求：获取当前公司中员工工资大于3100的员工
    public static List<Employee> filterEmployee2(List<Employee> list){
        List<Employee> emps = new ArrayList<>();
        for(Employee e : list){
            if(e.getSalary() > 3100){
                emps.add(e);
            }
        }
        return emps;
    }

    //优化方式1（策略设计模式）
    public static List<Employee> filterEmployee(List<Employee> list,MyPredicate<Employee> mp){
        List<Employee> emps = new ArrayList<>();
        for(Employee e : list){
            if(mp.test(e)){
                emps.add(e);
            }
        }

        return emps;
    }
    //优化方式2（优化方式1，每增一个比较方式就需要新增一个类，使用匿名内部类的方式）
    @Test
    public void test1(){
        List<Employee> employeeList = Arrays.asList(new Employee("test1",15,300),
                new Employee("test4",25,3300),
                new Employee("test2",36,3200),
                new Employee("test3",45,3100));
        List<Employee> employees = filterEmployee(employeeList, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() > 35;
            }
        });
        for(Employee e : employees){
            System.out.println(e);
        }
    }
    //优化方式3：使用lambda表达式
    @Test
    public void test2(){
        List<Employee> employeeList = Arrays.asList(new Employee("test1",15,300),
                new Employee("test4",25,3300),
                new Employee("test2",36,3200),
                new Employee("test3",45,3100));
        List<Employee> employees = filterEmployee(employeeList, (e) -> e.getAge() > 35);
       /* for(Employee e : employees){
            System.out.println(e);
        }*/
       employees.forEach(System.out::println);
    }

    //优化方式4：Stream API 优化
    @Test
    public void test3(){
        List<Employee> employeeList = Arrays.asList(new Employee("test1",15,300),
                new Employee("test4",25,3300),
                new Employee("test2",36,3200),
                new Employee("test3",45,3100));
        employeeList.stream().filter((e) -> e.getAge() > 35).forEach(System.out::println);

        System.out.println("-------------------");
        employeeList.stream().map(Employee::getName).forEach(System.out::println);
    }


}

