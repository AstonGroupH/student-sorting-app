package org.astongrouph.operations;

import org.astongrouph.DataProvider.FileDataProvider;
import org.astongrouph.DataProvider.ManualDataProvider;
import org.astongrouph.DataProvider.RandomDataProvider;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class FillCollectionHandler extends OperationHandler {
    @Override
    protected boolean canHandle(OperationLevel level) {
        return level == OperationLevel.FILL_COLLECTION;
    }

    @Override
    protected void processRequest() {
        System.out.print("Способы заполнения: ");
        System.out.println("[1] - из файла; [2] - случайным образом; [3] - вручную; [0] - выйти;");

        ValidateInput input = new ValidateInput();
        int choice = input.readInt("Введите способ заполнения: ", 0, 3);

        if (choice == 0) return;
        if (choice == 1) fillFromFile();
        if (choice == 2) fillRandom();
        if (choice == 3) fillManual();

        if (students != null && !students.isEmpty()) {
            System.out.println("Коллекция заполнена");
        }
    }

    protected void fillFromFile() {
        System.out.print("Укажите имя файла [example.csv]: ");
        String name = scanner.nextLine();

        if (name.isEmpty()) {
            name = "example.csv";
        }

        Path path = Paths.get("StudentsData", name);

        if(!Files.exists(path)) {
            System.out.println("Файл не найден: " + path.toAbsolutePath());
            return;
        }


        FileDataProvider provider = new FileDataProvider(path);
        students = provider.provide(collectionSize);
    }

    protected void fillRandom() {
        RandomDataProvider provider = new RandomDataProvider();
        students = provider.provide(collectionSize);
    }

    protected void fillManual() {
        ManualDataProvider provider = new ManualDataProvider(scanner);
        students = provider.provide(collectionSize);
    }
}
