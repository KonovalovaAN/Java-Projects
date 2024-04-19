import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Random;

public class Application extends JFrame {
    JButton buttonYes;
    JButton buttonNo;
    JLabel questionLabel;
    JPanel panel;
    Application(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(600, 200, 400, 400);
        setResizable(false);
        setLayout(new BorderLayout());

        questionLabel = new JLabel("Would you like to get a million dollars?");
        buttonYes = new JButton("Yes!");
        buttonNo = new JButton("No...");
        add(buttonYes);
        add(buttonNo);
        panel = new JPanel();
        panel.add(questionLabel);
        panel.add(buttonYes);
        panel.add(buttonNo);
        add(panel);

        buttonNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Application.this, "Thank you for participating in the survey.", "Survey", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        buttonYes.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {//mouseentered
                Random rand = new Random();
                buttonYes.setLocation(rand.nextInt(panel.getWidth() - buttonYes.getWidth() - 10),
                        rand.nextInt(panel.getWidth() - buttonYes.getHeight() - 10));
            }
        });
    }
    public static void main(String[] args) {
        Application application = new Application("Moving button");
        application.setVisible(true);
    }
}