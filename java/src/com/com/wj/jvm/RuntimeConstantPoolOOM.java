package com.com.wj.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * String.intern()是一个native方法，作用是：如果字符串常量池中已经包含了
 * 一个等于此String对象的字符串，则返回代表池中这个字符串的String对象，
 * 否则，将此String对象包含的字符串添加到常量池中，并且返回String对象
 * 的引用。在JDK1.6及之前的版本中，由于常量池分配在永久代内，可以通过
 * -XX:PermSize和-XX:MaxPermSize限制方法区大小，从而限制其中常量池
 * 的容量
 * VM Args : -XX:PermSize=10M -XX:MaxPermSize=10M
 *
 * 注意：在jvm1.8版本中，永久代改为元空间（Metaspace），使用的是本地内存
 * 默认情况下，元空间的大小仅受本地内存限制，但可以通过以下参数来指定元空间的大小：
 * -XX:MetaspaceSize，初始空间大小，达到该值就会触发垃圾收集进行类型卸载，
 * 同时GC会对该值进行调整：如果释放了大量的空间，就适当降低该值；如果释放了很少的空间，那么在不超过MaxMetaspaceSize时，适当提高该值。
 * -XX:MaxMetaspaceSize，最大空间，默认是没有限制的。
 * 分配的太大，需要等待的时间太长
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        //使用List保持着常量池引用，避免Full GC 回收常量池行为
        List<String> list = new ArrayList<>();
        //10MB的PermSize在integer范围内足够产生OOM了
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
