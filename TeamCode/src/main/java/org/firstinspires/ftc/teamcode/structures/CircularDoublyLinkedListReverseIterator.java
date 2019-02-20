package org.firstinspires.ftc.teamcode.structures;

import java.util.Iterator;

public final class CircularDoublyLinkedListReverseIterator<E> implements Iterator {

    private Node<E> tail;
    private Node<E> cursor;

    CircularDoublyLinkedListReverseIterator(Node<E> tail) {
        this.tail = tail;
        cursor = tail;
    }

    @Override
    public boolean hasNext() {
        return cursor.getNext() != tail;
    }

    @Override
    public E next() {
        return (cursor = cursor.getPrev()).getData();
    }
}
