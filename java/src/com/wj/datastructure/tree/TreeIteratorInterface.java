package com.wj.datastructure.tree;

import java.util.Iterator;

/**
 * 树遍历方法的接口
 * @param <T>
 */
public interface TreeIteratorInterface<T> {

    public Iterator<T> getPreorderIterator();
    public Iterator<T> getPostorderIterator();
    public Iterator<T> getInorderIterator();
    public Iterator<T> getLevelOrderIterator();
}
