import java.io.IOException;
import java.util.ArrayList;

public class SetModel implements Iterable<CustomInteger> {
    private ArrayList<CustomInteger> decimalNumbers;

    public SetModel() {
        decimalNumbers = new ArrayList<>();
    }

    public void add(CustomInteger num) {
        if (num.number < 0) {
            throw new IllegalArgumentException("Number must be non-negative");
        }

        if (!decimalNumbers.contains(num)) {
            decimalNumbers.add(num);
        } else {
            throw new IllegalArgumentException("Duplicate number not allowed");
        }
    }

    public String getBinNums() {
        BinaryNumberGenerator binaryNumberGenerator = new BinaryNumberGenerator(decimalNumbers);
        return binaryNumberGenerator.generateBinaryNumbers();
    }

    public String getDecNums() {
        Iterator<CustomInteger> iterator = iterator();
        StringBuilder result = new StringBuilder();
        while (iterator.hasNext()) {
            result.append(iterator.next()).append(" ");
        }
        return result.toString();
    }

    public void save() throws IOException {
        FileHandler fileHandler = new FileHandler(decimalNumbers);
        fileHandler.saveToFile();
    }

    @Override
    public Iterator<CustomInteger> iterator() {
        return new SetIterator(decimalNumbers);
    }
}
