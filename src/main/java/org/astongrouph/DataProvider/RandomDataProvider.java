package org.astongrouph.DataProvider;

import org.astongrouph.CustomArray.CustomArrayList;
import org.astongrouph.model.Student;

import java.util.Random;
import java.util.stream.Stream;

public class RandomDataProvider implements DataProvider {

    private final Random random = new Random();

    @Override
    public CustomArrayList<Student> provide(int count) {

        if (count <= 0) {
            throw new IllegalArgumentException(
                    "Количество студентов должно быть больше 0."
            );
        }

        CustomArrayList<Student> students = new CustomArrayList<>();

        Stream.generate(this::generateStudent)
                .limit(count)
                .forEach(students::add);

        return students;
    }


    private Student generateStudent() {

        return new Student.Builder()
                .groupNumber(random.nextInt(10) + 1)
                .averageScore(
                        Math.round(random.nextDouble() * 10000) / 100.0
                )
                .recordBookNumber(
                        random.nextInt(900000) + 100000
                )
                .build();
    }
}