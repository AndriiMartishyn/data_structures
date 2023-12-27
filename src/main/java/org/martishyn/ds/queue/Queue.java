package org.martishyn.ds.queue;

/**
 * Queue is a LILO (last-in-last-out) data structure.
 */
public interface Queue<T> {

    void add(T element);

    T poll();

    int size();

    boolean isEmpty();
}
