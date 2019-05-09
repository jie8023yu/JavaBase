package com.wj.proxy.dynamic;

import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 动态代理源码
 * Proxy-->newProxyInstance()  Class<?> cl = getProxyClass0(loader, intfs); 生成代理的Class
 *  -->getProxyClass0() 从本地缓存中获取  WeakCache---> Object subKey = Objects.requireNonNull(subKeyFactory.apply(key, parameter));
 *   Proxy--->apply()
 *    /*
 *              * Choose a name for the proxy class to generate.
 *
 *long num=nextUniqueNumber.getAndIncrement();
         *String proxyName=proxyPkg+proxyClassNamePrefix+num;
         *
         *
  *              * Generate the specified proxy class.
  *
         *byte[]proxyClassFile=ProxyGenerator.generateProxyClass(
         *proxyName,interfaces,accessFlags);
         *try{
         *return defineClass0(loader,proxyName,
         *proxyClassFile,0,proxyClassFile.length);
         *}catch(ClassFormatError e){
 */

public class ProxyUtils {
    /**
     * 根据类信息动态生成的二进制字节码文件保存到硬盘中，默认的是clazz目录下
     * params:clazz需要生成动态代理类
     * proxyName:为动态生成的代理类的名称
     */

    public static void generateClassFile(Class clazz,String proxyName) {
        byte[] proxyClassFile= ProxyGenerator.generateProxyClass(proxyName,new Class[]{clazz});
        String paths = clazz.getResource(".").getPath();
        System.out.println(paths);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(paths + proxyName + ".class");
            fos.write(proxyClassFile);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
