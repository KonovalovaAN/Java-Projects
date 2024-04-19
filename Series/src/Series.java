abstract class Series {
    int firstElement; //double
    int count;
    int delta; //double
    Series(int firstElement, int count, int data){
        this.firstElement = firstElement;
        this.count = count;
        this.delta = data;
    }
    //double
    abstract int calculate(int j);
    int seriesSum(){
        int sum = 0;
        for (int i = 0; i < count; i++){
            sum += calculate(i);
        }
        return sum;
    }
}
