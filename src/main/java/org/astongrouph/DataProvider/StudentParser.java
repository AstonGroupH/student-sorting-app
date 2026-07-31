package org.astongrouph.DataProvider;

import org.astongrouph.Ecxeptions.StudentParseException;
import org.astongrouph.model.Student;

public class StudentParser {

    public Student parse(String line) throws StudentParseException {

        if (line == null || line.isBlank()) {
            throw new StudentParseException(
                    "Строка не может быть пустой."
            );
        }

        String[] parts = line.split(",");

        if (parts.length != 3) {
            throw new StudentParseException(
                    "Ожидалось 3 поля в формате: группа, средний балл, номер зачётной книжки."
            );
        }

        try {

            return new Student.Builder()
                    .groupNumber(Integer.parseInt(parts[0].trim()))
                    .averageScore(Double.parseDouble(parts[1].trim()))
                    .recordBookNumber(Integer.parseInt(parts[2].trim()))
                    .build();

        } catch (NumberFormatException e) {

            throw new StudentParseException(
                    "Группа и номер зачётной книжки должны быть целыми числами, средний балл — числом."
            );
        }
    }
}