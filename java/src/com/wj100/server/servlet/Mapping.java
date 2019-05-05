package com.wj100.server.servlet;

import java.util.HashSet;
import java.util.Set;

public class Mapping {

    private String name;
    private Set<String> patterns;  //不会重复，使用Set

    public Mapping() {
        patterns = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getPatterns() {
        return patterns;
    }

    public void setPatterns(Set<String> patterns) {
        this.patterns = patterns;
    }

    public void addPatterns(String pattern) {
        this.patterns.add(pattern);
    }
}
