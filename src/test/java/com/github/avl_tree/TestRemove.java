package com.github.avl_tree;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class TestRemove {
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
    
}
