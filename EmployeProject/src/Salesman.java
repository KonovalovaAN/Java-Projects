class Salesman extends Worker {
    double revenue;

    Salesman(String lastName, String organization, double positionCoefficient, double revenue) {
        super(lastName, organization, positionCoefficient);
        this.revenue = revenue;
    }

    @Override
    double calculateSalary() {
        final double PERCENT_CONSTANT = 0.1; // КОНСТАНТА_ПРОЦЕНТ
        return Math.round(positionCoefficient * revenue * PERCENT_CONSTANT);
    }
}
