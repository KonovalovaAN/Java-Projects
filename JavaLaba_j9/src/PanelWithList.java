import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelWithList extends JPanel {
    private String[] months = {"January", "February", "March", "April", "May",
            "June", "July", "August", "September", "October", "November", "December"};
    private String[] colors = {"Red", "Blue", "Green", "Yellow", "Orange", "Purple",
            "Pink", "Black", "White", "Brown", "Gray", "Fuchsia"};

    private JPanel panel = new JPanel();
    private JList<String> sourceList = new JList<>();
    private DefaultListModel<String> sourceListModel = new DefaultListModel<>();
    private JList<String> targetList = new JList<>();
    private DefaultListModel<String> targetListModel = new DefaultListModel<>();
    private JButton buttonFirstToSecond = new JButton(">");
    private JButton buttonSecondToFirst = new JButton("<");

    public PanelWithList() {
        super();
        setBounds(200, 200, 600, 600);
        setLayout(new BorderLayout());
        panel.setLayout(new BorderLayout());

        initializeLists();
        initializeButtons();
        layoutComponents();
        attachListeners();
    }

    private void initializeLists() {
        for (String month : months) {
            sourceListModel.addElement(month);
        }

        for (String color : colors) {
            targetListModel.addElement(color);
        }

        sourceList.setModel(sourceListModel);
        targetList.setModel(targetListModel);

        sourceList.setPrototypeCellValue("MMMMMMMMMMMMMMM");
        targetList.setPrototypeCellValue("MMMMMMMMMMMMMMM");
    }

    private void initializeButtons() {
        panel.add(buttonFirstToSecond, BorderLayout.NORTH);
        panel.add(buttonSecondToFirst, BorderLayout.SOUTH);
    }

    private void layoutComponents() {
        add(sourceList, BorderLayout.WEST);
        add(targetList, BorderLayout.EAST);
        add(panel, BorderLayout.CENTER);
    }

    private void attachListeners() {
        buttonFirstToSecond.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                move(sourceList, targetListModel, sourceListModel);
            }
        });

        buttonSecondToFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                move(targetList, sourceListModel, targetListModel);
            }
        });
    }
    private void move(JList<String> list, DefaultListModel m1, DefaultListModel m2){
        int[] selectedIndices = list.getSelectedIndices();
        for (int i = selectedIndices.length - 1; i >= 0; i--) {
            m1.addElement(m2.getElementAt(selectedIndices[i]));
            m2.remove(selectedIndices[i]);
        }
    }
}