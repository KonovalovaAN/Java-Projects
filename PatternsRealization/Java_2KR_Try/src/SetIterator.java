import java.util.ArrayList;

public class SetIterator implements Iterator<CustomInteger> {
    private ArrayList<CustomInteger> decimalNumbers;
    private int index;

    public SetIterator(ArrayList<CustomInteger> decimalNumbers) {
        this.decimalNumbers = decimalNumbers;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < decimalNumbers.size();
    }

    @Override
    public CustomInteger next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more elements");
        }
        return decimalNumbers.get(index++);
    }
}
