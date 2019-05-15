package com.com.wj.jvm;

public class LocalVarTableSlot {

    public static void main(String[] args) {
        /*byte[] placeholder = new byte[64 * 1024 * 1024];
        System.gc();*/
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }

        /**
         * 注意：如果不写下面的语句，上面的内存是不会被回收的
         * 原因：代码虽然离开了placeholder的作用域，但是在此之后，没有任何对局部变量表的读写操作，
         * placeholder原本所占用的Slot还没有被其它变量所复用，所以作为GC Roots一部分的局部变量表
         * 任然保持着对它的关联。这种关联没有被及时打断，在绝大情况下影响都很轻微。但如果遇到一个方法，
         * 其后面的代码有一些耗时很长的操作，而前面又定义了占用大量内存，实际上已经不会再使用的变量，手动
         * 将其设置为null，便不见得是一个绝对无意义的操作
         */

        int a = 0;
        System.gc();

        System.out.println("123".substring(0,2));
    }
}
