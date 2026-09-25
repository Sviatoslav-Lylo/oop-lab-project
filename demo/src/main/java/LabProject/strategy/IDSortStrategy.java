package LabProject.strategy;

import LabProject.collection.DoublyLinkedList;
import LabProject.model.Person;

public class IDSortStrategy<T extends Person> implements SortingStrategy<T> {
    @Override
    public void sort(DoublyLinkedList<T> list) {
        int n = list.getSize();
        if(n < 2) return;
        
        boolean swapped;
        do {
            swapped = false;
            for(int i = 0; i < n - 1; i++) {
                T current = list.get(i);
                T next = list.get(i + 1);

                if(current.getId() > next.getId()) {
                    list.set(i, next);
                    list.set(i + 1, current);
                    swapped = true;
                }
            }
        } while(swapped);
    }
}
