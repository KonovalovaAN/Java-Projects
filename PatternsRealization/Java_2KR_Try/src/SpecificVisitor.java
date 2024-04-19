public class SpecificVisitor implements Visitor{
    int count = 0;
    @Override
    public void visit(Integer value) {
        count ++;
    }
    public int getCount(){
        return count;
    }
}
