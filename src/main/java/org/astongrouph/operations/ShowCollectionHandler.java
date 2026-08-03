package org.astongrouph.operations;

class ShowCollectionHandler extends OperationHandler {
    @Override
    protected boolean canHandle(OperationLevel level) {
        return level == OperationLevel.SHOW_COLLECTION;
    }

    @Override
    protected void processRequest() {
        if (students == null) {
            System.out.println("Коллекция не заполнена");
            return;
        }

        for (int index = 0; index < students.size(); index++) {
            String msg = String.format("[%d] Студент: ", index + 1) +
                    String.format("Номер группы: %d, ", students.get(index).getGroupNumber()) +
                    String.format("Средний балл: %.2f, ", students.get(index).getAverageScore()) +
                    String.format("Номер зачетной книжки: %d;", students.get(index).getRecordBookNumber());

            System.out.println(msg);
        }
    }
}
