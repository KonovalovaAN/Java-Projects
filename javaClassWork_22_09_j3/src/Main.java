import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //String filePath = "D:\\study\\Java\\javaClassWork_22_09_j3\\src\\notExist.txt";
        //String filePath = "D:\\study\\Java\\javaClassWork_22_09_j3\\src\\inputFile.txt";
        //String filePath = "D:\\study\\Java\\javaClassWork_22_09_j3\\src\\inputFile1.txt";
        //String filePath = "D:\\study\\Java\\javaClassWork_22_09_j3\\src\\inputFile2.txt";
        //String filePath = "D:\\study\\Java\\javaClassWork_22_09_j3\\src\\inputFile3.txt";
        String filePath = "D:\\study\\Java\\javaClassWork_22_09_j3\\src\\inputFile4.txt";
        //String filePath = "D:\\study\\Java\\javaClassWork_22_09_j3\\src\\inputFile5.txt";
        //String filePath = "D:\\study\\Java\\javaClassWork_22_09_j3\\src\\inputFile6.txt";

        try {
            printSolution(findSolution(inputFromFile(filePath)));
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
            return;
        } catch (InputMismatchException ex) {
            System.err.println(ex.getMessage());
            return;
        } catch (NoSuchElementException ex) {
            System.err.println("There is a wrong amount of arguments");
        } catch (NumberFormatException ex) {
            System.err.println(ex.getMessage());
            return;
        } catch (DataInvalidException | IllegalArgumentException ex) {
            System.err.println(ex.getMessage());
            return;
        }

    }
    static double[][] inputFromFile(String filePath) throws FileNotFoundException, InputMismatchException,
            NoSuchElementException, DataInvalidException, NumberFormatException, IllegalArgumentException {
        Scanner input = new Scanner(new FileReader(filePath));
        if (!input.hasNextInt()){
            throw new NumberFormatException("Wrong data");
        }
        int number = input.nextInt();
        if (number <= 0) {
            throw new DataInvalidException("Invalid matrix size value.");
        }
        double[][] matrix = new double[number][number + 1];
        for (int i = 0; i < number; i++) {
            for (int j = i; j < number + 1; j++) {
                if(input.hasNextInt()) {
                    matrix[i][j] = input.nextInt();
                } else {
                    throw new NumberFormatException("Wrong data");
                }
            }
        }
        if (input.hasNext()) {
            throw new DataInvalidException("There are elements left");
        }
        input.close();
        int checkInfNumbOfSolutions = 1;
        for (int i = 0; i < number; i++) {
            checkInfNumbOfSolutions *= matrix[i][i];
        }
        if (checkInfNumbOfSolutions == 0) {
            throw new IllegalArgumentException("The system of the equations has an infinite " +
                    "number of solutions or doesn't have");
        }
        return matrix;
    }
    public static void subtractLines(double[] line1, double[] line2) {
        for (int i = 0; i < line1.length; i++) {
            line1[i] -= line2[i];
        }
    }
    public static void multipleLineOnNumber(double[] line, double number) {
        for (int i = 0; i < line.length; i++) {
            line[i] *= number;
        }
    }
    public static void divideLineOnNumber(double[] line, double number) {
        for (int i = 0; i < line.length; i++) {
            line[i] /= number;
        }
    }
    public static double[] findSolution(double[][] matrix) {
        double[] result = new double[matrix.length];
        double current;
        for (int i = matrix.length - 1; i >= 0; i--) {
            divideLineOnNumber(matrix[i], matrix[i][i]);
            for (int j = i - 1; j >= 0; j--) {
                current = matrix[j][i];
                multipleLineOnNumber(matrix[i], current);
                subtractLines(matrix[j], matrix[i]);
                divideLineOnNumber(matrix[i], current);
            }
        }
        for (int j = 0; j < matrix.length; j++) {
            result[j] = matrix[j][matrix.length];
        }
        return result;
    }
    public static void printSolution(double[] result) throws FileNotFoundException {
        PrintWriter record = new PrintWriter(new File("output.txt"));
        record.write("Solution is: (");
        for (int j = 0; j < result.length ; j++) {
            record.write(result[j] + " ");
        }
        record.write(")");
        record.close();
    }
}
class DataInvalidException extends Exception {
    public DataInvalidException(String message){
        super(message);
    }
}