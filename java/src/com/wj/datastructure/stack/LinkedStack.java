package com.wj.datastructure.stack;
/**
 * 栈的链式实现
 */
public final class LinkedStack<T> implements StackInterface<T>{

    private Node topNode;

    public LinkedStack() {
        topNode = null;
    }

    @Override
    public void push(T newEntry) {
        Node newNode = new Node(newEntry,topNode);
        topNode = newNode;
    }

    @Override
    public T pop() {
        T top = peek();
        assert topNode != null;
        topNode = topNode.getNextNode();
        return top;
    }


    @Override
    public T peek() {
        if(isEmpty())
            throw new RuntimeException("");
        else
            return topNode.getData();
    }

    @Override
    public boolean isEmpty() {
        return topNode == null;
    }

    @Override
    public void clear() {
        topNode = null;
    }

    private class Node{
        private T data;
        private Node next;

        public Node() {
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node getNextNode() {
            return next;
        }

        public void setNextNode(Node nextNode) {
            this.next = nextNode;
        }
    }
}
