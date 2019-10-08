package com.wj.threadsafe;

/**
 * final 来 保证 安全
 *
 * 可以看见，用final修饰的成员变量，需要在初始化时进行赋值
 * 不能对final的变量进行set
 *
 * 注意 final 修饰的 引用类型 中的成员变量  是否 都是 final定义的
 *
 */
public class ImmutableFinal {


    private final int a;
    private final int b;
    private final User user;

    public ImmutableFinal(int a, int b) {
        this.a = a;
        this.b = b;
        this.user = new User();
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public User getUser() {
        return user;
    }

    static class User {
        private int age;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    public static void main(String[] args) {
        ImmutableFinal ref = new ImmutableFinal(1,2);
        User user = ref.getUser();
        //此处 虽然 User是final的，但是线程仍然不安全，User的私有属性 可以 被多个线程通过set方法进行赋值
        user.setAge(20);
    }

}
