import org.astongrouph.collection.*;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class CollectionTest {

    @Test
    void newListShouldBeEmpty() {
        CustomArrayList<String> list = new CustomArrayList<>();

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void shouldAddElement() {
        CustomArrayList<String> list = new CustomArrayList<>();

        list.add("Java");

        assertEquals(1, list.size());
        assertEquals("Java", list.get(0));
    }

    @Test
    void shouldInsertElementByIndex() {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        list.add(1);
        list.add(2);
        list.add(1, 10);

        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(10, list.get(1));
        assertEquals(2, list.get(2));
    }

    @Test
    void shouldReplaceElement() {
        CustomArrayList<String> list = new CustomArrayList<>();

        list.add("A");

        String old = list.set(0, "B");

        assertEquals("A", old);
        assertEquals("B", list.get(0));
    }

    @Test
    void shouldRemoveElement() {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);

        Integer removed = list.remove(1);

        assertEquals(2, removed);
        assertEquals(2, list.size());
        assertEquals(3, list.get(1));
    }

    @Test
    void shouldGrowWhenCapacityExceeded() {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        assertEquals(100, list.size());

        for (int i = 0; i < 100; i++) {
            assertEquals(i, list.get(i));
        }
    }

    @Test
    void shouldThrowExceptionForInvalidGetIndex() {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(0));
    }

    @Test
    void shouldThrowExceptionForInvalidSetIndex() {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        assertThrows(IndexOutOfBoundsException.class,
                () -> list.set(0, 10));
    }

    @Test
    void shouldThrowExceptionForInvalidRemoveIndex() {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        assertThrows(IndexOutOfBoundsException.class,
                () -> list.remove(0));
    }

    @Test
    void shouldWorkWithZeroCapacity() {
        CustomArrayList<Integer> list = new CustomArrayList<>(0);

        list.add(1);

        assertEquals(1, list.size());
        assertEquals(1, list.get(0));
    }

    @Test
    void shouldThrowExceptionForNegativeCapacity() {
        assertThrows(IllegalArgumentException.class,
                () -> new CustomArrayList<>(-1));
    }

    @Test
    void iteratorShouldDetectConcurrentModification() {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        list.add(1);
        list.add(2);

        Iterator<Integer> iterator = list.iterator();

        list.add(3);

        assertThrows(ConcurrentModificationException.class,
                iterator::next);
    }
}
