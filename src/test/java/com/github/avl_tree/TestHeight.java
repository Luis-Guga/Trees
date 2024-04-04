package com.github.avl_tree;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestHeight {
    @Test
    public void testHeightEmptyTreeShouldBeMinusOne() {
        AvlTree<Integer> avl = new AvlTree<>();
        assertEquals(avl.height(), -1);
    }

    @Test
    public void testHeightLinkedListLikeTree() {
        AvlTree<Integer> avl = new AvlTree<>();
        for (int i = -7; i < 0; i++) {
            avl.add(i);
        }
        assertEquals(avl.height(), 2);
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3, 4 })
    public void testRandomAddHeightShouldSatisfyInequality(int seed) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        AvlTree<Integer> avl = new AvlTree<>();
        Random random = new Random(seed);
        for (int i = 0; i < 1000; i++) {
            int numberToAdd = list.get(Math.abs(random.nextInt() % list.size()));
            avl.add(numberToAdd);
        }
        // This is the inequality of the height for an AVL tree
        assertTrue((Math.log(avl.size() + 1) / Math.log(2) - 1) <= avl.height()
                && avl.height() < Math.log(avl.size() + 2) / Math.log(1.618) - 0.328);
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3, 4 })
    public void testSequentialAddHeightShouldBeInInterval(int seed) {
        AvlTree<Integer> avl = new AvlTree<>();
        for (int i = 0; i < 1000; i++) {
            avl.add(i);
        }
        // This is the inequality of the height for an AVL tree
        assertTrue(Math.floor(Math.log(avl.size() + 1) / Math.log(2)) <= avl.height());
        assertTrue(avl.height() <= Math.log(avl.size() + 2) / Math.log(1.618) - 0.328);
    }

    @Test
    public void testTreeShouldRotateLeftOnce() {
        AvlTree<Integer> avl = new AvlTree<>();
        avl.add(1);
        assertEquals(avl.height(), 0);
        avl.add(2);
        assertEquals(avl.height(), 1);
        avl.add(3);
        assertEquals(avl.height(), 1);
    }

    @Test
    public void testTreeShouldRotateRightLeftOnce() {
        AvlTree<Integer> avl = new AvlTree<>();
        avl.add(0);
        assertEquals(avl.height(), 0);
        avl.add(-3);
        assertEquals(avl.height(), 1);
        avl.add(3);
        assertEquals(avl.height(), 1);
        avl.add(5);
        assertEquals(avl.height(), 2);
        avl.add(4);
        assertEquals(avl.height(), 2);
    }

    @Test
    public void testTreeShouldRotateRightOnce() {
        AvlTree<Integer> avl = new AvlTree<>();
        avl.add(-1);
        assertEquals(avl.height(), 0);
        avl.add(-2);
        assertEquals(avl.height(), 1);
        avl.add(-3);
        assertEquals(avl.height(), 1);
    }

    @Test
    public void testTreeShouldRotateLeftRightOnce() {
        AvlTree<Integer> avl = new AvlTree<>();
        avl.add(0);
        assertEquals(avl.height(), 0);
        avl.add(3);
        assertEquals(avl.height(), 1);
        avl.add(-3);
        assertEquals(avl.height(), 1);
        avl.add(-5);
        assertEquals(avl.height(), 2);
        avl.add(-4);
        assertEquals(avl.height(), 2);
    }
}
