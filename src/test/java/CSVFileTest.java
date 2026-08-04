import org.astongrouph.collection.CustomArrayList;
import org.astongrouph.csv.CSVFile;
import org.astongrouph.model.Student;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVFileTest {
    @Test
    public void writeAndReadCollectionToCSVFile() {
        final int size = 10;
        CustomArrayList<Student> students = new CustomArrayList<>();
        for (int i = 0; i < size; i++) {
            students.add(new Student.Builder( 12, 99.0, 1000 + i).build());
        }
        
        String name = "test.csv";
        CSVFile file = new CSVFile(name);
        file.writeToFle(students);

        CustomArrayList<Student> readStudents = new CustomArrayList<>();
        file.readFromFile(readStudents);

        assert(students.equals(readStudents));

        try {
            Files.deleteIfExists(Paths.get("test.csv"));
        }
        catch (Exception e) {
            System.out.println("Ошибка удаления: " + e.getMessage());
        }
    }
}
