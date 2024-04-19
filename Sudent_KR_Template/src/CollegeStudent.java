public class CollegeStudent extends Student {
    private int course;

    public CollegeStudent(String lastName, String schoolName, double averageGrade, int course) {
        super(lastName, schoolName, averageGrade);
        this.course = course;
    }

    @Override
    public void displayInformation() {
        System.out.println("College Student: " + getLastName() + ", School: " + getSchoolName() +
                ", Average Grade: " + getAverageGrade() + ", Course: " + course);
    }
}
