class LedLamp extends Lamp {
    int numberOfLEDs;
    final double CONSTANT2 = 2.0;
    public LedLamp(String manufacturer, double power, int numberOfLEDs) {
        super(manufacturer, power);
        this.numberOfLEDs = numberOfLEDs;
    }
    @Override
    public int calculatePrice() {
        return (int) (power * numberOfLEDs / CONSTANT2);
    }
    @Override
    public String toString() {
        return "LED lamp: manufacturer " + manufacturer + ", power " +
                power + ", number of LEDs " + numberOfLEDs + ", price=" + calculatePrice() +  '\n';
    }
}