package org.martishyn.ds.impl;

import org.martishyn.ds.interfaces.List;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 5;
    private Object[] elements;
    private int size;

    public ArrayList(int initCapacity) {
        if (initCapacity <= 0) {
            throw new IllegalArgumentException();
        }
        elements = new Object[initCapacity];
    }

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public static <T> List<T> of(T... elements) {
        List<T> populatedList = new ArrayList<>();
        Arrays.stream(elements).forEach(populatedList::add);
        return populatedList;
    }

    @Override
    public void add(T element) {
        if (!checkForEnoughSpace()) {
            grow();
        }
        elements[size++] = element;
    }

    private void grow() {
        Object[] newArray = Arrays.copyOf(elements, size * 2);
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }

    private boolean checkForEnoughSpace() {
        return size < elements.length;
    }

    @Override
    public void add(int index, T element) {
        checkIndex(index);
        if (index == size) {
            add(element);
        } else {
            System.arraycopy(elements, index, elements, index + 1, size - index);
            elements[index] = element;
            size++;
        }
    }

    @Override
    public void set(int index, T element) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        checkIndex(index);
        elements[index] = element;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    private void checkIndex(int index) {
        if (index < 0 || index > elements.length || isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return (T) elements[0];
    }

    @Override
    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return (T) elements[size - 1];
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Object removedElement = elements[index];
        if (index == size - 1) {
            elements[index] = null;
        } else {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        }
        size--;
        return (T) removedElement;
    }

    @Override
    public boolean contains(T element) {
        return Arrays.stream(elements)
                .anyMatch(elem -> elem.equals(element));
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        elements = new Object[DEFAULT_CAPACITY];
    }
}
