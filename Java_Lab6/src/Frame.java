import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class Frame extends JFrame {
    ProductData productData;
    JTextArea inputtedDataArea;
    JTextArea processedDataArea;

    public static void main(String[] args){
        Frame frame = new Frame("Production");
    }
    Frame(String title){
        super(title);
        productData = new ProductData();
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

        addNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Dialog dialog = new Dialog(Frame.this);
            }
        });
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JFileChooser fileChooser = new JFileChooser("D:\\study\\Java\\Java_Lab6\\src");
                if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try {
                        ProductData newProductData = new ProductData();
                        newProductData.readData(fileChooser.getSelectedFile());
                        productData = newProductData;
                        update();
                    } catch (IOException | IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputtedDataArea.setText("Inputted data");
                processedDataArea.setText("Processed data");
            }
        });
    }
    void update() {
        StringBuilder inputtedDataText = new StringBuilder();
        for (ProductData.Product product : productData.products) {
            inputtedDataText.append(product.toString());
        }
        inputtedDataArea.setText(inputtedDataText.toString());
        List<String> sortedCountries = productData.importersByTotalImportVolume();
        StringBuilder processedDataText = new StringBuilder();
        for (String country : sortedCountries) {
            processedDataText.append(country).append("\n");
        }
        processedDataArea.setText(processedDataText.toString());
    }
}
