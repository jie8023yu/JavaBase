package com.com.wj.jvm.bytecode;

import java.util.Date;

/**
 * 栈帧（stack frame）
 * 用于帮助虚拟机执行方法调用与方法执行的数据结构
 * 本身是一种数据结构，封装了方法的局部变量表，动态链接信息，方法的返回地址以及操作数栈等信息。
 *
 * 符号引用
 *    全限定名
 * 直接引用
 *    内存地址
 *
 *有些符号引用会在类加载的时候或者第一次使用的时候转换为直接引用（静态解析）
 *有些在每次运行期转换（动态链接，这体现为Java的多态性）
 *
 * 局部变量表，slot（最小单位）
 *
 * 方法调用5种情况
 *  invokeinterface 调用接口的方法，实际上运行期决定到底调用实现接口的哪个对象的方法
 *  invokestatic：调用静态方法
 *  invokespecial：调用自己的私有方法，构造方法<init> 以及父类的方法
 *  invokevirtual：调用虚方法，运行期动态查找的过程
 *  invokedynamic：动态调用方法
 *
 *  静态解析的四种情形：
 *      1.静态方法
 *      2.父类方法
 *      3.构造方法
 *      4.私有方法（共有方法可以重写，私有方法无法被重写）
 *  以上4种方法称作非虚方法，是在类加载阶段就能将符号引用转换为直接引用。
 *
 *  方法的静态分派：
 *      Parent p = new Sub();
 *      以上代码Parent是P的静态类型，Sub是P的指定类型（真正指向的类型）。
 *      变量的静态类型是不会发生变化的，实际类型是可以变化的，在运行期确定。
 *   方法重载，是一种静态的行为，根据传入的参数的静态类型来匹配。编译期就可以完全确定。
 *  方法的动态分派：
 *
 *  针对对方法的动态分派，虚拟机会在类的方法区建立一个虚方法表的结构（virtual method table）
 *      指向那些未重写父类的方法的直接入口（内存地址）
 *  针对于invokeinterface指令来说，虚拟机会建立一个叫接口方法表的数据结构
 *
 */
public class ByteCodeHowToExecute {

    public static void testStatic() {
        System.out.println("static");
    }

    public static void main(String[] args) {
        //测试invokestatic
//        testStatic();

        ByteCodeHowToExecute test = new ByteCodeHowToExecute();
        //方法静态分派测试
        System.out.println("方法静态分派测试----------------------");
        Parent p = new Sub();
        Parent p2 = new SSub();
        test.test(p);
        test.test(p2);
        //方法动态分派测试
        System.out.println("方法动态分派测试----------------------");
        p.test();
        p2.test();
        /**
         * 上面两会代码调用对应的jvm指令如下所示：
         * aload_2
         * invokevirtual #14 <com/com/wj/jvm/bytecode/Parent.test>
         * aload_3
         * invokevirtual #14 <com/com/wj/jvm/bytecode/Parent.test>
         *
         * 方法的动态分派：
         * 方法接收者（方法到底由谁调用），invokevirtual指令的多态查找流程，
         *  找到操作数栈顶的第一个元素，所指向对象的实际类型，如果在实际类型找到了与方法描述符，参数列表完全一样的方法，并且访问权限校验通过，
         *  就返回实际类型的这个方法的直接引用，如果没有找到，就按照继承关系，一直往上找，每个父类都按照这个查找流程查找，如果查找到就返回，
         *  找到最后一个父类还是没有，就报错了。
         *
         * 重载（overload）和重写（overwrite）
         */

        System.out.println("测试------------------------");
        Animal a = new Dog();
        a.test(new Date());
        a.test("test");


    }

    //重载方法begin
    public void test(Parent parent) {
        System.out.println("parent");
    }
    public void test(Sub sub) {
        System.out.println("sub");
    }
    public void test(SSub sSub) {
        System.out.println("ssub");
    }
    //重载方法end

}
class Parent {

    public void test(){
        System.out.println("parent");
    }
}

class Sub extends Parent {

    @Override
    public void test() {
        System.out.println("sub");
    }
}

class SSub extends Sub {

    @Override
    public void test() {
        System.out.println("SSub");
    }
}

class Animal {
    public void test(String str) {
        System.out.println("test str");
    }
    public void test(Date date) {
        System.out.println("test Date");
    }
}
class Dog extends Animal {
    @Override
    public void test(Date date) {
        System.out.println("dog date");
    }

    @Override
    public void test(String str) {
        System.out.println("dog str");
    }
}
