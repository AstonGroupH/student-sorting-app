package org.astongrouph.operations;

import org.astongrouph.DataProvider.StudentParser;
import org.astongrouph.Ecxeptions.StudentParseException;
import org.astongrouph.counter.MultiThreadedCounter;
import org.astongrouph.model.Student;

import java.util.Scanner;

public class CountOccurrencesHandler extends OperationHandler {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    protected boolean canHandle(OperationLevel level) {
        return level == OperationLevel.COUNT_OCCURRENCES;
    }

    @Override
    protected void processRequest() {
        if (students == null || students.isEmpty()) {
            System.out.println("Коллекция пуста.");
            return;
        }


        System.out.println("Введите студента:");
        System.out.println("Формат: группа,среднийБалл,номерЗачетки");

        String line = scanner.nextLine();

        try {

            Student target = new StudentParser().parse(line);

            int count = MultiThreadedCounter.countOccurrences(students, target);

            System.out.println("Количество совпадений: " + count);

        } catch (StudentParseException e) {

            System.out.println(e.getMessage());

        }
    }



}
