package org.astongrouph.DataProvider;

import org.astongrouph.Ecxeptions.InvalidStudentException;
import org.astongrouph.model.Student;

public class StudentValidator {


    public void validate(Student student)
            throws InvalidStudentException {

        if (student == null) {
            throw new InvalidStudentException(
                    "Студент отсутствует."
            );
        }

        if (student.getGroupNumber() <= 0) {
            throw new InvalidStudentException(
                    "Номер группы должен быть больше 0."
            );
        }

        if (student.getAverageScore() < 0 ||
                student.getAverageScore() > 100) {

            throw new InvalidStudentException(
                    "Средний балл должен находиться в диапазоне от 0 до 100."
            );
        }

        if (student.getRecordBookNumber() <= 0) {
            throw new InvalidStudentException(
                    "Номер зачётной книжки должен быть больше 0."
            );
        }
    }

}