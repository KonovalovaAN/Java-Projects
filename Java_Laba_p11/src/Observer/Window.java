package Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Window extends JFrame {
    private JLabel myLabel;
    private final DefaultListModel<String> pressedKeys = new DefaultListModel<>();
    private final Font font = new Font("Arial", Font.BOLD, 75);
    private Subject myKeyboard;

    public Window() {
        setTitle("Keyboard");
        setSize(1280, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 0));

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);

        myLabel = new JLabel("No button");
        myLabel.setForeground(Color.BLACK);
        myLabel.setFont(font);
        panel.add(myLabel);
        add(panel);

        JList<String> history = new JList<>(pressedKeys);
        history.setFont(font);
        add(new JScrollPane(history));

        myKeyboard = Keyboard.createKeyboard();
        Observer firstObserver= KeyPresser.createKeyPresser(myLabel);
        Observer secondObserver = KeyLogger.createKeyLogger(pressedKeys);
        myKeyboard.attach(firstObserver);
        myKeyboard.attach(secondObserver);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                myKeyboard.notify(keyEvent);
            }
        });

        setFocusable(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
