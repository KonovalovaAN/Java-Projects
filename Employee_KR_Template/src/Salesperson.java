class Salesperson extends Employee {
    private double revenue;

    public Salesperson(String lastName, String organization, double positionCoefficient, double revenue) {
        super(lastName, organization, positionCoefficient);
        this.revenue = revenue;
    }

    @Override
    double calculateSalary() {
        final double PERCENT_CONSTANT = 0.1;
        double salary = positionCoefficient * revenue * PERCENT_CONSTANT;
        return Math.round(salary);
    }

    @Override
    public String toString() {
        return "Salesperson: lastName=" + lastName + ", organization=" +
                organization + ", positionCoefficient=" + positionCoefficient +
                ", revenue=" + revenue + ", salary=" + calculateSalary() + '\n';
    }
}