package com.com.wj.jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * 自定义类加载器
 */
public class DefineClassLoader2 extends ClassLoader {

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();


    private String path;


    public DefineClassLoader2(String path) {
        super();
        this.path = path;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

//        System.out.println(name.replaceAll("\\.","/").concat(".class"));
//        File file = new File("I:/test/",name.replaceAll("\\.","/").concat(".class"));

        System.out.println("加载的类：" + name);
        File file = new File(path + "/",name.replaceAll("\\.","/").concat(".class"));
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int b = -1;
            while ((b = fis.read()) != -1) {
                baos.write(b);
            }
            byte[] bytes = baos.toByteArray();
//            System.out.println(bytesToHex(bytes));
            //测试使用（不要追究代码不严谨）
            baos.close();
            fis.close();
            return defineClass(name,bytes,0,bytes.length);
        } catch (Exception e) {

        }
        return super.findClass(name);

    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        DefineClassLoader2 classLoader = new DefineClassLoader2("I:/test");
        Class clz = classLoader.loadClass("com.com.wj.jvm.classloader.Test2");
//        Test2 test = (Test2) clz.newInstance();
        Object obj = clz.newInstance();
        System.out.println(clz.getClassLoader());
        System.out.println(clz);
        System.out.println(obj);
//        test.m();

//        System.out.println("com.com.wj.jvm.classloader.Test2".replaceAll("\\.","/").concat(".class"));
//        File file = new File("I:/test/","com.com.wj.jvm.classloader.Test2".replaceAll("\\.","/").concat(".class"));

//        DefineClassLoader classLoader = new DefineClassLoader();
//        classLoader.findClass("com.com.wj.jvm.classloader.Test2");
//        System.out.println("1");
    }
}
