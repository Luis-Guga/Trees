package com.github;

public interface BinaryTree<E> extends Cloneable {

    boolean add(E element);

    boolean remove(E element);

    boolean contains(E element);

    int heigth();

    int size();
}
