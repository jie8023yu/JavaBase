package com.com.wj.jvm.classloader;

import javax.xml.ws.Service;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 线程上下文类加载器
 *
 * 每个类都会使用自己的类加载器（即加载自身的类加载器）来去加载其他类（指的是所依赖的类）
 * 如果ClassX 引用了 ClassY，那么ClassX的类加载器就回去加载ClassY（ClassY尚未加载）
 *
 * 线程上下文类加载器（）
 * JDK1.2开始引入的
 * 类Thread.getContextClassLoader() Thread.setContextClassLoader(ClassLoader cl)
 * 进行获取，设置
 * 如果没有通过set方法设置的话，线程将会继承其父线程的上下文类加载器
 * java应用运行时的初始线程的上下文类加载器是系统类加载器，在线程中运行的代码可以通过该类加载器来加载类和资源。
 *
 * 例如：JDBC的驱动加载
 * 线程上下文类加载器的重要性：
 * SPI（Service Provider Interface）
 *
 * 父ClassLoader可以使用当前线程所指定的上下文类加载来加载类（Thread.currentThrad().getContextClassLoader()）
 * 这就改变了父ClassLoader不能使用子ClassLoader或是其他没有直接父子关系的classloader加载的类的情况，改变了
 * 双亲委派模型。
 *
 * 使用上下文类加载器去加载类，不就相当于不使用父加载器去加载了吗？这算破坏双亲委派吗？？？？
 *
 * 在双亲委托模型下，类加载是由上至下的，下层的类加载器会委托上层进行加载，但是对于SPI说，有些接口是JAVA核心库提供的，而Java核心库是由启动类加载器加载的，
 * 而这些接口的实现却来自于不同的jar包（厂商提供），java的启动类加载器是不会加载其他的jar包的。这样传统的双亲委派模型是无法满足的，而通过设置上下文类加载器，
 * 就可以由设置的类加载器来实现接口对类的加载。
 *
 * 没看懂上述这一段。
 *
 *
 */
public class WhyIsNeedTLClassLoader {

    public static void main(String[] args) {
        //sun.misc.Launcher$AppClassLoader@xxxxxx
        System.out.println(Thread.currentThread().getContextClassLoader());
        //null
        System.out.println(Thread.class.getClassLoader());

        System.out.println("----------------------");
        new TestThread();

    }
}
class TestThread implements Runnable {
    private Thread thread;

    public TestThread() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        ClassLoader classLoader = this.thread.getContextClassLoader();
        System.out.println("Class:" + classLoader.getClass());
        System.out.println("Parent:" + classLoader.getParent().getClass());

    }
}
/**
 * 线程上下文类加载器的一般使用模式（获取-使用-还原）
 * ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
 * try {
 *
 *     Thread.currentThread().setContextClassLoader(targetTccl);
 *     myMethod();
 * } finally {
 *
 *     Thread.currentThread().setContextClassLoader(classLoader);
 * }
 *
 * 如果一个类由类加载器A加载，那么这个类依赖的类也是由相同的类加载器加载。
 *
 * 当高层提供了统一的接口让底层去实现，同时又要在高层加载（或实例化）底层类时，就必须通过线程上下文类加载器来帮助高层的ClassLoader
 * 找到并加载该类
 */
class TestUseContextCL {
    public static void main(String[] args) {
        ServiceLoader<Driver> serviceLoader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            Driver driver = iterator.next();
            System.out.println("driver:" + driver.getClass() + ",laoder:" + driver.getClass().getClassLoader());

        }
        System.out.println("contextLoader:" + Thread.currentThread().getContextClassLoader());
        System.out.println("ServiceLoader:" + ServiceLoader.class.getClassLoader());
    }
}

/**
 * 所谓的DriverManger的例子，java提供了Driver的规范，然后由各个厂商提供对应的实现
 * 加载rt.jar使用的是引导类加载器，是最顶级的那个类加载器，如果按照正常来考虑，如下例子，使用自定义类加载器加载TestLoad类，在TestLoad类中使用DriverManager
 * 因为TestLoad类使用DefineClassLoader2加载器来加载，由于DriverManager也是在TestLoad中使用，此时也会用DefineClassLoader2作为定义加载器，
 * 最终这个DriverManager是由引导类加载器加载，如果调用getConnection方法，比如说：使用ServiceLoader去获取了META-INF/下面的实现类
 * 例如：com.mysql.jdbc.Driver
 * 如果直接在DriverManager里面写Class.forName("com.mysql.jdbc.Driver") 此时会使用加载DriverManager的Bootstrap类加载器去尝试加载这个类，
 * 发现是加载不到的，所以这个地方不能使用之前的双亲委托模型（主要是这里设计了SPI的模型）
 * 比如说：以前写获取数据库连接的方法：
 * 1.Class.forName("com.mysql.jdbc.Driver"); 把驱动类加载到JVM中
 * 2.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/test","root","root"); 获取连接
 * //如果要访问Oracle的就要显式的加载Oracle的驱动类，这样写很硬编码，很不好
 *
 * 所以推出了SPI的方式：
 * 从JDK1.6开始，DriverManager就不用再显式的加载
 * 只需要在目录下存在这样的结构META-INF/services/接口名称
 * 例如：META-INF/services/com.sql.Driver  这个文件里面写上具体的实现类
 * DriverManager在类加载时，会有一个static静态块执行，会使用ServiceLoader去加载
 * 通过迭代器的模式：ServiceLoader定义了一个LazyInterator
 * ServiceLoader<Driver> loadedDrivers = ServiceLoader.load(Driver.class);
 * Iterator<Driver> driversIterator = loadedDrivers.iterator();
 * driversIterator.hasNext() -》
 *    这里面设置的一个classLoader就是当前线程上下文类加载器，
 *    加载配置文件：configs = loader.getResources(fullName); 如果此处不使用这种方式，使用默认的肯定加载不到
 *    加载过后，得到DriverInfo，将这些DriverInfo放在registeredDrivers里面
 *    循环过程中执行driversIterator.next();----》
 *      hasNextService()---》
 *          c = Class.forName(cn, false, loader);  //尝试加载，此处的类加载器就是设置的上下文类加载器
 *    getConnection方法执行时，循环遍历整个registeredDrivers
 *    isDriverAllowed(aDriver.driver, callerCL) 判断能否加载，如果能加载，使用使用这个方法的类的类加载器来尝试加载
 *
 *
 *
 *
 *
 *
 */
class TestJdbc {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        System.out.println(DriverManager.class.getClassLoader());
//        System.out.println(Connection.class.getClassLoader());
//        System.out.println(TestJdbc.class.getClassLoader());
//        System.out.println();
//        DefineClassLoader2 classLoader = new DefineClassLoader2("E:/test");
        DefineClassLoader2 classLoader = new DefineClassLoader2("E:/test");
        DefineClassLoader2 context = new DefineClassLoader2("E:/test2");
//        Thread.currentThread().setContextClassLoader(context);
        Class clz = classLoader.loadClass("com.com.wj.jvm.classloader.TestLoad");
        clz.newInstance();
        System.out.println(clz.getClassLoader());
    }
}


