import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Window extends JFrame {
    private Queue<Integer> queue;
    private JTextArea queueArea;
    private JTextArea dataArea;
    private JList<Integer> list;

    private JButton clear;
    private JButton front;
    private JButton back;
    private JButton push;
    private JButton pop;
    private JButton pushAll;
    private JButton equalsButton;
    private JTextField secondQueueDataField;

    public Window() {
        super("My Queue");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        queue = Queue.create();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        setContentPane(mainPanel);

        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        queueArea = new JTextArea("Your queue will be here");
        queueArea.setEditable(false);
        queueArea.setBorder(new LineBorder(Color.BLACK));
        dataArea = new JTextArea("Write data here");
        dataArea.setBorder(new LineBorder(Color.BLACK));

        dataArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                dataArea.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });

        textPanel.add(queueArea);
        textPanel.add(dataArea);

        mainPanel.add(textPanel);

        list = new JList<>();
        JScrollPane listScrollPane = new JScrollPane(list);
        mainPanel.add(listScrollPane);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        mainPanel.add(buttonPanel);

        front = new JButton("front");
        back = new JButton("back");
        clear = new JButton("clear");
        push = new JButton("push");
        pop = new JButton("pop");
        pushAll = new JButton("pushAll");
        equalsButton = new JButton("Equals");

        buttonPanel.add(front);
        buttonPanel.add(back);
        buttonPanel.add(clear);
        buttonPanel.add(push);
        buttonPanel.add(pop);
        buttonPanel.add(pushAll);
        buttonPanel.add(equalsButton);

        equalsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleEquals();
            }
        });

        secondQueueDataField = new JTextField("Enter data for second queue");
        secondQueueDataField.setBorder(new LineBorder(Color.BLACK));

        secondQueueDataField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                secondQueueDataField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });

        buttonPanel.add(secondQueueDataField);

        front.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFront();
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBack();
            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleClear();
            }
        });

        push.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handlePush();
            }
        });

        pop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handlePop();
            }
        });

        pushAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handlePushAll();
            }
        });

        setSize(800, 500);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void handleFront() {
        try {
            queueArea.setText("Front: " + queue.front().toString());
        } catch (IndexOutOfBoundsException exception) {
            showErrorDialog(exception.getMessage());
        }
    }

    private void handleBack() {
        try {
            queueArea.setText("Back: " + queue.back().toString());
        } catch (IndexOutOfBoundsException exception) {
            showErrorDialog(exception.getMessage());
        }
    }

    private void handleClear() {
        queue.clear();
        update();
        secondQueueDataField.setText("Enter data for second queue");
    }

    private void handlePush() {
        try {
            int element = Integer.parseInt(dataArea.getText().trim());
            queue.push(element);
            update();
            dataArea.setText("Write data here");
        } catch (NumberFormatException exception) {
            showErrorDialog("Write one value that you want to add to the end");
        }
    }

    private void handlePushAll() {
        try {
            StringTokenizer stringTokenizer = new StringTokenizer(dataArea.getText());
            ArrayList<Integer> elements = new ArrayList<>();
            while (stringTokenizer.hasMoreTokens()) {
                int element = Integer.parseInt(stringTokenizer.nextToken());
                elements.add(element);
            }
            queue.pushAll(elements);
            update();
            dataArea.setText("Write data here");
        } catch (NumberFormatException exception) {
            showErrorDialog("Invalid input format");
        }
    }

    private void handlePop() {
        try {
            queue.pop();
            update();
        } catch (IndexOutOfBoundsException exception) {
            showErrorDialog(exception.getMessage());
        }
    }

    private void handleEquals() {
        Queue<Integer> anotherQueue = createQueueFromInput();
        boolean result = queue.equals(anotherQueue);
        JOptionPane.showMessageDialog(this, "Queues are equal: " + result, "Equals Result", JOptionPane.INFORMATION_MESSAGE);
    }

    private Queue<Integer> createQueueFromInput() {
        Queue<Integer> newQueue = Queue.create();

        try {
            StringTokenizer stringTokenizer = new StringTokenizer(secondQueueDataField.getText());
            while (stringTokenizer.hasMoreTokens()) {
                int element = Integer.parseInt(stringTokenizer.nextToken());
                newQueue.push(element);
            }
        } catch (NumberFormatException e) {
            showErrorDialog("Invalid input format for the second queue");
        }

        return newQueue;
    }

    private void update() {
        queueArea.setText(queue.toString());
        list.setModel(queue.getListModel());
    }

    private void showErrorDialog(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
