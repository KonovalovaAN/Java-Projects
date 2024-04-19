abstract class Employee {
    protected String lastName;
    protected String organization;
    protected double positionCoefficient;

    public Employee(String lastName, String organization, double positionCoefficient) {
        this.lastName = lastName;
        this.organization = organization;
        this.positionCoefficient = positionCoefficient;
    }
    abstract double calculateSalary();

    @Override
    public String toString() {
        return "Employee: lastName=" + lastName + ", organization="
                + organization + ", positionCoefficient=" + positionCoefficient + '\n';
    }
}
