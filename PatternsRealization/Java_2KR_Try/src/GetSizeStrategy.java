import java.util.ArrayList;

public class GetSizeStrategy implements Strategy {
    @Override
    public int cardinality(ArrayList<CustomInteger> elements) {
        return elements.size();
    }
}
