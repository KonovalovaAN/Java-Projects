package Strategy;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Frame extends JFrame {
    ArrayList<ProductData> products = new ArrayList<>();
    JTextArea inputtedDataArea;
    JTextArea processedDataArea;
    JRadioButton streamApiRadioButton;
    JRadioButton nonStreamApiRadioButton;
    ImportCountriesStrategy selectedStrategy;

    public static void main(String[] args){
        Frame frame = new Frame("Production");
    }
    Frame(String title){
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton open = new JButton("Open");
        open.setBounds(450, 300, 80, 30);
        JButton addNew = new JButton("Add");
        addNew.setBounds(360, 300, 80, 30);
        inputtedDataArea = new JTextArea("Unprocessed Data:", 20, 10);
        inputtedDataArea.setEditable(false);
        inputtedDataArea.setLineWrap(true);
        JScrollPane inputtedScrollPane = new JScrollPane(inputtedDataArea);
        inputtedScrollPane.setBounds(60, 40, 220, 230);

        processedDataArea = new JTextArea("Imported Countries (Sorted):", 20, 10);
        inputtedDataArea.setEditable(false);
        inputtedDataArea.setLineWrap(true);
        JScrollPane processedScrollPane = new JScrollPane(processedDataArea);
        processedScrollPane.setBounds(310, 40, 220, 230);

        JButton cleanButton = new JButton("Clean");
        cleanButton.setBounds(60, 300, 80, 30);

        streamApiRadioButton = new JRadioButton("Stream API");
        streamApiRadioButton.setBounds(60, 10, 100, 20);
        nonStreamApiRadioButton = new JRadioButton("Non-Stream API");
        nonStreamApiRadioButton.setBounds(160, 10, 120, 20);

        ButtonGroup strategyButtonGroup = new ButtonGroup();
        strategyButtonGroup.add(streamApiRadioButton);
        strategyButtonGroup.add(nonStreamApiRadioButton);

        streamApiRadioButton.setSelected(true);
        selectedStrategy = new StreamApiImportCountriesStrategy();

        setVisible(true);
        add(open);
        add(addNew);
        add(cleanButton);
        add(inputtedScrollPane);
        add(processedScrollPane);
        add(streamApiRadioButton);
        add(nonStreamApiRadioButton);

        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JFileChooser fileChooser = new JFileChooser("D:\\study\\Java\\Java_Laba_p11\\src\\Strategy");
                if(fileChooser.showOpenDialog(Frame.this) == JFileChooser.APPROVE_OPTION) {
                    try {
                        readData(fileChooser.getSelectedFile());
                        update();
                        open.setEnabled(false);
                        streamApiRadioButton.setEnabled(false);
                        nonStreamApiRadioButton.setEnabled(false);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        addNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Dialog dialog = new Dialog(Frame.this);
            }
        });
        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputtedDataArea.setText("Unprocessed Data:");
                processedDataArea.setText("Imported Countries (Sorted):");
                products.clear();
                open.setEnabled(true);
                streamApiRadioButton.setEnabled(true);
                nonStreamApiRadioButton.setEnabled(true);
            }
        });
        streamApiRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedStrategy = new StreamApiImportCountriesStrategy();
                update();
            }
        });

        nonStreamApiRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedStrategy = new NonStreamApiImportCountriesStrategy();
                update();
            }
        });
    }
    void readData (File file) throws IOException {
        Scanner input = new Scanner(file);
        if(!input.hasNextInt()){
            throw new IOException("Error: the file does not contain the number of products.");
        }
        int size = Integer.parseInt(input.nextLine());
        if (size <= 0) {
            throw  new IOException("Error: the number of products should be a positive integer.");
        }
        for(int i = 0; i < size; i++) {
            if(!input.hasNextLine()){
                throw new IOException("Error: the file does not contain the required number of characteristics.");
            }
            String productName = input.nextLine();
            if(!input.hasNextLine()) {
                throw new IOException("Error: the last product characteristic contains only the product name.");
            }
            String productCountry = input.nextLine();
            if(!input.hasNextInt()){
                throw new IOException("Error: the wrong number of products is indicated in the product specifications.");
            }
            int amountOfProducts = Integer.parseInt(input.nextLine());
            if(amountOfProducts <= 0) {
                throw new IOException("Error: the amount of products should be a positive integer.");
            }
            products.add(new ProductData(productName, productCountry, amountOfProducts));
        }
        if (input.hasNext()) {
            throw new IOException("Error: file has extra data.");
        }
        input.close();
    }
    void update() {
        String unprocessedData = products.stream()
                .map(ProductData::toString)
                .collect(Collectors.joining());

        inputtedDataArea.setText("Unprocessed Data:\n" + unprocessedData);

        List<String> importCountries = selectedStrategy.getImportCountries(products);

        StringBuilder result = new StringBuilder();
        for (String country : importCountries) {
            result.append(country).append(" units").append("\n");
        }

        processedDataArea.setText("Imported Countries (Sorted):\n" + result.toString());
    }
}