public abstract class Student {
    private String lastName;
    private String schoolName;
    private double averageGrade;

    public Student(String lastName, String schoolName, double averageGrade) {
        this.lastName = lastName;
        this.schoolName = schoolName;
        this.averageGrade = averageGrade;
    }

    public abstract void displayInformation();

    public String getLastName() {
        return lastName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public double getAverageGrade() {
        return averageGrade;
    }
}
