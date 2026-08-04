package org.astongrouph.operations;

import org.astongrouph.csv.CSVFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
                Path dataDir = Paths.get("StudentsData");
                Files.createDirectories(dataDir);

                Path filePath = dataDir.resolve(name);

                CSVFile file = new CSVFile(filePath.toString());

                file.writeToFile(students);
                break;
            }
            catch (IllegalArgumentException e) {
                System.out.println("Неверно задано имя файла для записи.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}