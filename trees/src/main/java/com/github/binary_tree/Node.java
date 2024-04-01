package com.github.binary_tree;

public class Node <T extends Comparable<T>> {
    private T data;
    private Node<T> leftChild;
    private Node<T> rightChild;
    private Node<T> parent;
    private com.github.binary_tree.PositionInTree positionInTree;

    public Node(T data, Node<T> parent, PositionInTree position) {
        this.data = data;
        this.leftChild = null;
        this.rightChild = null;
        this.parent = parent;
        this.positionInTree = position;
    }
    public void addRightChild(T childData, Node<T> parent, PositionInTree position) {
        rightChild = new Node<>(childData, parent, position);
    }
    public void addLeftChild(T childData, Node<T> parent, PositionInTree position) {
        leftChild = new Node<>(childData, parent, position);
    }
    public void changeLeftChild(Node<T> newChild) {
        leftChild = newChild;
    }
    public void changeRightChild(Node<T> newChild) {
        rightChild = newChild;
    }
    public T getData() {
        return data;
    }
    public Node<T> getLeftChild() {
        return leftChild;
    }
    public Node<T> getRightChild() {
        return rightChild;
    }
    public Node<T> getParent() {
        return parent;
    }

    public PositionInTree getPositionInTree() {
        return positionInTree;
    }
    public void changePosition(PositionInTree position) {
        this.positionInTree = position;
    }
    public void changeData(T data) {
        this.data = data;
    }
}
