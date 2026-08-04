package org.astongrouph.strategy;

import org.astongrouph.collection.CustomArrayList;

import java.util.Comparator;

public interface SortStrategy<T> {
    void sort(CustomArrayList<T> list, Comparator<T> comparator);
}