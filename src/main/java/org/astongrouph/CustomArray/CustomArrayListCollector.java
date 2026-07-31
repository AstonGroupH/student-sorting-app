package org.astongrouph.CustomArray;

import java.util.Set;
import java.util.Collections;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class CustomArrayListCollector<T>
        implements Collector<T, CustomArrayList<T>, CustomArrayList<T>> {

    @Override
    public Supplier<CustomArrayList<T>> supplier() {
        return CustomArrayList::new;
    }

    @Override
    public BiConsumer<CustomArrayList<T>, T> accumulator() {
        return CustomArrayList::add;
    }

    @Override
    public BinaryOperator<CustomArrayList<T>> combiner() {
        return (left, right) -> {

            for (int i = 0; i < right.size(); i++) {
                left.add(right.get(i));
            }

            return left;
        };
    }

    @Override
    public Function<CustomArrayList<T>, CustomArrayList<T>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }

    public static <T> Collector<T, ?, CustomArrayList<T>> toCustomArrayList() {
        return new CustomArrayListCollector<>();
    }
}
