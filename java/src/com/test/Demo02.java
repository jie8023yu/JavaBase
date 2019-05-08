package com.test;

import java.io.*;

public class Demo02 {

    public static void main(String[] args) {
        /*String path = Demo02.class.getClassLoader().getResource("com/test/test.txt").getPath();
        System.out.println(path);*/

       /* System.out.println(Demo02.class.getClassLoader().getResource(""));
        //file:/D:/git/JavaBase/java/out/production/JavaBase/
        System.out.println(Demo02.class.getClassLoader().getResource("/"));
        //null
        System.out.println(Demo02.class.getResource(""));
        //file:/D:/git/JavaBase/java/out/production/JavaBase/com/test/
        System.out.println(Demo02.class.getResource("/"));
        //file:/D:/git/JavaBase/java/out/production/JavaBase/*/
        System.out.println(Thread.currentThread().getContextClassLoader());
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.txt");
        System.out.println(is);

//        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/test/test.txt");
        /*if (null != is) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            try {
                line = br.readLine();
                System.out.println(line);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }*/


    }
}
