class IncandescentLamp extends Lamp {
    int operatingTime;
    final double CONSTANT1 = 1.5;
    public IncandescentLamp(String manufacturer, double power, int lifespan) {
        super(manufacturer, power);
        this.operatingTime = lifespan;
    }
    @Override
    public int calculatePrice() {
        return (int) (power * CONSTANT1 * operatingTime);
    }
    @Override
    public String toString() {
        return "Incandescent lamp: manufacturer " + manufacturer + ", power " +
                power + ", lifespan " + operatingTime + ", price " + calculatePrice() + '\n';
    }
}