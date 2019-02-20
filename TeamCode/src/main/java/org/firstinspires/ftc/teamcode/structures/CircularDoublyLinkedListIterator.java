package org.firstinspires.ftc.teamcode.structures;

import java.util.Iterator;

public final class CircularDoublyLinkedListIterator<E> implements Iterator {

    private Node<E> head;
    private Node<E> cursor;

    CircularDoublyLinkedListIterator(Node<E> head) {
        this.head = head;
        cursor = head;
    }

    @Override
    public boolean hasNext() {
        return cursor.getNext() != head;
    }

    @Override
    public E next() {
        return (cursor = cursor.getNext()).getData();
    }
}
