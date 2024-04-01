package com.github;

import java.util.Collection;

public interface BinaryTree<E> extends Cloneable {

    boolean add(E element);

    boolean remove(E element);

    boolean contains(E element);

    int height();

    int size();

    Collection<E> values();
}
