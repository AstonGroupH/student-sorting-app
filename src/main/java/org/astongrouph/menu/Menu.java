package org.astongrouph.menu;

import java.util.Scanner;
import org.astongrouph.operations.Operations;

public class Menu {
    private final Scanner scanner;
    private final int count = 40;
    private final Operations operations;

    public Menu() {
        scanner = new Scanner(System.in);
        operations = new Operations();
    }

    public void show() {
        showHead();
        runCircleChoice();
    }

    private void showHead() {
        System.out.println("=".repeat(count));
        String tm = "Сортировка студентов.";
        System.out.print(" ".repeat((count - tm.length()) / 2));
        System.out.println(tm);
        System.out.println("=".repeat(count));
    }

    private void showMainMenu() {
        System.out.println("Меню:");
        System.out.println("\t 1. Показать коллекцию данных.");
        System.out.println("\t 2. Заполнить коллекцию данных.");
        System.out.println("\t 3. Задать размер коллекции.");
        System.out.println("\t 4. Задать поле сортировки коллекции.");
        System.out.println("\t 5. Отсортировать коллекцию. ");
        System.out.println("\t 0. Выход.");

        System.out.print("\nВыберите действие: ");
    }

    private void runCircleChoice() {
        while (true) {
            System.out.println("-".repeat(count));
            showMainMenu();

            try {
                String line = scanner.nextLine();
                int choice = Integer.parseInt(line);

                if (choice == 0) {
                    System.out.println("До свидания!");
                    break;
                }

                operations.doOperation(choice);
            }
            catch (Exception ex) {
                System.out.println("Введено некорректное действие.");
            }
        }
    }
}
