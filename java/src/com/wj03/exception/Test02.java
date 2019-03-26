package com.wj03.exception;

public class Test02 {

    public static void main(String[] args) {
        System.out.println(get());
    }


    public static int get() {
        try {
            int i = 1 / 0;
            return 1;
        } catch (Exception e) {
            System.out.println("异常");
            return 1;
        } finally {
            System.out.println("lala");
            return 0;
        }
    }
}
