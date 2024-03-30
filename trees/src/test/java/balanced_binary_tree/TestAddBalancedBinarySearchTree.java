package balanced_binary_tree;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.balanced_binary_tree.BalancedBinarySearchTree;

public class TestAddBalancedBinarySearchTree {

    @Test
    @DisplayName("add non-existing elements on the left")
    public void testAddExistingItemsOntTheLeftShouldReturnFalse() {
        BalancedBinarySearchTree<Integer> avl = new BalancedBinarySearchTree<>();
        for (int i = 7; i > -7; i--) {
            avl.add(i);
        }
    }

    @Test
    @DisplayName("add non-existing elements on the right")
    public void testAddNonExistingItemsOnTheRightShouldReturnTrue() {
        BalancedBinarySearchTree<Integer> avl = new BalancedBinarySearchTree<>();
        for (int i = -7; i < 7; i++) {
            assertTrue(avl.add(i));
        }
    }

    @Test
    @DisplayName("add non-existing elements balanced")
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
    @DisplayName("add existing elements balanced")
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
    @DisplayName("add existing elements linearly on the right")
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
    @DisplayName("add existing elements linearly on the left")
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
