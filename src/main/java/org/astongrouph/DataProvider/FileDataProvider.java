package org.astongrouph.DataProvider;

import org.astongrouph.CustomArray.CustomArrayList;
import org.astongrouph.CustomArray.CustomArrayListCollector;
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

            return Files.lines(path)

                    .map(this::parseAndValidate)

                    .flatMap(student -> student)

                    .limit(count)

                    .collect(CustomArrayListCollector.toCustomArrayList());


        } catch (IOException e) {

            throw new RuntimeException(
                    "Ошибка чтения файла.",
                    e
            );
        }
    }


    private Stream<Student> parseAndValidate(String line) {

        try {

            Student student = parser.parse(line);

            validator.validate(student);

            return Stream.of(student);

        } catch (StudentParseException e) {

            System.out.println(
                    "Пропущена запись: " + e.getMessage()
            );

            return Stream.empty();

        } catch (InvalidStudentException e) {
            System.out.println(
                    e.getMessage()
            );

            return Stream.empty();
        }
    }
}