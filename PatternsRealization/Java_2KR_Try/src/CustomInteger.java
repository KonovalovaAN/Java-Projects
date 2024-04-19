public class CustomInteger implements Element {
    Integer number;
    CustomInteger(Integer number){
        this.number = number;
    };

    @Override
    public void accept(Visitor v) {
        v.visit(number);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public boolean equals(Object obj){
        CustomInteger myNum = (CustomInteger) obj;
        return number.equals(myNum.number);
    }
}
