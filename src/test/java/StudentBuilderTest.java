import org.astongrouph.model.Student;
import org.junit.jupiter.api.Test;

public class StudentBuilderTest {

    @Test
    public void whenBuildingStudentWithValidData_thenFieldsAreSetCorrectly() {
        var student = new Student.Builder(12, 100.1, 10099).build();
        String result = "Student: [ group number : 12 ] [ average score : 100,10 ] [ record book number : 10099 ]";

        assert (student.toString().equals(result));
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