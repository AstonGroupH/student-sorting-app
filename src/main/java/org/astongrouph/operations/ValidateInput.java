package org.astongrouph.operations;

import java.util.Scanner;

public class ValidateInput {
    public ValidateInput() {
        scanner = new Scanner(System.in);
    }
    public int readInt(String message, int min, int max) {
        while (true) {
            System.out.print(message);

            try {
                String line = scanner.nextLine();
                int val = Integer.parseInt(line);

                if (val >= min && val <= max) {
                    return val;
                }

                System.out.println("Значение должно быть от " + min + " до " + max);
            }
            catch (Exception ex) {
                System.out.println("Введено некорректное значение.");
            }
        }
    }

    private final Scanner scanner;
}
