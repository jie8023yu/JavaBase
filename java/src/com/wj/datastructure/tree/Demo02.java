package com.wj.datastructure.tree;

import java.util.Iterator;

/**
 * 使用迭代器中序遍历二叉树
 */
public class Demo02 {

    public static void main(String[] args) {
        BinaryTree<String> leftTree = new BinaryTree<>("b",new BinaryTree<>("d"),new BinaryTree<>("e"));
        BinaryTree<String> left2RightTree = new BinaryTree<>("f",null,new BinaryTree<>("g"));
        BinaryTree<String> rightTree = new BinaryTree<>("c",left2RightTree,null);
        BinaryTree<String> root = new BinaryTree<>("a",leftTree,rightTree);

        Iterator<String> iterator = root.getInorderIterator();
        while(iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
        }
    }
}
