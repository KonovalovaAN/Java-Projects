public class HighSchoolStudent extends Student {
    public enum Behavior {GOOD, SATISFACTORY, POOR}

    private int grade;
    private Behavior behavior;

    public HighSchoolStudent(String lastName, String schoolName, double averageGrade, int grade, Behavior behavior) {
        super(lastName, schoolName, averageGrade);
        this.grade = grade;
        this.behavior = behavior;
    }

    @Override
    public void displayInformation() {
        System.out.println("High School Student: " + getLastName() + ", School: " + getSchoolName() +
                ", Average Grade: " + getAverageGrade() + ", Grade: " + grade + ", Behavior: " + behavior);
    }
}
