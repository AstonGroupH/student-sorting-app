import org.astongrouph.model.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class StudentBuilderTest {

    @Test
    public void whenBuildingStudentWithValidData_thenFieldsAreSetCorrectly() {
        var student = new Student.Builder(12, 100.1, 10099).build();

        Assertions.assertNotNull(student);
    }

    @Test
    public void whenBuildingStudentWithNegativeGroupNumber_thenThrowException() {
        try {
            var student = new Student.Builder(-12, 100.1, 10099).build();
        } catch (Exception e) {
            assert(e.getMessage().equals("group number must be > 0"));
        }
    }

    @Test
    public void whenBuildingStudentWithNegativeAverageScore_thenThrowException() {
        try {
            var student = new Student.Builder(12, -100.1, 10099).build();
        } catch (Exception e) {
            assert(e.getMessage().equals("average score must be > 0"));
        }
    }

    @Test
    public void whenBuildingStudentWithNegativeRecordBookNumber_thenThrowException() {
        try {
            var student = new Student.Builder(12, 100.1, -10099).build();
        } catch (Exception e) {
            assert(e.getMessage().equals("record book number must be > 0"));
        }
    }

}