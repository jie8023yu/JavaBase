package com.com.wj.program.builder;

public class ManBuilder extends PersonBuilder{

    private Person person;


    public ManBuilder() {
        this.person = new Human();
    }


    @Override
    void builderHead() {
        person.setHead("man head");
    }

    @Override
    void builderHand() {
        person.setHand("man hand");
    }

    @Override
    void builderFoot() {
        person.setFoot("man foot");
    }

    @Override
    Person build() {
        return person;
    }
}
