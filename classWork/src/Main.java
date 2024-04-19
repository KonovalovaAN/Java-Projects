import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        // 7 + x - 2 +257 - 74 + x
        try{
            if(args.length != 2){
                throw new ArithmeticException("There should be two parameters!");
            }
            int x = 0;
            String expression = new String();
            x = Integer.parseInt(args[0]);
            expression = args[2];
            System.out.println("Result is: " + result(x, expression));
        } catch (ArithmeticException e){
            System.out.println(e.getMessage());
        } catch (NumberFormatException e){
            System.out.println(e.getMessage());
        }
    }
    public static int result(int x, String expression) throws  NumberFormatException{
        boolean flag = true;  // true +   false -
        int result = 0;
        StringTokenizer parseString = new StringTokenizer(expression, "+-", true);
        while (parseString.hasMoreTokens()){
            String currToken =parseString.nextToken();
            switch (currToken){
                case "+":
                    flag = true;
                    break;
                case "-":
                    flag = false;
                    break;
                case "x":
                case "X":
                    if(flag){
                        result += x;
                    } else{
                        result -= x;
                    }
                    break;
                default:
                    int temp = Integer.parseInt(currToken);
                    if(flag){
                        result += temp;
                    } else{
                        result -= temp;
                    }
            }
        }
        return result;
    }
}