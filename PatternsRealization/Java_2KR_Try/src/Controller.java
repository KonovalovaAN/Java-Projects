import javax.swing.*;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller {
    private final JTextArea inputTextArea;
    private final JTextArea outputTextArea;
    private final DefaultListModel<CustomInteger> listModel;
    private final ArrayList<JButton> buttons;
    private final ArrayList<JRadioButton> radioButtons;
    private final ArrayList<CustomInteger> decimalNumbers;
    private final SetModel model;

    public Controller(View v) {
        inputTextArea = v.getJTextAreas().get(0);
        outputTextArea = v.getJTextAreas().get(1);
        listModel = v.getListModel();
        buttons = v.getJButtons();
        radioButtons = v.getRadioButtons();
        decimalNumbers = new ArrayList<>();
        model = new SetModel();
        initListeners();
    }

    private void initListeners() {
        buttons.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBinaryView();
            }
        });

        buttons.get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDecimalView();
            }
        });

        buttons.get(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateCardinality();
            }
        });

        buttons.get(3).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNumber();
            }
        });

        buttons.get(4).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData();
            }
        });

        buttons.get(5).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInput();
            }
        });
    }

    private void updateBinaryView() {
        outputTextArea.setText(model.getBinNums());
    }

    private void updateDecimalView() {
        outputTextArea.setText(model.getDecNums());
    }

    private void calculateCardinality() {
        Strategy selectedStrategy;

        if (radioButtons.get(0).isSelected()) {
            selectedStrategy = new GetSizeStrategy();
        } else {
            selectedStrategy = new VisitorStrategy();
        }

        int result = selectedStrategy.cardinality(decimalNumbers);
        outputTextArea.setText(String.valueOf(result));
    }

    private void addNumber() {
        String input = inputTextArea.getText().trim();
        if (!input.isEmpty()) {
            try {
                CustomInteger customInteger = new CustomInteger(Integer.parseInt(input));
                model.add(customInteger);
                decimalNumbers.add(customInteger);
                inputTextArea.setText("");
                listModel.addElement(customInteger);
            } catch (NumberFormatException ex) {
                handleException("Invalid input: Please enter a valid integer.");
            } catch (IllegalArgumentException ex) {
                handleException(ex.getMessage());
            } catch (Exception ex) {
                handleException("Error adding number: " + ex.getMessage());
            }
        }
    }

    private void handleException(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void saveData() {
        try {
            model.save();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error saving data: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearInput() {
        inputTextArea.setText("");
    }

    public void execute() {
        updateBinaryView();
        updateDecimalView();
        addNumber();
        clearInput();
    }
}
