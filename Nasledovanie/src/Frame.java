import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Frame  extends JFrame {
    public static void main(String[] args) {
        Frame frame = new Frame("Series");
    }
    Series series;
    Frame(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(430, 380);
        setResizable(false);
        setLocationRelativeTo(null);
        JLabel firstElLabel = new JLabel("First element:");
        firstElLabel.setBounds(50,10,110,30);
        JTextField firstElTextField = new JTextField();
        firstElTextField.setBounds(50,40,110,30);
        JLabel deltaLabel = new JLabel("Delta: ");
        deltaLabel.setBounds(50,70,110,30);
        JTextField deltaTextField = new JTextField();
        deltaTextField.setBounds(50,100,110,30);
        JLabel numberLabel = new JLabel("Num of elements:");
        numberLabel.setBounds(50,130,110,30);
        JTextField countElTextField = new JTextField();
        countElTextField.setBounds(50,160,110,30);
        ButtonGroup group = new ButtonGroup();
        JRadioButton linearButton = new JRadioButton("linear");
        JRadioButton expButton = new JRadioButton("exponential");
        group.add(linearButton);
        group.add(expButton);
        linearButton.setBounds(180, 40, 100, 30);
        expButton.setBounds(280, 40, 100, 30);
        JLabel fileLabel = new JLabel("File to print:");
        fileLabel.setBounds(180, 70, 110, 30);
        JLabel chooseLabel = new JLabel("Select the series type:");
        chooseLabel.setBounds(180, 10, 200, 30);
        JTextField fileTextField = new JTextField();
        fileTextField.setBounds(180,100,190,30);
        JTextArea outTextArea = new JTextArea("elements", 10, 20);
        outTextArea.setEditable(false);
        outTextArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(outTextArea);
        scrollPane.setBounds(50, 210, 320, 60);
        JButton calcButton = new JButton("calculate");
        calcButton.setBounds(180, 160, 190, 30);
        JButton cleanButton = new JButton("clean");
        cleanButton.setBounds(260, 290, 110, 30);
        setVisible(true);
        add(fileLabel);
        add(deltaLabel);
        add(numberLabel);
        add(firstElLabel);
        add(countElTextField);
        add(firstElTextField);
        add(deltaTextField);
        add(linearButton);
        add(expButton);
        add(chooseLabel);
        add(calcButton);
        add(cleanButton);
        add(fileTextField);
        add(scrollPane);
        setLayout(null);
        calcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    if (!linearButton.isSelected() && !expButton.isSelected()) {
                        throw new IllegalArgumentException("Please select a series type (linear or exponential).");
                    }
                    double firstElement = Double.parseDouble(firstElTextField.getText());
                    int amountOfElements = Integer.parseInt(countElTextField.getText());
                    double delta = Double.parseDouble(deltaTextField.getText());
                    if (linearButton.isSelected()) {
                        series = new Linear(firstElement, amountOfElements, delta);
                    } else {
                        series = new Exponential(firstElement, amountOfElements, delta);
                    }
                    outTextArea.setText(series.toString());
                    series.printInFile(fileTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Frame.this, "Error: incorrect value of input parameters.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                catch (IOException ex) {
                    JOptionPane.showMessageDialog(Frame.this, "Invalid file name. Check if the field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(Frame.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outTextArea.setText("elements");
                deltaTextField.setText("");
                firstElTextField.setText("");
                countElTextField.setText("");
                fileTextField.setText("");
            }
        });
    }
}