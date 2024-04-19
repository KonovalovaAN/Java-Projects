import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class DataProcessor<T extends Student> {
    private List<T> students;
    public DataProcessor(List<T> students) {
        this.students = students;
    }
    public void displayInformation() {
        students.forEach(Student::displayInformation);
    }
    public long countSpecificStudents(T specificStudent) {
        return students.stream().filter(student -> student.equals(specificStudent)).count();
    }
    public T binarySearch(String schoolName, String lastName) {
        return students.stream()
                .filter(student -> student.getSchoolName().equals(schoolName) && student.getLastName().equals(lastName))
                .findFirst()
                .orElse(null);
    }
    public T findMinimum() {
        return students.stream()
                .min(Comparator.comparing(Student::getSchoolName).thenComparing(Student::getLastName))
                .orElse(null);
    }
}
