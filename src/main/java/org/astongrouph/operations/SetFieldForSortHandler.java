package org.astongrouph.operations;

import org.astongrouph.comparator.AverageScoreComparator;
import org.astongrouph.comparator.GroupNumberComparator;
import org.astongrouph.comparator.RecordBookNumberComparator;

class SetFieldForSortHandler extends OperationHandler {
    @Override
    protected boolean canHandle(OperationLevel level) {
        return level == OperationLevel.SET_FILED_FOR_SORT;
    }

    @Override
    protected void processRequest() {
        System.out.print("Поля для сортировки: ");
        System.out.println("[1] - номер группы; [2] - средний балл; [3] - номер зач. книжки;");

        ValidateInput input = new ValidateInput();
        int choice = input.readInt("Введите поле сортировки: ", 1, 3);

        comparator = switch (choice) {
            case 1 -> new GroupNumberComparator();
            case 2 -> new AverageScoreComparator();
            case 3 -> new RecordBookNumberComparator();
            default -> null;
        };

        System.out.println("Выбрано поле: " + OperationHandler.getComparatorName());
    }
}