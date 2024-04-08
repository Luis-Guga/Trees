package com.githab.binary_tree;

//import com.github.binary_tree.BinaryTree;
import com.github.binary_tree.BinaryTree;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestBinaryTree {
    @Test
    public void testAdd() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.add(1);
        binaryTree.add(5);
        binaryTree.add(4);
        assertTrue(binaryTree.contains(5));
        assertTrue(binaryTree.contains(4));
        assertTrue(binaryTree.contains(1));
        assertFalse(binaryTree.contains(0));
        assertFalse(binaryTree.contains(3));
        assertFalse(binaryTree.contains(-1));
    }
    @Test
    public void testContains() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.add(1);
        binaryTree.add(5);
        binaryTree.add(4);
        assertTrue(binaryTree.contains(1));
        assertTrue(binaryTree.contains(5));
        assertTrue(binaryTree.contains(4));
        assertFalse(binaryTree.contains(0));
        assertFalse(binaryTree.contains(-1));
    }
    @Test
    public void testRemove() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.add(9);
        binaryTree.add(20);
        binaryTree.add(21);
        binaryTree.add(16);
        binaryTree.add(13);
        binaryTree.add(11);
        binaryTree.add(10);
        binaryTree.add(12);
        binaryTree.add(14);
        binaryTree.add(15);
        binaryTree.add(18);
        binaryTree.add(17);
        binaryTree.add(19);
        binaryTree.remove(16);
        Assert.assertEquals(15, binaryTree.getRoot().getRightChild().getLeftChild().getData().intValue());
        binaryTree.remove(9);
        Assert.assertEquals(20, binaryTree.getRoot().getData().intValue());
    }
    @Test
    public void testSize() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.add(9);
        binaryTree.add(20);
        binaryTree.add(21);
        binaryTree.add(16);
        binaryTree.add(13);
        binaryTree.add(11);
        binaryTree.add(10);
        binaryTree.add(12);
        binaryTree.add(14);
        binaryTree.add(15);
        binaryTree.add(18);
        binaryTree.add(17);
        binaryTree.add(19);
        binaryTree.remove(16);
        Assert.assertEquals(12, binaryTree.size());
    }
    @Test
    public void testDepth() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.add(9);
        binaryTree.add(20);
        binaryTree.add(21);
        binaryTree.add(16);
        binaryTree.add(13);
        binaryTree.add(11);
        binaryTree.add(10);
        binaryTree.add(12);
        binaryTree.add(14);
        binaryTree.add(15);
        binaryTree.add(18);
        binaryTree.add(17);
        binaryTree.add(19);
        binaryTree.remove(16);
        binaryTree.remove(15);
        binaryTree.remove(14);
        assertEquals(5, binaryTree.height());
    }
    /*@Test
    public void valuesTest() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        List<Integer> elements = new ArrayList<>();
        binaryTree.add(9);
        elements.add(9);
        binaryTree.add(20);
        elements.add(20);
        binaryTree.add(21);
        elements.add(21);
        binaryTree.add(16);
        elements.add(16);
        binaryTree.add(13);
        elements.add(13);
        binaryTree.add(11);
        elements.add(11);
        binaryTree.add(10);
        elements.add(10);
        binaryTree.add(12);
        elements.add(12);
        binaryTree.add(14);
        elements.add(14);
        binaryTree.add(15);
        elements.add(15);
        binaryTree.add(18);
        elements.add(18);
        binaryTree.add(17);
        elements.add(17);
        binaryTree.add(19);
        elements.add(19);
        binaryTree.remove(16);
        elements.remove(16);
        binaryTree.remove(15);
        elements.remove(15);
        binaryTree.remove(14);
        elements.remove(14);
        assertTrue(elements.equals(binaryTree.values()));
    }*/
}
