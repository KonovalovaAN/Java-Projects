class Guard extends Worker {
    double protectedArea;

    Guard(String lastName, String organization, double positionCoefficient, double protectedArea) {
        super(lastName, organization, positionCoefficient);
        this.protectedArea = protectedArea;
    }

    @Override
    double calculateSalary() {
        final double BASE_CONSTANT = 1000;
        return Math.round(BASE_CONSTANT * positionCoefficient * protectedArea);
    }
}
