import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
abstract class Series {
    double firstElement;
    int numberOfElements;
    double delta;
    Series(double firstElement, int numberOfElements, double delta) throws IllegalArgumentException {
        this.firstElement = firstElement;
        if (numberOfElements > 0) {
            this.numberOfElements = numberOfElements;
        } else {
            throw new IllegalArgumentException("Error: incorrect number of elements");
        }
        this.delta = delta;
    }
    public String toString() {
        StringBuffer elements = new StringBuffer();
        elements.append(firstElement);
        elements.append(" ");
        for (int i = 1; i < numberOfElements; i++) {
            elements.append(calculateElement(i));
            elements.append(" ");
        }
        return elements.toString();
    }
    abstract double calculateElement(int i);
    public double seriesSum(){
        double sum = 0;
        for (int i = 0; i < numberOfElements; i++) {
            sum += calculateElement(i);
        }
        return sum;
    }
    public void printInFile(String path) throws IOException {
        FileWriter writer = new FileWriter(path, false);
        String ser = toString();
        writer.write("The elements of the series: ");
        writer.write('\n');
        writer.write(ser);
        writer.write('\n');
        writer.write("Sum is " + seriesSum());
        writer.close();
    }
}