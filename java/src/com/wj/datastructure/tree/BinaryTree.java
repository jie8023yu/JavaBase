package com.wj.datastructure.tree;

import com.wj.datastructure.stack.StackInterface;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 二叉树的实现类
 */
public class BinaryTree<T> implements BinaryTreeInterface<T>{

    private BinaryNode<T> root;

    public BinaryTree(){
        root = null;
    }

    public BinaryTree(T rootData){
        root = new BinaryNode<>(rootData);
    }

    public BinaryTree(T rootData,BinaryTree<T> leftTree,BinaryTree<T> rightTree){
        privateSetTree(rootData, leftTree, rightTree);
    }

    private void privateSetTree(T rootData,BinaryTree<T> leftTree,BinaryTree<T> rightTree){
        root = new BinaryNode<>(rootData);
        /*if(null != leftTree && !leftTree.isEmpty())
            root.setLeftChild(leftTree.root.copy());
        if(null != rightTree && !rightTree.isEmpty())
            root.setRightChild(rightTree.root.copy());*/
        if(null != leftTree && !leftTree.isEmpty())
            root.setLeftChild(leftTree.root);
        if(null != rightTree && !rightTree.isEmpty()){
            if(rightTree != leftTree){
                root.setRightChild(rightTree.root);
            }else{
                root.setRightChild(rightTree.root.copy());
            }
            if(null != leftTree && this != leftTree)
                leftTree.clear();
            if(null != rightTree && this != rightTree)
                rightTree.clear();
        }
    }


    @Override
    public void setTree(T rootData) {

    }

    @Override
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {

    }

    @Override
    public T getRootData() {
        if(isEmpty())
            throw new EmptyTreeException();
        else
            return root.getData();
    }

    @Override
    public int getHeight() {
        return root.getHeight();
    }

    @Override
    public int getNumberOfNodes() {
        return root.getNumberOfNodes();
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public Iterator<T> getPreorderIterator() {
        return null;
    }

    @Override
    public Iterator<T> getPostorderIterator() {
        return null;
    }

    @Override
    public Iterator<T> getInorderIterator() {
        return new InorderIterator();
    }

    @Override
    public Iterator<T> getLevelOrderIterator() {
        return null;
    }

    protected void setRootData(T rootData){
        root.setData(rootData);
    }

    protected void setRootNode(BinaryNode<T> rootNode){
        root = rootNode;
    }

    protected BinaryNode<T> getRootNode(){
        return root;
    }

    /**
     * 中序遍历
     */
    public void inorderTraverse(){
        inorderTraverse(root);
    }

    private void inorderTraverse(BinaryNode<T> node){
        if(null != node){
            inorderTraverse(node.getLeftChild());
            System.out.println(node.getData());
            inorderTraverse(node.getRightChild());
        }
    }



    private class InorderIterator<T> implements Iterator<T>{

        private StackInterface<BinaryNode<T>> nodeStack;
        private BinaryNode<T> currentNode;


        public InorderIterator() {
        }

        @Override
        public boolean hasNext() {
            return !nodeStack.isEmpty() || (currentNode != null);
        }

        @Override
        public T next() {
            BinaryNode<T> nextNode = null;
            while(null != currentNode){
                nodeStack.push(currentNode);
                currentNode = currentNode.getLeftChild();
            }
            if(!nodeStack.isEmpty()){
                nextNode = nodeStack.pop();
                assert nextNode != null;
                currentNode = nextNode.getRightChild();
            }else
                throw new NoSuchElementException();

            return nextNode.getData();
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

}
