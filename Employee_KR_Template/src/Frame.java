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
    public static void main(String[] args) {
        Frame frame = new Frame("Employee Processing");
        frame.setVisible(true);
    }

    ArrayList<Employee> employees;

    Frame(String title) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(650, 600);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JTextArea inputTextArea = new JTextArea();
        JTextArea outputTextArea = new JTextArea();
        JTextArea averageSalaryTextArea = new JTextArea();

        Dimension textAreaSize = new Dimension(200, 400);

        JScrollPane inputScrollPane = new JScrollPane(inputTextArea);
        inputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        inputScrollPane.setPreferredSize(textAreaSize);

        JScrollPane outputScrollPane = new JScrollPane(outputTextArea);
        outputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        outputScrollPane.setPreferredSize(textAreaSize);

        JScrollPane averageSalaryScrollPane = new JScrollPane(averageSalaryTextArea);
        averageSalaryScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        averageSalaryScrollPane.setPreferredSize(textAreaSize);

        JPanel radioPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        ButtonGroup bg1 = new ButtonGroup();
        JRadioButton rbSecurityGuard = new JRadioButton("Security Guard");
        rbSecurityGuard.setSelected(true);
        JRadioButton rbSalesperson = new JRadioButton("Salesperson");
        bg1.add(rbSecurityGuard);
        bg1.add(rbSalesperson);

        radioPanel.add(rbSecurityGuard);
        radioPanel.add(rbSalesperson);

        ButtonGroup bg2 = new ButtonGroup();
        JRadioButton sort1 = new JRadioButton("Sort by Salary");
        sort1.setSelected(true);
        JRadioButton sort2 = new JRadioButton("Sort by Salary/Coefficient");
        JRadioButton sort3 = new JRadioButton("Sort by 'B'");
        bg2.add(sort1);
        bg2.add(sort2);
        bg2.add(sort3);

        radioPanel.add(sort1);
        radioPanel.add(sort2);
        radioPanel.add(sort3);

        JButton load = new JButton("Load");
        JButton process = new JButton("Process");
        JButton clean = new JButton("Clean");

        buttonPanel.add(load);
        buttonPanel.add(process);
        buttonPanel.add(clean);

        add(inputScrollPane, BorderLayout.WEST);
        add(outputScrollPane, BorderLayout.CENTER);
        add(averageSalaryScrollPane, BorderLayout.EAST);
        add(radioPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser("D:\\study\\Java\\Employee_KR_Template\\src");

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = chooser.getSelectedFile();

                    try {
                        employees = new ArrayList<>(Files.lines(Paths.get(selectedFile.getAbsolutePath()))
                                .map(line -> {
                                    try {
                                        String[] parts = line.split(" ");
                                        String lastName = parts[0];
                                        String organization = parts[1];
                                        double positionCoefficient = Double.parseDouble(parts[2]);

                                        if (rbSecurityGuard.isSelected()) {
                                            double protectedArea = Double.parseDouble(parts[3]);
                                            return new SecurityGuard(lastName, organization, positionCoefficient, protectedArea);
                                        } else {
                                            double revenue = Double.parseDouble(parts[3]);
                                            return new Salesperson(lastName, organization, positionCoefficient, revenue);
                                        }
                                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                                        throw new RuntimeException("Invalid file format. Check if the file contains valid employee data.");
                                    }
                                })
                                .collect(Collectors.toList()));
                        inputTextArea.setText("");
                        employees.forEach(employee -> inputTextArea.append(employee.toString()));
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
                averageSalaryTextArea.setText(" ");
                employees.clear();
            }
        });

        process.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputTextArea.setText("");
                if (sort1.isSelected()) {
                    ArrayList<Employee> sortedBySalary = new ArrayList<>(EmployeeProcessor.sortBySalaryDescending(employees));
                    sortedBySalary.forEach(employee -> outputTextArea.append(employee.toString()));
                } else if (sort2.isSelected()) {
                    ArrayList<Employee> sortedBySalaryRatio = new ArrayList<>(EmployeeProcessor.sortBySalaryCoefficientRatioAscending(employees));
                    sortedBySalaryRatio.forEach(employee -> outputTextArea.append(employee.toString()));
                } else if (sort3.isSelected()) {
                    ArrayList<String> organizationsStartingWithB = new ArrayList<>(EmployeeProcessor.getOrganizationsStartingWithB(employees));
                    organizationsStartingWithB.forEach(org -> outputTextArea.append(org + "\n"));
                }
            }
        });
    }
}
