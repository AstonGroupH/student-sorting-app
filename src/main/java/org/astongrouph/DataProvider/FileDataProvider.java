package org.astongrouph.DataProvider;

import org.astongrouph.CustomArray.CustomArrayList;
import org.astongrouph.Ecxeptions.InvalidStudentException;
import org.astongrouph.Ecxeptions.StudentParseException;
import org.astongrouph.model.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileDataProvider implements DataProvider {

    private final Path path;
    private final StudentParser parser = new StudentParser();
    private final StudentValidator validator = new StudentValidator();

    public FileDataProvider(Path path) {
        this.path = path;
    }

    @Override
    public CustomArrayList<Student> provide(int count) {

        try {

            CustomArrayList<Student> students = new CustomArrayList<>();

            Files.lines(path).forEach(line -> {
                try {
                    Student student = parser.parse(line);
                    validator.validate(student);
                    students.add(student);
                } catch (StudentParseException | InvalidStudentException e) {
                    System.out.println("Пропущена строка: " + e.getMessage());
                }
            });

            return students;


        } catch (IOException e) {

            throw new RuntimeException(
                    "Ошибка чтения файла.",
                    e
            );
        }
    }
}