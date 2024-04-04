package com.github.binary_tree;

public class Node <T extends Comparable<T>> {
    private T data;
    private Node<T> leftChild;
    private Node<T> rightChild;

    public Node(T data) {
        this.data = data;
        this.leftChild = null;
        this.rightChild = null;
    }
    public void addRightChild(T childData) {
        rightChild = new Node<>(childData);
    }
    public void addLeftChild(T childData) {
        leftChild = new Node<>(childData);
    }
    public void changeLeftChild(Node<T> newChild) {
        leftChild = newChild;
    }
    public void changeRightChild(Node<T> newChild) {
        rightChild = newChild;
    }
    public void changeData(T data) {
        this.data = data;
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
}
