package org.astongrouph.operations;

import org.astongrouph.comparator.GroupNumberComparator;
import org.astongrouph.comparator.RecordBookNumberComparator;
import org.astongrouph.model.Student;
import org.astongrouph.strategy.OddEvenSortDecorator;
import org.astongrouph.strategy.QuickSort;
import org.astongrouph.strategy.SortStrategy;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;

class OddEvenSortCollectionHandler extends OperationHandler {
    @Override
    protected boolean canHandle(OperationLevel level) {
        return level == OperationLevel.SORTING_ODD_EVEN;
    }

    @Override
    protected void processRequest() {
        if (OperationHandler.collectionIsEmpty()) {
            System.out.println("Для сортировки заполните коллекцию.");
            return;
        }

        if (comparator == null) {
            System.out.println("Поле сортировки не задано.");
            return;
        }

        Map<Integer, Function<Student, Integer>> fields = Map.ofEntries(
                Map.entry(1, Student::getGroupNumber),
                Map.entry(2, Student::getRecordBookNumber));

        ValidateInput input = new ValidateInput();
        int choice;

        while (true) {
            choice = input.readInt("Введите поле, четные значения которого будут отсортированы\n"
                    + "[1] - номер группы; [2] - номер зач. книжки; : ", 1, 2);

            Comparator<Student> comp = switch (choice) {
                case 1 -> new GroupNumberComparator();
                case 2 -> new RecordBookNumberComparator();
                default -> null;
            };

            if (comp != null && comp.getClass() == comparator.getClass()) {
                System.out.println();
                System.out.println("Данное поле задано для сортировки. Выберете другое.");
                System.out.println();
            }
            else break;
        }
        
        SortStrategy<Student> decorator = new OddEvenSortDecorator<>(new QuickSort<>(), fields.get(choice));
        decorator.sort(students, comparator);

        System.out.println();
        System.out.println("Коллекция отсортирована");
    }
}
