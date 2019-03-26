package com.wj01.object.oop;

public class TestArrayCopy {

    public static void main(String[] args) {

//        testBasicCopy2();
        String[] str = {"阿里","百度","尚学堂","亚马逊"};

//        removeElement(str,2);

        extendRange(str);

    }

    public static void testBasicCopy() {
        String[] str = new String[5];
        for(int i = 0;i < str.length;i++) {
            str[i] = i + "";
        }
        String[] str2 = new String[3];
        System.arraycopy(str,2,str2,2,1);
        for(String s : str2) {
            System.out.println(s);
        }
    }

    //测试从数组中删除某个元素(本质上还是数组的拷贝)
    public static void testBasicCopy2() {
        String[] s1 = {"aa","bb","cc","dd","ee"};
        String[] s2 = new String[5];

        System.arraycopy(s1,3,s1,3-1,s1.length - 3);
        s1[s1.length - 1] = null;
        for(String str : s1) {
            System.out.println(str);
        }
    }

    //删除数组中指定指定索引位置的元素，并将原数组返回()
    public static String[] removeElement(String[] s,int index) {
        System.arraycopy(s,index + 1,s,index,s.length - index - 1);
        s[s.length - 1] = null;
        for(String str : s){
            System.out.println(str);
        }
        return s;
    }

    //数组扩容(本质上定义一个更大的数组，然后将原有的数组的内容拷贝到新数组中)
    public static String[] extendRange(String[] s) {
        String[] s2 = new String[s.length * 2 - 1];
        System.arraycopy(s,0,s2,0,s.length);
        for(String str : s2){
            System.out.println(str);
        }
        return s2;
    }
}
