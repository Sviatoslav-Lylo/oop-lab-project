package LabProject.strategy;

import LabProject.collection.DoublyLinkedList;

public interface SortingStrategy<T extends Comparable<? super T>> {
    void sort(DoublyLinkedList<T> list);
}
