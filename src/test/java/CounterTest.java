import org.astongrouph.collection.CustomArrayList;
import org.astongrouph.counter.MultiThreadedCounter;
import org.astongrouph.model.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CounterTest {


    @Test
    public void shouldCountStudentOccurrences() {

        CustomArrayList<Student> students =
                new CustomArrayList<>();


        Student target =
                new Student.Builder()
                        .groupNumber(101)
                        .averageScore(90)
                        .recordBookNumber(123)
                        .build();


        students.add(target);
        students.add(target);
        students.add(target);


        students.add(
                new Student.Builder()
                        .groupNumber(102)
                        .averageScore(70)
                        .recordBookNumber(555)
                        .build()
        );


        int result =
                MultiThreadedCounter.countOccurrences(
                        students,
                        target
                );


        assertEquals(3, result);
    }

    @Test
    public void shouldReturnZeroWhenStudentNotFound() {

        CustomArrayList<Student> students =
                new CustomArrayList<>();

        Student target =
                new Student.Builder()
                        .groupNumber(1)
                        .averageScore(90)
                        .recordBookNumber(10)
                        .build();


        int result = MultiThreadedCounter.countOccurrences(students, target);


        assertEquals(0, result);

    }
}