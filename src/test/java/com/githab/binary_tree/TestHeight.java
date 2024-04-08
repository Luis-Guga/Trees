package com.githab.binary_tree;

import com.github.binary_tree.BinaryTree;
import org.junit.Assert;
import org.junit.Test;

public class TestHeight {
    @Test
    public void testAscendingOrderAddingHeightEqualSize() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        for (int i = 1; i < 10; i ++) {
            tree.add(i);
            Assert.assertEquals(tree.size(), tree.height());
        }
    }

    @Test
    public void testSimpleTreeHeight() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(10);
        tree.add(5);
        tree.add(6);
        tree.add(9);
        tree.add(8);
        tree.add(4);
        tree.add(0);
        tree.add(-1);
        Assert.assertEquals(5, tree.height());
    }
}
