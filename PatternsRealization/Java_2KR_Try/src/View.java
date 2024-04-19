import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

public class View extends JFrame {

    JTextArea inputTextArea = new JTextArea();
    JTextArea outputTextArea = new JTextArea();
    DefaultListModel<CustomInteger> listModel = new DefaultListModel<>();
    JList<CustomInteger> customIntegerList = new JList<>(listModel);
    ArrayList<JButton> buttons;
    ButtonGroup strategyButtonGroup;
    ArrayList<JRadioButton> radioButtons;

    public View(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel textAreasPanel = new JPanel(new GridLayout(1, 2));

        TitledBorder inputBorder = BorderFactory.createTitledBorder("Input");
        inputTextArea.setBorder(inputBorder);

        TitledBorder outputBorder = BorderFactory.createTitledBorder("Output");
        outputTextArea.setBorder(outputBorder);
        outputTextArea.setEditable(false);

        JScrollPane outputScrollPane = new JScrollPane(outputTextArea);
        outputScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        textAreasPanel.add(inputTextArea);
        textAreasPanel.add(outputScrollPane);

        customIntegerList.setFixedCellWidth(75);
        JScrollPane listScrollPane = new JScrollPane(customIntegerList);
        TitledBorder listBorder = BorderFactory.createTitledBorder("");
        listScrollPane.setBorder(listBorder);

        mainPanel.add(textAreasPanel, BorderLayout.CENTER);
        mainPanel.add(listScrollPane, BorderLayout.EAST);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton binViewBut = new JButton("Binary view");
        JButton decViewBut = new JButton("Decimal view");
        JLabel chooseStrategyLab = new JLabel("Choose strategy:");

        JRadioButton getSizeStrategyRadioButton = new JRadioButton("Size count");
        JRadioButton VisitorStrategyRadioButton = new JRadioButton("Basic count");
        strategyButtonGroup = new ButtonGroup();
        radioButtons = new ArrayList<>();

        strategyButtonGroup.add(getSizeStrategyRadioButton);
        radioButtons.add(getSizeStrategyRadioButton);

        strategyButtonGroup.add(VisitorStrategyRadioButton);
        radioButtons.add(VisitorStrategyRadioButton);

        JButton cardinalityBut = new JButton("Cardinality");
        JButton addBut = new JButton("Add");
        JButton saveBut = new JButton("Save");
        JButton clearBut = new JButton("Clear");

        buttonsPanel.add(binViewBut);
        buttonsPanel.add(decViewBut);
        buttonsPanel.add(chooseStrategyLab);

        buttonsPanel.add(getSizeStrategyRadioButton);
        buttonsPanel.add(VisitorStrategyRadioButton);

        buttonsPanel.add(cardinalityBut);
        buttonsPanel.add(addBut);
        buttonsPanel.add(saveBut);
        buttonsPanel.add(clearBut);

        mainPanel.add(textAreasPanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(mainPanel, BorderLayout.CENTER);

        buttons = new ArrayList<>();
        buttons.add(binViewBut);
        buttons.add(decViewBut);
        buttons.add(cardinalityBut);
        buttons.add(addBut);
        buttons.add(saveBut);
        buttons.add(clearBut);

        setSize(800, 600);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public DefaultListModel<CustomInteger> getListModel() {
        return listModel;
    }

    public JList<CustomInteger> getCustomIntegerList() {
        return customIntegerList;
    }

    public ArrayList<JTextArea> getJTextAreas() {
        ArrayList<JTextArea> result = new ArrayList<>();
        result.add(inputTextArea);
        result.add(outputTextArea);
        return result;
    }

    public ArrayList<JButton> getJButtons() {
        return buttons;
    }

    public ButtonGroup getButtonGroup() {
        return strategyButtonGroup;
    }

    public ArrayList<JRadioButton> getRadioButtons() {
        return radioButtons;
    }
}
