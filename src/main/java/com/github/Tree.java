package com.github;

import java.util.Collection;

/**
 * Interface representing a generic tree structure.
 *
 * @version 1.0
 * @author Luis Hartmann
 * @param <E> the type of elements in the tree
 */
public interface Tree<E> extends Cloneable {

    /**
     * Adds an element to the tree.
     *
     * @param element the element to add
     * @return true if the element was effectively added, false if the value was
     *         already present
     */
    boolean add(E element);

    /**
     * Removes an element from the tree.
     *
     * @param element the element to remove
     * @return true if the element was successfully removed, false if the element
     *         was not present
     */
    boolean remove(E element);

    /**
     * Checks if the tree contains the specified element.
     *
     * @param element the element to check for containment
     * @return true if the tree contains the element, false otherwise
     */
    boolean contains(E element);

    /**
     * Returns the height of the tree.
     *
     * @return the height of the tree
     */
    int height();

    /**
     * Returns the number of elements in the tree.
     *
     * @return the number of elements in the tree
     */
    int size();

    /**
     * Returns a collection containing all the elements in the tree.
     *
     * @return a collection containing all the elements in the tree
     */
    Collection<E> values();
}
