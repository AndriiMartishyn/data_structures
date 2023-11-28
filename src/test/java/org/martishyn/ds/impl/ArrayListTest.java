package org.martishyn.ds.impl;


import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.martishyn.ds.interfaces.List;

import java.lang.reflect.Field;
import java.util.NoSuchElementException;

class ArrayListTest {
    private List<Integer> arrayList = new ArrayList<>();

    @Test
    void shouldThrowExceptionWhenInitCapacityNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ArrayList<>(-1));
    }
    @Test
    void shouldCreateArrayWithDefaultCapacity() {
        arrayList = new ArrayList<>();
        Object[] internalArray = getInternalArray();

        Assertions.assertEquals(5, internalArray.length);
    }

    @Test
    void shouldCreateArrayWithVarCapacity() {
        arrayList = new ArrayList<>(125);
        Object[] internalArray = getInternalArray();

        Assertions.assertEquals(125, internalArray.length);
    }

    @SneakyThrows
    @Test
    void shouldCreateArrayOfElementsPassed() {
        arrayList = ArrayList.of(1, 2, 3, 4);

        Assertions.assertEquals(4, getInternalSize());
    }

    @Test
    void shouldAddElementsInArray() {
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        Object[] internalArray = getInternalArray();

        Assertions.assertEquals(1, internalArray[0]);
        Assertions.assertEquals(2, internalArray[1]);
        Assertions.assertEquals(3, internalArray[2]);
        Assertions.assertEquals(3, getInternalSize());
    }

    @Test
    void addShouldResizeDefaultCapacityWhenArrayIsFull() {
        arrayList = new ArrayList<>(4);
        arrayList.add(0);
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);

        Assertions.assertTrue(getInternalArray().length > 4);
        Assertions.assertEquals(5, getInternalSize());
    }

    @Test
    void getMethodWithWrongIndexShouldThrowException() {
        int negativeIndex = -1;
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(negativeIndex));

        int biggerThanCapacityIndex = getInternalArray().length + 1;
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(biggerThanCapacityIndex));
    }

    @Test
    void getMethodWithEmptyListShouldThrowException() {
        Assertions.assertThrows(NoSuchElementException.class, arrayList::getFirst, "Another behavior of method");
        Assertions.assertThrows(NoSuchElementException.class, arrayList::getLast, "Another behavior of method");
    }

    @Test
    void addMethodWithWrongIndexShouldThrowException() {
        int negativeIndex = -1;
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> arrayList.add(negativeIndex, null));

        int biggerThanCapacityIndex = getInternalArray().length + 1;
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> arrayList.add(biggerThanCapacityIndex, null));
    }


    @Test
    void shouldAddElementByIndexAndResizeWhenFull() {
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);

        arrayList.add(5, 111);

        Assertions.assertEquals(111, getInternalArray()[5]);
        Assertions.assertEquals(6, getInternalSize());
    }

    @Test
    void shouldThrowExceptionWhenGettingFromEmptyArray() {
        arrayList = new ArrayList<>();

        Assertions.assertThrows(NoSuchElementException.class, () -> arrayList.getFirst());
        Assertions.assertThrows(NoSuchElementException.class, () -> arrayList.getLast());
    }


    @Test
    void shouldSetElementWithoutModifications() {
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        arrayList.set(0, 100);

        Assertions.assertEquals(100, getInternalArray()[0]);
        Assertions.assertEquals(3, getInternalArray()[2]);
        Assertions.assertEquals(3, getInternalSize());
    }

    @Test
    void shouldGetElementByIndex() {
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        Assertions.assertEquals(1, arrayList.get(0));
        Assertions.assertEquals(3, arrayList.get(2));
    }

    @Test
    void shouldGetFirstAndLastElement() {
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);

        Assertions.assertEquals(1, arrayList.getFirst());
        Assertions.assertEquals(4, arrayList.getLast());
        Assertions.assertEquals(4, getInternalSize());
    }

    @Test
    void shouldRemoveElementByIndex() {
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);

        int removedElement = arrayList.remove(0);
        int lastRemoved = arrayList.remove(3);

        Assertions.assertEquals(1, removedElement);
        Assertions.assertEquals(5, lastRemoved);
        Assertions.assertEquals(2, getInternalArray()[0]);
        Assertions.assertEquals(4, getInternalArray()[2]);
        Assertions.assertEquals(3, getInternalSize());
        Assertions.assertNull(getInternalArray()[3]);
    }

    @Test
    void shouldReturnTrueIfElementExists() {
        arrayList = ArrayList.of(1, 2, 3, 4, 5);

        Assertions.assertTrue(arrayList.contains(5));
        Assertions.assertFalse(arrayList.contains(100011));
    }

    @Test
    void shouldRemoveElementsInArrayWhenClearCalled() {
        arrayList = ArrayList.of(1, 2, 3, 4, 5);

        int sizeBeforeClear = getInternalSize();
        arrayList.clear();
        int sizeAfterClear = getInternalSize();

        Assertions.assertEquals(5, sizeBeforeClear);
        Assertions.assertEquals(0, sizeAfterClear);
    }

    @SneakyThrows
    private Object[] getInternalArray() {
        Field elements = ArrayList.class.getDeclaredField("elements");
        elements.setAccessible(true);
        return (Object[]) elements.get(arrayList);
    }

    @SneakyThrows
    private int getInternalSize() {
        Field size = ArrayList.class.getDeclaredField("size");
        size.setAccessible(true);
        return (int) size.get(arrayList);
    }
}
