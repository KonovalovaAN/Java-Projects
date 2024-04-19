import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Frame extends JFrame {
    JLabel status = new JLabel();
    JPanel panel = new JPanel();
    JButton button = new JButton();
    Frame(String title)
    {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);
        add(panel, BorderLayout.CENTER);
        add(status, BorderLayout.SOUTH);
        panel.add(button);
        panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                button.setLocation(e.getX(), e.getY());
                setStatus(e.getX(), e.getY());
                super.mouseClicked(e);
            }
        });
    }
//e.isCtrlDown
    void setStatus(int x, int y){
        status.setText("X = "+ x + " Y = " + y);
    }
}