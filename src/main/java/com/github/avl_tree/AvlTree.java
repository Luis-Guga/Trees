package com.github.avl_tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import com.github.Tree;


public class AvlTree<E extends Comparable> implements Tree<E> {
    private record Node<T>(T value, Node<T> leftChild, Node<T> rightChild) {
    }

    private static final int UNBALANCEMENT_NUMBER = 1;
    private final Comparator<E> comparator;

    private Node<E> root;

    public AvlTree() {
        this.comparator = null;
    }

    public AvlTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public AvlTree(Set<E> elements) {
        this.comparator = null;
        addAll(elements);
    }

    public int height() {
        return height(root);
    }

    private int height(Node<E> tree) {
        if (isEmptyTree(tree)) {
            return -1;
        }
        return 1 + Math.max(height(tree.leftChild()), height(tree.rightChild()));
    }

    private int size(Node<E> tree) {
        if (isEmptyTree(tree)) {
            return 0;
        }

        return 1 + size(tree.leftChild()) + size(tree.rightChild());
    }

    private boolean addAll(Set<E> elements) {
        int prevSize = this.size();
        for (E element : elements) {
            this.add(element);
        }
        return prevSize != this.size();
    }

    public int compare(E first, E second) {
        return this.comparator == null ? ((Comparable<E>) first).compareTo(second)
                : this.comparator.compare(first, second);
    }

    public boolean add(E element) {
        int previousSize = this.size(root);
        this.root = add(element, root);
        return this.size(root) != previousSize;

    }

    public boolean remove(E element) {
        int prevSize = this.size();
        this.root = remove(element, root);
        return this.size() != prevSize;
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
                if (isEmptyTree(tree.leftChild()) && !isEmptyTree(tree.rightChild())) {
                    newTree = new Node<E>(tree.rightChild().value(), tree.rightChild().leftChild(),
                            tree.rightChild().rightChild());
                } else if (!isEmptyTree(tree.leftChild()) && isEmptyTree(tree.rightChild())) {
                    newTree = new Node<E>(tree.leftChild().value(), tree.leftChild().leftChild(),
                            tree.leftChild().rightChild());
                }
            } else {
                Node<E> successor = tree.rightChild() == null ? tree
                        : findLeftMostChild(tree.rightChild());
                newTree = new Node<E>(successor.value(), tree.leftChild(),
                        remove(successor.value(), tree.rightChild()));
            }
        }

        if (isEmptyTree(newTree)) {
            return null;
        }
        if (isPendingToTheLeft(newTree)) {
            if (balance(newTree.leftChild()) >= 0) {
                newTree = rotateRight(newTree);
            } else {
                newTree = rotateLeftRight(newTree);
            }
        } else if (isPendingToTheRight(newTree)) {
            if (balance(newTree.rightChild()) <= 0) {
                newTree = rotateLeft(newTree);
            } else {
                newTree = rotateRightLeft(newTree);
            }
        }

        return newTree;
    }

    private Node<E> findLeftMostChild(Node<E> tree) {
        if (isEmptyTree(tree.leftChild())) {
            return tree;
        }
        return findLeftMostChild(tree.leftChild());
    }

    private Node<E> findRightMostChild(Node<E> tree) {
        if (isEmptyTree(tree.rightChild())) {
            return tree;
        }
        return findRightMostChild(tree.rightChild());
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

    public boolean contains(E element) {
        return contains(element, root);
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
        return height(tree.leftChild()) - height(tree.rightChild());
    }

    private boolean shouldRotateLeftRight(Node<E> tree, E valueInserted) {
        return goesBefore(tree.leftChild().value(), valueInserted);
    }

    private boolean shouldRotateRightLeft(Node<E> tree, E valueInserted) {
        return goesAfter(tree.rightChild().value(), valueInserted);
    }

    public int size() {
        return size(root);
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
