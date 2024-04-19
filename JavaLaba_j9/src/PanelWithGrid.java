import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

class MyListener extends MouseAdapter {
    private String originalText;
    private final Random random = new Random();
    Color[] colors = {
            Color.CYAN, Color.ORANGE, Color.YELLOW, Color.WHITE, Color.PINK,
            Color.BLUE, Color.GREEN, Color.MAGENTA, Color.RED
    };

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            JButton button = (JButton) e.getSource();
            originalText = button.getText();
            button.setText("Clicked!");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            JButton button = (JButton) e.getSource();
            button.setText(originalText);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        changeButtonColor(button);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        button.setBackground(null);
    }

    private void changeButtonColor(JButton btn) {
        int position = random.nextInt(colors.length);
        btn.setBackground(colors[position]);
    }
}

public class PanelWithGrid extends JPanel {
    static final int ROWS = 5;
    static final int COLS = 5;

    PanelWithGrid() {
        setLayout(new GridLayout(ROWS, COLS));
        MyListener listener = new MyListener();
        for (int i = 1; i <= ROWS * COLS; i++) {
            JButton button = new JButton("" + i);
            add(button);
            button.addMouseListener(listener);
        }
    }
}