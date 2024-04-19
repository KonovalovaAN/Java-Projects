abstract class Worker {
    String lastName;
    String organization;
    double positionCoefficient;

    Worker(String lastName, String organization, double positionCoefficient) {
        this.lastName = lastName;
        this.organization = organization;
        this.positionCoefficient = positionCoefficient;
    }

    abstract double calculateSalary();
}
