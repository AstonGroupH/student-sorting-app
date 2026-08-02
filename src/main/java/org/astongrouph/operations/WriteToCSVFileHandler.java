package org.astongrouph.operations;

import org.astongrouph.csv.CSVFile;

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