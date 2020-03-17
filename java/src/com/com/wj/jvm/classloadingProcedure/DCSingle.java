package com.com.wj.jvm.classloadingProcedure;

public class DCSingle {

    private static volatile DCSingle dcSingle;


    public static DCSingle getInstance() {
        if (dcSingle == null) {
            synchronized (DCSingle.class) {
                if (dcSingle == null) {
                    dcSingle = new DCSingle();
                }
            }
        }

        return dcSingle;
    }


}
