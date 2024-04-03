package com.github.avl_tree;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestRemoveAndSize {
    @Test
    public void testSimpleRemoval() {
        AvlTree<Integer> avl = new AvlTree<>();
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
        AvlTree<Integer> avl = new AvlTree<>();
        for (int i = 7; i > -7; i--) {
            avl.add(i);
        }
        int prevSize = avl.size();
        int prevHeight = avl.height();
        assertFalse(avl.remove(-10));
        assertEquals(avl.size(), prevSize);
        assertEquals(avl.height(), prevHeight);
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 1 })
    public void removeShouldGenerateLeftRightOrRightLeftRotation(int switchSides) {
        AvlTree<Integer> avl = new AvlTree<>();
        List<Integer> numbersList = new ArrayList<>();
        avl.add(switchSides * 0);
        avl.add(switchSides * 50);
        avl.add(switchSides * -50);
        avl.add(switchSides * -25);
        numbersList.add(switchSides * 0);
        numbersList.add(switchSides * 50);
        numbersList.add(switchSides * -50);
        numbersList.add(switchSides * -25);

        assertEquals(avl.height(), 2);
        avl.remove(switchSides * 50);
        numbersList.remove(numbersList.indexOf(switchSides * 50));
        assertEquals(avl.height(), 1);
        Collections.sort(numbersList);
        assertEquals(numbersList, avl.values());
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 1 })
    public void removeShouldRotateRightLeftOrLeftRightWithChildren(int switchSides) {
        AvlTree<Integer> avl = new AvlTree<>();
        List<Integer> numbersList = new ArrayList<>();

        avl.add(switchSides * 0);
        avl.add(switchSides * 50);
        avl.add(switchSides * -50);
        avl.add(switchSides * -25);
        avl.add(switchSides * -75);
        avl.add(switchSides * 25);
        avl.add(switchSides * 75);
        avl.add(switchSides * 30);
        avl.add(switchSides * 15);

        numbersList.add(switchSides * 0);
        numbersList.add(switchSides * 50);
        numbersList.add(switchSides * -50);
        numbersList.add(switchSides * -25);
        numbersList.add(switchSides * -75);
        numbersList.add(switchSides * 25);
        numbersList.add(switchSides * 75);
        numbersList.add(switchSides * 30);
        numbersList.add(switchSides * 15);
        assertEquals(avl.height(), 3);
        avl.remove(switchSides * 75);
        numbersList.remove(numbersList.indexOf(switchSides * 75));
        assertEquals(avl.height(), 3);
        Collections.sort(numbersList);
        assertEquals(numbersList, avl.values());
    }

    @Test
    public void removeShouldChangeTheRoot() {
        AvlTree<Integer> avl = new AvlTree<>();
        avl.add(0);
        avl.add(50);
        avl.add(-50);
        avl.add(-25);
        assertEquals(avl.height(), 2);
        avl.remove(0);
        assertEquals(avl.height(), 1);
        assertEquals(avl.values(), Arrays.asList(-50, -25, 50));
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3, 4 })
    void shouldStaySortedInRandomCases(int seed) {
        AvlTree<Integer> avl = new AvlTree<>();
        List<Integer> list = new ArrayList<>();
        Random random = new Random(seed);
        for (int i = 0; i < 1000; i++) {
            // Guarantee there will be repeated numbers
            int numberToAdd = random.nextInt();
            if (!list.contains(numberToAdd)) {
                list.add(numberToAdd);
            }
            avl.add(numberToAdd);
            assertTrue(avl.contains(numberToAdd));

        }
        for (int i = 0; i < 500; i++) {
            int numberToRemove = Math.abs(random.nextInt() % list.size());
            list.remove((Object) numberToRemove);
            avl.remove(numberToRemove);
            assertFalse(avl.contains(numberToRemove));
        }

        assertEquals(list.size(), avl.size());
        Collections.sort(list);
        assertEquals(list, avl.values());
    }
}
