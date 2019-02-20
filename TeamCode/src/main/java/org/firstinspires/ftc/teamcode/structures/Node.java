package org.firstinspires.ftc.teamcode.structures;

final class Node<E> {

    Node(Node<E> prev, E data, Node<E> next) {
        this.prev = prev;
        this.data = data;
        this.next = next;
    }
    
    Node<E> getPrev() {
        return prev;
    }

    void setPrev(Node<E> prev) {
        this.prev = prev;
    }

    E getData() {
        return data;
    }

    void setData(E data) {
        this.data = data;
    }

    Node<E> getNext() {
        return next;
    }

    void setNext(Node<E> next) {
        this.next = next;
    }

    private Node<E> prev;
    private E data;
    private Node<E> next;
}
