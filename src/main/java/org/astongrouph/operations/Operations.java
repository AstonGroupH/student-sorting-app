package org.astongrouph.operations;

import java.util.Comparator;
import java.util.Scanner;

import org.astongrouph.comparator.AverageScoreComparator;
import org.astongrouph.comparator.GroupNumberComparator;
import org.astongrouph.comparator.RecordBookNumberComparator;
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
                setNextHandler(new WriteToCSVFileHandler()).
                setNextHandler(new OddEvenSortCollectionHandler());

        OperationHandler.setScanner(scanner);
    }

    public void doOperation(int op) throws ArrayIndexOutOfBoundsException {
        OperationLevel value = OperationLevel.values()[op];
        handler.handleRequest(value);
    }

    private final ShowCollectionHandler handler;
}

enum OperationLevel { EXIT, SHOW_COLLECTION, FILL_COLLECTION, SET_SIZE_COLLECTION,
    SET_FILED_FOR_SORT, SORTING, SHOW_INFO, WRITE_TO_CSV_FILE, SORTING_ODD_EVEN}

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
    protected static String getComparatorName() {
        if (comparator instanceof GroupNumberComparator) return "Номер группы";
        if (comparator instanceof AverageScoreComparator) return "Средний балл";
        if (comparator instanceof RecordBookNumberComparator) return "Номер зачетной книжки";
        return "Не задано";
    }

    protected static boolean collectionIsEmpty() {
        return students == null || students.isEmpty();
    }

    protected abstract boolean canHandle(OperationLevel level);
    protected abstract void processRequest();

    private OperationHandler nextHandler;

    protected static CustomArrayList<Student> students;
    protected static int collectionSize = 10;
    protected static Scanner scanner;
    protected static Comparator<Student> comparator;
}
