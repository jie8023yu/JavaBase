package com.com.wj.program.builder;

public class WomanBuilder extends PersonBuilder{


    private Person person;


    public WomanBuilder() {
        this.person = new Woman();
    }


    @Override
    void builderHead() {
        person.setHead("woman head");
    }

    @Override
    void builderHand() {
        person.setHand("woman hand");
    }

    @Override
    void builderFoot() {
        person.setFoot("woman foot");
    }

    @Override
    Person build() {
        return person;
    }
}
