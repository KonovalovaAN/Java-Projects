import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;

public class FileHandler {
    private ArrayList<CustomInteger> decimalNumbers;

    public FileHandler(ArrayList<CustomInteger> decimalNumbers) {
        this.decimalNumbers = decimalNumbers;
    }

    public void saveToFile() throws IOException {
        JFileChooser chooseFile = new JFileChooser("C:\\Users\\PC\\Desktop\\Java_2KR_Try\\src");
        chooseFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooseFile.setDialogTitle("Choose file");
        int result = chooseFile.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooseFile.getSelectedFile();
            int k = 0;
            File newFile = new File(file + "\\set(" + k + ").txt");
            while (newFile.isFile()) {
                k += 1;
                newFile = new File(file + "\\set(" + k + ").txt");
            }

            FileWriter fileWriter = new FileWriter(newFile);
            boolean firstNumber = true;

            for (CustomInteger num : decimalNumbers) {
                if (!firstNumber) {
                    fileWriter.write(" ");
                } else {
                    firstNumber = false;
                }

                fileWriter.write(String.valueOf(num.number));
            }

            fileWriter.close();
        }
    }
}
