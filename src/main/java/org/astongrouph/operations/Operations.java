package org.astongrouph.operations;

import java.util.ArrayList;
import java.util.List;
import org.astongrouph.model.Student;

public class Operations {

    public Operations() {
        handler = new ShowCollectionHandler();
        handler.setNextHandler(new FillCollectionHandler()).
                setNextHandler(new SetSizeCollectionHandler()).
                setNextHandler(new SetFieldForSortHandler()).
                setNextHandler(new SortCollectionHandler()).
                setNextHandler(new ShowInfoHandler());
    }

    public void doOperation(int op) throws ArrayIndexOutOfBoundsException {
        OperationLevel value = OperationLevel.values()[op];
        handler.handleRequest(value);
    }

    private final ShowCollectionHandler handler;
}

enum OperationLevel { EXIT, SHOW_COLLECTION, FILL_COLLECTION, SET_SIZE_COLLECTION, SET_FILED_FOR_SORT, SORTING, SHOW_INFO }

abstract class OperationHandler {
    public OperationHandler setNextHandler(OperationHandler handler) throws IllegalArgumentException {
        if (handler != null) {
            this.nextHandler = handler;
            return nextHandler;
        }
        else
            throw new IllegalArgumentException("Невалидный handler: " + String.valueOf((Object) null));
    }

    public void handleRequest(OperationLevel level) {
        if (canHandle(level)) {
            processRequest();
        } else if (nextHandler != null) {
            nextHandler.handleRequest(level);
        }
        else {
            System.out.print("\t");
            System.out.println("Не могу обработать запрос." + level);
        }
    }

    protected abstract boolean canHandle(OperationLevel level);
    protected abstract void processRequest();

    private OperationHandler nextHandler;

    protected static List<Student> students;
    protected static int collectionSize = 10;
}

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

class FillCollectionHandler extends OperationHandler {
    @Override
    protected boolean canHandle(OperationLevel level) {
        return level == OperationLevel.FILL_COLLECTION;
    }

    @Override
    protected void processRequest() {
        System.out.println("Fill collection students!");
    }
}

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

class SetFieldForSortHandler extends OperationHandler {
    @Override
    protected boolean canHandle(OperationLevel level) {
        return level == OperationLevel.SET_FILED_FOR_SORT;
    }

    @Override
    protected void processRequest() {
        System.out.println("Set field for sort collection students!");
    }
}

class SortCollectionHandler extends OperationHandler {
    @Override
    protected boolean canHandle(OperationLevel level) {
        return level == OperationLevel.SORTING;
    }

    @Override
    protected void processRequest() {
        System.out.println("Sort collection students!");
    }
}

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

        System.out.println("Поле сортировки ... "); // дописать - задано или нет.
    }
}