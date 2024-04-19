abstract class Lamp {
    String manufacturer;
    double power;
    public Lamp(String manufacturer, double power) {
        this.manufacturer = manufacturer;
        this.power = power;
    }
    public abstract int calculatePrice();
}
