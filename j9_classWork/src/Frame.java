import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    static final int ROWS = 5;
    static final int COLS = 4;
    Frame(){
        setLayout(new GridLayout(ROWS, COLS));
        MyListener listener = new MyListener();
        for (int i = 1; i < ROWS*COLS; i++){
            JButton btn = new JButton("" + i);
            add(btn);
            btn.addMouseListener(listener);
        }
    }
}
