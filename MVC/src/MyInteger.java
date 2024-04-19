public class MyInteger implements Element {
    Integer num;
    MyInteger(Integer number){
        num = number;
    };

    @Override
    public String toString() {
        return String.valueOf(num);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean equals(Object obj){
        return num.equals(((MyInteger)obj).num);
    }
}
