package org.firstinspires.ftc.teamcode.structures;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public final class CircularDoublyLinkedList<E> implements List<E>, Deque<E>,
        Cloneable, Serializable {

    private final transient Class<E> type;
    private transient Node<E> first;
    private transient Node<E> last;
    private transient int size;

    public CircularDoublyLinkedList(Class<E> type) {
        size = 0;
        this.type = type;
    }

    public CircularDoublyLinkedList(Class<E> type, Collection<? extends E> collection) {
        this(type);
        addAll(collection);
    }

    public void linkFirst(E element) {
        linkBefore(element, first);
    }

    public void linkLast(E element) {
        linkAfter(element, last);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new CircularDoublyLinkedListIterator<E>(first);
    }

    @Override
    public Object[] toArray() {
        if (size <= 0) return null;
        switch (size) {
            case 1:
                return new Object[] {first};
            case 2:
                return new Object[] {first, last};
            default:
                int i = 0;
                Object[] array = new Object[size];
                for (E obj : this) {
                    array[i] = obj;
                    i++;
                }
                return array;
        }
    }

    @Override
    public <T> T[] toArray(T[] elements) {
        Object[] array = new Object[elements.length];
        int i = 0;
        for (T element : elements) {
            array[i] = element;
            i++;
        }
        return (T[])array;
    }

    @Override
    public boolean add(E element) {
        linkLast(element);
        size++;
        return true;
    }

    @Override
    public boolean remove(Object obj) {
        removeOccurrence(obj, first);
        size--;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        if (collection.size() > size || collection.iterator().next().getClass() == type) {
            return false;
        }
        for (Object item : collection) {
            for (E element : this) {
                if (item.equals(element)) {
                    break;
                }
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        //TODO: IMPLEMENT THIS
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        //TODO: IMPLEMENT THIS
        return true;
    }


    @Override
    public boolean removeAll(Collection<?> collection) {
        //TODO: IMPLEMENT THIS
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        //TODO: IMPLEMENT THIS
        return true;
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        //TODO: IMPLEMENT THIS
    }

    @Override
    public void sort(Comparator<? super E> comparator) {
        //TODO: IMPLEMENT THIS
    }

    @Override
    public void clear() {
        //TODO: IMPLEMENT THIS
    }

    @Override
    public boolean equals(Object obj) {
        //TODO: IMPLEMENT THIS
        return true;
    }

    @Override
    public int hashCode() {
        //TODO: IMPLEMENT THIS
        return 0;
    }

    @Override
    public E get(int index) {
        //TODO: IMPLEMENT THIS
        return null;
    }

    @Override
    public E set(int index, E element) {
        //TODO: IMPLEMENT THIS
        return null;
    }

    @Override
    public void add(int index, E element) {
        //TODO: IMPLEMENT THIS
    }

    @Override
    public E remove(int index) {
        return unlink(node(index));
    }

    @Override
    public int indexOf(Object obj) {
        return indexOf(obj, true);
    }

    @Override
    public int lastIndexOf(Object obj) {
        return indexOf(obj, false);
    }

    @Override
    public ListIterator<E> listIterator() {
        //TODO: IMPLEMENT THIS
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        //TODO: IMPLEMENT THIS
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        //TODO: IMPLEMENT THIS
        return null;
    }

    @Override
    public Spliterator<E> spliterator() {
        //TODO: IMPLEMENT THIS
        return null;
    }

    @Override
    public void addFirst(E element) {
        linkFirst(element);
    }

    @Override
    public void addLast(E element) {
        linkLast(element);
    }

    @Override
    public boolean offerFirst(E element) {
        addFirst(element);
        return true;
    }

    @Override
    public boolean offerLast(E element) {
        addLast(element);
        return true;
    }

    @Override
    public E removeFirst() {
        return unlinkFirst();
    }

    @Override
    public E removeLast() {
        return unlinkLast();
    }

    @Override
    public E pollFirst() {
        return poll();
    }

    @Override
    public E pollLast() {
        return last == null ? null : unlinkLast();
    }

    @Override
    public E getFirst() {
        return first == null ? null : first.getData();
    }

    @Override
    public E getLast() {
        return last == null ? null : last.getData();
    }

    @Override
    public E peekFirst() {
        return getFirst();
    }

    @Override
    public E peekLast() {
        return getLast();
    }

    @Override
    public boolean removeFirstOccurrence(Object obj) {
        return removeOccurrence(obj, first);
    }

    @Override
    public boolean removeLastOccurrence(Object obj) {
        return removeOccurrence(obj, last);
    }

    @Override
    public boolean offer(E element) {
        if (size < Integer.MAX_VALUE) {
            addLast(element);
            return true;
        }
        return false;
    }

    @Override
    public E remove() {
        return removeFirst();
    }

    @Override
    public E poll() {
        return first == null ? null : unlinkFirst();
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E peek() {
        return getFirst();
    }

    @Override
    public void push(E element) {
        addFirst(element);
    }

    @Override
    public E pop() {
        return removeFirst();
    }

    @Override
    public Iterator<E> descendingIterator() {
        //TODO: IMPLEMENT THIS
        return null;
    }

    private E unlinkFirst() {
        return unlink(first);
    }

    private E unlinkLast() {
        return unlink(last);
    }

    private E unlink(Node<E> element) {
        if (validateOp(element)) {
            E returnValue = element.getData();
            if (unlinkLastRemainingElement()) {
                return returnValue;
            }
            element.getPrev().setNext(element.getNext());
            element.getNext().setPrev(element.getPrev());
            if (element == first) {
                first = first.getNext();
            }
            if (element == last) {
                last = last.getPrev();
            }
            size--;
            return returnValue;
        }
        return null;
    }

    private boolean validateOp() {
        return size != 0;
    }

    private boolean validateOp(Node<E> element) {
        return size != 0 && element != null;
    }

    private boolean unlinkLastRemainingElement() {
        if (size == 1) {
            first = null;
            last = null;
            size--;
            return true;
        }
        return false;
    }

    private boolean removeOccurrence(Object obj, Node<E> headOrTail) {
        Node<E> escape = headOrTail == first ? last : first;
        Function<Node<E>, Node<E>> nodeOp = headOrTail == first ? Node::getNext : Node::getPrev;
        Function<Object, Boolean> comparator = obj == null ? Objects::isNull :
                (Object object) -> object.equals(obj);
        for(; headOrTail != null; headOrTail = nodeOp.apply(headOrTail)) {
            if (comparator.apply(headOrTail.getData())) {
                unlink(headOrTail);
                size--;
                return true;
            }
            if (headOrTail == escape) {
                return false;
            }
        }
        return false;
    }

    private int indexOf(Object obj, boolean fromHead) {
        int index = fromHead ? 0 : size;
        Node<E> escape = fromHead ? last : first;
        Function<Node<E>, Node<E>> nodeOp = fromHead ? Node::getNext : Node::getPrev;
        Function<Object, Boolean> comparator = obj == null ? Objects::isNull :
                (Object object) -> object.equals(obj);
        Node<E> temp = fromHead ? first : last;

        for(; temp != null; temp = nodeOp.apply(temp)) {
            index--;
            if (comparator.apply(temp.getData())) {
                return fromHead ? index + 1 : index;
            }
            if (temp == escape) {
                return -1;
            }
        }
        return -1;
    }

    private Node<E> node(int index) {
        if (!validIndex(index)) return null;
        int initialValue;
        Node<E> escape;
        Function<Integer, Boolean> comparator;
        Consumer<Integer> finalOp;
        Node<E> element;

        if (index < size / 2) {
            initialValue = 0;
            escape = last;
            comparator = (Integer i) -> i < index;
            finalOp = (Integer i) -> i++;
            element = first;
        } else {
            initialValue = size - 1;
            escape = first;
            comparator = (Integer i) -> i > index;
            finalOp = (Integer i) -> i--;
            element = last;
        }
        for (; comparator.apply(initialValue); finalOp.accept(initialValue)) {
            if (element != escape) {
                element = element.getNext();
            } else {
                return null;
            }
        }
        return element;
    }

    private boolean validIndex(int index) {
        return index >= 0 && index < size;
    }


    private boolean linkBefore(E newElement, Node<E> predecessor) {
        if (predecessor == null) {
            return false;
        }
        Node<E> newNode = new Node<>(predecessor, newElement, predecessor.getNext());
        predecessor.getNext().setPrev(newNode);
        predecessor.setNext(newNode);
        size++;
        return true;
    }

    private boolean linkAfter(E newElement, Node<E> successor) {
        if (successor == null) {
            return false;
        }
        Node<E> newNode = new Node<>(successor.getPrev(), newElement,  successor);
        successor.getPrev().setNext(newNode);
        successor.setPrev(newNode);
        size++;
        return true;
    }

    private void rectifyFirstAndLast() {
        if (size == 0) {
            first = last = null;
        }
    }
}
