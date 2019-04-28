package com.com.wj.jvm;

/**
 * VM Args:-Xss2M(设置大一些)
 * @Author wangjie （此段代码慎重运行，可能会造成Windows系统的假死状态，最好不要运行）
 */
public class JavaVMStackOOM {

    private void dontStop() {
        while (true) {

        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
