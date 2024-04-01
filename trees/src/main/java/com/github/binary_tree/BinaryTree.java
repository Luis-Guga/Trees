package com.github.binary_tree;

public class BinaryTree <T extends Comparable<T>> {
    private Node<T> root;

    public BinaryTree() {
        this.root = null;
    }

    public void add(T newElement) {
        if (this.root == null) {
            this.root = new Node<>(newElement, null, PositionInTree.ROOT);
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
        if (!contains(element)) {
            return;
        }
        Node<T> nodeToRemove = findElementToRemove(root, element);
        removeThisNode(nodeToRemove);
    }

    public Node<T> getRoot() {
        return this.root;
    }

    private Node<T> findElementToRemove(Node<T> currentNode, T element) {
        if (element.compareTo(currentNode.getData()) > 0) {
            return findElementToRemove(currentNode.getRightChild(), element);
        } else if (element.compareTo(currentNode.getData()) < 0) {
            return findElementToRemove(currentNode.getLeftChild(), element);
        } else {
            return currentNode;
        }
    }
    private void removeThisNode(Node<T> node) {
        if (node.getLeftChild() == null && node.getRightChild() == null) {
            Node<T> parent = node.getParent();
            if (node.getPositionInTree() == PositionInTree.LEFT_CHILD) {
                parent.changeLeftChild(null);
            } else if (node.getPositionInTree() == PositionInTree.RIGHT_CHILD) {
                parent.changeRightChild(null);
            } else {
                root = null;
            }
        } else if (node.getRightChild() == null) {
            Node<T> parent = node.getParent();
            Node<T> child = node.getLeftChild();
            if (node.getPositionInTree() == PositionInTree.LEFT_CHILD) {
                parent.changeLeftChild(child);
            } else if (node.getPositionInTree() == PositionInTree.RIGHT_CHILD) {
                parent.changeRightChild(child);
            } else {
                root = child;
                root.changePosition(PositionInTree.ROOT);
            }
        } else if (node.getLeftChild() == null) {
            Node<T> parent = node.getParent();
            Node<T> child = node.getRightChild();
            if (node.getPositionInTree() == PositionInTree.LEFT_CHILD) {
                parent.changeLeftChild(child);
            } else if (node.getPositionInTree() == PositionInTree.RIGHT_CHILD) {
                parent.changeRightChild(child);
            } else {
                root = child;
                root.changePosition(PositionInTree.ROOT);
            }
        } else {
            T maxLeftElement = findMaxElement(node.getLeftChild());
            remove(maxLeftElement);
            node.changeData(maxLeftElement);
        }
    }
    private T findMaxElement(Node<T> node) {
        if (node.getRightChild() == null) {
            return node.getData();
        } else {
            return findMaxElement(node.getRightChild());
        }
    }
    private boolean containsUnderThisNode(Node<T> currentNode, T element) {
        if (element.compareTo(currentNode.getData()) > 0) {
            if (currentNode.getRightChild() == null) {
                return false;
            } else {
                return containsUnderThisNode(currentNode.getRightChild(), element);
            }
        } else if (element.compareTo(currentNode.getData()) < 0) {
            if (currentNode.getLeftChild() == null) {
                return false;
            } else {
                return containsUnderThisNode(currentNode.getLeftChild(), element);
            }
        } else {
            return true;
        }
    }
    private void addElementToBestPlace(Node<T> currentNode, T newElement) {
        if (newElement.compareTo(currentNode.getData()) > 0) {
            if (currentNode.getRightChild() == null) {
                currentNode.addRightChild(newElement, currentNode, PositionInTree.RIGHT_CHILD);
            } else {
                addElementToBestPlace(currentNode.getRightChild(), newElement);
            }
        } else if (newElement.compareTo(currentNode.getData()) < 0) {
            if (currentNode.getLeftChild() == null) {
                currentNode.addLeftChild(newElement, currentNode, PositionInTree.LEFT_CHILD);
            } else {
                addElementToBestPlace(currentNode.getLeftChild(), newElement);
            }
        }
    }
}
