package com.com.wj.program.builder;

public class Test {

    public static void main(String[] args) {
        GetPerson getPerson = new GetPerson();

        Person p1 = getPerson.buildPerson(new ManBuilder());
        Person p2 = getPerson.buildPerson(new WomanBuilder());
        System.out.println(p1);
        System.out.println(p2);
    }
}
