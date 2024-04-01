package com.github.balanced_binary_tree;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class TestRemove {
    @Test
    public void testSimpleRemoval() {
        BalancedBinarySearchTree<Integer> avl = new BalancedBinarySearchTree<>();
        for (int i = 7; i > -7; i--) {
            avl.add(i);
        }
        for (int i = 7; i > -7; i--) {
            assertTrue(avl.remove(i));
        }
        assertEquals(avl.size(), 0);
    }

    @Test
    public void removeNonExistingElementShouldNotChangeTree() {
        BalancedBinarySearchTree<Integer> avl = new BalancedBinarySearchTree<>();
        for (int i = 7; i > -7; i--) {
            avl.add(i);
        }
        int prevSize = avl.size();
        int prevHeight = avl.height();
        assertFalse(avl.remove(-10));
        assertEquals(avl.size(), prevSize);
        assertEquals(avl.height(), prevHeight);
    }

    @Test
    public void testAddNonExistingItemsOnTheRightShouldReturnTrue() {
        BalancedBinarySearchTree<Integer> avl = new BalancedBinarySearchTree<>();
        for (int i = -7; i < 7; i++) {
            assertTrue(avl.add(i));
        }
    }

    @Test
    public void testAddNonExistingItemsBalancedShouldReturnTrue() {
        BalancedBinarySearchTree<Integer> avl = new BalancedBinarySearchTree<>();
        assertTrue(avl.add(5));
        assertTrue(avl.add(2));
        assertTrue(avl.add(1));
        assertTrue(avl.add(0));
        assertTrue(avl.add(3));
        assertTrue(avl.add(4));
        assertTrue(avl.add(8));
        assertTrue(avl.add(7));
        assertTrue(avl.add(6));
        assertTrue(avl.add(9));
        assertTrue(avl.add(10));
    }

    @Test
    public void testAddExistingItemsBalancedShouldReturnFalse() {
        BalancedBinarySearchTree<Integer> avl = new BalancedBinarySearchTree<>();
        avl.add(5);
        avl.add(2);
        avl.add(1);
        avl.add(0);
        avl.add(3);
        avl.add(4);
        avl.add(8);
        avl.add(7);
        avl.add(6);
        avl.add(9);
        avl.add(10);
        assertFalse(avl.add(5));
        assertFalse(avl.add(2));
        assertFalse(avl.add(1));
        assertFalse(avl.add(0));
        assertFalse(avl.add(3));
        assertFalse(avl.add(4));
        assertFalse(avl.add(8));
        assertFalse(avl.add(7));
        assertFalse(avl.add(6));
        assertFalse(avl.add(9));
        assertFalse(avl.add(10));

    }

    @Test
    public void testAddExistingItemsOnTheRightShouldReturnFalse() {
        BalancedBinarySearchTree<Integer> avl = new BalancedBinarySearchTree<>();
        for (int i = -7; i < 7; i++) {
            avl.add(i);
        }
        for (int i = -7; i < 7; i++) {
            assertFalse(avl.add(i));
        }
    }

    @Test
    public void testAddExistingItemsShouldReturnFalse() {
        BalancedBinarySearchTree<Integer> avl = new BalancedBinarySearchTree<>();
        for (int i = 7; i > -7; i--) {
            avl.add(i);
        }
        for (int i = 7; i > -7; i--) {
            assertFalse(avl.add(i));
        }
    }

}
