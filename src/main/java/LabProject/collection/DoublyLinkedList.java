package LabProject.collection;
import java.util.Iterator;

public class DoublyLinkedList<T extends Comparable<? super T>> implements Iterable<T> {
    private class Node {
        T data;
        Node prev;
        Node next;

        Node(T data){
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(T item) {
        Node newNode = new Node(item);
        if(head == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public boolean remove(T item) {
        Node current = head;

        while(current != null) {
            if(current.data.equals(item)) {
                if(current.prev != null) {
                    current.prev.next = current.next;
                }
                else {
                    head = current.next;
                }

                if(current.next != null) {
                    current.next.prev = current.prev;
                }
                else {
                    tail = current.prev;
                }

                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public T get(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node current = head;
        for(int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public void set(int index, T data) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node current = head;
        for(int i = 0; i < index; i++) {
            current = current.next;
        }
        current.data = data;
    }

    public int getSize() {
        return size;
    }
    
    //public void sort(Comparator<? super T> comparator) {
    //    if(size < 2) return;
    //    
    //    boolean swapped;
    //    do {
    //        Node current = head;
    //        swapped = false;
    //        while(current != null && current.next != null) {
    //            if(comparator.compare(current.data, current.next.data) > 0) {
    //                T temp = current.data;
    //                current.data = current.next.data;
    //                current.next.data = temp;
    //                swapped = true;
    //            }
    //            current = current.next;
    //        }
    //    } while(swapped);
    //}
    
    @Override // Iterator implementation for enhanced for loops
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T item = current.data;
                current = current.next;
                return item;
            }
        };
    }
}
