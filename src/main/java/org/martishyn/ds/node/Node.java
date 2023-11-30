package org.martishyn.ds.node;

/**
 * Node class is a data structure that represents element itself and link to the next element.
 * Basis for data structures that keep an underlying infrastructure as linked nodes.
 */
public class Node<T> {
     T element;
     Node<T> next;

    public Node(T element) {
        this.element = element;
    }
}
