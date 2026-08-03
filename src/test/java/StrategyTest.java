import org.astongrouph.collection.CustomArrayList;
import org.astongrouph.model.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.OddEvenSortDecorator;
import strategy.QuickSort;
import strategy.SortStrategy;

import java.util.Comparator;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тесты стратегии сортировки и декоратора чётных элементов")
public class StrategyTest {

    @Test
    @DisplayName("QuickSort должен корректно сортировать список студентов по среднему баллу")
    void quickSortShouldSortStudentsByAverageScore() {
        CustomArrayList<Student> students = new CustomArrayList<>();
        students.add(new Student.Builder(101, 4.5, 1002).build());
        students.add(new Student.Builder(102, 3.8, 1001).build());
        students.add(new Student.Builder(101, 4.2, 1004).build());
        students.add(new Student.Builder(103, 3.9, 1003).build());
        students.add(new Student.Builder(102, 4.8, 1006).build());
        students.add(new Student.Builder(101, 3.5, 1005).build());

        System.out.println("Тест: QuickSort — сортировка студентов по среднему баллу");
        System.out.println("До: " + students);

        CustomArrayList<Student> expected = new CustomArrayList<>();
        expected.add(new Student.Builder(101, 3.5, 1005).build());
        expected.add(new Student.Builder(102, 3.8, 1001).build());
        expected.add(new Student.Builder(103, 3.9, 1003).build());
        expected.add(new Student.Builder(101, 4.2, 1004).build());
        expected.add(new Student.Builder(101, 4.5, 1002).build());
        expected.add(new Student.Builder(102, 4.8, 1006).build());

        Comparator<Student> byAverageScore = Comparator.comparing(Student::getAverageScore);

        SortStrategy<Student> sorter = new QuickSort<>();
        sorter.sort(students, byAverageScore);

        System.out.println("После: " + students);
        System.out.println();

        assertEquals(expected, students);
    }

    @Test
    @DisplayName("OddEvenSortDecorator должен сортировать студентов с чётным средним баллом")
    void oddEvenDecoratorShouldSortStudentsWithEvenRecordBookByAverageScore() {
        CustomArrayList<Student> students = new CustomArrayList<>();
        students.add(new Student.Builder(101, 4.5, 1002).build());
        students.add(new Student.Builder(102, 3.8, 1001).build());
        students.add(new Student.Builder(101, 4.2, 1004).build());
        students.add(new Student.Builder(103, 3.9, 1003).build());
        students.add(new Student.Builder(102, 4.8, 1006).build());
        students.add(new Student.Builder(101, 3.5, 1005).build());

        System.out.println("Тест: Сортировка студентов с чётным  средним балом");
        System.out.println("До: " + students);

        CustomArrayList<Student> expected = new CustomArrayList<>();
        expected.add(new Student.Builder(101, 4.2, 1004).build());
        expected.add(new Student.Builder(102, 3.8, 1001).build());
        expected.add(new Student.Builder(101, 4.5, 1002).build());
        expected.add(new Student.Builder(103, 3.9, 1003).build());
        expected.add(new Student.Builder(102, 4.8, 1006).build());
        expected.add(new Student.Builder(101, 3.5, 1005).build());

        Function<Student, Integer> getRecordBookNumber = Student::getRecordBookNumber;
        Comparator<Student> byAverageScore = Comparator.comparing(Student::getAverageScore);

        SortStrategy<Student> decorator = new OddEvenSortDecorator<>(new QuickSort<>(), getRecordBookNumber);
        decorator.sort(students, byAverageScore);

        System.out.println("После: " + students);
        System.out.println();

        assertEquals(expected, students);
    }
}
