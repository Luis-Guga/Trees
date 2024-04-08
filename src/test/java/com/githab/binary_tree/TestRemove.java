package com.githab.binary_tree;

import com.github.binary_tree.BinaryTree;
import com.github.binary_tree.Node;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestRemove {
    private boolean checkIfTree(Node<Integer> root) {
        if (root == null) {
            return true;
        }
        if (root.getLeftChild() != null && root.getRightChild() != null) {
            if (root.getData().compareTo(root.getLeftChild().getData()) < 0 || root.getData().compareTo(root.getRightChild().getData()) > 0) {
                return false;
            }
            return checkIfTree(root.getLeftChild()) && checkIfTree(root.getRightChild());
        } else if (root.getRightChild() != null) {
            if (root.getData().compareTo(root.getRightChild().getData()) > 0) {
                return false;
            }
            return checkIfTree(root.getRightChild());
        } else if (root.getLeftChild() != null) {
            if (root.getData().compareTo(root.getLeftChild().getData()) < 0) {
                return false;
            }
            return checkIfTree(root.getLeftChild());
        } else {
            return true;
        }
    }
    @Test
    public void testRemoveRoot() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(6);
        tree.add(10);
        tree.add(7);
        tree.add(12);
        tree.add(3);
        tree.add(1);
        tree.add(5);
        Assert.assertEquals(tree.getRoot().getData(), (Integer) 6);
        tree.remove(6);
        checkIfTree(tree.getRoot());
        Assert.assertTrue(checkIfTree(tree.getRoot()));
    }
    @Test
    public void testRemoveAnotherNode() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        List<Integer> valuesList = new ArrayList<>();
        for (int i = 0; i < 1000; i += 37) {
            valuesList.add(i);
            tree.add(i);
        }
        tree.remove(valuesList.get(5));
        valuesList.remove(5);
        Assert.assertTrue(checkIfTree(tree.getRoot()));
        for (int i : valuesList) {
            Assert.assertTrue(tree.contains(i));
        }
    }
    @Test
    public void removeNotExistingElement() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        for (int i = 0; i < 10; i++) {
            tree.add(i);
        }
        int size = tree.size();
        Assert.assertFalse(tree.remove(10));
        Assert.assertEquals(size, tree.size());
    }
}
