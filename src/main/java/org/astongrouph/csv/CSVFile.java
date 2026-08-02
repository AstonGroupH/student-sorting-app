package org.astongrouph.csv;

import org.astongrouph.model.Student;
import org.astongrouph.collection.CustomArrayList;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CSVFile {
    public CSVFile(String name) throws IllegalArgumentException {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("CSVFile: name file is null or is empty!");

        this.name = name;
    }

    public void writeToFle(CustomArrayList<Student> data) throws IllegalArgumentException {
        if (data == null || data.isEmpty())
            throw new IllegalArgumentException("writeToFile: collections is null or is empty!");

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(name, true)))
        {
            for (var e : data) {
                String wrLine = convertStudentToString(e);
                bw.write(wrLine);
                bw.newLine();
            }

        }
        catch(IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void readFromFile(CustomArrayList<Student> data) throws IllegalArgumentException {
        if (data == null)
            throw new IllegalArgumentException("writeToFile: collections is null!");

        try(BufferedReader br = new BufferedReader (new FileReader(name))) {
            String line;
            while ((line = br.readLine()) != null) {
                Student student = convertStringToStudent(line);
                if (student != null) {
                    data.add(student);
                }
            }
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected String convertStudentToString(Student object) {
        if (object == null) return "";

        List<String> fieldsString = new ArrayList<>();
        List<Field> fields = List.of(object.getClass().getDeclaredFields());

        if (fields.isEmpty()) return "";

        try {
            for(var field : fields) {
                field.setAccessible(true);

                Object value = field.get(object);
                fieldsString.add(value.toString());
            }
        }
        catch (IllegalAccessException e) {
            return object.toString();
        }

        return String.join(",", fieldsString);
    }

    protected Student convertStringToStudent(String line) {
        if (line == null || line.isEmpty()) {
            return null;
        }

        List<String> field = List.of(line.split(","));

        // add validate
        return new Student.Builder()
                .groupNumber(Integer.parseInt(field.get(0).trim()))
                .averageScore(Float.parseFloat(field.get(1).trim()))
                .recordBookNumber(Integer.parseInt(field.get(2).trim()))
                .build();
    }

    private final String name;
}
