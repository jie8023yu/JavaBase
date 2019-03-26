package com.wj02.useclass;

public class TestStringBuilder2 {

    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();

        for(int i = 0;i < 25;i++) {
            sb.append('a' + i);

        }
        sb.reverse(); //倒序

    }
}
