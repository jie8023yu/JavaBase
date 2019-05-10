package com.wj.rpc.server;

public class TechImpl implements TechInterface {

    @Override
    public String xj(String name) {
        return "你好，xj" + name;
    }
}
