package com.com.wj.jvm.jvm01;

/**
 * 栈溢出
 * -Xss  栈 缺省1M
 * 方便测试，缩小到512K
 *
 */
public class StackOOM {

    private int stackLength = 1;

    public static void main(String[] args) {
        /*
        * 报错如下：java.lang.StackOverflowError
        * 虚拟机栈溢出
        *
        * 考虑 ：
        *   代码里面是否有无限递归
        *
        *
        * */
        StackOOM stackOOM = new StackOOM();
        try {
            stackOOM.recursion();
        } catch (Throwable e) {
            System.out.println(stackOOM.stackLength);
            e.printStackTrace();
        }
    }

    private void recursion() {
        stackLength++;
        recursion();
    }

}
