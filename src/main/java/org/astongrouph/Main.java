package org.astongrouph;

import org.astongrouph.model.Student;

public class Main {
    public static void main(String[] args) {
        try {
            var student = new Student.Builder(12, 100.1, 10099).build();
            System.out.println("Student build: " + student);
        }
        catch (Exception e) {
            System.err.println("Error student build: " + e.getMessage());
        }
    }
}