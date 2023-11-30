package org.martishyn.ds.node;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

class NodesTest<T> {

    @Test
    void createMethodTest() {
        int element = 111;
        Node<Integer> node = Nodes.create(element);

        assertThat(getNodeElement(node)).isEqualTo(element);
        assertThat(getNextNode(node)).isNull();
    }

    @Test
    void linkMethodTest() {
        Node<Integer> first = Nodes.create(1);
        Node<Integer> second = Nodes.create(2);
        Node<Integer> third = Nodes.create(3);

        Nodes.link(first, second);
        Nodes.link(second,third);

        assertThat(getNextNode(first)).isEqualTo(second);
        assertThat(getNextNode(second)).isEqualTo(third);
    }

    @Test
    void pairMethodTest(){
        int firstElement = 1;
        int secondElement = 2;
        Node<Integer> firstNode = Nodes.pair(firstElement, secondElement);
        Node<Integer> secondNode = getNextNode(firstNode);

        assertThat(getNodeElement(firstNode)).isEqualTo(firstElement);
        assertThat(getNodeElement(secondNode)).isEqualTo(secondElement);
        assertThat(getNextNode(secondNode)).isNull();
    }

    @Test
    void closedPairMethodTest(){
        int firstElement = 1;
        int secondElement = 2;

        Node<Integer> firstNode = Nodes.closedPair(firstElement, secondElement);
        Node<Integer> secondNode = getNextNode(firstNode);

        assertThat(getNodeElement(firstNode)).isEqualTo(firstElement);
        assertThat(getNodeElement(secondNode)).isEqualTo(secondElement);
        assertThat(getNextNode(secondNode)).isEqualTo(firstNode);
    }

    @Test
    void chainOfMethodTest(){
        Integer[] elements = new Integer[]{1,2,3,4,5};

        Node<Integer> firstNode = Nodes.chainOf(elements);
        Node<Integer> secondNode = getNextNode(firstNode);
        Node<Integer> thirdNode = getNextNode(secondNode);
        Node<Integer> fourthNode = getNextNode(thirdNode);
        Node<Integer> fifthNode = getNextNode(fourthNode);

        assertThat(getNodeElement(firstNode)).isEqualTo(1);
        assertThat(getNodeElement(secondNode)).isEqualTo(2);
        assertThat(getNodeElement(thirdNode)).isEqualTo(3);
        assertThat(getNodeElement(fourthNode)).isEqualTo(4);
        assertThat(getNodeElement(fifthNode)).isEqualTo(5);
        assertThat(getNextNode(fifthNode)).isNull();
    }

    @Test
    void circleOfMethodTest(){
        Integer[] elements = new Integer[]{1,2,3,4,5};

        Node<Integer> firstNode = Nodes.circleOf(elements);
        Node<Integer> secondNode = getNextNode(firstNode);
        Node<Integer> thirdNode = getNextNode(secondNode);
        Node<Integer> fourthNode = getNextNode(thirdNode);
        Node<Integer> fifthNode = getNextNode(fourthNode);

        assertThat(getNodeElement(firstNode)).isEqualTo(1);
        assertThat(getNodeElement(secondNode)).isEqualTo(2);
        assertThat(getNodeElement(thirdNode)).isEqualTo(3);
        assertThat(getNodeElement(fourthNode)).isEqualTo(4);
        assertThat(getNodeElement(fifthNode)).isEqualTo(5);
        assertThat(getNextNode(fifthNode)).isEqualTo(firstNode);
    }

    @SneakyThrows
    private <T> T getNodeElement(Node<T> node) {
        Field element = Node.class.getDeclaredField("element");
        element.setAccessible(true);
        return (T) element.get(node);
    }

    @SneakyThrows
    private <T> Node<T> getNextNode(Node<T> node) {
        Field next = Node.class.getDeclaredField("next");
        next.setAccessible(true);
        return (Node<T>) next.get(node);
    }
}
