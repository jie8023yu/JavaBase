package com.wj.datastructure.stack;

/**
 * ADT栈的接口
 */
public interface StackInterface<T> {

    public void push(T newEntry);

    public T pop();

    /**
     * 获取栈顶
     */
    public T peek();

    public boolean isEmpty();

    public void clear();


}
