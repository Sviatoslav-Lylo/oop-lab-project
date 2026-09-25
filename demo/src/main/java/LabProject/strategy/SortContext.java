package LabProject.strategy;

import LabProject.collection.DoublyLinkedList;

public class SortContext<T extends Comparable<? super T>> {
    private SortingStrategy<T> strategy;

    public void setStrategy(SortingStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public void executeSort(DoublyLinkedList<T> list) {
        if(strategy == null) {
            System.out.println("Помилка: Стратегію сортування не вибрано.");
            return;
        }
        strategy.sort(list);
    }
}
