package com.wj.datastructure.tree;

/**
 * 二叉结点类
 */
public class BinaryNode<T> {


    private T data;
    private BinaryNode<T> leftChild;
    private BinaryNode<T> rightChild;

    public BinaryNode() {
        this(null);
    }

    public BinaryNode(T dataPortion){
        this(dataPortion,null,null);
    }

    public BinaryNode(T dataPortion,BinaryNode<T> leftChild,BinaryNode<T> rightChild) {
        this.data = dataPortion;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryNode<T> rightChild) {
        this.rightChild = rightChild;
    }


    public boolean hasLeftChild() {
        return leftChild != null;
    }

    public boolean hasRightChild() {
        return rightChild != null;
    }

    public boolean isLeaf() {
        return (leftChild == null) && (rightChild == null);
    }

    public int getNumberOfNodes(){
        int leftNumber = 0;
        int rightNumber = 0;
        if(null != leftChild){
            leftNumber = leftChild.getNumberOfNodes();
        }

        if(null != rightChild){
            rightNumber = rightChild.getNumberOfNodes();
        }

        return 1 + leftNumber + rightNumber;
    }

    public int getHeight() {
        return getHeight(this);
    }

    private int getHeight(BinaryNode<T> node){
        int height = 0;
        if(null != node){
            height = 1 + Math.max(getHeight(node.getLeftChild()),getHeight(node.getRightChild()));
        }
        return height;
    }

    public BinaryNode<T> copy() {
        BinaryNode<T> newRoot = new BinaryNode<>(data);
        if(null != leftChild)
            newRoot.setLeftChild(leftChild.copy());
        if(null != rightChild)
            newRoot.setRightChild(rightChild.copy());
        return newRoot;
    }

}
