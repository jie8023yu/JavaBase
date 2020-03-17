package com.com.wj.jvm.garbageCollector;

/**
 * 线程本地分配缓冲
 *
 * 关闭栈上分配和本地线程TLAB
 * 关闭逃逸分析，关闭标量替换，关闭TLAB
 * -XX:-DoEscapeAnalysis -XX:-EliminateAllocations -XX:-UseTLAB
 *
 * 可以经过测试，发现效率上的不一致
 *
 */
public class TestTLAB {

    class User {
        int id;
        String name;

        public User(int id,String name) {
            this.id = id;
            this.name = name;
        }
    }

    void alloc(int i) {
        new User(i,"name" + i);
    }

    public static void main(String[] args) {
        for (int i = 0 ; i < 3 ; i++) test1();
    }

    public static void test1() {
        TestTLAB t = new TestTLAB();
        long start = System.currentTimeMillis();
        for (int i = 0 ; i < 1000_0000 ; i++) t.alloc(i);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}


