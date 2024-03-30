package com.github.balanced_binary_tree;

import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;

public class BalancedBinarySearchTree<E> extends AbstractSet<E> implements SortedSet<E>, Cloneable {
    private record Node<T>(T value, Node<T> leftChild, Node<T> rightChild) {
    }

    private final Comparator<E> comparator;

    private Node<E> root;

    public BalancedBinarySearchTree() {
        this.comparator = null;
    }

    public BalancedBinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public BalancedBinarySearchTree(SortedSet<E> elements) {
        this.comparator = null;
        addAll(elements);
    }

    public int heigth() {
        return heigth(root);
    }

    private int heigth(Node<E> tree) {
        if (tree == null) {
            return -1;
        }
        return 1 + Math.max(heigth(tree.leftChild()), heigth(tree.rightChild()));
    }

    private int size(Node<E> tree) {
        if (tree == null) {
            return 0;
        }

        return 1 + size(tree.leftChild()) + size(tree.rightChild());
    }

    public boolean addAll(SortedSet<E> elements) {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("unchecked")
    public int compare(E first, E second) {
        return this.comparator == null ? ((Comparable<E>) first).compareTo(second)
                : this.comparator.compare(first, second);
    }

    public boolean add(E element) {
        int previousSize = this.size(root);
        this.root = insert(element, root);
        return this.size(root) != previousSize;

    }

    private Node<E> insert(E element, Node<E> tree) {
        if (tree == null) {
            return new Node<E>(element, null, null);
        }
        if (compare(element, tree.value()) < 0) {
            return new Node<E>(tree.value(), insert(element, tree.leftChild()), tree.rightChild());
        } else if (compare(element, tree.value()) > 0) {
            return new Node<E>(tree.value(), tree.leftChild(), insert(element, tree.rightChild()));
        }
        return tree;
    }

    @Override
    public Iterator<E> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

    @Override
    public int size() {
        return size(root);
    }

    @Override
    public Comparator<? super E> comparator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'comparator'");
    }

    @Override
    public E first() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'first'");
    }

    @Override
    public SortedSet<E> headSet(E arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'headSet'");
    }

    @Override
    public E last() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'last'");
    }

    @Override
    public SortedSet<E> subSet(E arg0, E arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'subSet'");
    }

    @Override
    public SortedSet<E> tailSet(E arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tailSet'");
    }

}
