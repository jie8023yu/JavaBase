package com.com.wj.jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 加密class
 */
public class DefineClassLoaderWithEncription extends ClassLoader {

    public static int seed = 0B10110110;

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        File file = new File("I:/test/",name.replaceAll("\\.","/").concat(".wjclass"));
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int b = -1;
            while ((b = fis.read()) != -1) {
                baos.write(b ^ seed);
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

    public static void encryFile(String name) {
        File file = new File("I:/test/",name.replaceAll("\\.","/").concat(".class"));
        try {
            FileInputStream fis = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(new File("I:/test/",name.replaceAll("\\.","/").concat(".wjclass")));
            int b = -1;
            while ((b = fis.read()) != -1) {
                fos.write(b ^ seed);
            }
            fos.flush();
            fos.close();
            fis.close();
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        encryFile("com.com.wj.jvm.classloader.Test2");
        ClassLoader classLoader = new DefineClassLoaderWithEncription();
        Class clz = classLoader.loadClass("com.com.wj.jvm.classloader.Test2");
        Object obj = clz.newInstance();
        System.out.println(clz);
        System.out.println(obj);
        System.out.println(clz.getClassLoader());

    }
}
