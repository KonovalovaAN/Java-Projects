public class Main {
    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                throw new IllegalArgumentException("ERROR: only one non-empty parameter must be passed");
            }
            String inputString = args[0];
            if (!hasParentheses(inputString)) {
                throw new IllegalArgumentException("Message: no parentheses found in the string.");
            }
            if (!isBalanced(inputString)) {
                throw new IllegalArgumentException("Warning: incorrect entry of parentheses in the string.");
            }
            System.out.println("Your string: " + inputString);
            System.out.println("New string: " + deleteParentheses(inputString));
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static StringBuilder deleteParentheses (String inputString) {
        StringBuilder line = new StringBuilder(inputString);
        int countOpen = 0;
        int countClose = 0;
        int openBracketPosition = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '(') {
                countOpen++;
                openBracketPosition = i;
            } else if (line.charAt(i) == ')') {
                countClose++;
            }
            if (countOpen == countClose && countClose == 1 ){
                line.delete(openBracketPosition, i + 1);
                i = openBracketPosition - 1;
                countOpen = 0;
                countClose = 0;
                openBracketPosition = 0;
            } else if (countOpen == countClose && countClose != 1) {
                countOpen = 0;
                countClose = 0;
            }
        }
        return line;
    }
    public static boolean isBalanced(String inputString) {
        int balance = 0;
        for (int i = 0; i < inputString.length(); i++) {
            if (inputString.charAt(i) == '(') {
                balance++;
            } else if (inputString.charAt(i) == ')') {
                balance--;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }
    public static boolean hasParentheses(String inputString) {
        return inputString.contains("(") || inputString.contains(")");
    }
}