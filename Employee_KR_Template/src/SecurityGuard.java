class SecurityGuard extends Employee {
    private double protectedArea;

    public SecurityGuard(String lastName, String organization, double positionCoefficient, double protectedArea) {
        super(lastName, organization, positionCoefficient);
        this.protectedArea = protectedArea;
    }

    @Override
    double calculateSalary() {
        final double BASE_CONSTANT = 1000;
        double salary = BASE_CONSTANT * positionCoefficient * protectedArea;
        return Math.round(salary);
    }

    @Override
    public String toString() {
        return "SecurityGuard: lastName=" + lastName + ", organization=" +
                organization + ", positionCoefficient=" + positionCoefficient +
                ", protectedArea=" + protectedArea + ", salary=" + calculateSalary() + '\n';
    }
}