package org.martishyn.ds.stack;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.EmptyStackException;

import static org.assertj.core.api.Assertions.assertThat;

class LinkedStackTest {
    private Stack<Integer> linkedStack = new LinkedStack<>();

    @Test
    void ofMethodReturnsCorrectTopElement() {
        linkedStack = LinkedStack.of(1, 2, 3, 4, 5, 6, 7);
        assertThat(getTopElement(linkedStack).element).isEqualTo(7);
    }

    @Test
    void ofMethodReturnsCorrectSize() {
        linkedStack = LinkedStack.of(1, 2, 3, 4, 5, 6, 7, 8);
        assertThat(getInternalSize()).isEqualTo(8);
    }

    @Test
    void shouldThrowExceptionWhenPushNullElement() {
        Assertions.assertThrows(NullPointerException.class, () -> linkedStack.push(null));
    }

    @Test
    void shouldAddTopElementWhenTopIsNull() {
        LinkedStack.Node<Integer> topElement = getTopElement(linkedStack);
        assertThat(topElement).isNull();

        linkedStack.push(1);
        LinkedStack.Node<Integer> newTopElement = getTopElement(linkedStack);

        assertThat(newTopElement.element).isEqualTo(1);
        assertThat(newTopElement.next).isNull();
    }

    @Test
    void shouldAddTopElementAndChangePointerToNewTop() {
        linkedStack.push(1);
        linkedStack.push(2);
        linkedStack.push(3);

        LinkedStack.Node<Integer> topElement = getTopElement(linkedStack);

        assertThat(topElement.element).isEqualTo(3);
        assertThat(getInternalSize()).isEqualTo(3);

        linkedStack.push(4);
        LinkedStack.Node<Integer> newTopElement = getTopElement(linkedStack);
        LinkedStack.Node<Integer> thirdNode = getNextNode(newTopElement);

        assertThat(newTopElement.element).isEqualTo(4);
        assertThat(getNextNode(newTopElement)).isEqualTo(thirdNode);
        assertThat(getInternalSize()).isEqualTo(4);

    }

    @Test
    void shouldThrowExceptionWhenPopFromEmptyStack() {
        Assertions.assertThrows(EmptyStackException.class, () -> linkedStack.pop());
    }

    @Test
    void shouldPopTopElementFromSingleStack() {
        linkedStack.push(1);
        LinkedStack.Node<Integer> topElement = getTopElement(linkedStack);

        assertThat(topElement).isNotNull();
        assertThat(getInternalSize()).isEqualTo(1);

        int pop = linkedStack.pop();
        LinkedStack.Node<Integer> newTopElement = getTopElement(linkedStack);

        assertThat(pop).isEqualTo(1);
        assertThat(newTopElement).isNull();
        assertThat(getInternalSize()).isZero();
    }

    @Test
    void shouldPopTopElementFromAndMakeNextNodeAsTop() {
        linkedStack.push(1);
        linkedStack.push(222);
        linkedStack.push(113);

        LinkedStack.Node<Integer> topElement = getTopElement(linkedStack);
        LinkedStack.Node<Integer> nextNode = getNextNode(topElement);

        assertThat(topElement.element).isEqualTo(113);
        assertThat(topElement.next).isEqualTo(nextNode);
        assertThat(getInternalSize()).isEqualTo(3);

        int pop = linkedStack.pop();

        LinkedStack.Node<Integer> newTop = getTopElement(linkedStack);
        assertThat(pop).isEqualTo(113);
        assertThat(newTop).isEqualTo(nextNode);
        assertThat(getInternalSize()).isEqualTo(2);
    }


    @SneakyThrows
    private <T> LinkedStack.Node<T> getTopElement(Stack<T> stack) {
        Field top = LinkedStack.class.getDeclaredField("top");
        top.setAccessible(true);
        return (LinkedStack.Node<T>) top.get(stack);
    }

    @SneakyThrows
    private <T> LinkedStack.Node getNextNode(LinkedStack.Node<T> node) {
        Field next = LinkedStack.Node.class.getDeclaredField("next");
        next.setAccessible(true);
        return (LinkedStack.Node) next.get(node);
    }

    @SneakyThrows
    private int getInternalSize() {
        Field size = LinkedStack.class.getDeclaredField("size");
        size.setAccessible(true);
        return (int) size.get(linkedStack);
    }
}
