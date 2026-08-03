package org.astongrouph.operations;

class ShowInfoHandler extends OperationHandler {
    @Override
    protected boolean canHandle(OperationLevel level) {
        return level == OperationLevel.SHOW_INFO;
    }

    @Override
    protected void processRequest() {
        System.out.println("Метод сортировки: Быстрая сортировка (Quick sort).");
        System.out.println("Размер коллекции: " + collectionSize);

        StringBuilder msg = new StringBuilder("Коллекция");
        if (students == null) msg.append(" не заполнена.");
        else msg.append(" заполнена.");
        System.out.println(msg.toString());

        System.out.println("Поле сортировки: " + OperationHandler.getComparatorName());
    }
}
