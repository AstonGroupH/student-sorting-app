package org.astongrouph.DataProvider;

import org.astongrouph.CustomArray.CustomArrayList;
import org.astongrouph.CustomArray.CustomArrayListCollector;
import org.astongrouph.Ecxeptions.InvalidStudentException;
import org.astongrouph.Ecxeptions.StudentParseException;
import org.astongrouph.model.Student;

import java.util.Scanner;
import java.util.stream.IntStream;

public class ManualDataProvider implements DataProvider {

    private final Scanner scanner;
    private final StudentParser parser = new StudentParser();
    private final StudentValidator validator = new StudentValidator();

    public ManualDataProvider(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public CustomArrayList<Student> provide(int count) {

        return IntStream.range(0, count)
                .mapToObj(i -> readStudent())
                .collect(CustomArrayListCollector.toCustomArrayList());
    }

    private Student readStudent() {

        while (true) {

            System.out.println(
                    "Введите данные: группа,средний балл,номер зачётки"
            );

            String input = scanner.nextLine();

            try {

                Student student = parser.parse(input);

                validator.validate(student);

                return student;

            } catch (StudentParseException |
                     InvalidStudentException e) {

                System.out.println(
                        "Ошибка: " + e.getMessage()
                );
            }
        }
    }
}
