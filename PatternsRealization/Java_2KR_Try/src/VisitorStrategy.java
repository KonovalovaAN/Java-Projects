import java.util.ArrayList;

public class VisitorStrategy implements Strategy {
    @Override
    public int cardinality(ArrayList<CustomInteger> nums) {
        SpecificVisitor visitor = new SpecificVisitor();
        for (CustomInteger number: nums){
            number.accept(visitor);
        }
        return  visitor.getCount();
    }
}