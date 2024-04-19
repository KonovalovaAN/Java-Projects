import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyListener extends MouseAdapter {
    String data;

    @Override
    public void mousePressed(MouseEvent e) {
        //if getButton сравнение с левой клавишей мыши
        JButton button = (JButton)e.getSource();
        data = button.getText();
        button.setText("clicked");
    }
}
