package com.com.wj.program.builder;

public class GetPerson {
    public Person buildPerson(PersonBuilder pb) {
        pb.builderHead();
        pb.builderHand();
        pb.builderFoot();
        return pb.build();
    }
}
