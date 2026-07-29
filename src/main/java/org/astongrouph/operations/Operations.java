package org.astongrouph.operations;

import java.util.ArrayList;
import java.util.List;

public class Operations {

    public Operations() {
        handler = new ShowCollectionHandler();
        handler.setNextHandler(new FillCollectionHandler()).
                setNextHandler(new SetSizeCollectionHandler()).
                setNextHandler(new SetFieldForSortHandler()).
                setNextHandler(new SortCollectionHandler());
    }

    public void doOperation(int op) throws ArrayIndexOutOfBoundsException {
        OperationLevel value = OperationLevel.values()[op];
        handler.handleRequest(value);
    }

    private final ShowCollectionHandler handler;
}

enum OperationLevel { EXIT, SHOW_COLLECTION, FILL_COLLECTION, SET_SIZE_COLLECTION, SET_FILED_FOR_SORT, SORTING }

abstract class OperationHandler {
    public OperationHandler() {
        students = new ArrayList<>(10);
    }

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

    // tmp
    protected static List<String> students;
}

class ShowCollectionHandler extends OperationHandler {
    @Override
    protected boolean canHandle(OperationLevel level) {
        return level == OperationLevel.SHOW_COLLECTION;
    }

    @Override
    protected void processRequest() {
        System.out.println("Show collection students!");
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
        System.out.println("Set size collection students!");
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