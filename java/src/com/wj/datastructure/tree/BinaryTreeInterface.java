package com.wj.datastructure.tree;

/**
 * 二叉树的接口
 */
public interface BinaryTreeInterface<T> extends TreeInterface<T>,TreeIteratorInterface<T>{

    public void setTree(T rootData);

    public void setTree(T rootData,BinaryTreeInterface<T> leftTree,BinaryTreeInterface<T> rightTree);
}
