package com.githab.binary_tree;

import com.github.binary_tree.BinaryTree;
import com.github.binary_tree.Node;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestContains {
    @Test
    public void testContainsAddedElement() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(1);
        tree.add(2);
        tree.add(3);
        Assert.assertTrue(tree.contains(1));
        Assert.assertTrue(tree.contains(2));
        Assert.assertTrue(tree.contains(3));
    }

    @Test
    public void containsDoesntChangeTree() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        List<Integer> elementsList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 1000; i ++) {
            int element = random.nextInt();
            tree.add(element);
            elementsList.add(element);
        }
        Integer rootData = tree.getRoot().getData();
        int height = tree.height();
        int size = tree.size();
        Assert.assertEquals(height, tree.height());
        Assert.assertEquals(size, tree.size());
        Assert.assertEquals(rootData, tree.getRoot().getData());
    }
    @Test
    public void containsNotExistingElement() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(1);
        tree.add(6);
        tree.add(5);
        Assert.assertFalse(tree.contains(-1));
        Assert.assertFalse(tree.contains(3));
        Assert.assertFalse(tree.contains(10000));
    }
}
