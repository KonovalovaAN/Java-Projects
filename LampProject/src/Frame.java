import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Frame extends JFrame {
    ArrayList<Lamp> lamps;
    Lamp lamp;
    Frame(String title){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(640, 500);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JTextArea inputTextArea = new JTextArea();
        inputTextArea.setEditable(false);
        JTextArea outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JTextArea averagePriceTextArea = new JTextArea();
        averagePriceTextArea.setEditable(false);

        Dimension textAreaSize = new Dimension(200, 400);

        JScrollPane inputScrollPane = new JScrollPane(inputTextArea);
        inputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        inputScrollPane.setPreferredSize(textAreaSize);

        JScrollPane outputScrollPane = new JScrollPane(outputTextArea);
        outputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        outputScrollPane.setPreferredSize(textAreaSize);

        JScrollPane averagePriceScrollPane = new JScrollPane(averagePriceTextArea);
        averagePriceScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        averagePriceScrollPane.setPreferredSize(textAreaSize);

        JPanel panelWithRadioButtons = new JPanel();
        JPanel buttonPanel = new JPanel();

        ButtonGroup typeButtonGroup = new ButtonGroup();
        JRadioButton incandescentLampRadioButton = new JRadioButton("Incandescent lamp");
        incandescentLampRadioButton.setSelected(true);
        JRadioButton ledLampRadioButton = new JRadioButton("LED lamp");
        typeButtonGroup.add(incandescentLampRadioButton);
        typeButtonGroup.add(ledLampRadioButton);

        panelWithRadioButtons.add(incandescentLampRadioButton);
        panelWithRadioButtons.add(ledLampRadioButton);

        ButtonGroup sortButtonGroup = new ButtonGroup();
        JRadioButton sortInAscendingOrderOfPriceRadioButton = new JRadioButton("by price");
        sortInAscendingOrderOfPriceRadioButton.setSelected(true);
        JRadioButton sortByPricePowerRadioButton = new JRadioButton("by price / power ratio");
        JRadioButton sortByStartingWithCRadioButton = new JRadioButton("by starting with ‘C’");
        sortButtonGroup.add(sortInAscendingOrderOfPriceRadioButton);
        sortButtonGroup.add(sortByPricePowerRadioButton);
        sortButtonGroup.add(sortByStartingWithCRadioButton);

        panelWithRadioButtons.add(sortInAscendingOrderOfPriceRadioButton);
        panelWithRadioButtons.add(sortByPricePowerRadioButton);
        panelWithRadioButtons.add(sortByStartingWithCRadioButton);

        JButton load = new JButton("Open file");
        JButton sort = new JButton("Sort");
        sort.setEnabled(false);
        JButton clean = new JButton("Clean");
        clean.setEnabled(false);
        JButton calculateTheAveragePrice = new JButton("Calculate price");
        calculateTheAveragePrice.setEnabled(false);

        buttonPanel.add(load);
        buttonPanel.add(sort);
        buttonPanel.add(clean);
        buttonPanel.add(calculateTheAveragePrice);

        add(inputScrollPane, BorderLayout.WEST);
        add(outputScrollPane, BorderLayout.CENTER);
        add(averagePriceScrollPane, BorderLayout.EAST);
        add(panelWithRadioButtons, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser("D:\\Konovalova_3gr\\src");

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = chooser.getSelectedFile();
                    try {
                        lamps = new ArrayList<>(Files.lines(Paths.get(selectedFile.getAbsolutePath()))
                                .map(line -> {
                                    try {
                                        String[] parts = line.split(" ");
                                        if (parts.length != 3 || line.isEmpty()) {
                                            throw new IllegalArgumentException("Invalid file data.");
                                        }
                                        String manufacturer = parts[0];
                                        double power = Double.parseDouble(parts[1]);
                                        int additionalParam = Integer.parseInt(parts[2]);
                                        if(manufacturer.isEmpty() || power <= 0 || additionalParam <=0){
                                            throw new IllegalArgumentException("Check your input data.");
                                        }
                                        if (incandescentLampRadioButton.isSelected()) {
                                            return new IncandescentLamp(manufacturer, power, additionalParam);
                                        } else {
                                            return new LedLamp(manufacturer, power, additionalParam);
                                        }
                                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                                        throw new RuntimeException("Invalid file format. Check if the file contains valid lamp data.");
                                    }
                                })
                                .collect(Collectors.toList()));
                        inputTextArea.setText("");
                        incandescentLampRadioButton.setEnabled(false);
                        ledLampRadioButton.setEnabled(false);
                        sort.setEnabled(true);
                        clean.setEnabled(true);
                        calculateTheAveragePrice.setEnabled(true);
                        lamps.forEach(lamp -> inputTextArea.append(lamp.toString()));
                    } catch (NoSuchFileException ex) {
                        JOptionPane.showMessageDialog(null, "File not found: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (IOException | RuntimeException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        clean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputTextArea.setText(" ");
                outputTextArea.setText(" ");
                averagePriceTextArea.setText(" ");
                lamps.clear();
                incandescentLampRadioButton.setEnabled(true);
                ledLampRadioButton.setEnabled(true);
                clean.setEnabled(false);
                sort.setEnabled(false);
                calculateTheAveragePrice.setEnabled(false);
            }
        });

        sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputTextArea.setText("");
                if (sortInAscendingOrderOfPriceRadioButton.isSelected()) {
                    ArrayList<Lamp> sortedByPrice = new ArrayList<>(LampDataProcessor.sortByPriceAscending(lamps));
                    sortedByPrice.forEach(lamp -> outputTextArea.append(lamp.toString()));
                } else if (sortByPricePowerRadioButton.isSelected()) {
                    ArrayList<Lamp> sortedByPriceToPowerRatio = new ArrayList<>(LampDataProcessor.sortByPriceToPowerRatioDescending(lamps));
                    sortedByPriceToPowerRatio.forEach(lamp -> outputTextArea.append(lamp.toString()));
                } else if (sortByStartingWithCRadioButton.isSelected()) {
                    ArrayList<String> manufacturersStartingWithC = new ArrayList<>(LampDataProcessor.getManufacturersStartingWithC(lamps));
                    manufacturersStartingWithC.forEach(manufacturer -> outputTextArea.append(manufacturer + "\n"));
                }
            }
        });

        calculateTheAveragePrice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String manufacturer = JOptionPane.showInputDialog("Enter the manufacturer:");
                if (!manufacturer.isEmpty()) {
                    double average = LampDataProcessor.calculateAveragePriceByManufacturer(lamps, manufacturer);
                    if (average != 0.0) {
                        averagePriceTextArea.setText(Double.toString(average));
                    } else {
                        JOptionPane.showMessageDialog(null, "Manufacturer not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid manufacturer input.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}