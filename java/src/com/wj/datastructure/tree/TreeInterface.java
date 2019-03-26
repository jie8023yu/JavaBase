package com.wj.datastructure.tree;

/**
 * 所有树的接口
 */
public interface TreeInterface<T> {

    public T getRootData();
    public int getHeight();
    public int getNumberOfNodes();
    public boolean isEmpty();
    public void clear();
}
