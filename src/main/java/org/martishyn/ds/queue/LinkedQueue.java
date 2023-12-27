package org.martishyn.ds.queue;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * LinkedQueue {@link LinkedQueue#add(Object)} and {@link LinkedQueue#poll()} performed in O(1) time.
 */

public class LinkedQueue<T> implements Queue<T> {
    private int size;
    private Node<T> head;
    private Node<T> tail;

    static class Node<T> {
        T element;
        Node<T> next;

        public Node(T element) {
            this.element = element;
        }

        public Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }
    }
    /**
     * Adds element to the end of queue.
     *
     * @param element the element that will be added
     */
    @Override
    public void add(T element) {
        Objects.requireNonNull(element);
        Node<T> newNode = new Node<>(element);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /**
     * Retrieves and removes queue head.
     *
     * @return head element or null if queue is empty
     */
    @Override
    public T poll() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T element = head.element;
        if (size == 1) {
            head = tail = null;
        } else {
            head = head.next;
        }
        size--;
        return element;
    }

    /**
     * Returns the size of the queue
     *
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * Check if the queue is empty
     *
     * @return true or false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
