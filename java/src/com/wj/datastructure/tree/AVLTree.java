package com.wj.datastructure.tree;

import java.util.TreeMap;

public class AVLTree<T extends Comparable<? super T>> extends BinarySearchTree<T> implements SearchTreeInterface<T>{

    public AVLTree(){
        super();
    }

    public AVLTree(T rootEntry){
        super(rootEntry);
    }

    private BinaryNode<T> rebalance(BinaryNode<T> nodeN){

        return null;
    }

}
