package balanced_binary_tree;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import com.github.balanced_binary_tree.BalancedBinarySearchTree;

public class TestHeigth {
    @Test
    @DisplayName("test heigth empty tree")
    public void testHeigthEmptyTree() {
        BalancedBinarySearchTree<Integer> avl = new BalancedBinarySearchTree<>();
        assertEquals(avl.heigth(), -1);
    }

    @Test
    @DisplayName("test heigth linked-list-like avl tree")
    public void testEmptyTree() {
        BalancedBinarySearchTree<Integer> avl = new BalancedBinarySearchTree<>();
        for (int i = -7; i < 7; i++) {
            avl.add(i);
        }
        assertEquals(avl.heigth(), 13);
    }

    @Test
    @DisplayName("test heigth balanced avl tree")
    public void testHeigthLinkedListLikeTree() {
        BalancedBinarySearchTree<Integer> avl = new BalancedBinarySearchTree<>();
        for (int i = -7; i < 7; i++) {
            avl.add(i);
        }
        assertEquals(avl.heigth(), 13);
    }

    @Test
    @DisplayName("test naturally balanced tree")
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

        assertEquals(avl.heigth(), 3);
    }
}
