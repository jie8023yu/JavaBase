package com.com.wj.program;

public class ActiveEnum {

    public enum NormalActive {
        PLUS,MINUS,MULTI,DIVIDS,DIFFER;


        double oper(double x,double y) {
            switch (this) {
                case PLUS:return x + y;
                case MINUS:return x - y;
                case MULTI:return x * y;
                case DIVIDS:return x / y;
            }

            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {
        System.out.println(NormalActive.PLUS.oper(1,2));
    }
}
