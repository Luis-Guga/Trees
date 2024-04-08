package com.githab.binary_tree;

import com.github.binary_tree.BinaryTree;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TestValues {
    @Test
    public void ValuesListIsSorted() {
        List<Integer> valuesList = new ArrayList<>();
        BinaryTree<Integer> tree = new BinaryTree<>();
        Random random = new Random();
        for(int i = 0; i < 10; i++) {
            int newValue = random.nextInt();
            valuesList.add(newValue);
            tree.add(newValue);
        }
        Collections.sort(valuesList);
        Assert.assertEquals(valuesList, tree.values());
    }
}
