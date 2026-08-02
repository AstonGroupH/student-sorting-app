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
            StringBuilder msg = new StringBuilder();

            msg.append(String.format("[%d] Студент: ", index + 1));
            msg.append(String.format("Номер группы: %d, ", students.get(index).getGroupNumber()));
            msg.append(String.format("Средний балл: %.2f, ", students.get(index).getAverageScore()));
            msg.append(String.format("Номер зачетной книжки: %d;", students.get(index).getRecordBookNumber()));

            System.out.println(msg.toString());
        }
    }
}
