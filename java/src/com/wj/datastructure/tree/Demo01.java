package com.wj.datastructure.tree;

public class Demo01 {

    public static void main(String[] args) {

      /*  BinaryNode<String> rootData = new BinaryNode<>();*/
       /* root.setData("a");
        BinaryNode<String> leftChild = new BinaryNode<>();
        leftChild.setData("b");
        BinaryNode<String> rightChild = new BinaryNode<>();
        rightChild.setData("c");*/
        BinaryTree<String> leftTree = new BinaryTree<>("b",new BinaryTree<>("d"),new BinaryTree<>("e"));
        BinaryTree<String> left2RightTree = new BinaryTree<>("f",null,new BinaryTree<>("g"));
        BinaryTree<String> rightTree = new BinaryTree<>("c",left2RightTree,null);
        BinaryTree<String> root = new BinaryTree<>("a",leftTree,rightTree);


        root.inorderTraverse();

        System.out.println(1);
    }
}
