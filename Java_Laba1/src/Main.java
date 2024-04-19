public class Main {
    public static void main(String[] args) {
        double x = 0;
        double eps = 0;
        if(args.length != 2){
            throw new IllegalArgumentException("There should be two parameters");
        }
        try {
            x = Double.parseDouble(args[0]);
        } catch (NumberFormatException ex){
            throw new IllegalArgumentException(ex.getMessage());
        }
        try {
            eps = Double.parseDouble(args[1]);
        } catch (NumberFormatException ex){
            throw new IllegalArgumentException(ex.getMessage());
        }
        if (Math.abs(x) > 1){
            throw new IllegalArgumentException("Incorrect value of x");
        }
        System.out.println("Result is: " + sumOfRow(x, eps));
    }
    public static double sumOfRow(double x, double eps){
        boolean checkEpsilon = false;
        double sum = 0;
        int k = 1;
        double currentElement = Math.pow(x, 3);
        while (Math.abs(currentElement) >= eps){
            checkEpsilon = true;
            sum += currentElement;
            currentElement *= Math.pow(x, 6 * k + 3);
            k++;
        }
        if(checkEpsilon) {
            return sum;
        } else{
            throw new IllegalArgumentException("Incorrect value of eps");
        }
    }
}