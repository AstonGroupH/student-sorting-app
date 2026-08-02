package org.astongrouph.operations;

class SetSizeCollectionHandler extends OperationHandler {
    @Override
    protected boolean canHandle(OperationLevel level) {
        return level == OperationLevel.SET_SIZE_COLLECTION;
    }

    @Override
    protected void processRequest() {
        ValidateInput inputInt = new ValidateInput();
        collectionSize = inputInt.readInt("Задайте новый размер коллекции [1-100]: ", 1, 100);
        System.out.println("Установлен новый размер коллекции: " + collectionSize);

        students = null;
    }
}