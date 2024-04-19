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
        Frame frame = new Frame("Student Processing");
        frame.setVisible(true);
    }

    ArrayList<Student> students;

    Frame(String title) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(650, 600);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JTextArea inputTextArea = new JTextArea();
        JTextArea outputTextArea = new JTextArea();
        JTextArea averageGradeTextArea = new JTextArea();

        Dimension textAreaSize = new Dimension(200, 400);

        JScrollPane inputScrollPane = new JScrollPane(inputTextArea);
        inputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        inputScrollPane.setPreferredSize(textAreaSize);

        JScrollPane outputScrollPane = new JScrollPane(outputTextArea);
        outputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        outputScrollPane.setPreferredSize(textAreaSize);

        JScrollPane averageGradeScrollPane = new JScrollPane(averageGradeTextArea);
        averageGradeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        averageGradeScrollPane.setPreferredSize(textAreaSize);

        JPanel radioPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        ButtonGroup bg1 = new ButtonGroup();
        JRadioButton rbCollegeStudent = new JRadioButton("College Student");
        rbCollegeStudent.setSelected(true);
        JRadioButton rbHighSchoolStudent = new JRadioButton("High School Student");
        bg1.add(rbCollegeStudent);
        bg1.add(rbHighSchoolStudent);

        radioPanel.add(rbCollegeStudent);
        radioPanel.add(rbHighSchoolStudent);

        ButtonGroup bg2 = new ButtonGroup();
        JRadioButton sort1 = new JRadioButton("Sort 1");
        sort1.setSelected(true);
        JRadioButton sort2 = new JRadioButton("Sort 2");
        JRadioButton sort3 = new JRadioButton("Sort 3");
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
        add(averageGradeScrollPane, BorderLayout.EAST);
        add(radioPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser("D:\\study\\Java\\Sudent_KR_Template\\src");

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = chooser.getSelectedFile();

                    try {
                        students = new ArrayList<>(Files.lines(Paths.get(selectedFile.getAbsolutePath()))
                                .map(line -> {
                                    try {
                                        String[] parts = line.split(" ");
                                        String lastName = parts[0];
                                        String schoolName = parts[1];
                                        double averageGrade = Double.parseDouble(parts[2]);

                                        if (rbCollegeStudent.isSelected()) {
                                            int course = Integer.parseInt(parts[3]);
                                            return new CollegeStudent(lastName, schoolName, averageGrade, course);
                                        } else {
                                            int grade = Integer.parseInt(parts[3]);
                                            HighSchoolStudent.Behavior behavior = HighSchoolStudent.Behavior.valueOf(parts[4]);
                                            return new HighSchoolStudent(lastName, schoolName, averageGrade, grade, behavior);

                                        }
                                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                                        throw new RuntimeException("Invalid file format. Check if the file contains valid student data.");
                                    }
                                })
                                .collect(Collectors.toList()));

                        inputTextArea.setText("");
                        students.forEach(student -> inputTextArea.append(student.toString()));
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
                averageGradeTextArea.setText(" ");
                students.clear();
            }
        });

        process.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputTextArea.setText("");

                String lastNameInput = JOptionPane.showInputDialog(null, "Enter student's last name:");
                String schoolNameInput = JOptionPane.showInputDialog(null, "Enter student's school name:");
                double averageGradeInput = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter student's average grade:"));

                DataProcessor<Student> dataProcessor = new DataProcessor<>(students);

                if (sort1.isSelected()) {
                    Student specificStudent = new CollegeStudent(lastNameInput, schoolNameInput, averageGradeInput, 0);
                    long count = dataProcessor.countSpecificStudents(specificStudent);
                    outputTextArea.append("Count of specific students: " + count);
                } else if (sort2.isSelected()) {
                    Student specificStudent = new CollegeStudent(lastNameInput, schoolNameInput, averageGradeInput, 0);
                    Student result = dataProcessor.binarySearch(specificStudent.getSchoolName(), specificStudent.getLastName());
                    outputTextArea.append("Binary search result: " + result);
                } else if (sort3.isSelected()) {
                    Student specificStudent = new CollegeStudent(lastNameInput, schoolNameInput, averageGradeInput, 0);
                    Student minimumStudent = dataProcessor.findMinimum();
                    outputTextArea.append("Minimum student: " + minimumStudent);
                }
            }
        });


    }
}
