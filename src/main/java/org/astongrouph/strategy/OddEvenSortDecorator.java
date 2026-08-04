package org.astongrouph.strategy;


import org.astongrouph.collection.CustomArrayList;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;

public class OddEvenSortDecorator<T> implements SortStrategy<T> {

    private final SortStrategy<T> strategy;
    private final Function<T, Integer> numberExtractor;

    public OddEvenSortDecorator(SortStrategy<T> strategy, Function<T, Integer> numberExtractor) {
        this.strategy = Objects.requireNonNull(strategy);
        this.numberExtractor = Objects.requireNonNull(numberExtractor, "Функция извлечения числа не может быть null");
    }

    @Override
    public void sort(CustomArrayList<T> list, Comparator<T> comparator) {
        if (list == null || list.isEmpty()) return;

        CustomArrayList<T> evenElements = new CustomArrayList<>();
        for (T element : list) {
            Integer number = numberExtractor.apply(element);
            if (number != null && number % 2 == 0) {
                evenElements.add(element);
            }
        }

        strategy.sort(evenElements, comparator);

        int evenIndex = 0;
        for (int i = 0; i < list.size(); i++) {
            T element = list.get(i);
            Integer number = numberExtractor.apply(element);
            if (number != null && number % 2 == 0) {
                list.set(i, evenElements.get(evenIndex++));
            }
        }
    }
}