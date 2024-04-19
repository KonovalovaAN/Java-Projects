import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame extends JFrame{
    JLabel mousePos;
    JButton button;
    Frame(String title)
    {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(600, 200, 500, 400);
        setResizable(false);
        setLayout(new BorderLayout());

        mousePos = new JLabel("X = , Y = ");
        add(mousePos, BorderLayout.SOUTH);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel, BorderLayout.CENTER);

        button = new JButton();
        button.setBounds(20, 20, 40, 30);
        panel.add(button);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button.setLocation(e.getX(), e.getY());
                setMousePosLabel(e.getX(), e.getY());
            }
        });
        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                setMousePosLabel(e.getX(), e.getY());
            }
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseMoved(e);
            }
        });
        button.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                setMousePosLabel(e.getX() + button.getX(), e.getY() + button.getY());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int newButtonX = e.getX() + button.getX();
                int newButtonY = e.getY() + button.getY();
                setMousePosLabel(newButtonX, newButtonY);
                if (e.isControlDown()) {

                    int maxX = panel.getWidth() - button.getWidth();
                    int maxY = panel.getHeight() - button.getHeight();

                    if (newButtonX >= 0 && newButtonX <= maxX && newButtonY >= 0 && newButtonY <= maxY) {
                        button.setLocation(newButtonX, newButtonY);
                    }
                }
            }
        });
        button.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    StringBuilder string = new StringBuilder(button.getText());
                    try {
                        string.deleteCharAt(string.length() - 1);
                    } catch (StringIndexOutOfBoundsException ex) {
                        return;
                    }
                    button.setText(String.valueOf(string));
                    button.setSize(button.getPreferredSize());
                    return;
                }
                button.setText(button.getText() + e.getKeyChar());
                button.setSize(button.getPreferredSize());
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
    void setMousePosLabel(int x, int y)
    {
        mousePos.setText("X = "+x+", Y = "+y);
    }

    public static void main(String[] args) {
        Frame frame = new Frame("Magic button");
        frame.setVisible(true);
    }
}

