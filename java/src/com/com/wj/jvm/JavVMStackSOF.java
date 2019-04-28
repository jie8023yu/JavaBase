package com.com.wj.jvm;

/**
 * VM Args : -Xss128k
 */
public class JavVMStackSOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavVMStackSOF oom = new JavVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println(oom.stackLength);
            throw e;
        }
    }
}
