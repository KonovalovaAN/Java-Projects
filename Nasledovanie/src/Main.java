import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Linear linearSeries = null;
        Exponential exponentialSeries = null;
        try {
            linearSeries = new Linear(1.0, 5, 2.0);
            exponentialSeries = new Exponential(1.0, 5, 2.0);
        } catch (IllegalArgumentException ex) {
            System.err.println(ex.getMessage());
            return;
        }
        if (linearSeries != null && exponentialSeries != null) {
            System.out.println("Linear Series: " + linearSeries.toString());
            System.out.println("Sum of Linear Series: " + linearSeries.seriesSum());
            System.out.println("Exponential Series: " + exponentialSeries.toString());
            System.out.println("Sum of Exponential Series: " + exponentialSeries.seriesSum());
            try {
                linearSeries.printInFile("linearSeries.txt");
                exponentialSeries.printInFile("exponentialSeries.txt");
            } catch (IOException e) {
                System.err.println("An error occurred while writing to the file: " + e.getMessage());
            }
        }
    }
}