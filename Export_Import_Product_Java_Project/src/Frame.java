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
        inputtedDataArea = new JTextArea("Inputted data", 20, 10);
        inputtedDataArea.setEditable(false);
        inputtedDataArea.setLineWrap(true);
        JScrollPane inputtedScrollPane = new JScrollPane(inputtedDataArea);
        inputtedScrollPane.setBounds(60, 40, 220, 230);

        processedDataArea = new JTextArea("Processed data", 20, 10);
        inputtedDataArea.setEditable(false);
        inputtedDataArea.setLineWrap(true);
        JScrollPane processedScrollPane = new JScrollPane(processedDataArea);
        processedScrollPane.setBounds(310, 40, 220, 230);

        JButton cleanButton = new JButton("Clean");
        cleanButton.setBounds(60, 300, 80, 30);

        setVisible(true);
        add(open);
        add(addNew);
        add(cleanButton);
        add(inputtedScrollPane);
        add(processedScrollPane);

        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JFileChooser fileChooser = new JFileChooser("D:\\study\\Java\\Try_j6\\src");
                if(fileChooser.showOpenDialog(Frame.this) == JFileChooser.APPROVE_OPTION) {
                    try {
                        readData(fileChooser.getSelectedFile());
                        update();
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
                inputtedDataArea.setText("Inputted data");
                processedDataArea.setText("Processed data");
                products.clear();
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
//    void update() {
//        StringBuilder unprocessedData = new StringBuilder();
//        for (ProductData product : products) {
//            unprocessedData.append(product.toString());
//        }
//        inputtedDataArea.setText("Unprocessed Data:\n" + unprocessedData.toString());
//
//        Map<String, Integer> importVolumes = new HashMap<>();
//        for (ProductData product : products) {
//            String country = product.getProductCountry();
//            int volume = product.getAmountOfProduct();
//            importVolumes.put(country, importVolumes.getOrDefault(country, 0) + volume);
//        }
//        List<Map.Entry<String, Integer>> importList = new ArrayList<>(importVolumes.entrySet());
//        Collections.sort(importList, new Comparator<Map.Entry<String, Integer>>() {
//            @Override
//            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
//                return entry2.getValue().compareTo(entry1.getValue());
//            }
//        });
//        StringBuilder result = new StringBuilder();
//        for (Map.Entry<String, Integer> entry : importList) {
//            result.append(entry.getKey()).append(": ").append(entry.getValue()).append(" units\n");
//        }
//        processedDataArea.setText("Importing Countries (Sorted):\n" + result.toString());
//    }

    void update() {
        String unprocessedData = products.stream()
                .map(ProductData::toString)
                .collect(Collectors.joining());

        inputtedDataArea.setText("Unprocessed Data:\n" + unprocessedData);

        Map<String, Integer> importVolumes = products.stream()
                .collect(Collectors.groupingBy(ProductData::getProductCountry,
                        Collectors.summingInt(ProductData::getAmountOfProduct)));

        StringBuilder result = new StringBuilder();
        importVolumes.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .forEach(entry -> result.append(entry.getKey())
                        .append(": ")
                        .append(entry.getValue())
                        .append(" units")
                        .append("\n"));

        processedDataArea.setText("Importing Countries (Sorted):\n" + result.toString());
    }
}