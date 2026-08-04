package org.astongrouph.strategy;

import org.astongrouph.collection.CustomArrayList;

import java.util.Comparator;

public class QuickSort<T> implements SortStrategy<T> {

    @Override
    public void sort(CustomArrayList<T> list, Comparator<T> comparator) {

        if (list == null || list.isEmpty()) {
            return;
        }
        recursiveQuickSort(list, 0, list.size() - 1, comparator);
    }

    // Рекурсивный вспомогательный метод
    private void recursiveQuickSort(CustomArrayList<T> list, int low, int high, Comparator<T> comparator) {
        if (low < high) {
            // Находим индекс раздела
            int partitionIndex = partition(list, low, high, comparator);
            // Рекурсивно сортируем элементы до и после раздела
            recursiveQuickSort(list, low, partitionIndex - 1, comparator);
            recursiveQuickSort(list, partitionIndex + 1, high, comparator);
        }
    }

    // Метод разбиения, который находит правильную позицию опорного элемента
    private int partition(CustomArrayList<T> list, int low, int high, Comparator<T> comparator) {
        // Выбираем опорный элемент (в этом случае — последний)
        T pivot = list.get(high);
        int i = low - 1;
        // Итерируем по списку и перемещаем элементы меньше опорного в левую часть
        for (int j = low; j < high; j++) {
            if (comparator.compare(list.get(j), pivot) <= 0) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);

        return i + 1;
    }

    private void swap(CustomArrayList<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}