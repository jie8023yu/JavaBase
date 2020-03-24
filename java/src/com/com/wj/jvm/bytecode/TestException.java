package com.com.wj.jvm.bytecode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;

/**
 * 对于Java类中的每一个实例方法（非静态方法），在其编译后所生成的字节码当中，方法参数的数量总是会比源代码中方法参数的数量多一个
 * （this），它位于方法的第一个参数位置，这样，我么就可以在java的实例方法中使用this来访问当前对象的属性以及方法。
 *
 * 这个操作是在编译器间完成的，及由javac编译器在编译的时候将对this的访问转化为对一个普通实例方法参数的访问，运行期间，
 * 由JVM在调用实例方法时，自动向实例方法传入该this参数。所以，在实例方法的局部变量表中，至少会有一个指向当前对象的局部变量。
 */
public class TestException {

    /**
     * 下面的代码反编译后，查看，会有4个局部变量
     * this,is，serverSocket,e（只要有异常，就会进入某个异常捕获，只会进入一个）
     */
    public void test() {
        try {
            InputStream is = new FileInputStream("test.txt");
            ServerSocket serverSocket = new ServerSocket(999);
            serverSocket.accept();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } catch (Exception e) {

        } finally {
            System.out.println("finally");
        }
    }

}
