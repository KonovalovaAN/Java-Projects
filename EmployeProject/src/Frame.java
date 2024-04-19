import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static javax.swing.SwingConstants.*;

public class Frame extends JFrame {
    private JTextArea rawDataTextArea;
    private JTextArea sortedDataTextArea;
    private JTextArea averageSalaryTextArea;
    private JRadioButton guardRadioButton;
    private JRadioButton salesmanRadioButton;

    public Frame(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(600,400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        rawDataTextArea = new JTextArea();
        sortedDataTextArea = new JTextArea();
        averageSalaryTextArea = new JTextArea();

        guardRadioButton = new JRadioButton("Guard");
        salesmanRadioButton = new JRadioButton("Salesman");

        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(guardRadioButton);
        radioButtonGroup.add(salesmanRadioButton);

        JButton sortButton = new JButton("Sort");
        JButton openButton = new JButton("Open");
        JButton clearButton = new JButton("Clear");

        rawDataTextArea = new JTextArea("Inputted data", 20, 10);
        rawDataTextArea.setEditable(false);
        rawDataTextArea.setLineWrap(true);

        sortedDataTextArea = new JTextArea("Inputted data", 20, 10);
        sortedDataTextArea.setEditable(false);
        sortedDataTextArea.setLineWrap(true);

        averageSalaryTextArea = new JTextArea("Inputted data", 20, 10);
        averageSalaryTextArea.setEditable(false);
        averageSalaryTextArea.setLineWrap(true);

        JScrollPane inputtedScrollPane = new JScrollPane(rawDataTextArea);
        inputtedScrollPane.setPreferredSize(new Dimension(200, 300));
        add(inputtedScrollPane, BorderLayout.WEST);

        JScrollPane sortedDataScrollPane = new JScrollPane(sortedDataTextArea);
        sortedDataScrollPane.setPreferredSize(new Dimension(200, 300));
        add(sortedDataScrollPane, BorderLayout.CENTER);

        JScrollPane averageSalaryScrollPane = new JScrollPane(averageSalaryTextArea);
        averageSalaryScrollPane.setPreferredSize(new Dimension(200, 300));
        add(averageSalaryScrollPane, BorderLayout.EAST);
        setVisible(true);
    }

    public static void main(String[] args) {
        Frame frame = new Frame("KR");
    }
}
