import org.astongrouph.CustomArray.CustomArrayList;
import org.astongrouph.DataProvider.FileDataProvider;
import org.astongrouph.DataProvider.RandomDataProvider;
import org.astongrouph.model.Student;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataProviderTest {

    @Test
    void randomProviderGeneratesCorrectNumberOfStudents() {

        RandomDataProvider provider = new RandomDataProvider();

        CustomArrayList<Student> students =
                provider.provide(10);

        assertEquals(10, students.size());
    }

    @Test
    void fileProviderSkipsInvalidStudents() throws Exception {

        Path file = Files.createTempFile("students", ".txt");

        Files.writeString(file,
                "101,85.5,123456\n" +
                        "-5,90.0,123457\n" +
                        "102,75.0,123458\n" +
                        "103,150.0,123459\n" +
                        "104,95.0,123460\n"
        );

        FileDataProvider provider =
                new FileDataProvider(file);

        CustomArrayList<Student> students =
                provider.provide(10);

        assertEquals(3, students.size());

        assertEquals(101, students.get(0).getGroupNumber());
        assertEquals(102, students.get(1).getGroupNumber());
        assertEquals(104, students.get(2).getGroupNumber());

        Files.deleteIfExists(file);
    }
}