import java.util.Scanner;

public class KMPAutomaton {

    private static final int ALPHABET_SIZE = 2;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.next();
            int length = input.length();

            int[] kmpTable = buildKMPTable(input);
            int[][] dfa = buildDFA(input, kmpTable);

            StringBuilder resultBuilder = new StringBuilder();
            for (int i = 0; i <= length; i++) {
                for (int j = 0; j < ALPHABET_SIZE; j++) {
                    resultBuilder.append(dfa[i][j]).append(" ");
                }
                resultBuilder.append("\n");
            }

            System.out.print(resultBuilder);
        }
    }

    public static int[][] buildDFA(String pattern, int[] kmpTable) {
        int patternLength = pattern.length();
        int[][] dfa = new int[patternLength + 1][ALPHABET_SIZE];

        for (int i = 0; i <= patternLength; i++) {
            for (int j = 0; j < ALPHABET_SIZE; j++) {
                char c = (char) ('0' + j);
                int k = i;

                while (k > 0 && k < patternLength && c != pattern.charAt(k)) {
                    k = kmpTable[k - 1];
                }

                if (k < patternLength && c == pattern.charAt(k)) {
                    k++;
                }
                dfa[i][j] = k;
            }
        }

        return dfa;
    }

    public static int[] buildKMPTable(String pattern) {
        int patternLength = pattern.length();
        int[] kmpTable = new int[patternLength];
        int j = 0;

        for (int i = 1; i < patternLength; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = kmpTable[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            kmpTable[i] = j;
        }

        return kmpTable;
    }
}
