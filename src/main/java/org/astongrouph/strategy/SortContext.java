package org.astongrouph.strategy;

import org.astongrouph.collection.CustomArrayList;

import java.util.Comparator;
import java.util.function.Function;

public class SortContext<T> {

    private final SortStrategy<T> strategy;

    public SortContext() {
        this.strategy = new QuickSort<>();
    }

    /**
     * Базовая сортировка списка.
     */
    public void executeSort(CustomArrayList<T> list, Comparator<T> comparator) {
        System.out.println("Сортировка: QuickSort");
        long startTime = System.currentTimeMillis();
        strategy.sort(list, comparator);
        long endTime = System.currentTimeMillis();
        System.out.println("Сортировка завершена за " + (endTime - startTime) + " мс");
    }

    /**
     * Сортировка только чётных элементов по извлечённому числу.
     */
    public void executeSortEvenOnly(CustomArrayList<T> list, Comparator<T> comparator, Function<T, Integer> numberExtractor) {
        System.out.println("Сортировка (четные/нечетные): QuickSort");
        long startTime = System.currentTimeMillis();

        SortStrategy<T> decoratedStrategy = new OddEvenSortDecorator<>(new QuickSort<>(), numberExtractor);
        decoratedStrategy.sort(list, comparator);

        long endTime = System.currentTimeMillis();
        System.out.println("Сортировка чётных завершена за " + (endTime - startTime) + " мс");
    }
}