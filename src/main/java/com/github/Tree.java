package com.github;

public interface Tree<E> extends Cloneable {
    boolean add(E element);

    void remove(E element);

    boolean contains(E element);
}
