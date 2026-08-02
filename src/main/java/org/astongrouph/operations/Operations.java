package org.astongrouph.operations;

import java.util.Scanner;

import org.astongrouph.csv.CSVFile;
import org.astongrouph.model.Student;
import org.astongrouph.collection.CustomArrayList;

public class Operations {

    public Operations(Scanner scanner) {
        handler = new ShowCollectionHandler();
        handler.setNextHandler(new FillCollectionHandler()).
                setNextHandler(new SetSizeCollectionHandler()).
                setNextHandler(new SetFieldForSortHandler()).
                setNextHandler(new SortCollectionHandler()).
                setNextHandler(new ShowInfoHandler()).
                setNextHandler(new WriteToCSVFileHandler());

        OperationHandler.setScanner(scanner);
    }

    public void doOperation(int op) throws ArrayIndexOutOfBoundsException {
        OperationLevel value = OperationLevel.values()[op];
        handler.handleRequest(value);
    }

    private final ShowCollectionHandler handler;
}

enum OperationLevel { EXIT, SHOW_COLLECTION, FILL_COLLECTION, SET_SIZE_COLLECTION,
    SET_FILED_FOR_SORT, SORTING, SHOW_INFO, WRITE_TO_CSV_FILE }

abstract class OperationHandler {
    public OperationHandler setNextHandler(OperationHandler handler) throws IllegalArgumentException {
        if (handler != null) {
            this.nextHandler = handler;
            return nextHandler;
        }
        else
            throw new IllegalArgumentException("Invalid handler: " + String.valueOf((Object) null));
    }

    public void handleRequest(OperationLevel level) {
        if (canHandle(level)) {
            processRequest();
        } else if (nextHandler != null) {
            nextHandler.handleRequest(level);
        }
        else {
            System.out.print("\t");
            System.out.println("Can't process the request: " + level);
        }
    }

    public static void setScanner(Scanner sc) { scanner = sc; }

    protected abstract boolean canHandle(OperationLevel level);
    protected abstract void processRequest();

    private OperationHandler nextHandler;

    protected static CustomArrayList<Student> students;
    protected static int collectionSize = 10;
    protected static Scanner scanner;
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

class WriteToCSVFileHandler extends OperationHandler {
    @Override
    protected boolean canHandle(OperationLevel level) {
        return level == OperationLevel.WRITE_TO_CSV_FILE;
    }

    @Override
    protected void processRequest() {
        if (students == null) {
            System.out.println("Заполните коллекцию, чтобы записать ее в файл.");
            return;
        }

        while (true) {
            System.out.print("Укажите имя файла [example.csv]: ");
            String name = scanner.nextLine();

            if (name.isEmpty()) {
                name = "example.csv";
            }

            try {
                CSVFile file = new CSVFile(name);
                file.writeToFle(students);
                break;
            }
            catch (IllegalArgumentException e) {
                System.out.println("Неверно задано имя файла для записи.");
            }
        }
    }
}