package org.martishyn.ds.stack;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * LinkedStack implementation of Stack interface. Based on inner {@link Node} class with linking.
 * Contains head(top) element.
 */
public class LinkedStack<T> implements Stack<T> {
    int size;
    protected Node<T> top;

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
     * The method creates a stack of linked elements
     *
     * @param elements generic Elements passed
     */
    public static <T> LinkedStack<T> of(T... elements) {
        LinkedStack<T> linkedStack = new LinkedStack<>();
        Arrays.stream(elements)
                .forEach(linkedStack::push);
        return linkedStack;
    }

    /**
     * The method pushes an element onto the top of this stack.
     *
     * @param element added Element
     */
    @Override
    public void push(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        Node<T> newNode = new Node<>(element);
        if (!isEmpty()) {
            newNode.next = top;
        }
        top = newNode;
        size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        Node<T> returnedNode = top;
        if (size == 1) {
            top = null;
        } else {
            top = top.next;
        }
        size--;
        return (T) returnedNode.element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
