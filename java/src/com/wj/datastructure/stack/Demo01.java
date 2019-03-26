package com.wj.datastructure.stack;

public class Demo01 {

    public static void main(String[] args) {
        StackInterface<String> stack = new LinkedStack<>();
        stack.push("1");
        stack.push("2");
        stack.push("3");


        stack.clear();

        System.out.println(stack.peek());

      /*  System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());*/
    }
}
