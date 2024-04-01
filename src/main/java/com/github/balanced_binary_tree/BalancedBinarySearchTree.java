package com.github.balanced_binary_tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

import com.github.BinaryTree;

public class BalancedBinarySearchTree<E> implements BinaryTree<E> {
    private record Node<T>(T value, Node<T> leftChild, Node<T> rightChild) {
    }

    private static final int UNBALANCEMENT_NUMBER = 1;
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
        if (isEmptyTree(tree)) {
            return -1;
        }
        return 1 + Math.max(heigth(tree.leftChild()), heigth(tree.rightChild()));
    }

    private int size(Node<E> tree) {
        if (isEmptyTree(tree)) {
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
        this.root = add(element, root);
        return this.size(root) != previousSize;

    }

    @Override
    public boolean remove(E element) {
        return remove(element, root) != null;
    }

    private Node<E> remove(E element, Node<E> tree) {
        if (isEmptyTree(tree)) {
            return null;
        }
        Node<E> newTree = null;
        if (goesBefore(element, tree.value())) {
            newTree = new Node<E>(tree.value(), remove(element, tree.leftChild()), tree.rightChild());
        } else if (goesAfter(element, tree.value())) {
            newTree = new Node<E>(tree.value(), tree.leftChild(), remove(element, tree.rightChild()));
        } else {
            if (isEmptyTree(tree.leftChild()) || isEmptyTree(tree.rightChild())) {
                if (isEmptyTree(tree.leftChild())) {
                    newTree = new Node<E>(tree.rightChild().value(), tree.rightChild().leftChild(),
                            tree.rightChild().rightChild());
                } else {
                    newTree = new Node<E>(tree.leftChild().value(), tree.leftChild().leftChild(),
                            tree.leftChild().rightChild());
                }
            } else {
                Node<E> successor = findClosestBiggerNumber(tree.value(), tree);
                newTree = new Node<E>(successor.value(), tree.leftChild(),
                        remove(successor.value(), tree.rightChild()));
            }
        }

        if (isEmptyTree(newTree)) {
            return null;
        }
        if (isPendingToTheLeft(newTree)) {
            if (shouldRotateLeftRight(newTree, element)) {
                newTree = rotateLeftRight(newTree);
            } else {
                newTree = rotateRight(newTree);
            }
        } else if (isPendingToTheRight(newTree)) {
            if (shouldRotateRightLeft(newTree, element)) {
                newTree = rotateRightLeft(newTree);
            } else {
                newTree = rotateLeft(newTree);
            }
        }

        return newTree;
    }

    private Node<E> findClosestBiggerNumber(E value, Node<E> tree) {
        if (isEmptyTree(tree.rightChild())) {
            return tree;
        }
        return findClosestBiggerNumber(value, tree.rightChild().leftChild());
    }

    private boolean isEmptyTree(Node<E> tree) {
        return tree == null;
    }

    private Node<E> add(E element, Node<E> tree) {
        if (isEmptyTree(tree)) {
            return new Node<E>(element, null, null);
        }
        Node<E> newTree;
        if (goesBefore(element, tree.value())) {
            Node<E> leftChild = add(element, tree.leftChild());
            Node<E> rightChild = tree.rightChild();
            newTree = new Node<E>(tree.value(), leftChild, rightChild);
        } else if (goesAfter(element, tree.value())) {
            Node<E> leftChild = tree.leftChild();
            Node<E> rightChild = add(element, tree.rightChild());
            newTree = new Node<E>(tree.value(), leftChild, rightChild);
        } else {
            newTree = tree;
        }

        if (isPendingToTheLeft(newTree)) {
            if (shouldRotateLeftRight(newTree, element)) {
                newTree = rotateLeftRight(newTree);
            } else {
                newTree = rotateRight(newTree);
            }
        } else if (isPendingToTheRight(newTree)) {
            if (shouldRotateRightLeft(newTree, element)) {
                newTree = rotateRightLeft(newTree);
            } else {
                newTree = rotateLeft(newTree);
            }
        }

        return newTree;
    }

    private boolean isLeaf(Node<E> tree) {
        return isEmptyTree(tree.rightChild()) && isEmptyTree(tree.leftChild());
    }

    private boolean goesAfter(E a, E b) {
        return compare(a, b) > 0;
    }

    private boolean goesBefore(E a, E b) {
        return compare(a, b) < 0;
    }

    private boolean isPendingToTheLeft(Node<E> tree) {
        return balance(tree) > UNBALANCEMENT_NUMBER;
    }

    private boolean isPendingToTheRight(Node<E> tree) {
        return balance(tree) < -UNBALANCEMENT_NUMBER;
    }

    private Node<E> rotateLeftRight(Node<E> tree) {
        Node<E> newRightChild = new Node<E>(
                tree.value(),
                tree.leftChild().rightChild().rightChild(),
                tree.rightChild());
        Node<E> newLeftChild = new Node<E>(
                tree.leftChild().value(),
                tree.leftChild().leftChild(),
                tree.leftChild().rightChild().leftChild());
        return new Node<E>(tree.leftChild().rightChild().value(), newLeftChild, newRightChild);
    }

    private Node<E> rotateRightLeft(Node<E> tree) {
        Node<E> newLeftChild = new Node<E>(tree.value(),
                tree.leftChild(),
                tree.rightChild().leftChild().leftChild());
        Node<E> newRightChild = new Node<E>(tree.rightChild().value(),
                tree.rightChild().leftChild().rightChild(),
                tree.rightChild().rightChild());
        return new Node<E>(tree.rightChild().leftChild().value(),
                newLeftChild,
                newRightChild);
    }

    private Node<E> rotateRight(Node<E> tree) {
        Node<E> newRightChild = new Node<E>(
                tree.value(),
                tree.leftChild().rightChild(),
                tree.rightChild());
        Node<E> newRoot = new Node<E>(
                tree.leftChild().value(),
                tree.leftChild().leftChild(),
                newRightChild);
        return new Node<E>(
                newRoot.value(),
                newRoot.leftChild(),
                newRoot.rightChild());
    }

    private Node<E> rotateLeft(Node<E> tree) {
        Node<E> newLeftChild = new Node<E>(
                tree.value(),
                tree.leftChild(),
                tree.rightChild().leftChild());
        Node<E> newRoot = new Node<E>(
                tree.rightChild().value(),
                newLeftChild,
                tree.rightChild().rightChild());
        return new Node<E>(
                newRoot.value(),
                newLeftChild,
                newRoot.rightChild());

    }

    public Collection<E> values() {
        return inorderTraversal(root);
    }

    private List<E> inorderTraversal(Node<E> tree) {
        if (isEmptyTree(tree)) {
            return Collections.emptyList();
        }
        List<E> allElements = new ArrayList<>();
        allElements.addAll(inorderTraversal(tree.leftChild()));
        allElements.add(tree.value());
        allElements.addAll(inorderTraversal(tree.rightChild()));
        return allElements;
    }

    private int balance(Node<E> tree) {
        return heigth(tree.leftChild()) - heigth(tree.rightChild());
    }

    private boolean shouldRotateLeftRight(Node<E> tree, E valueInserted) {
        return goesBefore(tree.leftChild().value(), valueInserted) || balance(tree.leftChild()) < 0;
    }

    private boolean shouldRotateRightLeft(Node<E> tree, E valueInserted) {
        return goesAfter(tree.rightChild().value(), valueInserted) || balance(tree.rightChild()) > 0;
    }

    public Iterator<E> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

    public int size() {
        return size(root);
    }

    public Comparator<? super E> comparator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'comparator'");
    }

    public E first() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'first'");
    }

    public SortedSet<E> headSet(E arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'headSet'");
    }

    public E last() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'last'");
    }

    public SortedSet<E> subSet(E arg0, E arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'subSet'");
    }

    public SortedSet<E> tailSet(E arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tailSet'");
    }

    public boolean contains(E element) {
        return contains(element, root);
    }

    private boolean contains(E element, Node<E> tree) {
        if (tree == null) {
            return false;
        }

        if (goesAfter(element, tree.value())) {
            return contains(element, tree.rightChild());
        } else if (goesBefore(element, tree.value())) {
            return contains(element, tree.leftChild());
        } else {
            return true;
        }
    }

}
