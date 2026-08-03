package org.astongrouph.operations;

import java.util.Scanner;

public class Operations {

    public Operations(Scanner scanner) {
        handler = new ShowCollectionHandler();
        handler.setNextHandler(new FillCollectionHandler()).
                setNextHandler(new SetSizeCollectionHandler()).
                setNextHandler(new SetFieldForSortHandler()).
                setNextHandler(new SortCollectionHandler()).
                setNextHandler(new ShowInfoHandler()).
                setNextHandler(new WriteToCSVFileHandler()).
                setNextHandler(new OddEvenSortCollectionHandler()).
                setNextHandler(new CountOccurrencesHandler());


        OperationHandler.setScanner(scanner);
    }

    public void doOperation(int op) throws ArrayIndexOutOfBoundsException {
        OperationLevel value = OperationLevel.values()[op];
        handler.handleRequest(value);
    }

    private final ShowCollectionHandler handler;
}
