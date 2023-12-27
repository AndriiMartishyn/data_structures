package org.martishyn.ds.queue;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.martishyn.ds.stack.LinkedStack;

import java.lang.reflect.Field;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

class LinkedQueueTest {

    private static final String HEAD = "head";
    private static final String TAIL = "tail";
    private final Queue<Integer> linkedQueue = new LinkedQueue<>();

    @Test
    void shouldThrowExceptionWhenAddNullElement() {
        Assertions.assertThrows(NullPointerException.class, () -> linkedQueue.add(null));
    }

    @Test
    void shouldAddHeadAndTailWhenAddingToEmptyQueue() {
        assertThat(getInternalSize(linkedQueue)).isZero();
        assertThat(getParticularNode(linkedQueue, HEAD)).isNull();
        assertThat(getParticularNode(linkedQueue, TAIL)).isNull();

        linkedQueue.add(1);
        LinkedQueue.Node<Integer> head = getParticularNode(linkedQueue, HEAD);
        LinkedQueue.Node<Integer> tail = getParticularNode(linkedQueue, TAIL);

        assertThat(getInternalSize(linkedQueue)).isEqualTo(1);
        assertThat(head.element).isEqualTo(1);
        assertThat(tail.element).isEqualTo(1);
    }

    @Test
    void shouldMakeAddedNodeAsTail() {
        assertThat(getInternalSize(linkedQueue)).isZero();
        assertThat(getParticularNode(linkedQueue, HEAD)).isNull();
        assertThat(getParticularNode(linkedQueue, TAIL)).isNull();

        linkedQueue.add(1);
        LinkedQueue.Node<Integer> oldTail = getParticularNode(linkedQueue, TAIL);

        linkedQueue.add(2);
        LinkedQueue.Node<Integer> newTail = getParticularNode(linkedQueue, TAIL);

        assertThat(getInternalSize(linkedQueue)).isEqualTo(2);
        assertThat(oldTail.element).isEqualTo(1);
        assertThat(newTail.element).isEqualTo(2);
    }

    @Test
    void shouldThrowExceptionWhenPollFromEmptyList() {
        Assertions.assertThrows(NoSuchElementException.class, linkedQueue::poll);
    }

    @Test
    void shouldRemoveNodesWhenPollLastElement() {
        assertThat(getInternalSize(linkedQueue)).isZero();

        linkedQueue.add(111);
        LinkedQueue.Node<Integer> head = getParticularNode(linkedQueue, TAIL);
        LinkedQueue.Node<Integer> tail = getParticularNode(linkedQueue, TAIL);

        int polledElement = linkedQueue.poll();

        assertThat(getInternalSize(linkedQueue)).isZero();
        assertThat(head.element).isEqualTo(111);
        assertThat(tail.element).isEqualTo(111);
        assertThat(getParticularNode(linkedQueue, HEAD)).isNull();
        assertThat(getParticularNode(linkedQueue, TAIL)).isNull();
        assertThat(polledElement).isEqualTo(111);
    }

    @Test
    void shouldMoveHeadPointerToTheNextNodeWhenPoll() {
        assertThat(getInternalSize(linkedQueue)).isZero();

        linkedQueue.add(111);
        linkedQueue.add(222);
        linkedQueue.add(333);

        LinkedQueue.Node<Integer> head = getParticularNode(linkedQueue, HEAD);
        int polledElement = linkedQueue.poll();
        LinkedQueue.Node<Integer> newHead = getParticularNode(linkedQueue, HEAD);

        assertThat(getInternalSize(linkedQueue)).isEqualTo(2);
        assertThat(head.next).isEqualTo(newHead);
        assertThat(polledElement).isEqualTo(111);
    }


    @SneakyThrows
    private <T> LinkedQueue.Node<T> getParticularNode(Queue<T> queue, String field) {
        Field head = LinkedQueue.class.getDeclaredField(field);
        head.setAccessible(true);
        return (LinkedQueue.Node<T>) head.get(queue);
    }

    @SneakyThrows
    private int getInternalSize(Queue queue) {
        Field size = LinkedQueue.class.getDeclaredField("size");
        size.setAccessible(true);
        return (int) size.get(queue);
    }
}
