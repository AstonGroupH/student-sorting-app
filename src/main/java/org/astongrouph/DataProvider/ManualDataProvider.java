package org.astongrouph.DataProvider;

import org.astongrouph.CustomArray.CustomArrayList;
import org.astongrouph.Ecxeptions.StudentParseException;
import org.astongrouph.model.Student;

import java.util.Scanner;

public class ManualDataProvider implements DataProvider {

    private final Scanner scanner;
    private final StudentParser parser = new StudentParser();
    private final StudentValidator validator = new StudentValidator();

    public ManualDataProvider(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public CustomArrayList<Student> provide(int count) {

        CustomArrayList<Student> students = new CustomArrayList<>();

        while (students.size() < count) {

            System.out.println("Введите данные студентов.");
            System.out.println("Формат: номерГруппы,среднийБалл,номерЗачетки");
            String line = scanner.nextLine();

            try {
                Student student = parser.parse(line);
                students.add(student);

            } catch (StudentParseException e) {
                System.out.println("Ошибка: " + e.getMessage());
                System.out.println("Попробуйте ещё раз.");
            }

        }

        return students;
    }
}
