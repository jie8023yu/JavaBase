package com.wj.datastructure.tree;

/**
 * 测试红黑树
 */
public class TestRBTree {

    public static void main(String[] args) {
        RBTree tree = new RBTree();
//        int[] a = {10,20,30,40,50,60,70,80,90,65};
        int[] a = {10,20,30,40,50,60,70,80};
        for (int i = 0;i < a.length;i++) {
            tree.add(a[i]);
        }

        tree.delete(60);

        System.out.println(1);
    }
}
