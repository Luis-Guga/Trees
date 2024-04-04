package com.github.binary_tree;

public class BinaryTree <T extends Comparable<T>> {
    private int treeSize;
    private Node<T> root;
    private int depth;

    public BinaryTree() {
        this.root = null;
        treeSize = 0;
    }

    public void add(T newElement) {
        if (!contains(newElement)) {
            treeSize++;
        }
        if (this.root == null) {
            this.root = new Node<>(newElement);
        } else {
            addElementToBestPlace(this.root, newElement);
        }
    }

    public boolean contains(T element) {
        if (this.root == null) {
            return false;
        }
        else {
            return containsUnderThisNode(this.root, element);
        }
    }

    public void remove(T element) {
        removeElement(element, false);
    }

    public int size() {
        return treeSize;
    }

    public int depth() {
        depth = 0;
        calculateDepth(this.root, 1);
        return depth;
    }

    public Node<T> getRoot() {
        return this.root;
    }

    private void calculateDepth(Node<T> node, int curDepth) {
        if (node == null) {
            return;
        }
        depth = Math.max(depth, curDepth);
        calculateDepth(node.getRightChild(), curDepth + 1);
        calculateDepth(node.getLeftChild(), curDepth + 1);
    }

    private void removeElement(T element, boolean changePlaceElement) {
        if (!changePlaceElement) {
            this.treeSize--;
        }
        iterativeRemoveElement(this.root, element);
    }
    private void iterativeRemoveElement(Node<T> root, T element) {
        Node<T> parent = null;
        Node<T> currentNode = root;
        boolean isLeftChild = false;
        while (currentNode != null) {
            if (currentNode.getData().compareTo(element) < 0) {
                parent = currentNode;
                currentNode = currentNode.getRightChild();
                isLeftChild = false;
            } else if (currentNode.getData().compareTo(element) > 0) {
                parent = currentNode;
                currentNode = currentNode.getLeftChild();
                isLeftChild = true;
            } else {
                break;
            }
        }
        if (currentNode == null) {
            return;
        }
        if (parent == null) {
            this.root = removeNode(root);
            return;
        }
        if (isLeftChild) {
            parent.changeLeftChild(removeNode(currentNode));
        } else {
            parent.changeRightChild(removeNode(currentNode));
        }
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
        if (element.compareTo(root.getData()) > 0) {
            if (root.getRightChild() == null) {
                return false;
            } else {
                return containsUnderThisNode(root.getRightChild(), element);
            }
        } else if (element.compareTo(root.getData()) < 0) {
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
        if (newElement.compareTo(currentNode.getData()) > 0) {
            if (currentNode.getRightChild() == null) {
                currentNode.addRightChild(newElement);
            } else {
                addElementToBestPlace(currentNode.getRightChild(), newElement);
            }
        } else if (newElement.compareTo(currentNode.getData()) < 0) {
            if (currentNode.getLeftChild() == null) {
                currentNode.addLeftChild(newElement);
            } else {
                addElementToBestPlace(currentNode.getLeftChild(), newElement);
            }
        }
    }
}
