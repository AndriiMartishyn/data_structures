package org.martishyn.ds.node;

/**
 * A class that consists of static methods for {@link Node}
 */
public class Nodes {
    private Nodes() {
    }

    /**
     * Create a new instance of {@link Node}
     *
     * @param element element of type T
     * @param <T>     generic type
     * @return a new instance of {@link Node}
     */
    public static <T> Node<T> create(T element) {
        return new Node<T>(element);
    }

    /**
     * Create a connection between 2 Nodes, so the first node stores reference to the second node
     *
     * @param first  {@link Node}
     * @param second {@link Node}
     * @param <T>    generic type
     */
    public static <T> void link(Node<T> first, Node<T> second) {
        first.next = second;
    }

    /**
     * Create 2 {@link Node} objects, creates connections between them, so first {@link Node}will refer
     * to the second {@link Node}
     *
     * @param firstElement  of any type T
     * @param secondElement of any type T
     * @param <T>           generic type
     * @return a reference to the first node
     */
    public static <T> Node<T> pair(T firstElement, T secondElement) {
        Node<T> first = new Node<>(firstElement);
        first.next = new Node<>(secondElement);
        return first;
    }

    /**
     * Create 2 {@link Node} objects, creates connections between them, so first node will hold a reference to the second
     * and vice versa
     *
     * @param firstElement  of any type T
     * @param secondElement of any type T
     * @param <T>           generic type
     * @return a reference to the first node
     */
    public static <T> Node<T> closedPair(T firstElement, T secondElement) {
        Node<T> first = new Node<>(firstElement);
        first.next = new Node<>(secondElement);
        first.next.next = first;
        return first;
    }

    /**
     * Creates a linked list of nodes based on provided elements. Last node not linked with first node.
     *
     * @param elements elements of any type T
     * @param <T>      generic type
     * @return a reference to the first node
     */
    public static <T> Node<T> chainOf(T... elements) {
        Node<T> first = new Node<>(elements[0]);
        Node<T> currentNode = first;
        for (int i = 1; i < elements.length; i++) {
            currentNode.next = new Node<>(elements[i]);
            currentNode = currentNode.next;
        }
        return first;
    }

    /**
     * Creates a linked list of nodes based on provided elements. Last node not linked with first node.
     *
     * @param elements elements of any type T
     * @param <T>      generic type
     * @return a reference to the first node
     */
    public static <T> Node<T> circleOf(T... elements) {
        Node<T> first = new Node<>(elements[0]);
        Node<T> currentNode = first;
        for (int i = 1; i < elements.length; i++) {
            currentNode.next = new Node<>(elements[i]);
            currentNode = currentNode.next;
        }
        currentNode.next = first;
        return first;
    }
}
