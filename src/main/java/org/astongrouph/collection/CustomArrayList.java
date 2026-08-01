package org.astongrouph.collection;

import java.util.AbstractList;
import java.util.Arrays;

public class CustomArrayList<E> extends AbstractList<E> {
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;

    public CustomArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public CustomArrayList(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        elements = new Object[initialCapacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (E)elements[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        E old = (E)elements[index];
        elements[index] = element;
        return old;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if  (size == elements.length) {
            ensureCapacity();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
        modCount++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Object old = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        modCount++;
        elements[size] = null;
        return (E)old;
    }

    private void  ensureCapacity() {
        int newCapacity = elements.length == 0 ? DEFAULT_CAPACITY : elements.length * 2;
        elements = Arrays.copyOf(elements, newCapacity);
    }

    public boolean isEmpty(){
        return  size == 0;
    }


}
