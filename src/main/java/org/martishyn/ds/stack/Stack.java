package org.martishyn.ds.stack;

/**
 * Stack is a LIFO (last-in-first-out) data structure. May be implemented in various ways.
 */
public interface Stack<T> {

    void push(T element);

    T pop();

    int size();

    boolean isEmpty();

}
