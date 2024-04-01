package com.github.avl_tree;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class TestAdd {

    @Test
    public void testAddItemsShouldIncreaseSize() {
        AvlTree<Integer> avl = new AvlTree<>();
        int size = 0;
        for (int i = 7; i > -7; i--) {
            avl.add(i);
            size++;
            assertEquals(avl.size(), size);
        }
    }

    @Test
    public void testAddNonExistingItemsOnTheRightShouldReturnTrue() {
        AvlTree<Integer> avl = new AvlTree<>();
        for (int i = -7; i < 7; i++) {
            assertTrue(avl.add(i));
        }
    }

    @Test
    public void testAddNonExistingItemsBalancedShouldReturnTrue() {
        AvlTree<Integer> avl = new AvlTree<>();
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
    public void testAddExistingItemsBalancedShouldKeepTreeUnchanged() {
        Set<Integer> list = new HashSet<>();
        list.add(5);
        list.add(2);
        list.add(1);
        list.add(0);
        list.add(3);
        list.add(4);
        list.add(8);
        list.add(7);
        list.add(6);
        list.add(9);
        list.add(10);

        AvlTree<Integer> avl = new AvlTree<Integer>(list);
        int size = list.size();
        for (Integer element : list) {
            assertFalse(avl.add(element));
            assertEquals(avl.size(), size);
        }

    }

    @Test
    public void testAddExistingItemsOnTheRightShouldReturnFalse() {
        AvlTree<Integer> avl = new AvlTree<>();
        for (int i = -7; i < 7; i++) {
            avl.add(i);
        }
        for (int i = -7; i < 7; i++) {
            assertFalse(avl.add(i));
        }
    }

    @Test
    public void testAddExistingItemsShouldReturnFalse() {
        AvlTree<Integer> avl = new AvlTree<>();
        for (int i = 7; i > -7; i--) {
            avl.add(i);
        }
        for (int i = 7; i > -7; i--) {
            assertFalse(avl.add(i));
        }
    }

}
