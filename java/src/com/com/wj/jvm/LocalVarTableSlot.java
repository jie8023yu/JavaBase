package com.com.wj.jvm;

public class LocalVarTableSlot {

    public static void main(String[] args) {
        /*byte[] placeholder = new byte[64 * 1024 * 1024];
        System.gc();*/
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        int a = 0;
        System.gc();

        System.out.println("123".substring(0,2));
    }
}
