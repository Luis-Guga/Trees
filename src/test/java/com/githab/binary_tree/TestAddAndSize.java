package com.githab.binary_tree;
import com.github.binary_tree.BinaryTree;
import org.junit.Assert;
import org.junit.Test;

public class TestAddAndSize {
    @Test
    public void testAddIncreasesSize() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        int size = 0;
        for (int i = 0; i < 20; i += 3) {
            tree.add(i);
            size++;
            Assert.assertEquals(size, tree.size());
        }
    }

    @Test
    public void testContainsAddedElements() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        for (int i = 0; i > -10; i--) {
            tree.add(i);
            Assert.assertTrue(tree.contains(i));
        }
    }

    @Test
    public void testAddsToRightPlace() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(10);
        tree.add(12);
        Assert.assertEquals(tree.getRoot().getRightChild().getData(), (Integer) 12);
        tree.add(9);
        Assert.assertEquals(tree.getRoot().getLeftChild().getData(), (Integer) 9);
        tree.add(14);
        tree.add(15);
        tree.add(13);
        Assert.assertEquals(tree.getRoot().getRightChild().getRightChild().getLeftChild().getData(), (Integer) 13);
        tree.add(5);
        tree.add(7);
        tree.add(6);
        Assert.assertEquals(tree.getRoot().getLeftChild().getLeftChild().getRightChild().getLeftChild().getData(), (Integer) 6);
    }
    @Test
    public void addExistingElementsNotChangesTree() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        for (int i = 0; i < 80; i += 7) {
            Assert.assertTrue(tree.add(i));
        }
        for (int i = 0; i < 80; i += 7) {
            int size = tree.size();
            Assert.assertFalse(tree.add(i));
            Assert.assertEquals(size, tree.size());
        }
    }
}
