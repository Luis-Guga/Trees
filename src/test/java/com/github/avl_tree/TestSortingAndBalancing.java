package com.github.avl_tree;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestSortingAndBalancing {
    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3, 4 })
    void shouldStaySortedInRandomCasesWithStringAndOtherComparator(int seed) {
        AvlTree<String> avl = new AvlTree<>(Collections.reverseOrder());
        List<String> list = new ArrayList<>();
        Random random = new Random(seed);

        for (int i = 0; i < 1000; i++) {
            // length is bounded by 20
            int length = Math.abs(random.nextInt() % 20);
            byte[] array = new byte[length];
            random.nextBytes(array);
            String generatedString = new String(array, StandardCharsets.UTF_8);
            if (!list.contains(generatedString)) {
                list.add(generatedString);
            }
            avl.add(generatedString);
        }
        for (int i = 0; i < 500; i++) {
            int indexToRemove = Math.abs(random.nextInt() % list.size());
            String stringAtIndex = list.get(indexToRemove);
            list.remove(indexToRemove);
            avl.remove(stringAtIndex);
        }

        assertEquals(list.size(), avl.size());
        Collections.sort(list, Collections.reverseOrder());
        assertEquals(list, avl.values());
    }
}
