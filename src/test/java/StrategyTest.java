import org.astongrouph.model.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.OddEvenSortDecorator;
import strategy.QuickSort;
import strategy.SortStrategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тесты стратегии сортировки и декоратора чётных элементов")
public class StrategyTest {

    @Test
    @DisplayName("QuickSort должен корректно сортировать список студентов по среднему баллу")
    void quickSortShouldSortStudentsByAverageScore() {
        List<Student> students = new ArrayList<>(List.of(
                new Student.Builder(101, 4.5, 1002).build(),
                new Student.Builder(102, 3.8, 1001).build(),
                new Student.Builder(101, 4.2, 1004).build(),
                new Student.Builder(103, 3.9, 1003).build(),
                new Student.Builder(102, 4.8, 1006).build(),
                new Student.Builder(101, 3.5, 1005).build()
        ));
        System.out.println("Тест: QuickSort — сортировка студентов по среднему баллу");
        System.out.println("До: " + students);

        List<Student> expected = List.of(
                new Student.Builder(101, 3.5, 1005).build(),
                new Student.Builder(102, 3.8, 1001).build(),
                new Student.Builder(103, 3.9, 1003).build(),
                new Student.Builder(101, 4.2, 1004).build(),
                new Student.Builder(101, 4.5, 1002).build(),
                new Student.Builder(102, 4.8, 1006).build()
        );

        Comparator<Student> byAverageScore = Comparator.comparing(Student::getAverageScore);

        SortStrategy<Student> sorter = new QuickSort<>();
        sorter.sort(students, byAverageScore);

        System.out.println("После: " + students);
        System.out.println("Ожидаемо: " + expected);
        System.out.println();

        assertEquals(expected, students);
    }

    @Test
    @DisplayName("OddEvenSortDecorator должен сортировать студентов с чётным recordBookNumber по среднему баллу")
    void oddEvenDecoratorShouldSortStudentsWithEvenRecordBookByAverageScore() {
        List<Student> students = new ArrayList<>(List.of(
                new Student.Builder(101, 4.5, 1002).build(),
                new Student.Builder(102, 3.8, 1001).build(),
                new Student.Builder(101, 4.2, 1004).build(),
                new Student.Builder(103, 3.9, 1003).build(),
                new Student.Builder(102, 4.8, 1006).build(),
                new Student.Builder(101, 3.5, 1005).build()
        ));
        System.out.println("Тест: Сортировка студентов с чётным recordBookNumber по среднему баллу");
        System.out.println("До: " + students);

        List<Student> expected = List.of(
                new Student.Builder(101, 4.2, 1004).build(),
                new Student.Builder(102, 3.8, 1001).build(),
                new Student.Builder(101, 4.5, 1002).build(),
                new Student.Builder(103, 3.9, 1003).build(),
                new Student.Builder(102, 4.8, 1006).build(),
                new Student.Builder(101, 3.5, 1005).build()
        );

        Function<Student, Integer> getRecordBookNumber = Student::getRecordBookNumber;

        Comparator<Student> byAverageScore = Comparator.comparing(Student::getAverageScore);

        SortStrategy<Student> decorator = new OddEvenSortDecorator<>(new QuickSort<>(), getRecordBookNumber);
        decorator.sort(students, byAverageScore);

        System.out.println("После: " + students);
        System.out.println();

        assertEquals(expected, students);
    }
}