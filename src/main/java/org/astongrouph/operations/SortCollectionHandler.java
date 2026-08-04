package org.astongrouph.operations;

import org.astongrouph.model.Student;
import strategy.QuickSort;
import strategy.SortStrategy;

class SortCollectionHandler extends OperationHandler {
    @Override
    protected boolean canHandle(OperationLevel level) {
        return level == OperationLevel.SORTING;
    }

    @Override
    protected void processRequest() {
        if (OperationHandler.collectionIsEmpty()) {
            System.out.println("Для сортировки заполните коллекцию.");
            return;
        }

        if (comparator == null) {
            System.out.println("Поле сортировки не задано.");
            return;
        }

        SortStrategy<Student> sorter = new QuickSort<>();
        sorter.sort(students, comparator);

        System.out.println("Коллекция отсортирована");
    }
}
