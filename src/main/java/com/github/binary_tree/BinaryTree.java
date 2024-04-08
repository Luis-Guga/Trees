package com.github.binary_tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinaryTree <T> {
    private final Comparator<T> comparator;
    private int treeSize;
    private Node<T> root;
    private int height;
    private List<T> values;

    public BinaryTree(Comparator<T> comparator) {
        this.root = null;
        this.treeSize = 0;
        this.comparator = comparator;
    }

    public BinaryTree() {
        this.root = null;
        this.treeSize = 0;
        this.comparator = null;
    }

    public boolean add(T newElement) {
        if (contains(newElement)) {
            return false;
        }
        if (this.root == null) {
            this.root = new Node<>(newElement);
        } else {
            addElementToBestPlace(this.root, newElement);
        }
        treeSize++;
        return true;
    }

    public boolean contains(T element) {
        if (this.root == null) {
            return false;
        }
        else {
            return containsUnderThisNode(this.root, element);
        }
    }

    public boolean remove(T element) {
        return removeElement(element, false);
    }

    public int size() {
        return treeSize;
    }

    public int height() {
        height = 0;
        calculateDepth(this.root, 1);
        return height;
    }

    public Node<T> getRoot() {
        return this.root;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public List<T> values() {
        this.values = new ArrayList<>();
        recursiveValues(this.root);
        return this.values;
    }

    private void recursiveValues(Node<T> root) {
        if (root.getLeftChild() != null && root.getRightChild() != null) {
            recursiveValues(root.getLeftChild());
            this.values.add(root.getData());
            recursiveValues(root.getRightChild());
        } else if (root.getLeftChild() != null) {
            recursiveValues(root.getLeftChild());
            this.values.add(root.getData());
        } else if (root.getRightChild() != null) {
            this.values.add(root.getData());
            recursiveValues(root.getRightChild());
        } else {
            this.values.add(root.getData());
        }
    }
    private void calculateDepth(Node<T> node, int curDepth) {
        if (node == null) {
            return;
        }
        height = Math.max(height, curDepth);
        calculateDepth(node.getRightChild(), curDepth + 1);
        calculateDepth(node.getLeftChild(), curDepth + 1);
    }

    private boolean removeElement(T element, boolean changePlaceElement) {
        boolean elementDeleted = iterativeRemoveElement(this.root, element);
        if (!changePlaceElement && elementDeleted) {
            this.treeSize--;
        }
        return elementDeleted;
    }
    private boolean iterativeRemoveElement(Node<T> root, T element) {
        Node<T> parent = null;
        Node<T> currentNode = root;
        boolean isLeftChild = false;
        while (currentNode != null) {
            if (compare(currentNode.getData(), element) < 0) {
                parent = currentNode;
                currentNode = currentNode.getRightChild();
                isLeftChild = false;
            } else if (compare(currentNode.getData(), element) > 0) {
                parent = currentNode;
                currentNode = currentNode.getLeftChild();
                isLeftChild = true;
            } else {
                break;
            }
        }
        if (currentNode == null) {
            return false;
        }
        if (parent == null) {
            this.root = removeNode(root);
            return true;
        }
        if (isLeftChild) {
            parent.changeLeftChild(removeNode(currentNode));
        } else {
            parent.changeRightChild(removeNode(currentNode));
        }
        return true;
    }
    private Node<T> removeNode(Node<T> node) {
        Node<T> newNode;
        if (node.getRightChild() == null && node.getLeftChild() == null) {
            newNode = null;
        } else if (node.getLeftChild() == null) {
            newNode = node.getRightChild();
        } else if (node.getRightChild() == null) {
            newNode = node.getLeftChild();
        } else {
            newNode = removeNodeBothNotNull(node);
        }
        return newNode;
    }
    private Node<T> removeNodeBothNotNull(Node<T> node) {
        T newValue = findMaxElement(node.getLeftChild());
        removeElement(newValue, true);
        node.changeData(newValue);
        return node;
    }

    private T findMaxElement(Node<T> node) {
        if (node.getRightChild() == null) {
            return node.getData();
        } else {
            return findMaxElement(node.getRightChild());
        }
    }
    private boolean containsUnderThisNode(Node<T> root, T element) {
        if (compare(element, root.getData()) > 0) {
            if (root.getRightChild() == null) {
                return false;
            } else {
                return containsUnderThisNode(root.getRightChild(), element);
            }
        } else if (compare(element, root.getData()) < 0) {
            if (root.getLeftChild() == null) {
                return false;
            } else {
                return containsUnderThisNode(root.getLeftChild(), element);
            }
        } else {
            return true;
        }
    }
    private void addElementToBestPlace(Node<T> currentNode, T newElement) {
        if (compare(newElement, currentNode.getData()) > 0) {
            if (currentNode.getRightChild() == null) {
                currentNode.addRightChild(newElement);
            } else {
                addElementToBestPlace(currentNode.getRightChild(), newElement);
            }
        } else if (compare(newElement, currentNode.getData()) < 0) {
            if (currentNode.getLeftChild() == null) {
                currentNode.addLeftChild(newElement);
            } else {
                addElementToBestPlace(currentNode.getLeftChild(), newElement);
            }
        }
    }

    private int compare(T first, T second) {
        return this.comparator == null ? ((Comparable<T>) first).compareTo(second) :
                comparator.compare(first, second);
    }
}
