package com.wj.datastructure.tree;

import java.util.Iterator;

/**
 * 用于查找树的接口
 * @param <T>
 */
public interface SearchTreeInterface<T extends Comparable<? super T>> extends TreeInterface<T> {

    public boolean contains(T entry);

    public T getEntry(T entry);

    public T add(T newEntry);

    public T remove(T entry);

    public Iterator<T> getInorderIterator();
}
