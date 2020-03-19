package com.com.wj.jvm.classloader;


public class TestClassLoader {

    public static void main(String[] args) {
        /*
        获取到的为null值，意味着是最顶级的类加载器加载的（Bootstrap）
        为何返回为null，因为Bootstrap是C++实现的，Java中没有一个类跟Bootstrap对应
         */
        System.out.println(String.class.getClassLoader());
        System.out.println(sun.awt.HKSCS.class.getClassLoader());
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader());
        //AppClassLoader
        System.out.println(TestClassLoader.class.getClassLoader());
        //ClassLoader的对应Class的类加载器也是Bootstrap
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader().getClass().getClassLoader());
        System.out.println(TestClassLoader.class.getClass().getClassLoader());


    }
}
