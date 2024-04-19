import java.util.ArrayList;
import java.util.Arrays;

public class BinaryNumberGenerator {
    private ArrayList<CustomInteger> decimalNumbers;

    public BinaryNumberGenerator(ArrayList<CustomInteger> decimalNumbers) {
        this.decimalNumbers = decimalNumbers;
    }

    public String generateBinaryNumbers() {
        if (decimalNumbers.isEmpty()) {
            return "Cannot generate binary numbers from an empty list.";
        }

        int[] binaryArray = createBinaryArray();
        return arrayToString(binaryArray);
    }

    private CustomInteger findMaxDecimalNumber() {
        if (decimalNumbers.isEmpty()) {
            throw new IllegalStateException("Cannot find max decimal number in an empty list");
        }

        CustomInteger maxDecimalNumber = decimalNumbers.get(0);
        for (CustomInteger decimalNumber : decimalNumbers) {
            if (decimalNumber.number > maxDecimalNumber.number) {
                maxDecimalNumber = decimalNumber;
            }
        }
        return maxDecimalNumber;
    }

    private int[] createBinaryArray() {
        CustomInteger maxDecimal = findMaxDecimalNumber();
        int[] binaryArray = new int[maxDecimal.number + 1];
        Arrays.fill(binaryArray, 0);

        for (CustomInteger decimalNumber : decimalNumbers) {
            binaryArray[decimalNumber.number] = 1;
        }

        return binaryArray;
    }

    private String arrayToString(int[] array) {
        StringBuilder result = new StringBuilder();
        for (int num : array) {
            result.append(num).append(" ");
        }
        return result.toString();
    }
}
